package io.dsub.dumpdbmgmt.xmlobj;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.xml.bind.annotation.*;
import java.util.Set;

/////////////////////////////////////////
// Use for xml master release obj parse//
/////////////////////////////////////////

@ToString
@Getter
@Setter
@XmlRootElement(name = "master")
@XmlAccessorType(XmlAccessType.FIELD)
public class XmlMaster extends XmlObject {
    @XmlAttribute(name = "id")
    private Long id;

    @XmlElement(name = "main_release")
    private Long mainRelease;

    @XmlElementWrapper(name = "artists")
    @XmlElement(name = "artist")
    private Set<ArtistInfo> artists;

    @XmlElementWrapper(name = "genres")
    @XmlElement(name = "genre")
    private Set<String> genres;

    @XmlElementWrapper(name = "styles")
    @XmlElement(name = "style")
    private Set<String> styles;

    @XmlElement(name = "year")
    private Short year;

    @XmlElement(name = "title")
    private String title;

    @XmlElement(name = "data_quality")
    private String dataQuality;

    @XmlElementWrapper(name = "videos")
    @XmlElement(name = "video")
    private Set<VideoUrl> videos;

    @XmlAccessorType(XmlAccessType.FIELD)
    @Getter
    @Setter
    @ToString
    public static class ArtistInfo {
        @XmlElement(name = "id")
        private Long id;
    }

    @XmlAccessorType(XmlAccessType.FIELD)
    @Getter
    @Setter
    @ToString
    public static class VideoUrl {
        @XmlAttribute(name = "src")
        private String url;
    }
}
