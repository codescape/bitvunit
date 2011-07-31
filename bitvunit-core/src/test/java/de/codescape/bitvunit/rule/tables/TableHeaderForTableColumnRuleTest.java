package de.codescape.bitvunit.rule.tables;

import de.codescape.bitvunit.rule.Violations;
import org.junit.Test;

import static de.codescape.bitvunit.test.Assertions.assertNoViolations;
import static de.codescape.bitvunit.test.Assertions.assertViolations;
import static de.codescape.bitvunit.util.HtmlPageUtil.toHtmlPage;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class TableHeaderForTableColumnRuleTest {

    private final TableHeaderForTableColumnRule rule = new TableHeaderForTableColumnRule();

    @Test
    public void ruleHasAName() throws Exception {
        assertNotNull(rule.getName());
        assertEquals("TableHeaderForTableColumn", rule.getName());
    }

    @Test
    public void tableWithTableHeadersPresent() throws Exception {
        String content = "<html>" +
                "<body>" +
                "  <table>" +
                "    <tr>" +
                "      <th>First row</th>" +
                "      <th>Second row</th>" +
                "    </tr>" +
                "    <tr>" +
                "      <td>Some value</td>" +
                "      <td>Another value</td>" +
                "    </tr>" +
                "  </table>" +
                "</body>" +
                "</html>";
        Violations violations = rule.applyTo(toHtmlPage(content));
        assertNoViolations(violations, rule);
    }

    @Test
    public void tableWithTableHeadersMissing() throws Exception {
        String content = "<html>" +
                "<body>" +
                "  <table>" +
                "    <tr>" +
                "      <td>Some value</td>" +
                "      <td>Another value</td>" +
                "    </tr>" +
                "    <tr>" +
                "      <td>More values</td>" +
                "      <td>More values</td>" +
                "    </tr>" +
                "  </table>" +
                "</body>" +
                "</html>";
        Violations violations = rule.applyTo(toHtmlPage(content));
        assertViolations(violations, rule, 2);
    }

    @Test
    public void tableWithOnlyOneRow() throws Exception {
        String content = "<html>" +
                "<body>" +
                "  <table>" +
                "    <tr>" +
                "      <td>Some value</td>" +
                "      <td>Another value</td>" +
                "    </tr>" +
                "  </table>" +
                "</body>" +
                "</html>";
        Violations violations = rule.applyTo(toHtmlPage(content));
        assertViolations(violations, rule, 1);
    }

}
