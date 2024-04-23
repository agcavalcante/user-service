package com.gabriel.userservice.domain.exception;

import lombok.Getter;

@Getter
public class DomainException extends RuntimeException {

    private final DomainErrorCode domainErrorCode;
    private final String param;

    public DomainException(DomainErrorCode domainErrorCode, String message, String param) {
        super(message);
        this.domainErrorCode = domainErrorCode;
        this.param = param;
    }

    public DomainException(DomainErrorCode domainErrorCode, String message) {
        this(domainErrorCode, message, null);
    }

}