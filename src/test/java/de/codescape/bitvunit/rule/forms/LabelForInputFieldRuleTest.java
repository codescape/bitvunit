package de.codescape.bitvunit.rule.forms;

import static de.codescape.bitvunit.test.HtmlPageCreator.create;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Test;

import de.codescape.bitvunit.rule.Violation;

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
        List<Violation> violations = rule.applyTo(create(content));
        assertTrue(violations.isEmpty());
    }

    @Test
    public void inputTextFieldWithMissingLabel() throws Exception {
        String content = "<html><body><form><input type=\"text\" id=\"firstname\" /></form></body></html>";
        List<Violation> violations = rule.applyTo(create(content));
        assertEquals(1, violations.size());
        assertEquals(rule, violations.get(0).getRule());
    }

    @Test
    public void inputHiddenFieldWithMissingLabel() throws Exception {
        String content = "<html><body><form><input type=\"hidden\" id=\"firstname\" /></form></body></html>";
        List<Violation> violations = rule.applyTo(create(content));
        assertTrue(violations.isEmpty());
    }

    @Test
    public void inputPasswordFieldWithMissingLabel() throws Exception {
        String content = "<html><body><form><input type=\"password\" id=\"password\" /></form></body></html>";
        List<Violation> violations = rule.applyTo(create(content));
        assertEquals(1, violations.size());
        assertEquals(rule, violations.get(0).getRule());
    }

    @Test
    public void inputPasswordFieldWithAssociatedLabel() throws Exception {
        String content = "<html><body><form><label for=\"password\">Password</label><input type=\"password\" id=\"password\" /></form></body></html>";
        List<Violation> violations = rule.applyTo(create(content));
        assertTrue(violations.isEmpty());
    }

}
