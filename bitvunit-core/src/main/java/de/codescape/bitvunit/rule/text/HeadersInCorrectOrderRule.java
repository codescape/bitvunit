package de.codescape.bitvunit.rule.text;

import com.gargoylesoftware.htmlunit.html.HtmlElement;
import de.codescape.bitvunit.model.Page;
import de.codescape.bitvunit.rule.AbstractRule;
import de.codescape.bitvunit.rule.Violations;

/**
 * HeadersInCorrectOrderRule ensures that all headers from <code>&lt;h1 /&gt;</code> to <code>&lt;h6 /&gt;</code> within
 * the given HTML document are in correct oder. That means the following rules must be satisfied: The first header on
 * the given page must be the only one of type <code>&lt;h1 /&gt;</code>. All other headers may only one level deeper
 * and all levels higher than the previous header.
 *
 * @since 0.2
 */
public class HeadersInCorrectOrderRule extends AbstractRule {

    private static final String RULE_NAME = "HeadersInCorrectOrder";
    private static final String RULE_MESSAGE_SKIPPING = "Header levels may only be one level deeper than the level of the previous header.";
    private static final String RULE_MESSAGE_MULTIPLE_H1 = "The first header on the given page must be the only one of type <h1 />.";
    private static final String RULE_MESSAGE_FIRST_H1 = "The first header on the given page must be of type <h1 />.";

    @Override
    public String getName() {
        return RULE_NAME;
    }

    @Override
    protected void applyTo(Page page, Violations violations) {
        HtmlElement lastHeader = null;
        for (HtmlElement currentHeader : page.findAllHeaderTags()) {
            if (lastHeader == null) {
                checkFirstHeader(violations, currentHeader);
            } else {
                checkAllOtherHeaders(violations, lastHeader, currentHeader);
            }
            lastHeader = currentHeader;
        }
    }

    private void checkAllOtherHeaders(Violations violations, HtmlElement lastHeader, HtmlElement currentHeader) {
        checkOnlyOneLevelOneHeaderAllowed(violations, currentHeader);
        checkNoSkippingOfLevelsAllowed(violations, lastHeader, currentHeader);
    }

    private void checkNoSkippingOfLevelsAllowed(Violations violations, HtmlElement lastHeader, HtmlElement currentHeader) {
        if (headerLevel(currentHeader) > headerLevel(lastHeader) + 1) {
            violations.add(createViolation(currentHeader, RULE_MESSAGE_SKIPPING));
        }
    }

    private void checkOnlyOneLevelOneHeaderAllowed(Violations violations, HtmlElement currentHeader) {
        if (headerLevel(currentHeader) == 1) {
            violations.add(createViolation(currentHeader, RULE_MESSAGE_MULTIPLE_H1));
        }
    }

    private void checkFirstHeader(Violations violations, HtmlElement currentHeader) {
        if (headerLevel(currentHeader) != 1) {
            violations.add(createViolation(currentHeader, RULE_MESSAGE_FIRST_H1));
        }
    }

    private int headerLevel(HtmlElement header) {
        return Integer.parseInt(header.getTagName().substring(1, 2));
    }

}
