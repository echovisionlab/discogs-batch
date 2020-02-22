package io.dsub.dumpdbmgmt.xmlobj;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.xml.bind.annotation.*;
import java.util.HashSet;
import java.util.Set;

//////////////////////////////////
// Use for xml release obj parse//
//////////////////////////////////

@ToString
@Getter
@Setter
@XmlRootElement(name = "release")
@XmlAccessorType(XmlAccessType.FIELD)
public class XmlRelease extends XmlObject {
    @XmlAttribute(name = "id")
    private Long releaseId;
    @XmlAttribute(name = "status")
    private String status;
    @XmlElement(name = "title")
    private String title;
    @XmlElement(name = "country")
    private String country;
    @XmlElement(name = "notes")
    private String notes;
    @XmlElement(name = "data_quality")
    private String dataQuality;

    @XmlElement(name = "master_id")
    private Master master;

    @XmlElementWrapper(name = "genres")
    @XmlElement(name = "genre")
    private Set<String> genres;

    @XmlElementWrapper(name = "styles")
    @XmlElement(name = "style")
    private Set<String> styles;

    @XmlElement(name = "released")
    private String releaseDate;

    @XmlElementWrapper(name = "artists")
    @XmlElement(name = "artist")
    private Set<AlbumArtist> albumArtists;

    @XmlElementWrapper(name = "extraartists")
    @XmlElement(name = "artist")
    private Set<CreditedArtist> creditedArtists;

    @XmlElementWrapper(name = "labels")
    @XmlElement(name = "label")
    private Set<Label> labels = new HashSet<>();

    @XmlElementWrapper(name = "formats")
    @XmlElement(name = "format")
    private Set<Format> formats = new HashSet<>();

    @XmlElementWrapper(name = "tracklist")
    @XmlElement(name = "track")
    private Set<Track> tracks = new HashSet<>();

    @XmlElementWrapper(name = "identifiers")
    @XmlElement(name = "identifier")
    private Set<Identifier> identifiers = new HashSet<>();

    @XmlElementWrapper(name = "companies")
    @XmlElement(name = "company")
    private Set<Company> companies = new HashSet<>();

    @XmlElementWrapper(name = "videos")
    @XmlElement(name = "video")
    private Set<Video> videos = new HashSet<>();

    @Getter
    @Setter
    @ToString
    @XmlAccessorType(XmlAccessType.FIELD)
    public static class AlbumArtist {
        @XmlElement(name = "id")
        Long id;
        @XmlElement(name = "name")
        String name;
    }

    @Getter
    @Setter
    @ToString
    @XmlAccessorType(XmlAccessType.FIELD)
    public static class CreditedArtist {
        @XmlElement(name = "id")
        Long id;
        @XmlElement(name = "name")
        String name;
        @XmlElement(name = "role")
        String role;
    }

    @Getter
    @Setter
    @ToString
    @XmlAccessorType(XmlAccessType.FIELD)
    public static class Label {
        @XmlAttribute(name = "catno")
        String catno;
        @XmlAttribute(name = "id")
        Long id;
        @XmlAttribute(name = "name")
        String labelName;
    }

    @Getter
    @Setter
    @ToString
    @XmlAccessorType(XmlAccessType.FIELD)
    public static class Format {
        @XmlAttribute(name = "name")
        String name;
        @XmlAttribute(name = "qty")
        Integer qty;
        @XmlAttribute(name = "text")
        String text;
        @XmlElementWrapper(name = "descriptions")
        @XmlElement(name = "description")
        Set<String> description;
    }

    @Getter
    @Setter
    @ToString
    @XmlAccessorType(XmlAccessType.FIELD)
    public static class Track {
        @XmlElement(name = "position")
        String position;
        @XmlElement(name = "title")
        String title;
        @XmlElement(name = "duration")
        String duration;
    }

    @Getter
    @Setter
    @ToString
    @XmlAccessorType(XmlAccessType.FIELD)
    public static class Identifier {
        @XmlAttribute(name = "description")
        String description;
        @XmlAttribute(name = "type")
        String type;
        @XmlAttribute(name = "value")
        String value;
    }

    @Getter
    @Setter
    @ToString
    @XmlAccessorType(XmlAccessType.FIELD)
    public static class Company {
        @XmlElement(name = "id")
        Long id;
        @XmlElement(name = "name")
        String name;
        @XmlElement(name = "entity_type_name")
        String job;
    }

    @Getter
    @Setter
    @ToString
    @XmlAccessorType(XmlAccessType.FIELD)
    public static class Video {
        @XmlElement(name = "title")
        String title;
        @XmlElement(name = "description")
        String description;
        @XmlAttribute(name = "src")
        String url;
    }

    @ToString
    @XmlAccessorType(XmlAccessType.FIELD)
    public static class Master {
        @XmlValue
        Long masterId;
        @XmlAttribute(name = "is_main_release")
        boolean isMaster;

        public Long getMasterId() {
            return masterId;
        }

        public void setMasterId(Long masterId) {
            this.masterId = masterId;
        }

        public boolean isMaster() {
            return isMaster;
        }

        public void setMaster(boolean master) {
            isMaster = master;
        }
    }
}
