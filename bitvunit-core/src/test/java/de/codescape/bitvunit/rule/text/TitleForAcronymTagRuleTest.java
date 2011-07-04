package de.codescape.bitvunit.rule.text;

import de.codescape.bitvunit.rule.Violations;
import org.junit.Test;

import static de.codescape.bitvunit.test.Assertions.assertNoViolations;
import static de.codescape.bitvunit.test.Assertions.assertViolations;
import static de.codescape.bitvunit.util.HtmlPageUtil.htmlPageFromString;
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
        Violations violations = rule.applyTo(htmlPageFromString(content));
        assertNoViolations(violations);
    }

    @Test
    public void acronymElementWithEmptyTitleAttribute() throws Exception {
        String content = "<html><body><acronym title=\"\">ASAP</acronym></body></html>";
        Violations violations = rule.applyTo(htmlPageFromString(content));
        assertViolations(violations, rule, 1);
    }

    @Test
    public void acronymElementWithMissingTitleAttribute() {
        String content = "<html><body><acronym>ASAP</acronym></body></html>";
        Violations violations = rule.applyTo(htmlPageFromString(content));
        assertViolations(violations, rule, 1);
    }

}
