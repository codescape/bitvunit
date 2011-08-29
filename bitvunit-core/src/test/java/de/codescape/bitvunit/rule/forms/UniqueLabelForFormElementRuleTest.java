package de.codescape.bitvunit.rule.forms;

import de.codescape.bitvunit.rule.Violations;
import org.junit.Test;

import static de.codescape.bitvunit.test.Assertions.assertNoViolations;
import static de.codescape.bitvunit.test.Assertions.assertViolations;
import static de.codescape.bitvunit.util.html.HtmlPageUtil.toHtmlPage;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class UniqueLabelForFormElementRuleTest {

    private final UniqueLabelForFormElementRule rule = new UniqueLabelForFormElementRule();

    @Test
    public void ruleHasAName() throws Exception {
        assertNotNull(rule.getName());
        assertEquals("UniqueLabelForFormElement", rule.getName());
    }

    @Test
    public void singleLabelForFormElement() throws Exception {
        String content = "<html>" +
                "<body>" +
                "  <form>" +
                "    <label for=\"username\">Username</label>" +
                "    <input type=\"text\" name=\"username\" id=\"username\" />" +
                "  </form>" +
                "</body>" +
                "</html>";
        Violations violations = rule.applyTo(toHtmlPage(content));
        assertNoViolations(violations, rule);
    }

    @Test
    public void twoLabelsForTwoDifferentFormElements() throws Exception {
        String content = "<html>" +
                "<body>" +
                "  <form>" +
                "    <label for=\"username\">Username</label>" +
                "    <input type=\"text\" name=\"username\" id=\"username\" />" +
                "    <label for=\"password\">Password</label>" +
                "    <input type=\"password\" name=\"password\" id=\"password\" />" +
                "  </form>" +
                "</body>" +
                "</html>";
        Violations violations = rule.applyTo(toHtmlPage(content));
        assertNoViolations(violations, rule);
    }

    @Test
    public void duplicateLabelForFormElement() throws Exception {
        String content = "<html>" +
                "<body>" +
                "  <form>" +
                "    <label for=\"username\">Username</label>" +
                "    <input type=\"text\" name=\"username\" id=\"username\" />" +
                "    <label for=\"username\">Your Username</label>" +
                "  </form>" +
                "</body>" +
                "</html>";
        Violations violations = rule.applyTo(toHtmlPage(content));
        assertViolations(violations, rule, 2);
    }

}
