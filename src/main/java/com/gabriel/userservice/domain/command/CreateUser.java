package com.gabriel.userservice.domain.command;

import lombok.Builder;

@Builder
public record CreateUser(
        String name,
        String socialId,
        UserStatus status
) {


    public enum UserStatus {
        ACTIVE,
        DISABLED
    }
}
