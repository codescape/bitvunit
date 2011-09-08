package de.codescape.bitvunit.rule.text;

import de.codescape.bitvunit.rule.Violations;
import org.junit.Test;

import static de.codescape.bitvunit.test.Assertions.assertNoViolations;
import static de.codescape.bitvunit.test.Assertions.assertViolations;
import static de.codescape.bitvunit.util.html.HtmlPageUtil.toHtmlPage;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class HeadingContainsTextRuleTest {

    private HeadingContainsTextRule rule = new HeadingContainsTextRule();

    @Test
    public void ruleHasAName() throws Exception {
        assertNotNull(rule.getName());
        assertEquals("HeadingContainsText", rule.getName());
    }

    @Test
    public void headingsWithoutTextPresent() throws Exception {
        String content = "<html><body>" +
                "<h1></h1>" +
                "<h2></h2>" +
                "<h3></h3>" +
                "<h4></h4>" +
                "<h5></h5>" +
                "<h6></h6>" +
                "</body></html>";
        Violations violations = rule.applyTo(toHtmlPage(content));
        assertViolations(violations, rule, 6);
    }

    @Test
    public void headingsWithoutWhitespaceOnlyTextPresent() throws Exception {
        String content = "<html><body>" +
                "<h1> </h1>" +
                "<h2>  </h2>" +
                "<h3>   </h3>" +
                "<h4>    </h4>" +
                "<h5>     </h5>" +
                "<h6>      </h6>" +
                "</body></html>";
        Violations violations = rule.applyTo(toHtmlPage(content));
        assertViolations(violations, rule, 6);
    }

    @Test
    public void headingsWithTextPresent() throws Exception {
        String content = "<html><body>" +
                "<h1>first heading</h1>" +
                "<h2>second heading</h2>" +
                "<h3>third heading</h3>" +
                "<h4>fourth heading</h4>" +
                "<h5>fifth heading</h5>" +
                "<h6>sixth heading</h6>" +
                "</body></html>";
        Violations violations = rule.applyTo(toHtmlPage(content));
        assertNoViolations(violations, rule);
    }

}
