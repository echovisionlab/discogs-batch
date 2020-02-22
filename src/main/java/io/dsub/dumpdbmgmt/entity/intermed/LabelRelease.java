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
 * Document Entity represents a label and a release.
 * Each entity will contain a release catNo; which is
 * unique identifier for each release from a catalog
 * from labels' perspective.
 */

@Getter
@With
@AllArgsConstructor
@Document(collection = "label_release")
public final class LabelRelease extends BaseEntity {
    @Id
    private final ObjectId id;
    @Field(name = "catNo")
    private final String catNo;
    @DBRef
    private final Release release;
    @DBRef
    private final Label label;

    public LabelRelease() {
        this.id = null;
        this.catNo = null;
        this.release = null;
        this.label = null;
    }

    @Override
    public String toString() {
        return "LabelRelease{" +
                "id=" + id +
                ", catNo='" + catNo + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LabelRelease that = (LabelRelease) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(catNo, that.catNo);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, catNo);
    }
}
