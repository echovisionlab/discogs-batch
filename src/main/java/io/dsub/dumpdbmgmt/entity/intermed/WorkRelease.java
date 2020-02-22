package io.dsub.dumpdbmgmt.entity.intermed;

import io.dsub.dumpdbmgmt.entity.BaseEntity;
import io.dsub.dumpdbmgmt.entity.Label;
import io.dsub.dumpdbmgmt.entity.Release;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.With;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.Objects;

/**
 * Document entity to represent
 * company who serviced a release.
 * i.e. Mastered at, copyrighted, etc.
 */

@Getter
@With
@AllArgsConstructor
@Document(collection = "work_release")
public final class WorkRelease extends BaseEntity {
    @Id
    private final ObjectId id;
    @Field(name = "service_note")
    private final String serviceNote;
    @DBRef
    private final Release release;
    @DBRef
    private final Label label;

    public WorkRelease() {
        this.id = null;
        this.serviceNote = null;
        this.release = null;
        this.label = null;
    }

    @Override
    public String toString() {
        return "LabelRelease{" +
                "id=" + id +
                ", serviceNote='" + serviceNote + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        WorkRelease that = (WorkRelease) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(serviceNote, that.serviceNote);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, serviceNote);
    }
}
