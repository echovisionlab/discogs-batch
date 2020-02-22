package io.dsub.dumpdbmgmt.entity.nested;

import io.dsub.dumpdbmgmt.entity.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.With;
import org.springframework.data.mongodb.core.mapping.Field;

@Getter
@With
@AllArgsConstructor
public final class Video extends BaseEntity {
    @Field(name = "title")
    private final String title;
    @Field(name = "description")
    private final String description;
    @Field(name = "url")
    private final String url;

    public Video() {
        this.title = null;
        this.description = null;
        this.url = null;
    }
}
