package de.codescape.bitvunit.rule.lists;

import com.gargoylesoftware.htmlunit.html.HtmlDefinitionDescription;
import com.gargoylesoftware.htmlunit.html.HtmlDefinitionList;
import com.gargoylesoftware.htmlunit.html.HtmlDefinitionTerm;
import com.gargoylesoftware.htmlunit.html.HtmlElement;
import de.codescape.bitvunit.model.Page;
import de.codescape.bitvunit.rule.AbstractRule;
import de.codescape.bitvunit.rule.Violations;

/**
 * DefinitionListContainsItemsRule ensures that every definition list <code>&lt;dl /&gt;</code> within the given HTML
 * document contains one or more definition terms <code>&lt;dt /&gt;</code> and definition descriptions <code>&lt;dd
 * /&gt;</code>.
 *
 * @author Stefan Glase
 * @since 0.3
 */
public class DefinitionListContainsItemsRule extends AbstractRule {

    private static final String RULE_NAME = "DefinitionListContainsItems";
    private static final String RULE_MESSAGE = "Definition lists should always have one or more list items.";

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
