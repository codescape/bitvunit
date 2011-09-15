package de.codescape.bitvunit.rule.text;

import com.gargoylesoftware.htmlunit.html.HtmlElement;
import de.codescape.bitvunit.model.Page;
import de.codescape.bitvunit.rule.AbstractRule;
import de.codescape.bitvunit.rule.Violations;

/**
 * HeadingContainsTextRule ensures that all headings from <code>&lt;h1/&gt;</code> up to <code>&lt;h6/&gt;</code> within
 * the given HTML document contain some text content in the body of the tag.
 * <p/>
 * A valid heading would look like this:
 * <pre><code>&lt;h1&gt;Documentation&lt;/h1&gt;</code></pre>
 *
 * @author Stefan Glase
 * @since 0.6
 */
public class HeadingContainsTextRule extends AbstractRule {

    private static final String RULE_NAME = "HeadingContainsText";
    private static final String RULE_MESSAGE = "Headings always should contain text content because users may navigate or search by headings.";

    @Override
    public String getName() {
        return RULE_NAME;
    }

    @Override
    protected void applyTo(Page page, Violations violations) {
        for (HtmlElement header : page.findAllHeadingTags()) {
            if (header.getTextContent().trim().isEmpty()) {
                violations.add(createViolation(header, RULE_MESSAGE));
            }
        }
    }

}
