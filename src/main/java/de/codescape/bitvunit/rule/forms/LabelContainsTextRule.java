package de.codescape.bitvunit.rule.forms;

import com.gargoylesoftware.htmlunit.html.HtmlLabel;
import de.codescape.bitvunit.model.Page;
import de.codescape.bitvunit.rule.AbstractRule;
import de.codescape.bitvunit.rule.Violation;

import java.util.List;

/**
 * LabelContainsTextRule ensures that every <code>%lt;label /&gt;</code> within the given HTML document contains a
 * textual representation for the given label.
 *
 * @since 0.1
 */
public class LabelContainsTextRule extends AbstractRule {

    public static final String RULE_NAME = "LabelContainsText";
    private static final String RULE_MESSAGE = "Every label must contain text to describe the associated form field.";

    @Override
    public String getName() {
        return RULE_NAME;
    }

    @Override
    protected void applyTo(Page page, List<Violation> violations) {
        for (HtmlLabel label : page.findAllLabelTags()) {
            validateLabel(label, violations);
        }
    }

    private void validateLabel(HtmlLabel label, List<Violation> violations) {
        if (label.getTextContent() == null || label.getTextContent().isEmpty()) {
            violations.add(createViolation(label.getStartLineNumber(), RULE_MESSAGE));
        }
    }

}
