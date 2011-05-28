package de.codescape.bitvunit.rule.text;

import com.gargoylesoftware.htmlunit.html.HtmlBlink;
import de.codescape.bitvunit.model.Page;
import de.codescape.bitvunit.rule.AbstractRule;
import de.codescape.bitvunit.rule.Violation;

import java.util.List;

/**
 * AvoidBlinkTextRule ensures that every <code>&lt;blink /&gt;</code> element within the given HTML document leads to a
 * violation because the <code>&lt;blink /&gt;</code> tag result in blinking text that cannot be stopped and it is not
 * defined in any W3C HTML specification.
 *
 * @since 0.1
 */
public class AvoidBlinkTextRule extends AbstractRule {

    public static final String RULE_NAME = "AvoidBlinkText";
    private static final String RULE_MESSAGE = "The <blink /> element is not defined in any W3C HTML specification and should not be used.";

    @Override
    public String getName() {
        return RULE_NAME;
    }

    @Override
    protected void applyTo(Page page, List<Violation> violations) {
        for (HtmlBlink blink : page.findAllBlinkTags()) {
            violations.add(createViolation(blink.getStartLineNumber(), RULE_MESSAGE));
        }
    }

}