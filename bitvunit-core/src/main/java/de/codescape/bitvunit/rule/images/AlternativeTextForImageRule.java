package de.codescape.bitvunit.rule.images;

import com.gargoylesoftware.htmlunit.html.HtmlImage;
import de.codescape.bitvunit.model.Page;
import de.codescape.bitvunit.rule.AbstractRule;
import de.codescape.bitvunit.rule.Violations;

import static de.codescape.bitvunit.util.html.HtmlElementUtil.hasAttribute;

/**
 * AlternativeTextForImageRule ensures that every image created by the <code>&lt;img/&gt;</code> tag within the given
 * HTML document provides an alternative text through it's <code>alt</code> attribute. This rule is already satisfied by
 * an empty attribute <code>alt=""</code> since this is the encouraged way for images that are used for layout aspects.
 *
 * @author Stefan Glase
 * @since 0.1
 */
public class AlternativeTextForImageRule extends AbstractRule {

    private static final String RULE_NAME = "AlternativeTextForImage";
    private static final String RULE_MESSAGE = "Every image must provide an alternative text through it's alt attribute.";

    @Override
    public String getName() {
        return RULE_NAME;
    }

    @Override
    protected void applyTo(Page page, Violations violations) {
        for (HtmlImage image : page.findAllImageTags()) {
            validateImage(image, page, violations);
        }
    }

    private void validateImage(HtmlImage image, Page page, Violations violations) {
        if (!hasAttribute(image, "alt")) {
            violations.add(createViolation(image, page, RULE_MESSAGE));
        }
    }

}
