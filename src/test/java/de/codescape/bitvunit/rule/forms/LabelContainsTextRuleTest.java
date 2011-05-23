package de.codescape.bitvunit.rule.forms;

import de.codescape.bitvunit.rule.Violation;
import org.junit.Test;

import java.util.List;

import static de.codescape.bitvunit.test.HtmlPageCreator.create;
import static org.junit.Assert.*;

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
        List<Violation> violations = rule.applyTo(create(content));
        assertTrue(violations.isEmpty());
    }

    @Test
    public void labelWithWrappedText() throws Exception {
        String content = "<html><body><form><label for=\"firstname\"><span>Firstname</span></label></form></body></html>";
        List<Violation> violations = rule.applyTo(create(content));
        assertTrue(violations.isEmpty());
    }

    @Test
    public void labelWithMissingText() throws Exception {
        String content = "<html><body><form><label for=\"firstname\"></label></form></body></html>";
        List<Violation> violations = rule.applyTo(create(content));
        assertEquals(1, violations.size());
        assertEquals(rule, violations.get(0).getRule());
    }

}
