package com.gabriel.userservice.domain.exception.model;

public enum DomainErrorType {
    api_error,
    idempotency_error,
    invalid_request_error,
    security_error;

    private DomainErrorType() {

    }
}
