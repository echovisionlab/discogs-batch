package io.dsub.dumpdbmgmt.entity.nested;

import io.dsub.dumpdbmgmt.entity.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.With;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.Objects;

@With
@Getter
@AllArgsConstructor
public final class Identifier extends BaseEntity {
    @Field(name = "description")
    private final String description;
    @Field(name = "type")
    private final String type;
    @Field(name = "value")
    private final String value;

    public Identifier() {
        this.description = null;
        this.type = null;
        this.value = null;
    }

    public Identifier(String description) {
        this.description = description;
        this.type = null;
        this.value = null;
    }

    @Override
    public String toString() {
        return "Identifier{" +
                "description='" + description + '\'' +
                ", type='" + type + '\'' +
                ", value='" + value + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Identifier)) return false;
        Identifier that = (Identifier) o;
        return Objects.equals(description, that.description) &&
                Objects.equals(type, that.type) &&
                Objects.equals(value, that.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(description, type, value);
    }
}
