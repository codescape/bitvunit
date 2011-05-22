package de.codescape.bitvunit.rule.tables;

import de.codescape.bitvunit.rule.Violation;
import org.junit.Test;

import java.util.List;

import static de.codescape.bitvunit.test.HtmlPageCreator.create;
import static org.junit.Assert.assertEquals;

public class CaptionTextForTablesRuleTest {

    private CaptionTextForTablesRule rule = new CaptionTextForTablesRule();

    @Test
    public void tableWithMissingCaptionText() throws Exception {
        String content = "<html><body><table><tr><td>Hello World</td></tr></table></body></html>";

        List<Violation> violations = rule.applyTo(create(content));

        assertEquals(1, violations.size());
        assertEquals(rule, violations.get(0).getRule());
    }

    @Test
    public void tableWithEmptyCaptionText() throws Exception {
        String content = "<html><body><table><caption></caption><tr><td>Hello World</td></tr></table></body></html>";

        List<Violation> violations = rule.applyTo(create(content));

        assertEquals(1, violations.size());
        assertEquals(rule, violations.get(0).getRule());
    }

    @Test
    public void tableWithWhitespaceOnlyCaptionText() throws Exception {
        String content = "<html><body><table><caption> </caption><tr><td>Hello World</td></tr></table></body></html>";

        List<Violation> violations = rule.applyTo(create(content));

        assertEquals(1, violations.size());
        assertEquals(rule, violations.get(0).getRule());
    }

    @Test
    public void tableWithCaptionText() throws Exception {
        String content = "<html><body><table><caption>Short summary</caption><tr><td>Accessibility</td></tr></table></body></html>";

        List<Violation> violations = rule.applyTo(create(content));

        assertEquals(0, violations.size());
    }

}
