package de.codescape.bitvunit.rule.forms;

import de.codescape.bitvunit.rule.Violations;
import org.junit.Test;

import static de.codescape.bitvunit.test.Assertions.assertNoViolations;
import static de.codescape.bitvunit.test.Assertions.assertViolations;
import static de.codescape.bitvunit.util.html.HtmlPageUtil.toHtmlPage;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class SelectFieldWithValidOptionRuleTest {

    private SelectFieldWithValidOptionRule rule = new SelectFieldWithValidOptionRule();

    @Test
    public void ruleHasAName() throws Exception {
        assertNotNull(rule.getName());
        assertEquals("SelectFieldWithValidOption", rule.getName());
    }

    @Test
    public void firstOptionMustNotBeDisabledAndUsedForInstructions() throws Exception {
        String content = "<html><body>" +
                " <select name=\"gender\">" +
                "  <option disabled=\"disabled\">Please choose</option>" +
                "  <option>male</option>" +
                "  <option>female</option>" +
                " </select>" +
                "</body></html>";
        Violations violations = rule.applyTo(toHtmlPage(content));
        assertViolations(violations, rule, 1);
    }

    @Test
    public void minimumOneOptionMustExist() throws Exception {
        String content = "<html><body>" +
                " <select name=\"gender\"></select>" +
                "</body></html>";
        Violations violations = rule.applyTo(toHtmlPage(content));
        assertViolations(violations, rule, 1);
    }

    @Test
    public void validSelectFieldWithFirstOptionAvailableForSelection() throws Exception {
        String content = "<html><body>" +
                " <select name=\"gender\">" +
                "  <option>male</option>" +
                "  <option>female</option>" +
                " </select>" +
                "</body></html>";
        Violations violations = rule.applyTo(toHtmlPage(content));
        assertNoViolations(violations, rule);
    }

}
