package io.dsub.dumpdbmgmt.entity;

import lombok.Getter;
import org.springframework.data.mongodb.core.mapping.Field;

import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import java.time.LocalDateTime;

@Getter
public abstract class BaseEntity {

    @Field
    protected LocalDateTime createdAt;

    @Field
    protected LocalDateTime updatedAt;

    @PrePersist
    protected void persist() {
        if (createdAt == null) {
            this.createdAt = LocalDateTime.now();
        }
    }

    @PreUpdate
    protected void update() {
        this.updatedAt = LocalDateTime.now();
    }
}
