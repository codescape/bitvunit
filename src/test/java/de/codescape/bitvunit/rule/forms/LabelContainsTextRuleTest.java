package de.codescape.bitvunit.rule.forms;

import de.codescape.bitvunit.rule.Violations;
import org.junit.Test;

import static de.codescape.bitvunit.test.Assertions.assertNoViolations;
import static de.codescape.bitvunit.test.Assertions.assertViolations;
import static de.codescape.bitvunit.test.HtmlPageCreator.createHtmlPage;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class LabelContainsTextRuleTest {

    private LabelContainsTextRule rule = new LabelContainsTextRule();

    @Test
    public void ruleHasAName() throws Exception {
        assertNotNull(rule.getName());
        assertEquals("LabelContainsText", rule.getName());
    }

    @Test
    public void labelWithText() throws Exception {
        String content = "<html><body><form><label for=\"firstname\">Firstname</label></form></body></html>";
        Violations violations = rule.applyTo(createHtmlPage(content));
        assertNoViolations(violations);
    }

    @Test
    public void labelWithWrappedText() throws Exception {
        String content = "<html><body><form><label for=\"firstname\"><span>Firstname</span></label></form></body></html>";
        Violations violations = rule.applyTo(createHtmlPage(content));
        assertNoViolations(violations);
    }

    @Test
    public void labelWithMissingText() throws Exception {
        String content = "<html><body><form><label for=\"firstname\"></label></form></body></html>";
        Violations violations = rule.applyTo(createHtmlPage(content));
        assertViolations(violations, rule, 1);
    }

}
