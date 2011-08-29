package de.codescape.bitvunit.rule.forms;

import de.codescape.bitvunit.rule.Violations;
import org.junit.Test;

import static de.codescape.bitvunit.test.Assertions.assertNoViolations;
import static de.codescape.bitvunit.test.Assertions.assertViolations;
import static de.codescape.bitvunit.util.html.HtmlPageUtil.toHtmlPage;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;


public class FieldsetContainsLegendRuleTest {

    private FieldsetContainsLegendRule rule = new FieldsetContainsLegendRule();

    @Test
    public void ruleHasAName() throws Exception {
        assertNotNull(rule.getName());
        assertEquals("FieldsetContainsLegend", rule.getName());
    }

    @Test
    public void fieldsetWithLegendPresent() throws Exception {
        String content = "<html><body><fieldset><legend>Description</legend></fieldset></body></html>";
        Violations violations = rule.applyTo(toHtmlPage(content));
        assertNoViolations(violations, rule);
    }

    @Test
    public void fieldsetWithLegendMissing() throws Exception {
        String content = "<html><body><fieldset><p>no legend</p></fieldset></body></html>";
        Violations violations = rule.applyTo(toHtmlPage(content));
        assertViolations(violations, rule, 1);
    }

}
