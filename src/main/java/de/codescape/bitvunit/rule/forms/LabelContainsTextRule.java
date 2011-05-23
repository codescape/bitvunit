package de.codescape.bitvunit.rule.forms;

import com.gargoylesoftware.htmlunit.html.HtmlLabel;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
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
    protected void applyTo(HtmlPage htmlPage, List<Violation> violations) {
        List<HtmlLabel> labels = getElementsByTagName(htmlPage, HtmlLabel.TAG_NAME);
        for (HtmlLabel label : labels) {
            if (label.getTextContent() == null || label.getTextContent().isEmpty()) {
                violations.add(createViolation(this, label.getStartLineNumber(), RULE_MESSAGE));
            }
        }
    }

}
