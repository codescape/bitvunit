package de.codescape.bitvunit.rule.links;

import de.codescape.bitvunit.rule.Violations;
import org.junit.Test;

import static de.codescape.bitvunit.test.Assertions.assertNoViolations;
import static de.codescape.bitvunit.test.Assertions.assertViolations;
import static de.codescape.bitvunit.util.HtmlPageUtil.toHtmlPage;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class AnchorsWithoutTargetAttributeRuleTest {

    private AnchorsWithoutTargetAttributeRule rule = new AnchorsWithoutTargetAttributeRule();

    @Test
    public void ruleHasAName() throws Exception {
        assertNotNull(rule.getName());
        assertEquals("AnchorsWithoutTargetAttribute", rule.getName());
    }

    @Test
    public void linkWithTargetAttributePresent() throws Exception {
        String content = "<html><body><a href=\"url.htm\" target=\"_blank\">Click me</a></body></html>";
        Violations violations = rule.applyTo(toHtmlPage(content));
        assertViolations(violations, rule, 1);
    }

    @Test
    public void linkWithNoTargetAttribute() throws Exception {
        String content = "<html><body><a href=\"url.htm\">Click me</a></body></html>";
        Violations violations = rule.applyTo(toHtmlPage(content));
        assertNoViolations(violations, rule);
    }

}
