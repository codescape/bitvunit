package de.codescape.bitvunit.rule.lists;

import de.codescape.bitvunit.rule.Violations;
import org.junit.Test;

import static de.codescape.bitvunit.test.Assertions.assertNoViolations;
import static de.codescape.bitvunit.test.Assertions.assertViolations;
import static de.codescape.bitvunit.util.HtmlPageUtil.htmlPageFromString;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class OrderedListContainsListItemsRuleTest {

    private OrderedListContainsListItemsRule rule = new OrderedListContainsListItemsRule();

    @Test
    public void ruleHasAName() throws Exception {
        assertNotNull(rule.getName());
        assertEquals("OrderedListContainsListItems", rule.getName());
    }

    @Test
    public void orderedListWithoutItems() throws Exception {
        String content = "<html><body><ol></ol></body></html>";
        Violations violations = rule.applyTo(htmlPageFromString(content));
        assertViolations(violations, rule, 1);
    }

    @Test
    public void orderedListContainingToOrderedListsWithoutItems() throws Exception {
        String content = "<html><body><ol><li><ol></ol></li><li><ol></ol></li></ol></body></html>";
        Violations violations = rule.applyTo(htmlPageFromString(content));
        assertViolations(violations, rule, 2);
    }

    @Test
    public void orderedListWithItems() throws Exception {
        String content = "<html><body><ol><li>First item</li></ol></body></html>";
        Violations violations = rule.applyTo(htmlPageFromString(content));
        assertNoViolations(violations);
    }

}
