package de.codescape.bitvunit.rule.images;

import de.codescape.bitvunit.rule.Violations;
import org.junit.Test;

import static de.codescape.bitvunit.test.Assertions.assertNoViolations;
import static de.codescape.bitvunit.test.Assertions.assertViolations;
import static de.codescape.bitvunit.test.HtmlPageCreator.createHtmlPage;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class AlternativeTextForAreaOfImageMapRuleTest {

    private AlternativeTextForAreaOfImageMapRule rule = new AlternativeTextForAreaOfImageMapRule();

    @Test
    public void ruleHasAName() throws Exception {
        assertNotNull(rule.getName());
        assertEquals("AlternativeTextForAreaOfImageMap", rule.getName());
    }

    @Test
    public void alternativeTextMissing() throws Exception {
        String content = "<html><body><img src=\"map.gif\" usemap=\"#mymap\"><map name=\"mymap\"><area shape=\"rect\" coords=\"0,0,10,10\" href=\"page2.htm\" /></map></body></html>";
        Violations violations = rule.applyTo(createHtmlPage(content));
        assertViolations(violations, rule, 1);
    }

    @Test
    public void alternativeTextPresent() throws Exception {
        String content = "<html><body><img src=\"map.gif\" usemap=\"#mymap\"><map name=\"mymap\"><area shape=\"rect\" coords=\"0,0,10,10\" href=\"page2.htm\" alt=\"Proceed to checkout\" /></map></body></html>";
        Violations violations = rule.applyTo(createHtmlPage(content));
        assertNoViolations(violations, rule);
    }

}
