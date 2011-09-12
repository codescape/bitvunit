package de.codescape.bitvunit.rule.frames;

import de.codescape.bitvunit.rule.Violations;
import org.junit.Test;

import static de.codescape.bitvunit.test.Assertions.assertNoViolations;
import static de.codescape.bitvunit.test.Assertions.assertViolations;
import static de.codescape.bitvunit.util.html.HtmlPageUtil.toHtmlPage;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class AlternativeTextForInlineFrameRuleTest {

    private AlternativeTextForInlineFrameRule rule = new AlternativeTextForInlineFrameRule();

    @Test
    public void ruleHasAName() throws Exception {
        assertNotNull(rule.getName());
        assertEquals("AlternativeTextForInlineFrame", rule.getName());
    }

    @Test
    public void innerFrameWithAlternativeTextMissing() throws Exception {
        String content = "<html><body><iframe src=\"correct-url.html\"></iframe></body></html>";
        Violations violations = rule.applyTo(toHtmlPage(content));
        assertViolations(violations, rule, 1);
    }

    @Test
    public void innerFrameWithAlternativeEmptyTextPresent() throws Exception {
        String content = "<html><body><iframe src=\"correct-url.html\">          </iframe></body></html>";
        Violations violations = rule.applyTo(toHtmlPage(content));
        assertViolations(violations, rule, 1);
    }

    @Test
    public void innerFrameWithAlternativeLinkPresent() throws Exception {
        String content = "<html><body><iframe src=\"correct.htm\"><a href=\"alternative.htm\">Alternative URL</a></iframe></body></html>";
        Violations violations = rule.applyTo(toHtmlPage(content));
        assertNoViolations(violations, rule);
    }

}
