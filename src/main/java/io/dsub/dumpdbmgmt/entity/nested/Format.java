package io.dsub.dumpdbmgmt.entity.nested;

import io.dsub.dumpdbmgmt.entity.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.With;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

@Getter
@With
@AllArgsConstructor
public final class Format extends BaseEntity {
    @Field(name = "name")
    private final String name;
    @Field(name = "quantity")
    private final Short qty;
    @Field(name = "descriptions")
    private Set<String> descriptions = Collections.synchronizedSet(new HashSet<>());

    public Format() {
        this.name = null;
        this.qty = null;
    }
}
