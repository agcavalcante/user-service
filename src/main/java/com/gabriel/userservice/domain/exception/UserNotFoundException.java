package com.gabriel.userservice.domain.exception;

public class UserNotFoundException extends DomainException {

    public UserNotFoundException(DomainErrorCode errorCode, String message) {
        super(errorCode, message);
    }
}
