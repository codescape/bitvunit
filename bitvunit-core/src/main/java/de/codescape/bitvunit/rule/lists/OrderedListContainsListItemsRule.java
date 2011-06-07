package de.codescape.bitvunit.rule.lists;

import com.gargoylesoftware.htmlunit.html.HtmlElement;
import com.gargoylesoftware.htmlunit.html.HtmlListItem;
import com.gargoylesoftware.htmlunit.html.HtmlOrderedList;
import de.codescape.bitvunit.model.Page;
import de.codescape.bitvunit.rule.AbstractRule;
import de.codescape.bitvunit.rule.Violations;

import java.util.List;

/**
 * OrderedListContainsListItemsRule ensures that every ordered list <code>&lt;ol /&gt;</code>  within the given HTML
 * document contains one or more list items <code>&lt;li /&gt;</code>.
 *
 * @since 0.3
 */
public class OrderedListContainsListItemsRule extends AbstractRule {

    private static final String RULE_NAME = "OrderedListContainsListItems";
    private static final String RULE_MESSAGE = "Ordered lists should always have one or more list items.";

    @Override
    public String getName() {
        return RULE_NAME;
    }

    @Override
    protected void applyTo(Page page, Violations violations) {
        List<HtmlOrderedList> orderedLists = page.findAllOrderedLists();
        for (HtmlOrderedList orderedList : orderedLists) {
            if (!listHasOneOrMoreListItems(orderedList)) {
                violations.add(createViolation(orderedList, RULE_MESSAGE));
            }
        }
    }

    private boolean listHasOneOrMoreListItems(HtmlOrderedList list) {
        for (HtmlElement childElement : list.getChildElements()) {
            if (HtmlListItem.TAG_NAME.equals(childElement.getTagName())) {
                return true;
            }
        }
        return false;
    }

}
