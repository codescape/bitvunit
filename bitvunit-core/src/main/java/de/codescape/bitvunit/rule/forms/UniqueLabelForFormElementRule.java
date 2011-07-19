package de.codescape.bitvunit.rule.forms;

import com.gargoylesoftware.htmlunit.html.HtmlLabel;
import de.codescape.bitvunit.model.Page;
import de.codescape.bitvunit.rule.AbstractRule;
import de.codescape.bitvunit.rule.Violations;

import java.util.List;

/**
 * UniqueLabelForFormElementRule ensures that there is never more than one <code>&lt;label /&gt;</code> with the same
 * value in its <code>for</code> attribute. There should be never be more than one label pointing to a single form
 * element.
 *
 * @since 0.3
 * @author Stefan Glase
 */
public class UniqueLabelForFormElementRule extends AbstractRule {

    private static final String RULE_NAME = "UniqueLabelForFormElement";
    private static final String RULE_MESSAGE = "There should be never be more than one label pointing to a single form element.";

    @Override
    public String getName() {
        return RULE_NAME;
    }

    @Override
    protected void applyTo(Page page, Violations violations) {
        List<HtmlLabel> labels = page.findAllLabelTags();
        for (HtmlLabel label : labels) {
            if (!exactlyOneLabelWithGivenForAttribute(labels, label.getForAttribute())) {
                violations.add(createViolation(label, RULE_MESSAGE));
            }
        }
    }

    private boolean exactlyOneLabelWithGivenForAttribute(List<HtmlLabel> labels, String forAttribute) {
        int count = 0;
        for (HtmlLabel label : labels) {
            if (forAttribute.equals(label.getForAttribute())) {
                count++;
            }
        }
        return count == 1;
    }

}
