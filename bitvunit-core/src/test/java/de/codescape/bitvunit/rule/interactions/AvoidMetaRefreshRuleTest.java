package de.codescape.bitvunit.rule.interactions;

import de.codescape.bitvunit.rule.Violations;
import org.junit.Test;

import static de.codescape.bitvunit.test.Assertions.assertNoViolations;
import static de.codescape.bitvunit.test.Assertions.assertViolations;
import static de.codescape.bitvunit.util.HtmlPageUtil.htmlPageFromString;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class AvoidMetaRefreshRuleTest {

    private AvoidMetaRefreshRule rule = new AvoidMetaRefreshRule();

    @Test
    public void ruleHasAName() throws Exception {
        assertNotNull(rule.getName());
        assertEquals("AvoidMetaRefresh", rule.getName());
    }

    @Test
    public void metaRefreshPresent() throws Exception {
        String content = "<html><head><meta http-equiv=\"refresh\" content=\"3; URL=page.htm\"></head><body><p>Redirecting</p></body></html>";
        Violations violations = rule.applyTo(htmlPageFromString(content));
        assertViolations(violations, rule, 1);
    }

    @Test
    public void metaRefreshNotPresent() throws Exception {
        String content = "<html><head><title>No Refresh</title></head><body><p>Hello World</p></body></html>";
        Violations violations = rule.applyTo(htmlPageFromString(content));
        assertNoViolations(violations, rule);
    }

}
