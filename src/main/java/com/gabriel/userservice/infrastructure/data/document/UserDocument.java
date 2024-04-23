package com.gabriel.userservice.infrastructure.data.document;

import com.gabriel.userservice.domain.command.CreateUser;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.SuperBuilder;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@ToString
@Getter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@Document("user")
public class UserDocument extends BaseDocument {

    private String name;
    @Indexed(unique = true) private String socialId;
    private CreateUser.UserStatus status;
}
