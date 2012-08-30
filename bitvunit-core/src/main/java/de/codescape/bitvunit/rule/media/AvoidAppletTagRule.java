package de.codescape.bitvunit.rule.media;

import com.gargoylesoftware.htmlunit.html.HtmlApplet;
import de.codescape.bitvunit.model.Page;
import de.codescape.bitvunit.rule.AbstractRule;
import de.codescape.bitvunit.rule.Violations;

/**
 * AvoidAppletTagRule ensures that there are no <code>&lt;applet /&gt;</code> tags used on the page under test. This tag
 * is not supported since HTML 5 and should be replaced by that <code>&lt;object /&gt;</code> tag.
 *
 * @author Stefan Glase
 * @since 0.8
 */
public class AvoidAppletTagRule extends AbstractRule {

    private static final String RULE_NAME = "AvoidAppletTag";
    private static final String RULE_MESSAGE = "The <applet> tag is not supported since HTML 5 and the <object> tag should be used instead.";

    @Override
    public String getName() {
        return RULE_NAME;
    }

    @Override
    protected void applyTo(Page page, Violations violations) {
        for (HtmlApplet applet : page.findAllAppletTags()) {
            violations.add(createViolation(applet, RULE_MESSAGE));
        }
    }

}
