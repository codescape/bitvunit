package de.codescape.bitvunit.rule.frames;

import de.codescape.bitvunit.rule.Violations;
import org.junit.Test;

import static de.codescape.bitvunit.test.Assertions.assertNoViolations;
import static de.codescape.bitvunit.test.Assertions.assertViolations;
import static de.codescape.bitvunit.util.HtmlPageUtil.htmlPageFromString;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class TitleForFrameRuleTest {

    private TitleForFrameRule rule = new TitleForFrameRule();

    @Test
    public void ruleHasAName() throws Exception {
        assertNotNull(rule.getName());
        assertEquals("TitleForFrame", rule.getName());
    }

    @Test
    public void frameWithNoTitlePresent() throws Exception {
        String content = "<html><frameset><frame src=\"inner.htm\" /></frameset></html>";
        Violations violations = rule.applyTo(htmlPageFromString(content));
        assertViolations(violations, rule, 1);
    }

    @Test
    public void twoFramesWithNoTitlePresent() throws Exception {
        String content = "<html><frameset cols=\"250,*\"><frame src=\"menu.htm\" /><frame src=\"main.htm\" /></frameset></html>";
        Violations violations = rule.applyTo(htmlPageFromString(content));
        assertViolations(violations, rule, 2);
    }

    @Test
    public void frameWithTitlePresent() throws Exception {
        String content = "<html><frameset><frame src=\"navigation.htm\" title=\"Navigation\" /></frameset></html>";
        Violations violations = rule.applyTo(htmlPageFromString(content));
        assertNoViolations(violations, rule);
    }

}
