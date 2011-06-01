package de.codescape.bitvunit.rule.images;

import de.codescape.bitvunit.rule.Violations;
import org.junit.Test;

import static de.codescape.bitvunit.test.Assertions.assertNoViolations;
import static de.codescape.bitvunit.test.Assertions.assertViolations;
import static de.codescape.bitvunit.test.HtmlPageCreator.createHtmlPage;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class AlternativeTextForImageRuleTest {

    private AlternativeTextForImageRule rule = new AlternativeTextForImageRule();

    @Test
    public void ruleHasAName() throws Exception {
        assertNotNull(rule.getName());
        assertEquals("AlternativeTextForImage", rule.getName());
    }

    @Test
    public void imageWithMissingAlternativeText() throws Exception {
        String content = "<html><body><img src=\"myImage.gif\" /></body></html>";
        Violations violations = rule.applyTo(createHtmlPage(content));
        assertViolations(violations, rule, 1);
    }

    @Test
    public void imageWithEmptyAlternativeText() throws Exception {
        String content = "<html><body><img src=\"myImage.gif\" alt=\"\" /></body></html>";
        Violations violations = rule.applyTo(createHtmlPage(content));
        assertNoViolations(violations);
    }

    @Test
    public void imageWithAlternativeText() throws Exception {
        String content = "<html><body><img src=\"myImage.gif\" alt=\"Alternative Text\" /></body></html>";
        Violations violations = rule.applyTo(createHtmlPage(content));
        assertNoViolations(violations);
    }

}
