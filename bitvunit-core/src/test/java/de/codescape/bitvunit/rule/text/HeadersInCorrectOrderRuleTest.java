package de.codescape.bitvunit.rule.text;

import de.codescape.bitvunit.rule.Violations;
import org.junit.Test;

import static de.codescape.bitvunit.test.Assertions.assertNoViolations;
import static de.codescape.bitvunit.test.Assertions.assertViolations;
import static de.codescape.bitvunit.util.html.HtmlPageUtil.toHtmlPage;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class HeadersInCorrectOrderRuleTest {

    private HeadersInCorrectOrderRule rule = new HeadersInCorrectOrderRule();

    @Test
    public void ruleHasAName() throws Exception {
        assertNotNull(rule.getName());
        assertEquals("HeadersInCorrectOrder", rule.getName());
    }

    @Test
    public void headersInCorrectOrder() throws Exception {
        String content = "<html><body><h1>First Level</h1><h2>Second Level</h2><h3>Third Level</h3></body></html>";
        Violations violations = rule.applyTo(toHtmlPage(content));
        assertNoViolations(violations);
    }

    @Test
    public void headersMustStartAtFirstLevel() throws Exception {
        String content = "<html><body><h2>First Level</h2><h3>Third Level</h3></body></html>";
        Violations violations = rule.applyTo(toHtmlPage(content));
        assertViolations(violations, rule, 1);
    }

    @Test
    public void notMoreThanOneHeaderOfFirstLevelIsAllowed() throws Exception {
        String content = "<html><body><h1>First Level</h1><h2>Third Level</h2><h1>First Level again</h1></body></html>";
        Violations violations = rule.applyTo(toHtmlPage(content));
        assertViolations(violations, rule, 1);
    }

    @Test
    public void headersMayNotSkipLevels() throws Exception {
        String content = "<html><body><h1>First Level</h1><h3>Third Level</h3></body></html>";
        Violations violations = rule.applyTo(toHtmlPage(content));
        assertViolations(violations, rule, 1);
    }

    @Test
    public void headersMayNotSkipMultipleLevels() throws Exception {
        String content = "<html><body><h1>First Level</h1><h3>Third Level</h3><h6>Third Level</h6></body></html>";
        Violations violations = rule.applyTo(toHtmlPage(content));
        assertViolations(violations, rule, 2);
    }

}
