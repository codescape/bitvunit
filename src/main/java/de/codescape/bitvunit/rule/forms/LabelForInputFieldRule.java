package de.codescape.bitvunit.rule.forms;

import com.gargoylesoftware.htmlunit.html.HtmlInput;
import com.gargoylesoftware.htmlunit.html.HtmlLabel;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import de.codescape.bitvunit.rule.AbstractRule;
import de.codescape.bitvunit.rule.Violation;

import java.util.List;

/**
 * LabelForInputFieldRule ensures that every <code>&lt;input /&gt;</code> field of type <code>text</code> or
 * <code>password</code> within the given HTML document is associated with a <code>&lt;label /&gt;</code> element that
 * references the <code>&lt;input /&gt;</code> element through its <code>for</code> attribute.
 *
 * @since 0.1
 */
public class LabelForInputFieldRule extends AbstractRule {

    public static final String RULE_NAME = "LabelForInputField";
    private static final String RULE_MESSAGE = "For every input field there must be exactly one associated label tag.";

    @Override
    public String getName() {
        return RULE_NAME;
    }

    @Override
    protected void applyTo(HtmlPage htmlPage, List<Violation> violations) {
        List<HtmlInput> inputs = getElementsByTagName(htmlPage, HtmlInput.TAG_NAME);
        List<HtmlLabel> labels = getElementsByTagName(htmlPage, HtmlLabel.TAG_NAME);
        for (HtmlInput input : inputs) {
            if (hasValidId(input) && isTextOrPasswordField(input)) {
                if (!labelExistsFor(input.getId(), labels)) {
                    violations.add(createViolation(this, input.getStartLineNumber(), RULE_MESSAGE));
                }
            }
        }
    }

    private boolean labelExistsFor(String id, List<HtmlLabel> labels) {
        for (HtmlLabel label : labels) {
            if (label.getForAttribute() != null && label.getForAttribute().equals(id)) {
                return true;
            }
        }
        return false;
    }

    private boolean isTextOrPasswordField(HtmlInput input) {
        String type = input.getTypeAttribute();
        return type != null && (type.equals("text") || type.equals("password"));
    }

    private boolean hasValidId(HtmlInput input) {
        return input.getId() != null && !input.getId().isEmpty();
    }

}
