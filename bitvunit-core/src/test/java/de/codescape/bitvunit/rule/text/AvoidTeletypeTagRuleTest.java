package de.codescape.bitvunit.rule.text;

import de.codescape.bitvunit.rule.Violations;
import org.junit.Test;

import static de.codescape.bitvunit.test.Assertions.assertNoViolations;
import static de.codescape.bitvunit.test.Assertions.assertViolations;
import static de.codescape.bitvunit.util.html.HtmlPageUtil.toHtmlPage;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class AvoidTeletypeTagRuleTest {

    private AvoidTeletypeTagRule rule = new AvoidTeletypeTagRule();

    @Test
    public void ruleHasAName() throws Exception {
        assertNotNull(rule.getName());
        assertEquals("AvoidTeletypeTag", rule.getName());
    }

    @Test
    public void singleViolationRaisedByRule() throws Exception {
        String content = "<html><body><tt>Teletype text</tt></body></html>";
        Violations violations = rule.applyTo(toHtmlPage(content));
        assertViolations(violations, rule, 1);
    }

    @Test
    public void noViolationRaisedByRule() throws Exception {
        String content = "<html><body><p class=\"mono\">Teletype text</p><body></html>";
        Violations violations = rule.applyTo(toHtmlPage(content));
        assertNoViolations(violations, rule);
    }

}
