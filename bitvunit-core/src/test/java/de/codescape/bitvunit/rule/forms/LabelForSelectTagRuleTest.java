package de.codescape.bitvunit.rule.forms;

import de.codescape.bitvunit.rule.Violations;
import org.junit.Test;

import static de.codescape.bitvunit.test.Assertions.assertNoViolations;
import static de.codescape.bitvunit.test.Assertions.assertViolations;
import static de.codescape.bitvunit.util.HtmlPageUtil.toHtmlPage;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class LabelForSelectTagRuleTest {

    private LabelForSelectTagRule rule = new LabelForSelectTagRule();

    @Test
    public void ruleHasAName() throws Exception {
        assertNotNull(rule.getName());
        assertEquals("LabelForSelectTag", rule.getName());
    }

    @Test
    public void selectWithAssociatedLabel() throws Exception {
        String content = "<html><body><form><label for=\"gender\">Comment</label><select id=\"gender\"><option>m</option><option>f</option></select></form></body></html>";
        Violations violations = rule.applyTo(toHtmlPage(content));
        assertNoViolations(violations);
    }

    @Test
    public void selectWithMissingLabel() throws Exception {
        String content = "<html><body><form><select id=\"gender\"><option>m</option><option>f</option></select></form></body></html>";
        Violations violations = rule.applyTo(toHtmlPage(content));
        assertViolations(violations, rule, 1);
    }

}
