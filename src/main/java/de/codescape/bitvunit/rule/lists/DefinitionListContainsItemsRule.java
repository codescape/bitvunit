package de.codescape.bitvunit.rule.lists;

import com.gargoylesoftware.htmlunit.html.HtmlDefinitionDescription;
import com.gargoylesoftware.htmlunit.html.HtmlDefinitionList;
import com.gargoylesoftware.htmlunit.html.HtmlDefinitionTerm;
import com.gargoylesoftware.htmlunit.html.HtmlElement;
import de.codescape.bitvunit.model.Page;
import de.codescape.bitvunit.rule.AbstractRule;
import de.codescape.bitvunit.rule.Violations;

/**
 * TODO document rule
 *
 * @since 0.3
 */
public class DefinitionListContainsItemsRule extends AbstractRule {

    private static final String RULE_NAME = "DefinitionListContainsItems";
    private static final String RULE_MESSAGE = "Some message!";

    @Override
    public String getName() {
        return RULE_NAME;
    }

    @Override
    protected void applyTo(Page page, Violations violations) {
        for (HtmlDefinitionList definitionList : page.findAllDefinitionLists()) {
            if (!containsDefinitionTermsOrDescription(definitionList)) {
                violations.add(createViolation(definitionList, RULE_MESSAGE));
            }
        }
    }

    private boolean containsDefinitionTermsOrDescription(HtmlDefinitionList definitionList) {
        for (HtmlElement childElement : definitionList.getChildElements()) {
            if (childElement instanceof HtmlDefinitionTerm || childElement instanceof HtmlDefinitionDescription) {
                return true;
            }
        }
        return false;
    }

}
