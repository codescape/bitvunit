package de.codescape.bitvunit.rule.text;

import de.codescape.bitvunit.rule.Violation;
import de.codescape.bitvunit.rule.Violations;
import org.junit.Test;

import java.util.List;

import static de.codescape.bitvunit.test.Assertions.assertNoViolations;
import static de.codescape.bitvunit.test.Assertions.assertViolations;
import static de.codescape.bitvunit.test.HtmlPageCreator.createHtmlPage;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class TitleForAbbreviationTagRuleTest {

    private TitleForAbbreviationTagRule rule = new TitleForAbbreviationTagRule();

    @Test
    public void ruleHasAName() throws Exception {
        assertNotNull(rule.getName());
        assertEquals("TitleForAbbreviationTag", rule.getName());
    }

    @Test
    public void abbrElementWithTitleAttribute() throws Exception {
        String content = "<html><body><abbr title=\"Structured Query Language\">SQL</abbr></body></html>";
        Violations violations = rule.applyTo(createHtmlPage(content));
        assertNoViolations(violations);
    }

    @Test
    public void abbrElementWithEmptyTitleAttribute() throws Exception {
        String content = "<html><body><abbr title=\"\">SQL</abbr></body></html>";
        Violations violations = rule.applyTo(createHtmlPage(content));
        assertViolations(violations, rule, 1);
    }

    @Test
    public void abbrElementWithMissingTitleAttribute() {
        String content = "<html><body><abbr>SQL</abbr></body></html>";
        Violations violations = rule.applyTo(createHtmlPage(content));
        assertViolations(violations, rule, 1);
    }

}
