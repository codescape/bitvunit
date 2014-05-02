package de.codescape.bitvunit.rule.text;

import de.codescape.bitvunit.rule.Violations;
import org.junit.Test;

import static de.codescape.bitvunit.test.Assertions.assertNoViolations;
import static de.codescape.bitvunit.test.Assertions.assertViolations;
import static de.codescape.bitvunit.util.html.HtmlPageUtil.toHtmlPage;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class AvoidFontTagRuleTest {

    private AvoidFontTagRule rule = new AvoidFontTagRule();

    @Test
    public void ruleHasAName() throws Exception {
        assertNotNull(rule.getName());
        assertEquals("AvoidFontTag", rule.getName());
    }

    @Test
    public void fontTagShouldNotBeUsed() throws Exception {
        String content = "<html><body><font size=\"4\">Hello World</font></body></html>";
        Violations violations = rule.applyTo(toHtmlPage(content));
        assertViolations(violations, rule, 1);
    }

    @Test
    public void usingSpanAndFormattingViaCssIsAccepted() throws Exception {
        String content = "<html><body><span id=\"teaser\">Hello World</span></body></html>";
        Violations violations = rule.applyTo(toHtmlPage(content));
        assertNoViolations(violations, rule);
    }

}
