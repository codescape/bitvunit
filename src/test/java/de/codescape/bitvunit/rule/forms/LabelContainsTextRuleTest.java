package de.codescape.bitvunit.rule.forms;

import de.codescape.bitvunit.rule.Violation;
import org.junit.Test;

import java.util.List;

import static de.codescape.bitvunit.test.Assertions.assertNoViolation;
import static de.codescape.bitvunit.test.Assertions.assertViolation;
import static de.codescape.bitvunit.test.HtmlPageCreator.create;
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
        List<Violation> violations = rule.applyTo(create(content));
        assertNoViolation(violations);
    }

    @Test
    public void labelWithWrappedText() throws Exception {
        String content = "<html><body><form><label for=\"firstname\"><span>Firstname</span></label></form></body></html>";
        List<Violation> violations = rule.applyTo(create(content));
        assertNoViolation(violations);
    }

    @Test
    public void labelWithMissingText() throws Exception {
        String content = "<html><body><form><label for=\"firstname\"></label></form></body></html>";
        List<Violation> violations = rule.applyTo(create(content));
        assertViolation(violations, rule);
    }

}
