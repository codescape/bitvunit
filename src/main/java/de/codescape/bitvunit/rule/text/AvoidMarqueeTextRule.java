package de.codescape.bitvunit.rule.text;

import com.gargoylesoftware.htmlunit.html.HtmlMarquee;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import de.codescape.bitvunit.rule.AbstractRule;
import de.codescape.bitvunit.rule.Violation;

import java.util.List;

/**
 * TODO add description
 * @since 0.1
 */
public class AvoidMarqueeTextRule extends AbstractRule {

    private static final String RULE_NAME = "AvoidMarqueeText";
    private static final String RULE_MESSAGE = ;

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
