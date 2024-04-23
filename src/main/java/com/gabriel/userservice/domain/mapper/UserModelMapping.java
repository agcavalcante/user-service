package com.gabriel.userservice.domain.mapper;

import com.gabriel.userservice.domain.command.CreateUser;
import com.gabriel.userservice.domain.config.MapperSharedConfig;
import com.gabriel.userservice.domain.model.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(config = MapperSharedConfig.class)
public interface UserModelMapping {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "status", constant = "ACTIVE")
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    @Mapping(target = "version", ignore = true)
    User toEntity(CreateUser createUser);
}
