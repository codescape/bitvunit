package de.codescape.bitvunit.rule.images;

import com.gargoylesoftware.htmlunit.html.HtmlImage;
import de.codescape.bitvunit.model.Page;
import de.codescape.bitvunit.rule.AbstractRule;
import de.codescape.bitvunit.rule.Violation;

import java.util.List;

import static de.codescape.bitvunit.util.ElementInspector.elementHasAttribute;

/**
 * AlternativeTextForImageRule ensures that every image within the given HTML document provides an alternative text
 * through its <code>alt</code> attribute. This rule is satisfied by an empty attribute <code>alt=""</code> since this
 * is the encouraged way for images that are used for layout aspects.
 *
 * @since 0.1
 */
public class AlternativeTextForImageRule extends AbstractRule {

    public static final String RULE_NAME = "AlternativeTextForImage";
    private static final String RULE_MESSAGE = "Every image must provide an alternative text through its alt attribute.";

    @Override
    public String getName() {
        return RULE_NAME;
    }

    @Override
    protected void applyTo(Page page, List<Violation> violations) {
        for (HtmlImage image : page.findAllImageTags()) {
            validateImage(image, violations);
        }
    }

    private void validateImage(HtmlImage image, List<Violation> violations) {
        if (!elementHasAttribute(image, "alt")) {
            violations.add(createViolation(image.getStartLineNumber(), RULE_MESSAGE));
        }
    }

}
