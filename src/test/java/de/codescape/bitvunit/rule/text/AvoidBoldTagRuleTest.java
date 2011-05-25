package de.codescape.bitvunit.rule.text;

import de.codescape.bitvunit.rule.Violation;
import org.junit.Test;

import java.util.List;

import static de.codescape.bitvunit.test.Assertions.assertNoViolation;
import static de.codescape.bitvunit.test.Assertions.assertViolation;
import static de.codescape.bitvunit.test.HtmlPageCreator.createHtmlPage;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class AvoidBoldTagRuleTest {

    private AvoidBoldTagRule rule = new AvoidBoldTagRule();

    @Test
    public void ruleHasAName() throws Exception {
        assertNotNull(rule.getName());
        assertEquals("AvoidBoldTag", rule.getName());
    }

    @Test
    public void boldTagPresent() throws Exception {
        String content = "<html><body><b>Meaningful words</b></body></html>";
        List<Violation> violations = rule.applyTo(createHtmlPage(content));
        assertViolation(violations, rule);
    }

    @Test
    public void boldTagNotPresent() throws Exception {
        String content = "<html><body><strong>Meaningful words</strong></body></html>";
        List<Violation> violations = rule.applyTo(createHtmlPage(content));
        assertNoViolation(violations);
    }

}
