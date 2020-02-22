package io.dsub.dumpdbmgmt.batch.processor;

import io.dsub.dumpdbmgmt.entity.Label;
import io.dsub.dumpdbmgmt.xmlobj.XmlLabel;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.stereotype.Component;

@StepScope
@Component("labelProcessor")
public class LabelProcessor implements ItemProcessor<XmlLabel, Label> {

    /**
     * Simple process to assign xml elements and attributes to a Label object.
     * If profile contains "[b]DO NOT USE.[/b]", indicates the object is
     * marked as disposed, hence filter as null.
     */

    @Override
    public Label process(XmlLabel item) {

        Label label = new Label(item.getId());

        if (item.getProfile() != null) {
            if (item.getProfile().contains("[b]DO NOT USE.[/b]")) {
                return null;
            }
            label = label.withProfile(item.getProfile());
        }

        if (item.getName() != null) {
            label = label.withName(item.getName());
        }

        if (item.getContactInfo() != null) {
            label = label.withContactInfo(item.getContactInfo());
        }

        if (item.getDataQuality() != null) {
            label = label.withDataQuality(item.getDataQuality());
        }

        if (item.getUrls() != null) {
            label = label.withUrls(item.getUrls());
        }

        return label;
    }
}
