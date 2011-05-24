package de.codescape.bitvunit.rule.tables;

import de.codescape.bitvunit.rule.Violation;
import org.junit.Test;

import java.util.List;

import static de.codescape.bitvunit.test.Assertions.assertNoViolation;
import static de.codescape.bitvunit.test.Assertions.assertViolation;
import static de.codescape.bitvunit.test.HtmlPageCreator.createHtmlPage;
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
        List<Violation> violations = rule.applyTo(createHtmlPage(content));
        assertViolation(violations, rule);
    }

    @Test
    public void tableWithEmptyCaptionText() throws Exception {
        String content = "<html><body><table><caption></caption><tr><td>Hello World</td></tr></table></body></html>";
        List<Violation> violations = rule.applyTo(createHtmlPage(content));
        assertViolation(violations, rule);
    }

    @Test
    public void tableWithWhitespaceOnlyCaptionText() throws Exception {
        String content = "<html><body><table><caption> </caption><tr><td>Hello World</td></tr></table></body></html>";
        List<Violation> violations = rule.applyTo(createHtmlPage(content));
        assertViolation(violations, rule);
    }

    @Test
    public void tableWithCaptionText() throws Exception {
        String content = "<html><body><table><caption>Short summary</caption><tr><td>Accessibility</td></tr></table></body></html>";
        List<Violation> violations = rule.applyTo(createHtmlPage(content));
        assertNoViolation(violations);
    }

}
