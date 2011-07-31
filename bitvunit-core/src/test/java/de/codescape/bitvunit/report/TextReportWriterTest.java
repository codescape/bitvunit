package de.codescape.bitvunit.report;

import com.gargoylesoftware.htmlunit.html.HtmlPage;
import de.codescape.bitvunit.rule.Rule;
import de.codescape.bitvunit.rule.Violation;
import de.codescape.bitvunit.rule.Violations;
import de.codescape.bitvunit.ruleset.RuleSet;
import de.codescape.bitvunit.ruleset.XmlRuleSet;
import org.apache.commons.io.IOUtils;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.FileReader;
import java.io.OutputStream;
import java.io.PrintStream;

import static de.codescape.bitvunit.util.HtmlPageUtil.toHtmlPage;
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
        assertTrue("Should contain header", getConsoleOutput().contains("BitvUnit"));
        assertTrue("Should contain violations", getConsoleOutput().contains("Violations found:"));
        assertTrue("Should contain rules", getConsoleOutput().contains("Rules checked:"));
    }

    @Test
    public void writesToFile() throws Exception {
        TextReportWriter writer = new TextReportWriter();
        writer.setWriteToFile(true);

        writer.writeReport(someHtmlPage(), someRuleSet(), someViolations());

        String fileOutput = getFileOutput(writer.getOutputFilename());
        assertTrue("Should contain header", fileOutput.contains("BitvUnit"));
        assertTrue("Should contain violations", fileOutput.contains("Violations found:"));
        assertTrue("Should contain rules", fileOutput.contains("Rules checked:"));
    }

    private String getConsoleOutput() {
        return consoleOutput.toString();
    }

    private String getFileOutput(String filename) throws Exception {
        return IOUtils.toString(new FileReader(filename));
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
        return toHtmlPage("<html><head><title>Hello World</title></head><body></body></html>");
    }

}
