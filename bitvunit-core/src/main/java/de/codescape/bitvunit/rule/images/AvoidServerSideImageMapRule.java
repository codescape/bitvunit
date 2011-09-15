package de.codescape.bitvunit.rule.images;

import com.gargoylesoftware.htmlunit.html.HtmlImage;
import de.codescape.bitvunit.model.Page;
import de.codescape.bitvunit.rule.AbstractRule;
import de.codescape.bitvunit.rule.Violations;

import static de.codescape.bitvunit.util.html.HtmlElementUtil.hasAttribute;

/**
 * AvoidServerSideImageMapRule ensures that there are no server side image maps created by the <code>&lt;img
 * ismap="ismap"/&gt;</code> tag within the given HTML document because they are inaccessible by users who cannot use a
 * mouse because of some disability.
 *
 * @author Stefan Glase
 * @since 0.4
 */
public class AvoidServerSideImageMapRule extends AbstractRule {

    private static final String RULE_NAME = "AvoidServerSideImageMap";
    private static final String RULE_MESSAGE = "Do not use server side image maps because they are inaccessible without a mouse.";

    @Override
    public String getName() {
        return RULE_NAME;
    }

    @Override
    protected void applyTo(Page page, Violations violations) {
        for (HtmlImage image : page.findAllImageTags()) {
            if (hasAttribute(image, "ismap")) {
                violations.add(createViolation(image, RULE_MESSAGE));
            }
        }
    }

}
