package de.codescape.bitvunit.rule.frames;

import de.codescape.bitvunit.rule.Violations;
import org.junit.Test;

import static de.codescape.bitvunit.test.Assertions.assertNoViolations;
import static de.codescape.bitvunit.test.Assertions.assertViolations;
import static de.codescape.bitvunit.util.html.HtmlPageUtil.toHtmlPage;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class TitleForInlineFrameRuleTest {

    private TitleForInlineFrameRule rule = new TitleForInlineFrameRule();

    @Test
    public void ruleHasAName() throws Exception {
        assertNotNull(rule.getName());
        assertEquals("TitleForInlineFrame", rule.getName());
    }

    @Test
    public void inlineFrameWithoutTitlePresent() throws Exception {
        String content = "<html><body><iframe src=\"nav.html\" /></body></html>";
        Violations violations = rule.applyTo(toHtmlPage(content));
        assertViolations(violations, rule, 1);
    }

    @Test
    public void inlineFrameWithTitlePresent() throws Exception {
        String content = "<html><body><iframe title=\"Navigation\" src=\"nav.html\" /></body></html>";
        Violations violations = rule.applyTo(toHtmlPage(content));
        assertNoViolations(violations, rule);
    }

}
