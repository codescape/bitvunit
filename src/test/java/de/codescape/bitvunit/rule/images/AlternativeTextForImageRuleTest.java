package de.codescape.bitvunit.rule.images;

import de.codescape.bitvunit.rule.Violation;
import org.junit.Test;

import java.util.List;

import static de.codescape.bitvunit.test.HtmlPageCreator.create;
import static org.junit.Assert.*;

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

        List<Violation> violations = rule.applyTo(create(content));

        assertEquals(1, violations.size());
        assertEquals(rule, violations.get(0).getRule());
    }

    @Test
    public void imageWithEmptyAlternativeText() throws Exception {
        String content = "<html><body><img src=\"myImage.gif\" alt=\"\" /></body></html>";

        List<Violation> violations = rule.applyTo(create(content));

        assertTrue(violations.isEmpty());
    }

    @Test
    public void imageWithAlternativeText() throws Exception {
        String content = "<html><body><img src=\"myImage.gif\" alt=\"Alternative Text\" /></body></html>";

        List<Violation> violations = rule.applyTo(create(content));

        assertTrue(violations.isEmpty());
    }

}
