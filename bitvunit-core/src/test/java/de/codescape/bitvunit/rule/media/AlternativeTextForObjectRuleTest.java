package de.codescape.bitvunit.rule.media;

import de.codescape.bitvunit.rule.Violations;
import org.junit.Test;

import static de.codescape.bitvunit.test.Assertions.assertNoViolations;
import static de.codescape.bitvunit.test.Assertions.assertViolations;
import static de.codescape.bitvunit.util.HtmlPageUtil.toHtmlPage;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class AlternativeTextForObjectRuleTest {

    private AlternativeTextForObjectRule rule = new AlternativeTextForObjectRule();

    @Test
    public void ruleHasAName() throws Exception {
        assertNotNull(rule.getName());
        assertEquals("AlternativeTextForObject", rule.getName());
    }

    @Test
    public void objectWithoutAlternativeTextPresent() throws Exception {
        String content = "<html><body><object data=\"movie.mp4\" type=\"video/mp4\"></object></body></html>";
        Violations violations = rule.applyTo(toHtmlPage(content));
        assertViolations(violations, rule, 1);
    }

    @Test
    public void objectWithAlternativeTextPresent() throws Exception {
        String content = "<html><body><object data=\"movie.mp4\" type=\"video/mp4\">Movie about usage of BitvUnit</object></body></html>";
        Violations violations = rule.applyTo(toHtmlPage(content));
        assertNoViolations(violations, rule);
    }

    @Test
    public void objectWithAlternativeTextInInnerElementPresent() throws Exception {
        String content = "<html><body><object data=\"movie.mp4\" type=\"video/mp4\"><p>Movie about usage of BitvUnit</p></object></body></html>";
        Violations violations = rule.applyTo(toHtmlPage(content));
        assertNoViolations(violations, rule);
    }

}
