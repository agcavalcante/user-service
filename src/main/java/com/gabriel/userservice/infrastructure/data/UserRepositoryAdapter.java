package com.gabriel.userservice.infrastructure.data;

import com.gabriel.userservice.domain.model.User;
import com.gabriel.userservice.domain.port.repository.UserRepository;
import com.gabriel.userservice.infrastructure.data.mongo.MongoUserRepository;
import com.gabriel.userservice.infrastructure.mapper.UserDocumentMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Log4j2
@Service
@RequiredArgsConstructor
public class UserRepositoryAdapter implements UserRepository {

    private final MongoUserRepository mongoUserRepository;
    private final UserDocumentMapper userDocumentMapper;

    @Override
    public Mono<User> save(User user) {
        return mongoUserRepository.save(userDocumentMapper.toDocument(user)).map(userDocumentMapper::toEntity);
    }

    @Override
    public Mono<User> findById(String id) {
        return mongoUserRepository.findById(id).map(userDocumentMapper::toEntity);
    }

    @Override
    public Mono<Void> delete(String id) {
        return mongoUserRepository.deleteById(id);
    }
}
