package io.dsub.dumpdbmgmt.entity.intermed;

import io.dsub.dumpdbmgmt.entity.Artist;
import io.dsub.dumpdbmgmt.entity.BaseEntity;
import io.dsub.dumpdbmgmt.entity.Release;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.With;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

/**
 * Document entity for MongoDB to represent
 * relationship between release and artist who
 * recorded as "Credited".
 */

@Getter
@AllArgsConstructor
@With
@Document(collection = "artist_credit")
public final class ArtistCredit extends BaseEntity {

    @Id
    private final ObjectId id;
    @DBRef
    private final Artist artist;
    @DBRef
    private final Release release;
    @Field(name = "credit")
    private final String credit;

    public ArtistCredit() {
        this.id = null;
        this.artist = null;
        this.release = null;
        this.credit = null;
    }
}
