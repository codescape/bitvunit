package de.codescape.bitvunit.rule.images;

import de.codescape.bitvunit.rule.Violation;
import org.junit.Test;

import java.util.List;

import static de.codescape.bitvunit.test.Assertions.assertNoViolationExists;
import static de.codescape.bitvunit.test.Assertions.assertViolationExists;
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
        List<Violation> violations = rule.applyTo(createHtmlPage(content));
        assertViolationExists(violations, rule);
    }

    @Test
    public void imageWithEmptyAlternativeText() throws Exception {
        String content = "<html><body><img src=\"myImage.gif\" alt=\"\" /></body></html>";
        List<Violation> violations = rule.applyTo(createHtmlPage(content));
        assertNoViolationExists(violations);
    }

    @Test
    public void imageWithAlternativeText() throws Exception {
        String content = "<html><body><img src=\"myImage.gif\" alt=\"Alternative Text\" /></body></html>";
        List<Violation> violations = rule.applyTo(createHtmlPage(content));
        assertNoViolationExists(violations);
    }

}
