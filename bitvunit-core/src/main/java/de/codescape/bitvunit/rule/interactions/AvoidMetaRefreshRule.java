package de.codescape.bitvunit.rule.interactions;

import com.gargoylesoftware.htmlunit.html.HtmlMeta;
import de.codescape.bitvunit.model.Page;
import de.codescape.bitvunit.rule.AbstractRule;
import de.codescape.bitvunit.rule.Violations;

/**
 * AvoidMetaRefreshRule ensures that there is no fixed refresh caused by the <code>&lt;meta http-equiv="refresh"
 * content="seconds; URL=target"/&gt;</code> tag within the given HTML document.
 * <p/>
 * Automatically updating pages may cause significant accessibility problems to people who are disabled or who use
 * technology that hinders normal interaction patterns.
 *
 * @author Stefan Glase
 * @since 0.2
 */
public class AvoidMetaRefreshRule extends AbstractRule {

    private static final String RULE_NAME = "AvoidMetaRefresh";
    private static final String RULE_MESSAGE = "Automatically updating pages may cause significant accessibility problems to people that are disabled or that use technology that hinders normal interaction patterns.";

    @Override
    public String getName() {
        return RULE_NAME;
    }

    @Override
    protected void applyTo(Page page, Violations violations) {
        for (HtmlMeta metaTag : page.findAllMetaTags()) {
            if ("refresh".equalsIgnoreCase(metaTag.getHttpEquivAttribute())) {
                violations.add(createViolation(metaTag, page, RULE_MESSAGE));
            }
        }
    }

}
