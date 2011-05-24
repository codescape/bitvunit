package de.codescape.bitvunit.rule.forms;

import de.codescape.bitvunit.rule.Violation;
import org.junit.Test;

import java.util.List;

import static de.codescape.bitvunit.test.Assertions.assertNoViolation;
import static de.codescape.bitvunit.test.Assertions.assertViolation;
import static de.codescape.bitvunit.test.HtmlPageCreator.createHtmlPage;
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
        List<Violation> violations = rule.applyTo(createHtmlPage(content));
        assertNoViolation(violations);
    }

    @Test
    public void inputTextFieldWithMissingLabel() throws Exception {
        String content = "<html><body><form><input type=\"text\" id=\"firstname\" /></form></body></html>";
        List<Violation> violations = rule.applyTo(createHtmlPage(content));
        assertViolation(violations, rule);
    }

    @Test
    public void inputHiddenFieldWithMissingLabel() throws Exception {
        String content = "<html><body><form><input type=\"hidden\" id=\"firstname\" /></form></body></html>";
        List<Violation> violations = rule.applyTo(createHtmlPage(content));
        assertNoViolation(violations);
    }

    @Test
    public void inputPasswordFieldWithMissingLabel() throws Exception {
        String content = "<html><body><form><input type=\"password\" id=\"password\" /></form></body></html>";
        List<Violation> violations = rule.applyTo(createHtmlPage(content));
        assertViolation(violations, rule);
    }

    @Test
    public void inputPasswordFieldWithAssociatedLabel() throws Exception {
        String content = "<html><body><form><label for=\"password\">Password</label><input type=\"password\" id=\"password\" /></form></body></html>";
        List<Violation> violations = rule.applyTo(createHtmlPage(content));
        assertNoViolation(violations);
    }

}
