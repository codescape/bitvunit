package de.codescape.bitvunit.rule.links;

import com.gargoylesoftware.htmlunit.html.HtmlAnchor;
import de.codescape.bitvunit.model.Page;
import de.codescape.bitvunit.rule.AbstractRule;
import de.codescape.bitvunit.rule.Violations;

import static de.codescape.bitvunit.util.HtmlElementUtil.hasAttribute;

/**
 * AvoidTargetAttributeRule ensures that no anchor <code>&lt;a /&gt;</code> tag within the given HTML document contains
 * the target attribute that forces a link to open in a specific or new window.
 *
 * @author Stefan Glase
 * @since 0.5
 */
public class AvoidTargetAttributeRule extends AbstractRule {

    private static final String RULE_NAME = "AvoidTargetAttribute";
    private static final String RULE_MESSAGE = "Hyperlinks should not include the target attribute to force the target page opening in a specific window.";

    @Override
    public String getName() {
        return RULE_NAME;
    }

    @Override
    protected void applyTo(Page page, Violations violations) {
        for (HtmlAnchor anchor : page.findAllAnchorTags()) {
            if (hasAttribute(anchor, "target")) {
                violations.add(createViolation(anchor, RULE_MESSAGE));
            }
        }
    }

}
