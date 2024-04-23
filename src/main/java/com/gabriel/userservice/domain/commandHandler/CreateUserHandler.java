package com.gabriel.userservice.domain.commandHandler;

import com.gabriel.userservice.domain.command.CreateUser;
import com.gabriel.userservice.domain.model.User;
import com.gabriel.userservice.domain.service.UserService;
import com.gabriel.userservice.domain.mapper.UserModelMapping;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
@Log4j2
@RequiredArgsConstructor
public class CreateUserHandler {

    private final UserService userService;
    private final UserModelMapping userModelMapping;

    public Mono<User> handle(CreateUser createUser) {
        return userService.createUser(userModelMapping.toEntity(createUser));
    }
}
