package io.dsub.dumpdbmgmt.xmlobj;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.xml.bind.annotation.*;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

//////////////////////////////////
// Use for xml artist obj parse //
//////////////////////////////////

@ToString
@Getter
@Setter
@XmlRootElement(name = "artist")
@XmlAccessorType(XmlAccessType.FIELD)
public class XmlArtist extends XmlObject {
    @XmlElement(name = "id")
    private Long id;
    @XmlElement(name = "name")
    private String name;
    @XmlElement(name = "realname")
    private String realName;
    @XmlElement(name = "profile")
    private String profile;
    @XmlElement(name = "data_quality")
    private String dataQuality;

    @XmlElementWrapper(name = "urls")
    @XmlElement(name = "url")
    private Set<String> urls = Collections.synchronizedSet(new HashSet<>());

    @XmlElementWrapper(name = "namevariations")
    @XmlElement(name = "name")
    private Set<String> nameVariations = Collections.synchronizedSet(new HashSet<>());

    @XmlElementWrapper(name = "aliases")
    @XmlElement(name = "name")
    private Set<Aliase> aliases = Collections.synchronizedSet(new HashSet<>());

    @XmlElementWrapper(name = "groups")
    @XmlElement(name = "name")
    private Set<Group> groups = Collections.synchronizedSet(new HashSet<>());

    @XmlElementWrapper(name = "members")
    @XmlElement(name = "name")
    private Set<Member> members = Collections.synchronizedSet(new HashSet<>());

    @XmlAccessorType(XmlAccessType.FIELD)
    @ToString
    @Getter
    @Setter
    public static class Aliase {
        @XmlValue
        private String name;
        @XmlAttribute(name = "id")
        private Long id;
    }

    @XmlAccessorType(XmlAccessType.FIELD)
    @ToString
    @Getter
    @Setter
    public static class Group {
        @XmlValue
        private String name;
        @XmlAttribute(name = "id")
        private Long id;
    }

    @XmlAccessorType(XmlAccessType.FIELD)
    @ToString
    @Getter
    @Setter
    public static class Member {
        @XmlValue
        private String name;
        @XmlAttribute(name = "id")
        private Long id;
    }
}
