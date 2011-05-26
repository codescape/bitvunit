package de.codescape.bitvunit.rule.text;

import de.codescape.bitvunit.rule.Violation;
import org.junit.Test;

import java.util.List;

import static de.codescape.bitvunit.test.Assertions.assertNoViolationExists;
import static de.codescape.bitvunit.test.Assertions.assertViolationExists;
import static de.codescape.bitvunit.test.HtmlPageCreator.createHtmlPage;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class AvoidItalicTagRuleTest {

    private AvoidItalicTagRule rule = new AvoidItalicTagRule();

    @Test
    public void ruleHasAName() throws Exception {
        assertNotNull(rule.getName());
        assertEquals("AvoidItalicTag", rule.getName());
    }

    @Test
    public void italicTagPresent() throws Exception {
        String content = "<html><body><i>Meaningful words</i></body></html>";
        List<Violation> violations = rule.applyTo(createHtmlPage(content));
        assertViolationExists(violations, rule);
    }

    @Test
    public void italicTagNotPresent() throws Exception {
        String content = "<html><body><em>Meaningful words</em></body></html>";
        List<Violation> violations = rule.applyTo(createHtmlPage(content));
        assertNoViolationExists(violations);
    }

}
