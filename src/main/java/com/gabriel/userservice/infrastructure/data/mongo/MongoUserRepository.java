package com.gabriel.userservice.infrastructure.data.mongo;

import com.gabriel.userservice.infrastructure.data.document.UserDocument;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

@Repository
public interface MongoUserRepository extends ReactiveMongoRepository<UserDocument, String> {

    @Query("{'id' : ?0, 'status' : 'ACTIVE'}")
    Mono<UserDocument> findById(String id);
}
