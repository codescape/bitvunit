package de.codescape.bitvunit.rule.forms;

import de.codescape.bitvunit.rule.Violations;
import org.junit.Test;

import static de.codescape.bitvunit.test.Assertions.assertNoViolations;
import static de.codescape.bitvunit.test.Assertions.assertViolations;
import static de.codescape.bitvunit.util.html.HtmlPageUtil.toHtmlPage;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class LabelForInputFieldRuleTest {

    private LabelForInputFieldRule rule = new LabelForInputFieldRule();

    @Test
    public void ruleHasAName() throws Exception {
        assertNotNull(rule.getName());
        assertEquals("LabelForInputField", rule.getName());
    }

    // button

    @Test
    public void inputTypeButtonShouldNotRequireLabel() {
        shouldNotRequireLabelForInputType("button");
    }

    // checkbox

    @Test
    public void inputTypeCheckboxShouldRequireLabel() {
        String inputType = "checkbox";
        shouldRequireLabelForInputType(inputType);
        shouldAcceptLabelForInputType(inputType);
    }

    // color

    @Test
    public void inputTypeColorShouldRequireLabel() {
        String inputType = "color";
        shouldRequireLabelForInputType(inputType);
        shouldAcceptLabelForInputType(inputType);
    }

    // date

    @Test
    public void inputTypeDateShouldRequireLabel() {
        String inputType = "date";
        shouldRequireLabelForInputType(inputType);
        shouldAcceptLabelForInputType(inputType);
    }

    // file

    @Test
    public void inputTypeFileShouldRequireLabel() {
        String inputType = "file";
        shouldRequireLabelForInputType(inputType);
        shouldAcceptLabelForInputType(inputType);
    }

    // hidden


    @Test
    public void inputTypeHiddenShouldNotRequireLabel() {
        shouldNotRequireLabelForInputType("hidden");
    }

    // password

    @Test
    public void inputTypePasswordShouldRequireLabel() {
        String inputType = "password";
        shouldRequireLabelForInputType(inputType);
        shouldAcceptLabelForInputType(inputType);
    }

    // radio

    @Test
    public void inputTypeRadioShouldRequireLabel() {
        String inputType = "radio";
        shouldRequireLabelForInputType(inputType);
        shouldAcceptLabelForInputType(inputType);
    }

    // reset

    @Test
    public void inputTypeResetShouldNotRequireLabel() {
        shouldNotRequireLabelForInputType("reset");
    }

    // submit

    @Test
    public void inputTypeSubmitShouldNotRequireLabel() {
        shouldNotRequireLabelForInputType("submit");
    }

    // text

    @Test
    public void inputTypeTextShouldRequireLabel() {
        String inputType = "text";
        shouldRequireLabelForInputType(inputType);
        shouldAcceptLabelForInputType(inputType);
    }

    private void shouldNotRequireLabelForInputType(String inputType) {
        String content = "<html><body><form>" +
                "   <input type=\"" + inputType + "\" id=\"" + inputType + "\">" +
                "</form></body></html>";
        assertNoViolations(rule.applyTo(toHtmlPage(content)));
    }

    private void shouldRequireLabelForInputType(String inputType) {
        String content = "<html><body><form>" +
                "   <input type=\"" + inputType + "\" id=\"" + inputType + "\">" +
                "</form></body></html>";
        assertViolations(rule.applyTo(toHtmlPage(content)), rule, 1);
    }

    private void shouldAcceptLabelForInputType(String inputType) {
        String validContent = "<html><body><form>" +
                "   <label for=\"" + inputType + "\">" + inputType + "</label>" +
                "   <input type=\"" + inputType + "\" id=\"" + inputType + "\">" +
                "</form></body></html>";
        assertNoViolations(rule.applyTo(toHtmlPage(validContent)));
    }

}
