package de.codescape.bitvunit.rule.text;

import de.codescape.bitvunit.rule.Violation;
import org.junit.Test;

import java.util.List;

import static de.codescape.bitvunit.test.Assertions.assertNoViolationExists;
import static de.codescape.bitvunit.test.Assertions.assertViolationExists;
import static de.codescape.bitvunit.test.HtmlPageCreator.createHtmlPage;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

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
        List<Violation> violations = rule.applyTo(createHtmlPage(content));
        assertViolationExists(violations, rule);
    }

    @Test
    public void blinkTextNotPresent() throws Exception {
        String content = "<html><body><p>Hello World</p></body></html>";
        List<Violation> violations = rule.applyTo(createHtmlPage(content));
        assertNoViolationExists(violations);
    }

}
