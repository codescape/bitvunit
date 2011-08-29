package de.codescape.bitvunit.rule.forms;

import de.codescape.bitvunit.rule.Violations;
import org.junit.Test;

import static de.codescape.bitvunit.test.Assertions.assertNoViolations;
import static de.codescape.bitvunit.test.Assertions.assertViolations;
import static de.codescape.bitvunit.util.html.HtmlPageUtil.toHtmlPage;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class LabelForTextareaRuleTest {

    private LabelForTextareaRule rule = new LabelForTextareaRule();

    @Test
    public void ruleHasAName() throws Exception {
        assertNotNull(rule.getName());
        assertEquals("LabelForTextarea", rule.getName());
    }

    @Test
    public void textareaWithAssociatedLabel() throws Exception {
        String content = "<html><body><form><label for=\"comment\">Comment</label><textarea id=\"comment\"></textarea></form></body></html>";
        Violations violations = rule.applyTo(toHtmlPage(content));
        assertNoViolations(violations);
    }

    @Test
    public void textareaWithMissingLabel() throws Exception {
        String content = "<html><body><form><textarea id=\"firstname\"></textarea></form></body></html>";
        Violations violations = rule.applyTo(toHtmlPage(content));
        assertViolations(violations, rule, 1);
    }

}
