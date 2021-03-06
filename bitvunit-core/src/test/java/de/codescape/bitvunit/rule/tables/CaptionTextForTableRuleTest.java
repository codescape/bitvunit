package de.codescape.bitvunit.rule.tables;

import de.codescape.bitvunit.rule.Violations;
import org.junit.Test;

import static de.codescape.bitvunit.test.Assertions.assertNoViolations;
import static de.codescape.bitvunit.test.Assertions.assertViolations;
import static de.codescape.bitvunit.util.html.HtmlPageUtil.toHtmlPage;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class CaptionTextForTableRuleTest {

    private CaptionTextForTableRule rule = new CaptionTextForTableRule();

    @Test
    public void ruleHasAName() throws Exception {
        assertNotNull(rule.getName());
        assertEquals("CaptionTextForTable", rule.getName());
    }

    @Test
    public void tableWithMissingCaptionText() throws Exception {
        String content = "<html><body><table><tr><td>Hello World</td></tr></table></body></html>";
        Violations violations = rule.applyTo(toHtmlPage(content));
        assertViolations(violations, rule, 1);
    }

    @Test
    public void tableWithEmptyCaptionText() throws Exception {
        String content = "<html><body><table><caption></caption><tr><td>Hello World</td></tr></table></body></html>";
        Violations violations = rule.applyTo(toHtmlPage(content));
        assertViolations(violations, rule, 1);
    }

    @Test
    public void tableWithWhitespaceOnlyCaptionText() throws Exception {
        String content = "<html><body><table><caption> </caption><tr><td>Hello World</td></tr></table></body></html>";
        Violations violations = rule.applyTo(toHtmlPage(content));
        assertViolations(violations, rule, 1);
    }

    @Test
    public void tableWithCaptionText() throws Exception {
        String content = "<html><body><table><caption>Short summary</caption><tr><td>Accessibility</td></tr></table></body></html>";
        Violations violations = rule.applyTo(toHtmlPage(content));
        assertNoViolations(violations);
    }

}
