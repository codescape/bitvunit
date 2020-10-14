package de.codescape.bitvunit.rule.media;

import com.gargoylesoftware.htmlunit.html.HtmlUnknownElement;
import de.codescape.bitvunit.model.Page;
import de.codescape.bitvunit.rule.AbstractRule;
import de.codescape.bitvunit.rule.Violations;

/**
 * AlternativeTextForAppletRule ensures that every <code>&lt;applet/&gt;</code> within the given HTML document provides
 * an alternative textual description to explain the <code>&lt;applet/&gt;</code> to everyone who is not able to view
 * and display the <code>&lt;applet/&gt;</code> itself.
 *
 * @author Stefan Glase
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
        for (HtmlUnknownElement applet : page.findAllAppletTags()) {
            if (applet.getTextContent() == null || applet.getTextContent().trim().isEmpty()) {
                violations.add(createViolation(applet, page, RULE_MESSAGE));
            }
        }
    }

}
