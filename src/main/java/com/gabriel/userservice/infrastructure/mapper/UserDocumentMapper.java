package com.gabriel.userservice.infrastructure.mapper;

import com.gabriel.userservice.domain.config.MapperSharedConfig;
import com.gabriel.userservice.domain.model.User;
import com.gabriel.userservice.infrastructure.data.document.UserDocument;
import org.mapstruct.Mapper;

@Mapper(config = MapperSharedConfig.class)
public interface UserDocumentMapper {

    UserDocument toDocument(User user);

    User toEntity(UserDocument userDocument);
}
