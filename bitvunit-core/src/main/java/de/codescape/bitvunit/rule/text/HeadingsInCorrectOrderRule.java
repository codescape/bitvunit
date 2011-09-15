package de.codescape.bitvunit.rule.text;

import com.gargoylesoftware.htmlunit.html.HtmlElement;
import de.codescape.bitvunit.model.Page;
import de.codescape.bitvunit.rule.AbstractRule;
import de.codescape.bitvunit.rule.Violations;

/**
 * HeadingsInCorrectOrderRule ensures that all headings from <code>&lt;h1/&gt;</code> up to <code>&lt;h6/&gt;</code>
 * within the given HTML document are in correct oder. That means the following rules must be satisfied: The first
 * heading on the given page must be the only one of type <code>&lt;h1/&gt;</code>. All other headings may only be one
 * level deeper and all levels higher than the previous heading.
 *
 * @author Stefan Glase
 * @since 0.2
 */
public class HeadingsInCorrectOrderRule extends AbstractRule {

    private static final String RULE_NAME = "HeadingsInCorrectOrder";
    private static final String RULE_MESSAGE_SKIPPING = "Heading levels may only be one level deeper than the level of the previous heading.";
    private static final String RULE_MESSAGE_MULTIPLE_H1 = "The first heading on the given page must be the only one of type <h1 />.";
    private static final String RULE_MESSAGE_FIRST_H1 = "The first heading on the given page must be of type <h1 />.";

    @Override
    public String getName() {
        return RULE_NAME;
    }

    @Override
    protected void applyTo(Page page, Violations violations) {
        HtmlElement lastHeading = null;
        for (HtmlElement currentHeading : page.findAllHeadingTags()) {
            if (lastHeading == null) {
                checkFirstHeading(violations, currentHeading);
            } else {
                checkAllOtherHeadings(violations, lastHeading, currentHeading);
            }
            lastHeading = currentHeading;
        }
    }

    private void checkAllOtherHeadings(Violations violations, HtmlElement lastHeading, HtmlElement currentHeading) {
        checkOnlyOneLevelOneHeadingAllowed(violations, currentHeading);
        checkNoSkippingOfLevelsAllowed(violations, lastHeading, currentHeading);
    }

    private void checkNoSkippingOfLevelsAllowed(Violations violations, HtmlElement lastHeading, HtmlElement currentHeading) {
        if (headingLevel(currentHeading) > headingLevel(lastHeading) + 1) {
            violations.add(createViolation(currentHeading, RULE_MESSAGE_SKIPPING));
        }
    }

    private void checkOnlyOneLevelOneHeadingAllowed(Violations violations, HtmlElement currentHeading) {
        if (headingLevel(currentHeading) == 1) {
            violations.add(createViolation(currentHeading, RULE_MESSAGE_MULTIPLE_H1));
        }
    }

    private void checkFirstHeading(Violations violations, HtmlElement currentHeading) {
        if (headingLevel(currentHeading) != 1) {
            violations.add(createViolation(currentHeading, RULE_MESSAGE_FIRST_H1));
        }
    }

    private int headingLevel(HtmlElement heading) {
        return Integer.parseInt(heading.getTagName().substring(1, 2));
    }

}
