package de.codescape.bitvunit.rule.text;

import com.gargoylesoftware.htmlunit.html.HtmlBold;
import de.codescape.bitvunit.model.Page;
import de.codescape.bitvunit.rule.AbstractRule;
import de.codescape.bitvunit.rule.Violations;

/**
 * AvoidBoldTagRule ensures that every <code>&lt;b /&gt;</code> element within the given HTML document leads to a
 * violation because the <code>&lt;strong /&gt;</code> element should be used to outline important text.
 *
 * @author Stefan Glase
 * @since 0.2
 */
public class AvoidBoldTagRule extends AbstractRule {

    private static final String RULE_NAME = "AvoidBoldTag";
    private static final String RULE_MESSAGE = "The <b /> element should not be used because <strong /> is a more semantic way to outline text.";

    @Override
    public String getName() {
        return RULE_NAME;
    }

    @Override
    protected void applyTo(Page page, Violations violations) {
        for (HtmlBold bold : page.findAllBoldTags()) {
            violations.add(createViolation(bold, RULE_MESSAGE));
        }
    }

}
