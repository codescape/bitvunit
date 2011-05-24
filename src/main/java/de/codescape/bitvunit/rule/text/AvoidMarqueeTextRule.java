package de.codescape.bitvunit.rule.text;

import com.gargoylesoftware.htmlunit.html.HtmlMarquee;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import de.codescape.bitvunit.rule.AbstractRule;
import de.codescape.bitvunit.rule.Violation;

import java.util.List;

/**
 * AvoidMarqueeTextRule ensures that every <code>&lt;marquee /&gt;</code> element within the given HTML document leads
 * to a violation because the <code>&lt;marquee /&gt;</code> results in moving text that cannot be stopped and it is not
 * defined in any W3C HTML specification.
 *
 * @since 0.1
 */
public class AvoidMarqueeTextRule extends AbstractRule {

    public static final String RULE_NAME = "AvoidMarqueeText";
    private static final String RULE_MESSAGE = "The <marquee /> element is not defined in any W3C HTML specification and should not be used.";

    @Override
    public String getName() {
        return RULE_NAME;
    }

    @Override
    protected void applyTo(HtmlPage htmlPage, List<Violation> violations) {
        List<HtmlMarquee> marquees = getElementsByTagName(htmlPage, HtmlMarquee.TAG_NAME);
        for (HtmlMarquee marquee : marquees) {
            violations.add(createViolation(this, marquee.getStartLineNumber(), RULE_MESSAGE));
        }
    }

}
