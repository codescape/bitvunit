package de.codescape.bitvunit.rule.images;

import com.gargoylesoftware.htmlunit.html.HtmlImage;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import de.codescape.bitvunit.rule.AbstractRule;
import de.codescape.bitvunit.rule.Violation;

import java.util.ArrayList;
import java.util.List;

/**
 * AlternativeTextForImageRule ensures that every image within the given HTML document provides an alternative text
 * through it's <code>alt</code> attribute. This rule is satisfied by an empty attribute <code>alt=""</code> since this
 * is the encouraged way for images that are used for layout aspects.
 *
 * @since 0.1
 */
public class AlternativeTextForImageRule extends AbstractRule {

    public static final String RULE_NAME = "AlternativeTextForImage";
    private static final String RULE_MESSAGE = "Every image must provide an alternative text through it's alt attribute.";

    @Override
    public String getName() {
        return RULE_NAME;
    }

    @Override
    public List<Violation> applyTo(HtmlPage htmlPage) {
        List<Violation> violations = new ArrayList<Violation>();

        List<HtmlImage> images = getElementsByTagName(htmlPage, HtmlImage.TAG_NAME);
        for (HtmlImage image : images) {
            if (!image.getAttributesMap().containsKey("alt")) {
                violations.add(createViolation(this, image.getStartLineNumber(), RULE_MESSAGE));
            }
        }

        return violations;
    }

}
