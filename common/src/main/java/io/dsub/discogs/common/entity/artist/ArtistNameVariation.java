package io.dsub.discogs.common.entity.artist;

import io.dsub.discogs.common.entity.base.BaseTimeEntity;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.With;

@Data
@Entity
@With
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
@AllArgsConstructor
@Table(
    name = "artist_name_variation",
    uniqueConstraints =
        @UniqueConstraint(
            name = "uq_artist_name_variation_artist_id_name",
            columnNames = {"artist_id", "name_variation"}))
public class ArtistNameVariation extends BaseTimeEntity {

  private static final Long SerialVersionUID = 1L;

  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE)
  @Column(name = "id", columnDefinition = "serial", updatable = false, nullable = false)
  private Long id;

  @ManyToOne(optional = false)
  @JoinColumn(name = "artist_id", referencedColumnName = "id")
  private Artist artist;

  @Column(length = 2000, name = "name_variation")
  private String name;
}