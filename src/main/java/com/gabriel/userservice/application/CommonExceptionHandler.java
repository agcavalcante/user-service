package com.gabriel.userservice.application;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.gabriel.userservice.application.dto.ErrorResponseDto;
import com.gabriel.userservice.domain.exception.DomainErrorCode;
import com.gabriel.userservice.domain.exception.DomainException;
import com.gabriel.userservice.domain.exception.model.DomainError;
import com.gabriel.userservice.domain.exception.model.DomainErrorType;
import lombok.SneakyThrows;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.web.reactive.error.ErrorWebExceptionHandler;
import org.springframework.core.ResolvableType;
import org.springframework.core.codec.CharSequenceEncoder;
import org.springframework.core.io.buffer.DefaultDataBufferFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.util.MimeType;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.util.Map;

@ControllerAdvice
public class CommonExceptionHandler implements ErrorWebExceptionHandler {


    private static final Logger log = LoggerFactory.getLogger(CommonExceptionHandler.class);
    protected static final ObjectMapper objectMapper = new ObjectMapper();
    public static final ErrorResponseDto GENERIC_SERVER_ERROR;

    public CommonExceptionHandler() {
    }

    @ExceptionHandler({Throwable.class})
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ResponseBody
    protected ErrorResponseDto genericExceptions(Throwable e) {
        log.error("Internal Server Error: {}", e.getMessage(), e);
        return GENERIC_SERVER_ERROR;
    }

    @ExceptionHandler({DomainException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    protected ErrorResponseDto domainExceptions(DomainException e) {
        log.info("Validation failed: {}, {}: {}", e.getDomainErrorCode(), e.getParam(), e.getMessage());
        return ErrorResponseDto.of(new DomainError(DomainErrorType.invalid_request_error, e.getDomainErrorCode(), e.getMessage(), e.getParam()));
    }

    @SneakyThrows
    public Mono<Void> handle(ServerWebExchange exchange, Throwable ex) {
        if (ex instanceof DomainException e) {
            return this.setErrorOnResponseBody(exchange, this.domainExceptions(e), HttpStatus.BAD_REQUEST);
        } else {
            return this.setErrorOnResponseBody(exchange, this.genericExceptions(ex), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    protected Mono<Void> setErrorOnResponseBody(ServerWebExchange exchange, ErrorResponseDto e, HttpStatusCode statusCode) throws JsonProcessingException {
        CharSequenceEncoder encoder = CharSequenceEncoder.textPlainOnly();
        exchange.getResponse().setStatusCode(statusCode);
        return exchange.getResponse().writeWith(Mono.just(encoder.encodeValue(objectMapper.writeValueAsString(e), new DefaultDataBufferFactory(), ResolvableType.NONE, (MimeType)null, (Map)null)));
    }

    static {
        GENERIC_SERVER_ERROR = ErrorResponseDto.of(new DomainError[]{new DomainError(DomainErrorType.api_error, DomainErrorCode.generic, "We had an unexpected error, please try again")});
    }
}
