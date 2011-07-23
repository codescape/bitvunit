package de.codescape.bitvunit.rule.media;

import de.codescape.bitvunit.rule.Violations;
import org.junit.Test;

import static de.codescape.bitvunit.test.Assertions.assertNoViolations;
import static de.codescape.bitvunit.test.Assertions.assertViolations;
import static de.codescape.bitvunit.util.HtmlPageUtil.htmlPageFromString;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class AlternativeTextForAppletRuleTest {

    private AlternativeTextForAppletRule rule = new AlternativeTextForAppletRule();

    @Test
    public void ruleHasAName() throws Exception {
        assertNotNull(rule.getName());
        assertEquals("AlternativeTextForApplet", rule.getName());
    }

    @Test
    public void alternativeTextPresent() throws Exception {
        String content = "<html><body><applet code=\"Applet.class\">Displaying current stock prices.</applet></body></html>";
        Violations violations = rule.applyTo(htmlPageFromString(content));
        assertNoViolations(violations, rule);
    }

    @Test
    public void alternativeTextMissing() throws Exception {
        String content = "<html><body><applet code=\"Applet.class\"></applet></body></html>";
        Violations violations = rule.applyTo(htmlPageFromString(content));
        assertViolations(violations, rule, 1);
    }

}
