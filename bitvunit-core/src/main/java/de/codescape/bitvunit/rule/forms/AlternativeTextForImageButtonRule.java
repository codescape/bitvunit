package de.codescape.bitvunit.rule.forms;

import com.gargoylesoftware.htmlunit.html.HtmlInput;
import de.codescape.bitvunit.model.Page;
import de.codescape.bitvunit.rule.AbstractRule;
import de.codescape.bitvunit.rule.Violations;

import static de.codescape.bitvunit.util.html.HtmlElementUtil.hasNonEmptyAttribute;

/**
 * AlternativeTextForImageButtonRule ensures that every image button created through <code>&lt;input
 * type="image"/&gt;</code> within the given HTML document provides an alternative text through it's <code>alt</code>
 * attribute.
 * <p>
 * A good example for a valid image button would look like this:
 * <pre><code>&lt;input type="image" src="register.png" alt="Register Account"/&gt;</code></pre>
 *
 * @author Stefan Glase
 * @since 0.6
 */
public class AlternativeTextForImageButtonRule extends AbstractRule {

    private static final String RULE_NAME = "AlternativeTextForImageButton";
    private static final String RULE_MESSAGE = "Every image button should supply an alternative text that describes the function of the image input element.";

    @Override
    public String getName() {
        return RULE_NAME;
    }

    @Override
    protected void applyTo(Page page, Violations violations) {
        for (HtmlInput input : page.findAllInputTags()) {
            if (isImageButton(input) && !hasNonEmptyAttribute(input, "alt")) {
                violations.add(createViolation(input, page, RULE_MESSAGE));
            }
        }
    }

    private boolean isImageButton(HtmlInput input) {
        return input.getTypeAttribute().equals("image");
    }

}
