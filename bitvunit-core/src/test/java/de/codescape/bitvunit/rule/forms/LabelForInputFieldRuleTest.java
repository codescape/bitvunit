package de.codescape.bitvunit.rule.forms;

import de.codescape.bitvunit.rule.Violations;
import org.junit.Test;

import static de.codescape.bitvunit.test.Assertions.assertNoViolations;
import static de.codescape.bitvunit.test.Assertions.assertViolations;
import static de.codescape.bitvunit.util.HtmlPageUtil.toHtmlPage;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class LabelForInputFieldRuleTest {

    private LabelForInputFieldRule rule = new LabelForInputFieldRule();

    @Test
    public void ruleHasAName() throws Exception {
        assertNotNull(rule.getName());
        assertEquals("LabelForInputField", rule.getName());
    }

    @Test
    public void inputTextFieldWithAssociatedLabel() throws Exception {
        String content = "<html><body><form><label for=\"firstname\">Firstname</label><input type=\"text\" id=\"firstname\" /></form></body></html>";
        Violations violations = rule.applyTo(toHtmlPage(content));
        assertNoViolations(violations);
    }

    @Test
    public void inputTextFieldWithMissingLabel() throws Exception {
        String content = "<html><body><form><input type=\"text\" id=\"firstname\" /></form></body></html>";
        Violations violations = rule.applyTo(toHtmlPage(content));
        assertViolations(violations, rule, 1);
    }

    @Test
    public void inputHiddenFieldWithMissingLabel() throws Exception {
        String content = "<html><body><form><input type=\"hidden\" id=\"firstname\" /></form></body></html>";
        Violations violations = rule.applyTo(toHtmlPage(content));
        assertNoViolations(violations);
    }

    @Test
    public void inputPasswordFieldWithMissingLabel() throws Exception {
        String content = "<html><body><form><input type=\"password\" id=\"password\" /></form></body></html>";
        Violations violations = rule.applyTo(toHtmlPage(content));
        assertViolations(violations, rule, 1);
    }

    @Test
    public void inputPasswordFieldWithAssociatedLabel() throws Exception {
        String content = "<html><body><form><label for=\"password\">Password</label><input type=\"password\" id=\"password\" /></form></body></html>";
        Violations violations = rule.applyTo(toHtmlPage(content));
        assertNoViolations(violations);
    }

}
