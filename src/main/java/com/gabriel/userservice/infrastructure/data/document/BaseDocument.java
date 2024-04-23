package com.gabriel.userservice.infrastructure.data.document;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.annotation.Version;

import java.time.Instant;

@Getter
@SuperBuilder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class BaseDocument {

    @Id private String id;
    @CreatedDate private Instant createdAt;
    @LastModifiedDate private Instant updatedAt;
    @Version private Long version;
}
