package de.codescape.bitvunit.rule.images;

import de.codescape.bitvunit.rule.Violations;
import org.junit.Test;

import static de.codescape.bitvunit.test.Assertions.assertNoViolations;
import static de.codescape.bitvunit.test.Assertions.assertViolations;
import static de.codescape.bitvunit.util.html.HtmlPageUtil.toHtmlPage;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class AlternativeTextForLinkedImageRuleTest {

    private AlternativeTextForLinkedImageRule rule = new AlternativeTextForLinkedImageRule();

    @Test
    public void ruleHasAName() throws Exception {
        assertNotNull(rule.getName());
        assertEquals("AlternativeTextForLinkedImage", rule.getName());
    }

    @Test
    public void linkedImageWithoutAlternativeTextPresent() throws Exception {
        String content = "<html><body><a href=\"page.html\"><img src=\"contact.gif\" /></a></body></html>";
        Violations violations = rule.applyTo(toHtmlPage(content));
        assertViolations(violations, rule, 1);
    }

    @Test
    public void linkedImageWithEmptyAlternativeTextPresent() throws Exception {
        String content = "<html><body><a href=\"page.html\"><img src=\"contact.gif\" alt=\"\" /></a></body></html>";
        Violations violations = rule.applyTo(toHtmlPage(content));
        assertViolations(violations, rule, 1);
    }

    @Test
    public void linkedImageWithAlternativeTextPresent() throws Exception {
        String content = "<html><body><a href=\"page.html\"><img src=\"contact.gif\" alt=\"Contact\" /></a></body></html>";
        Violations violations = rule.applyTo(toHtmlPage(content));
        assertNoViolations(violations, rule);
    }

    @Test
    public void nonLinkedImageWithMissingAlternativeText() throws Exception {
        String content = "<html><body><img src=\"contact.gif\" alt=\"\" /></body></html>";
        Violations violations = rule.applyTo(toHtmlPage(content));
        assertNoViolations(violations, rule);
    }

}
