package de.codescape.bitvunit.rule.page;

import de.codescape.bitvunit.rule.Violations;
import org.junit.Test;

import static de.codescape.bitvunit.test.Assertions.assertNoViolations;
import static de.codescape.bitvunit.test.Assertions.assertViolations;
import static de.codescape.bitvunit.util.html.HtmlPageUtil.toHtmlPage;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class TitleForPageRuleTest {

    private TitleForPageRule rule = new TitleForPageRule();

    @Test
    public void ruleHasAName() throws Exception {
        assertNotNull(rule.getName());
        assertEquals("TitleForPage", rule.getName());
    }

    @Test
    public void noTitleTagPresent() throws Exception {
        String content = "<html><head></head></html>";
        Violations violations = rule.applyTo(toHtmlPage(content));
        assertViolations(violations, rule, 1);
    }

    @Test
    public void emptyTitleTagPresent() throws Exception {
        String content = "<html><head><title></title></head></html>";
        Violations violations = rule.applyTo(toHtmlPage(content));
        assertViolations(violations, rule, 1);
    }

    @Test
    public void whitespaceOnlyTitleTagPresent() throws Exception {
        String content = "<html><head><title>   </title></head></html>";
        Violations violations = rule.applyTo(toHtmlPage(content));
        assertViolations(violations, rule, 1);
    }

    @Test
    public void noViolationRaisedByRule() throws Exception {
        String content = "<html><head><title>Hello World</title></head></html>";
        Violations violations = rule.applyTo(toHtmlPage(content));
        assertNoViolations(violations, rule);
    }

}
