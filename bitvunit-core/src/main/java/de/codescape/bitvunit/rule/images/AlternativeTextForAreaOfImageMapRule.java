package de.codescape.bitvunit.rule.images;

import com.gargoylesoftware.htmlunit.html.HtmlArea;
import de.codescape.bitvunit.model.Page;
import de.codescape.bitvunit.rule.AbstractRule;
import de.codescape.bitvunit.rule.Violations;

/**
 * AlternativeTextForAreaOfImageMapRule ensures that every area of an image map within the given HTML document provides
 * an alternative text through its <code>alt</code> attribute.
 *
 * @since 0.3
 */
public class AlternativeTextForAreaOfImageMapRule extends AbstractRule {

    private static final String RULE_NAME = "AlternativeTextForAreaOfImageMap";
    private static final String RULE_MESSAGE = "Every area of an image map should provide an alternative text through its alt attribute.";

    @Override
    public String getName() {
        return RULE_NAME;
    }

    @Override
    protected void applyTo(Page page, Violations violations) {
        for (HtmlArea area : page.findAllAreaTags()) {
            if (area.getAltAttribute() == null || area.getAltAttribute().isEmpty()) {
                violations.add(createViolation(area, RULE_MESSAGE));
            }
        }
    }

}
