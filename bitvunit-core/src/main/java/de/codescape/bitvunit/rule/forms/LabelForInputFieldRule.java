package de.codescape.bitvunit.rule.forms;

import com.gargoylesoftware.htmlunit.html.HtmlInput;
import de.codescape.bitvunit.model.Page;
import de.codescape.bitvunit.rule.AbstractRule;
import de.codescape.bitvunit.rule.Violations;

import static de.codescape.bitvunit.util.HtmlElementUtil.hasNonEmptyAttribute;
import static de.codescape.bitvunit.util.HtmlLabelUtil.containsLabelForId;

/**
 * LabelForInputFieldRule ensures that every <code>&lt;input /&gt;</code> field of type <code>text</code> or
 * <code>password</code> within the given HTML document is associated with a <code>&lt;label /&gt;</code> element that
 * references the <code>&lt;input /&gt;</code> element through its <code>for</code> attribute.
 *
 * @author Stefan Glase
 * @since 0.1
 */
public class LabelForInputFieldRule extends AbstractRule {

    private static final String RULE_NAME = "LabelForInputField";
    private static final String RULE_MESSAGE = "For every input field there must be exactly one associated label tag.";

    @Override
    public String getName() {
        return RULE_NAME;
    }

    @Override
    protected void applyTo(Page page, Violations violations) {
        for (HtmlInput input : page.findAllInputTags()) {
            if (hasNonEmptyAttribute(input, "id") && isTextOrPasswordField(input) && !containsLabelForId(page.findAllLabelTags(), input.getId())) {
                violations.add(createViolation(input, RULE_MESSAGE));
            }
        }
    }

    private boolean isTextOrPasswordField(HtmlInput input) {
        String type = input.getTypeAttribute();
        return type != null && (type.equals("text") || type.equals("password"));
    }

}
