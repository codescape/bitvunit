package de.codescape.bitvunit.rule.forms;

import com.gargoylesoftware.htmlunit.html.HtmlInput;
import de.codescape.bitvunit.model.Page;
import de.codescape.bitvunit.rule.AbstractRule;
import de.codescape.bitvunit.rule.Violations;

import static de.codescape.bitvunit.util.html.HtmlElementUtil.hasNonEmptyAttribute;
import static de.codescape.bitvunit.util.html.HtmlLabelUtil.containsLabelForId;

/**
 * LabelForInputFieldRule ensures that every <code>&lt;input/&gt;</code> field of type <code>text</code> or
 * <code>password</code> within the given HTML document is associated with a <code>&lt;label/&gt;</code> element that
 * references the <code>&lt;input/&gt;</code> element through its <code>for</code> attribute.
 * <p/>
 * A valid label associated with an input element should look like this:
 * <pre><code>
 * &lt;label for="username"&gt;Username&lt;/label&gt;
 * &lt;input type="text" id="username"/&gt;</code></pre>
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
            if (hasNonEmptyAttribute(input, "id")
                    && isTextOrPasswordField(input)
                    && !containsLabelForId(page.findAllLabelTags(), input.getId())) {
                violations.add(createViolation(input, page, RULE_MESSAGE));
            }
        }
    }

    private boolean isTextOrPasswordField(HtmlInput input) {
        String type = input.getTypeAttribute();
        return type != null
                && !type.equals("submit")
                && !type.equals("reset")
                && !type.equals("hidden")
                && !type.equals("button");
    }

}
