package com.gabriel.userservice.application.dto;

import com.gabriel.userservice.domain.exception.model.DomainError;

import java.util.List;

public record ErrorResponseDto(List<DomainError> errors) {
    public ErrorResponseDto(List<DomainError> errors) {
        this.errors = errors;
    }

    public static ErrorResponseDto of(DomainError... errors) {
        return new ErrorResponseDto(List.of(errors));
    }

    public List<DomainError> errors() {
        return this.errors;
    }
}