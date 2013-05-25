package de.codescape.bitvunit.rule.page;

import com.gargoylesoftware.htmlunit.html.HtmlElement;
import de.codescape.bitvunit.model.Page;
import de.codescape.bitvunit.rule.AbstractRule;
import de.codescape.bitvunit.rule.Violations;

import java.util.Arrays;
import java.util.List;

/**
 * AvoidAbstractRoleRule ensures that no abstract roles that are part of the Accessible Rich Internet Applications
 * (WAI-ARIA) 1.0 document are used within the current HTML page.
 *
 * @author Stefan Glase
 * @see <a href="http://www.w3.org/TR/wai-aria/roles#abstract_roles">http://www.w3.org/TR/wai-aria/roles#abstract_roles</a>
 * @since 0.6
 */
public class AvoidAbstractRoleRule extends AbstractRule {

    private static final String RULE_NAME = "AvoidAbstractRole";
    private static final String RULE_MESSAGE = "Abstract roles must not be used inside HTML documents because their only purpose is the aggregation of their child roles.";
    private static final List<String> ABSTRACT_ROLES = Arrays.asList("command", "composite", "input", "landmark",
            "range", "roletype", "section", "sectionhead", "select", "structure", "widget", "window");

    @Override
    public String getName() {
        return RULE_NAME;
    }

    @Override
    protected void applyTo(Page page, Violations violations) {
        List<HtmlElement> elements = page.findAllElementsWithAttribute("role");
        for (HtmlElement element : elements) {
            String roleValue = element.getAttribute("role");
            if (roleValue != null && !roleValue.isEmpty() && ABSTRACT_ROLES.contains(roleValue)) {
                violations.add(createViolation(element, page, RULE_MESSAGE));
            }
        }
    }

}