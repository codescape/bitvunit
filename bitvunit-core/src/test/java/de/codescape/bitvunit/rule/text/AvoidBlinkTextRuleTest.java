package de.codescape.bitvunit.rule.text;

import de.codescape.bitvunit.rule.Violations;
import org.junit.Test;

import static de.codescape.bitvunit.test.Assertions.assertNoViolations;
import static de.codescape.bitvunit.test.Assertions.assertViolations;
import static de.codescape.bitvunit.util.HtmlPageUtil.toHtmlPage;
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
        Violations violations = rule.applyTo(toHtmlPage(content));
        assertViolations(violations, rule, 1);
    }

    @Test
    public void blinkTextNotPresent() throws Exception {
        String content = "<html><body><p>Hello World</p></body></html>";
        Violations violations = rule.applyTo(toHtmlPage(content));
        assertNoViolations(violations);
    }

}
