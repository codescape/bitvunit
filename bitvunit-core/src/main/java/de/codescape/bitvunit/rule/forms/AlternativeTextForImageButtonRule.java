package de.codescape.bitvunit.rule.forms;

import com.gargoylesoftware.htmlunit.html.HtmlInput;
import de.codescape.bitvunit.model.Page;
import de.codescape.bitvunit.rule.AbstractRule;
import de.codescape.bitvunit.rule.Violations;

import static de.codescape.bitvunit.util.html.HtmlElementUtil.hasNonEmptyAttribute;

/**
 * AlternativeTextForImageButtonRule ensures that every image button within the given HTML document provides an
 * alternative text through its <code>alt</code> attribute.
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
                violations.add(createViolation(input, RULE_MESSAGE));
            }
        }
    }

    private boolean isImageButton(HtmlInput input) {
        return input.getTypeAttribute().equals("image");
    }

}
