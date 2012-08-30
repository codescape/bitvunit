package de.codescape.bitvunit.rule.media;

import de.codescape.bitvunit.rule.Violations;
import org.junit.Test;

import static de.codescape.bitvunit.test.Assertions.assertNoViolations;
import static de.codescape.bitvunit.test.Assertions.assertViolations;
import static de.codescape.bitvunit.util.html.HtmlPageUtil.toHtmlPage;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class AvoidAppletTagRuleTest {

    private AvoidAppletTagRule rule = new AvoidAppletTagRule();

    @Test
    public void ruleHasAName() throws Exception {
        assertNotNull(rule.getName());
        assertEquals("AvoidAppletTag", rule.getName());
    }

    @Test
    public void singleViolationRaisedByRule() throws Exception {
        String content = "<html><body><applet code=\"applet.class\"></applet></body></html>";
        Violations violations = rule.applyTo(toHtmlPage(content));
        assertViolations(violations, rule, 1);
    }

    @Test
    public void noViolationRaisedByRule() throws Exception {
        String content = "<html><body><object data=\"applet.class\"></object></body></html>";
        Violations violations = rule.applyTo(toHtmlPage(content));
        assertNoViolations(violations, rule);
    }

}
