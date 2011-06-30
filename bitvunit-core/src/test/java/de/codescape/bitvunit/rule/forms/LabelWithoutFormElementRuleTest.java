package de.codescape.bitvunit.rule.forms;

import de.codescape.bitvunit.rule.Violations;
import org.junit.Test;

import static de.codescape.bitvunit.test.Assertions.assertNoViolations;
import static de.codescape.bitvunit.test.Assertions.assertViolations;
import static de.codescape.bitvunit.test.HtmlPageCreator.createHtmlPage;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class LabelWithoutFormElementRuleTest {

    private LabelWithoutFormElementRule rule = new LabelWithoutFormElementRule();

    @Test
    public void ruleHasAName() throws Exception {
        assertNotNull(rule.getName());
        assertEquals("LabelWithoutFormElement", rule.getName());
    }

    @Test
    public void labelWithoutFormElementPresent() throws Exception {
        String content = "<html><body><form><label for=\"missingId\">Some text</label></form></body></html>";
        Violations violations = rule.applyTo(createHtmlPage(content));
        assertViolations(violations, rule, 1);
    }

    @Test
    public void labelWithFormElementPresent() throws Exception {
        String content = "<html><body><form><label for=\"validId\">Some text</label><input id=\"validId\" /></form></body></html>";
        Violations violations = rule.applyTo(createHtmlPage(content));
        assertNoViolations(violations, rule);
    }

    @Test
    public void labelWithNonFormElementPresent() throws Exception {
        String content = "<html><body><form><label for=\"wrongElement\">Some Text</label><p id=\"wrongElement\">Not a form element</p></body></html>";
        Violations violations = rule.applyTo(createHtmlPage(content));
        assertViolations(violations, rule, 1);
    }

}
