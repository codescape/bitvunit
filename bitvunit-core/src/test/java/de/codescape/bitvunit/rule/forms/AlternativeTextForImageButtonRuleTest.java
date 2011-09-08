package de.codescape.bitvunit.rule.forms;

import de.codescape.bitvunit.rule.Violations;
import org.junit.Test;

import static de.codescape.bitvunit.test.Assertions.assertNoViolations;
import static de.codescape.bitvunit.test.Assertions.assertViolations;
import static de.codescape.bitvunit.util.html.HtmlPageUtil.toHtmlPage;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class AlternativeTextForImageButtonRuleTest {

    private AlternativeTextForImageButtonRule rule = new AlternativeTextForImageButtonRule();

    @Test
    public void ruleHasAName() throws Exception {
        assertNotNull(rule.getName());
        assertEquals("AlternativeTextForImageButton", rule.getName());
    }

    @Test
    public void imageButtonWithMissingAlternativeText() throws Exception {
        String content = "<html><body><form><input type=\"image\"/></form></body></html>";
        Violations violations = rule.applyTo(toHtmlPage(content));
        assertViolations(violations, rule, 1);
    }

    @Test
    public void imageButtonWithAlternativeTextPresent() throws Exception {
        String content = "<html><body><form><input type=\"image\" alt=\"Create Account\"/></form></body></html>";
        Violations violations = rule.applyTo(toHtmlPage(content));
        assertNoViolations(violations, rule);
    }

}
