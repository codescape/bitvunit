package de.codescape.bitvunit.rule.page;

import de.codescape.bitvunit.rule.Violations;
import org.junit.Test;

import static de.codescape.bitvunit.test.Assertions.assertNoViolations;
import static de.codescape.bitvunit.test.Assertions.assertViolations;
import static de.codescape.bitvunit.util.html.HtmlPageUtil.toHtmlPage;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class SingleTitlePerPageRuleTest {

    private SingleTitlePerPageRule rule = new SingleTitlePerPageRule();

    @Test
    public void ruleHasAName() throws Exception {
        assertNotNull(rule.getName());
        assertEquals("SingleTitlePerPage", rule.getName());
    }

    @Test
    public void multipleTitlesOnPage() throws Exception {
        String content = "<html><head>" +
                "   <title>First</title>" +
                "   <title>Second</title>" +
                "   <title>Third</title>" +
                "</head><body><p>text</p></body></html>";
        Violations violations = rule.applyTo(toHtmlPage(content));
        assertViolations(violations, rule, 3);
    }

    @Test
    public void missingTitleOnPage() throws Exception {
        String content = "<html><head></head><body><p>text</p></body></html>";
        Violations violations = rule.applyTo(toHtmlPage(content));
        assertViolations(violations, rule, 1);
    }

    @Test
    public void exactlyOneTitleOnPage() throws Exception {
        String content = "<html><head><title>Good title</title></head><body><p>text</p></body></html>";
        Violations violations = rule.applyTo(toHtmlPage(content));
        assertNoViolations(violations, rule);
    }

}
