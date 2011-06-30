package de.codescape.bitvunit.rule.lists;

import de.codescape.bitvunit.rule.Violations;
import org.junit.Test;

import static de.codescape.bitvunit.test.Assertions.assertNoViolations;
import static de.codescape.bitvunit.test.Assertions.assertViolations;
import static de.codescape.bitvunit.test.HtmlPageCreator.createHtmlPage;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class DefinitionListContainsItemsRuleTest {

    private DefinitionListContainsItemsRule rule = new DefinitionListContainsItemsRule();

    @Test
    public void ruleHasAName() throws Exception {
        assertNotNull(rule.getName());
        assertEquals("DefinitionListContainsItems", rule.getName());
    }

    @Test
    public void emptyDefinitionListPresent() throws Exception {
        String content = "<html><body><dl></dl></body></html>";
        Violations violations = rule.applyTo(createHtmlPage(content));
        assertViolations(violations, rule, 1);
    }

    @Test
    public void selfClosingDefinitionListPresent() throws Exception {
        String content = "<html><body><dl /></body></html>";
        Violations violations = rule.applyTo(createHtmlPage(content));
        assertViolations(violations, rule, 1);
    }

    @Test
    public void definitionListWithDefinitionTermAndDescription() throws Exception {
        String content = "<html><body><dl><dt>Speaker</dt><dd>Some wise words...</dd></dl></body></html>";
        Violations violations = rule.applyTo(createHtmlPage(content));
        assertNoViolations(violations, rule);
    }

    @Test
    public void definitionListWithDifferentContentOnly() throws Exception {
        String content = "<html><body><dl><p>Hello World</p></dl></body></html>";
        Violations violations = rule.applyTo(createHtmlPage(content));
        assertViolations(violations, rule, 1);
    }

}
