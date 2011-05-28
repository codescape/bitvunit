package de.codescape.bitvunit.rule.text;

import de.codescape.bitvunit.rule.Violation;
import org.junit.Test;

import java.util.List;

import static de.codescape.bitvunit.test.Assertions.assertNoViolationExists;
import static de.codescape.bitvunit.test.Assertions.assertViolationExists;
import static de.codescape.bitvunit.test.HtmlPageCreator.createHtmlPage;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class TitleForAcronymTagRuleTest {

    private TitleForAcronymTagRule rule = new TitleForAcronymTagRule();

    @Test
    public void ruleHasAName() throws Exception {
        assertNotNull(rule.getName());
        assertEquals("TitleForAcronymTag", rule.getName());
    }

    @Test
    public void acronymElementWithTitleAttribute() throws Exception {
        String content = "<html><body><acronym title=\"as soon as possible\">ASAP</acronym></body></html>";
        List<Violation> violations = rule.applyTo(createHtmlPage(content));
        assertNoViolationExists(violations);
    }

    @Test
    public void acronymElementWithEmptyTitleAttribute() throws Exception {
        String content = "<html><body><acronym title=\"\">ASAP</acronym></body></html>";
        List<Violation> violations = rule.applyTo(createHtmlPage(content));
        assertViolationExists(violations, rule);
    }

    @Test
    public void acronymElementWithMissingTitleAttribute() {
        String content = "<html><body><acronym>ASAP</acronym></body></html>";
        List<Violation> violations = rule.applyTo(createHtmlPage(content));
        assertViolationExists(violations, rule);
    }

}
