package de.codescape.bitvunit.rule.applets;

import com.gargoylesoftware.htmlunit.html.HtmlApplet;
import de.codescape.bitvunit.model.Page;
import de.codescape.bitvunit.rule.AbstractRule;
import de.codescape.bitvunit.rule.Violations;

/**
 * TODO add documentation
 *
 * @since 0.3
 */
public class AlternativeTextForAppletRule extends AbstractRule {

    private static final String RULE_NAME = "AlternativeTextForApplet";
    private static final String RULE_MESSAGE = "Every applet should include a description to explain the purpose of the applet to anybody who is not able to use the applet.";

    @Override
    public String getName() {
        return RULE_NAME;
    }

    @Override
    protected void applyTo(Page page, Violations violations) {
        for (HtmlApplet applet : page.findAllAppletTags()) {
            if (applet.getTextContent() == null || applet.getTextContent().trim().isEmpty()) {
                violations.add(createViolation(applet, RULE_MESSAGE));
            }
        }
    }

}
