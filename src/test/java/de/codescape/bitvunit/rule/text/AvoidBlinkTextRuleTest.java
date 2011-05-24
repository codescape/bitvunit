package de.codescape.bitvunit.rule.text;

import de.codescape.bitvunit.rule.Violation;
import org.junit.Test;

import java.util.List;

import static de.codescape.bitvunit.test.Assertions.assertNoViolation;
import static de.codescape.bitvunit.test.Assertions.assertViolation;
import static de.codescape.bitvunit.test.HtmlPageCreator.create;
import static org.junit.Assert.*;

public class AvoidBlinkTextRuleTest {

    private AvoidBlinkTextRule rule = new AvoidBlinkTextRule();

    @Test
    public void ruleHasAName() throws Exception {
        assertNotNull(rule.getName());
        assertEquals("AvoidBlinkText", rule.getName());
    }

    @Test
    public void blinkTextPresent() throws Exception {
        String content = "<html><body><blink>Hello World</blink></body></html>";
        List<Violation> violations = rule.applyTo(create(content));
        assertViolation(violations, rule);
    }

    @Test
    public void blinkTextNotPresent() throws Exception {
        String content = "<html><body><p>Hello World</p></body></html>";
        List<Violation> violations = rule.applyTo(create(content));
        assertNoViolation(violations);
    }

}
