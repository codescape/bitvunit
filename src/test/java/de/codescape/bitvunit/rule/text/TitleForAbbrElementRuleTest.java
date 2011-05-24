package de.codescape.bitvunit.rule.text;

import de.codescape.bitvunit.rule.Violation;
import org.junit.Test;

import java.util.List;

import static de.codescape.bitvunit.test.Assertions.assertNoViolation;
import static de.codescape.bitvunit.test.Assertions.assertViolation;
import static de.codescape.bitvunit.test.HtmlPageCreator.create;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class TitleForAbbrElementRuleTest {

    private TitleForAbbrElementRule rule = new TitleForAbbrElementRule();

    @Test
    public void ruleHasAName() throws Exception {
        assertNotNull(rule.getName());
        assertEquals("TitleForAbbrElement", rule.getName());
    }

    @Test
    public void abbrElementWithTitleAttribute() throws Exception {
        String content = "<html><body><abbr title=\"Structured Query Language\">SQL</abbr></body></html>";
        List<Violation> violations = rule.applyTo(create(content));
        assertNoViolation(violations);
    }

    @Test
    public void abbrElementWithEmptyTitleAttribute() throws Exception {
        String content = "<html><body><abbr title=\"\">SQL</abbr></body></html>";
        List<Violation> violations = rule.applyTo(create(content));
        assertViolation(violations, rule);
    }

    @Test
    public void abbrElementWithMissingTitleAttribute() {
        String content = "<html><body><abbr>SQL</abbr></body></html>";
        List<Violation> violations = rule.applyTo(create(content));
        assertViolation(violations, rule);
    }

}
