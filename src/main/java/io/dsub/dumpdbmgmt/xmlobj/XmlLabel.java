package io.dsub.dumpdbmgmt.xmlobj;

import lombok.Getter;
import lombok.ToString;

import javax.xml.bind.annotation.*;
import java.util.Set;

////////////////////////////////
// Use for xml label obj parse//
////////////////////////////////

@ToString
@Getter
@XmlRootElement(name = "label")
@XmlAccessorType(XmlAccessType.FIELD)
public class XmlLabel extends XmlObject {
    @XmlElement(name = "id")
    private Long id;
    @XmlElement(name = "name")
    private String name;
    @XmlElement(name = "contactinfo")
    private String contactInfo;
    @XmlElement(name = "profile")
    private String profile;
    @XmlElement(name = "data_quality")
    private String dataQuality;
    @XmlElementWrapper(name = "urls")
    @XmlElement(name = "url")
    private Set<String> urls;
    @XmlElementWrapper(name = "sublabels")
    @XmlElement(name = "label")
    private Set<SubLabel> SubLabels;

    @Getter
    @ToString
    @XmlAccessorType(XmlAccessType.FIELD)
    public static class SubLabel {
        @XmlValue
        private String name;
        @XmlAttribute(name = "id")
        private Long id;
    }
}
