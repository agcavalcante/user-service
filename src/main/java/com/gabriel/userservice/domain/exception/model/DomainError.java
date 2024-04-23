package com.gabriel.userservice.domain.exception.model;

import com.gabriel.userservice.domain.exception.DomainErrorCode;

public record DomainError(DomainErrorType type, DomainErrorCode code, String message, String param) {
    public DomainError(DomainErrorType type, DomainErrorCode code, String message) {
        this(type, code, message, (String)null);
    }

    public DomainError(DomainErrorType type, DomainErrorCode code, String message, String param) {
        this.type = type;
        this.code = code;
        this.message = message;
        this.param = param;
    }

    public DomainError withType(final DomainErrorType type) {
        return this.type == type ? this : new DomainError(type, this.code, this.message, this.param);
    }

    public DomainError withCode(final DomainErrorCode code) {
        return this.code == code ? this : new DomainError(this.type, code, this.message, this.param);
    }

    public DomainError withMessage(final String message) {
        return this.message == message ? this : new DomainError(this.type, this.code, message, this.param);
    }

    public DomainError withParam(final String param) {
        return this.param == param ? this : new DomainError(this.type, this.code, this.message, param);
    }

    public DomainErrorType type() {
        return this.type;
    }

    public DomainErrorCode code() {
        return this.code;
    }

    public String message() {
        return this.message;
    }

    public String param() {
        return this.param;
    }
}
