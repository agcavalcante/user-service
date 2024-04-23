package com.gabriel.userservice.infrastructure.config;


import lombok.extern.log4j.Log4j2;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.config.EnableReactiveMongoAuditing;

@Configuration
@EnableReactiveMongoAuditing
@Log4j2
public class MongoConfiguration {

}
