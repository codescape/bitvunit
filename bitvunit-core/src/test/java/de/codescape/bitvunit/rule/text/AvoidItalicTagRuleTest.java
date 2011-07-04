package de.codescape.bitvunit.rule.text;

import de.codescape.bitvunit.rule.Violations;
import org.junit.Test;

import static de.codescape.bitvunit.test.Assertions.assertNoViolations;
import static de.codescape.bitvunit.test.Assertions.assertViolations;
import static de.codescape.bitvunit.util.HtmlPageUtil.htmlPageFromString;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class AvoidItalicTagRuleTest {

    private AvoidItalicTagRule rule = new AvoidItalicTagRule();

    @Test
    public void ruleHasAName() throws Exception {
        assertNotNull(rule.getName());
        assertEquals("AvoidItalicTag", rule.getName());
    }

    @Test
    public void italicTagPresent() throws Exception {
        String content = "<html><body><i>Meaningful words</i></body></html>";
        Violations violations = rule.applyTo(htmlPageFromString(content));
        assertViolations(violations, rule, 1);
    }

    @Test
    public void italicTagNotPresent() throws Exception {
        String content = "<html><body><em>Meaningful words</em></body></html>";
        Violations violations = rule.applyTo(htmlPageFromString(content));
        assertNoViolations(violations);
    }

}
