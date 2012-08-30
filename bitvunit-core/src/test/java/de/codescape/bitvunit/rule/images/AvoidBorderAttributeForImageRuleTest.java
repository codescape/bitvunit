package de.codescape.bitvunit.rule.images;

import de.codescape.bitvunit.rule.Violations;
import org.junit.Test;

import static de.codescape.bitvunit.test.Assertions.assertNoViolations;
import static de.codescape.bitvunit.test.Assertions.assertViolations;
import static de.codescape.bitvunit.util.html.HtmlPageUtil.toHtmlPage;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class AvoidBorderAttributeForImageRuleTest {

    private AvoidBorderAttributeForImageRule rule = new AvoidBorderAttributeForImageRule();

    @Test
    public void ruleHasAName() throws Exception {
        assertNotNull(rule.getName());
        assertEquals("AvoidBorderAttributeForImage", rule.getName());
    }

    @Test
    public void singleViolationRaisedByRule() throws Exception {
        String content = "<html><body><img src=\"image.gif\" border=\"2\"></body></html>";
        Violations violations = rule.applyTo(toHtmlPage(content));
        assertViolations(violations, rule, 1);
    }

    @Test
    public void noViolationRaisedByRule() throws Exception {
        String content = "<html><body><img sr=\"image.gif\"></body></html>";
        Violations violations = rule.applyTo(toHtmlPage(content));
        assertNoViolations(violations, rule);
    }

}
