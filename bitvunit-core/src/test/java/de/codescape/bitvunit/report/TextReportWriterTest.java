package de.codescape.bitvunit.report;

import com.gargoylesoftware.htmlunit.html.HtmlPage;
import de.codescape.bitvunit.rule.Rule;
import de.codescape.bitvunit.rule.Violation;
import de.codescape.bitvunit.rule.Violations;
import de.codescape.bitvunit.ruleset.RuleSet;
import de.codescape.bitvunit.ruleset.XmlRuleSet;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.OutputStream;
import java.io.PrintStream;

import static de.codescape.bitvunit.util.HtmlPageUtil.htmlPageFromString;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

public class TextReportWriterTest {

    private OutputStream consoleOutput;
    private PrintStream originalConsole;

    @Before
    public void beforeTestsRedirectConsole() throws Exception {
        originalConsole = System.out;
        consoleOutput = new ByteArrayOutputStream();
        System.setOut(new PrintStream(consoleOutput));
    }

    @After
    public void afterTestsUndoConsoleRedirect() throws Exception {
        System.setOut(originalConsole);
    }

    @Test
    public void writesToConsole() throws Exception {
        TextReportWriter writer = new TextReportWriter();
        writer.setWriteToFile(false);

        writer.writeReport(someHtmlPage(), someRuleSet(), someViolations());

        assertNotNull(getConsoleOutput());
        assertTrue("Should contain header", getConsoleOutput().contains("BitvUnit Report"));
        assertTrue("Should contain violations", getConsoleOutput().contains("Violations found:"));
        assertTrue("Should contain rules", getConsoleOutput().contains("Rules checked:"));
    }

    private String getConsoleOutput() {
        return consoleOutput.toString();
    }

    private Violations someViolations() {
        Violations violations = new Violations();
        for (Rule rule : someRuleSet().getRules()) {
            violations.add(new Violation(rule, someHtmlPage().getBody(), "No empty body allowed!"));
        }
        return violations;
    }

    private RuleSet someRuleSet() {
        return new XmlRuleSet("/rulesets/all-rules.xml");
    }

    private HtmlPage someHtmlPage() {
        return htmlPageFromString("<html><head><title>Hello World</title></head><body></body></html>");
    }

}
