package de.codescape.bitvunit.report;

import org.junit.Test;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

public class TextReportWriterTest extends AbstractReportWriterTest {

    @Test
    public void shouldBeAbleToGetFrameworkVersion() throws Exception {
        assertNotNull(new TextReportWriter().getBitvUnitVersion());
    }

    @Test
    public void writesToConsole() throws Exception {
        TextReportWriter writer = new TextReportWriter();
        writer.setWriteToFile(false);

        writer.writeReport(someHtmlPage(), someRuleSet(), someViolations());

        validateOutput(getConsoleOutput());
    }

    @Test
    public void writesToFile() throws Exception {
        TextReportWriter writer = new TextReportWriter();
        writer.setWriteToFile(true);

        writer.writeReport(someHtmlPage(), someRuleSet(), someViolations());

        validateOutput(getFileOutput(writer.getOutputFilename()));
    }

    private void validateOutput(String textString) {
        assertNotNull(textString);
        assertTrue("Should contain header", textString.contains("BitvUnit"));
        assertTrue("Should contain violations", textString.contains("Violations found:"));
        assertTrue("Should contain rules", textString.contains("Rules checked:"));
    }

}
