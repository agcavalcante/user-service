package com.gabriel.userservice.domain.model;

import lombok.*;
import lombok.experimental.SuperBuilder;

@SuperBuilder
@ToString
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class User extends BaseEntity {

    private String name;
    private String socialId;
    private UserStatus status;

    public enum UserStatus {
        ACTIVE,
        DISABLED
    }

    public User softDeleteUser(User user) {
        user.status = UserStatus.DISABLED;
        return this;
    }
}
