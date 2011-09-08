package de.codescape.bitvunit.rule.page;

import com.gargoylesoftware.htmlunit.html.HtmlTitle;
import de.codescape.bitvunit.model.Page;
import de.codescape.bitvunit.rule.AbstractRule;
import de.codescape.bitvunit.rule.Violations;

/**
 * TitleForPageRule ensures that there is an non empty title within the inspected HTML document to describe that page.
 *
 * @author Stefan Glase
 * @since 0.6
 */
public class TitleForPageRule extends AbstractRule {

    private static final String RULE_NAME = "TitleForPage";
    private static final String RULE_MESSAGE = "Every document should contain a valid non empty title to describe that page!";

    @Override
    public String getName() {
        return RULE_NAME;
    }

    @Override
    protected void applyTo(Page page, Violations violations) {
        HtmlTitle title = page.findTitleTag();
        if (title == null || title.getTextContent().trim().isEmpty()) {
            violations.add(createViolation(title, RULE_MESSAGE));
        }
    }

}
