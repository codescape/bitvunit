package de.codescape.bitvunit.rule.tables;

import de.codescape.bitvunit.rule.Violations;
import org.junit.Test;

import static de.codescape.bitvunit.test.Assertions.assertViolations;
import static de.codescape.bitvunit.util.html.HtmlPageUtil.toHtmlPage;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class TableHeaderContainsTextRuleTest {

    TableHeaderContainsTextRule rule = new TableHeaderContainsTextRule();

    @Test
    public void ruleHasAName() throws Exception {
        assertNotNull(rule.getName());
        assertEquals("TableHeaderContainsText", rule.getName());
    }

    @Test
    public void tableHeadersWithText() throws Exception {
        String content = "<html><body><table><tr><th>Heading 1</th><th>Heading 2</th></tr><tr><td>Cell 1</td><td>Cell 2</td></tr></table></body></html>";
        Violations violations = rule.applyTo(toHtmlPage(content));
        assertViolations(violations, rule, 0);
    }

    @Test
    public void tableHeadersWithMissingText() throws Exception {
        String content = "<html><body><table><tr><th></th><th /></tr><tr><td>Cell 1</td><td>Cell 2</td></tr></table></body></html>";
        Violations violations = rule.applyTo(toHtmlPage(content));
        assertEquals(2, violations.size());
        assertViolations(violations, rule, 2);
    }

}
