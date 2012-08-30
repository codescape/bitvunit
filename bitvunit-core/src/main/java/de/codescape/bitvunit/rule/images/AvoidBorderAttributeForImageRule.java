package de.codescape.bitvunit.rule.images;

import com.gargoylesoftware.htmlunit.html.HtmlImage;
import de.codescape.bitvunit.model.Page;
import de.codescape.bitvunit.rule.AbstractRule;
import de.codescape.bitvunit.rule.Violations;
import de.codescape.bitvunit.util.html.HtmlElementUtil;

import java.util.List;

/**
 * AvoidBorderAttributeForImageRule ensures that no image on the current page under test uses the <code>border</code>
 * attribute to assign a border to itself which is deprecated since HTML 4.01 and should be done through cascading style
 * sheets.
 *
 * @author Stefan Glase
 * @since 0.8
 */
public class AvoidBorderAttributeForImageRule extends AbstractRule {

    private static final String RULE_NAME = "AvoidBorderAttributeForImage";
    private static final String RULE_MESSAGE = "Images should not use the border attribute which is deprecated since HTML 4.01.";

    @Override
    public String getName() {
        return RULE_NAME;
    }

    @Override
    protected void applyTo(Page page, Violations violations) {
        for (HtmlImage image : page.findAllImageTags()) {
            if (HtmlElementUtil.hasAttribute(image, "border")) {
                violations.add(createViolation(image, RULE_MESSAGE));
            }
        }
    }

}
