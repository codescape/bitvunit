package de.codescape.bitvunit.report;

import org.junit.Test;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

public class TextReportWriterTest extends AbstractReportWriterTest {

    @Test
    public void shouldBeAbleToGetBitvUnitVersionInUse() throws Exception {
        assertNotNull(new TextReportWriter().getBitvUnitVersion());
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

}
