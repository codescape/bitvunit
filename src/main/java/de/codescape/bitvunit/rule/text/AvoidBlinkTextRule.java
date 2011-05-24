package de.codescape.bitvunit.rule.text;

import com.gargoylesoftware.htmlunit.html.HtmlBlink;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import de.codescape.bitvunit.rule.AbstractRule;
import de.codescape.bitvunit.rule.Violation;

import java.util.List;

public class AvoidBlinkTextRule extends AbstractRule {

    public static final String RULE_NAME = "AvoidBlinkText";
    private static final String RULE_MESSAGE = "The <blink /> element is not defined in any W3C HTML specification and should not be used.";

    @Override
    public String getName() {
        return RULE_NAME;
    }

    @Override
    protected void applyTo(HtmlPage htmlPage, List<Violation> violations) {
        List<HtmlBlink> blinks = getElementsByTagName(htmlPage, HtmlBlink.TAG_NAME);
        for (HtmlBlink blink : blinks) {
            violations.add(createViolation(this, blink.getStartLineNumber(), RULE_MESSAGE));
        }
    }

}
