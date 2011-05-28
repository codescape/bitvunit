package de.codescape.bitvunit.rule.forms;

import de.codescape.bitvunit.rule.Violation;
import org.junit.Test;

import java.util.List;

import static de.codescape.bitvunit.test.Assertions.assertNoViolations;
import static de.codescape.bitvunit.test.Assertions.assertViolations;
import static de.codescape.bitvunit.test.HtmlPageCreator.createHtmlPage;
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
        List<Violation> violations = rule.applyTo(createHtmlPage(content));
        assertNoViolations(violations);
    }

    @Test
    public void textareaWithMissingLabel() throws Exception {
        String content = "<html><body><form><textarea id=\"firstname\"></textarea></form></body></html>";
        List<Violation> violations = rule.applyTo(createHtmlPage(content));
        assertViolations(violations, rule, 1);
    }

}
