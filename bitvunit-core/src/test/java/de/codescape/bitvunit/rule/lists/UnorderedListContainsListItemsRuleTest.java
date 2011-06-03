package de.codescape.bitvunit.rule.lists;

import de.codescape.bitvunit.rule.Violations;
import org.junit.Test;

import static de.codescape.bitvunit.test.Assertions.assertNoViolations;
import static de.codescape.bitvunit.test.Assertions.assertViolations;
import static de.codescape.bitvunit.test.HtmlPageCreator.createHtmlPage;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class UnorderedListContainsListItemsRuleTest {

    private UnorderedListContainsListItemsRule rule = new UnorderedListContainsListItemsRule();

    @Test
    public void ruleHasAName() throws Exception {
        assertNotNull(rule.getName());
        assertEquals("UnorderedListContainsListItems", rule.getName());
    }

    @Test
    public void unorderedListWithoutItems() throws Exception {
        String content = "<html><body><ul></ul></body></html>";
        Violations violations = rule.applyTo(createHtmlPage(content));
        assertViolations(violations, rule, 1);
    }

    @Test
    public void unorderedListContainingToUnorderedListsWithoutItems() throws Exception {
        String content = "<html><body><ul><li><ul></ul></li><li><ul></ul></li></ul></body></html>";
        Violations violations = rule.applyTo(createHtmlPage(content));
        assertViolations(violations, rule, 2);
    }

    @Test
    public void unorderedListWithItems() throws Exception {
        String content = "<html><body><ul><li>First item</li></ul></body></html>";
        Violations violations = rule.applyTo(createHtmlPage(content));
        assertNoViolations(violations);
    }

}
