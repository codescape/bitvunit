package de.codescape.bitvunit.report;

import org.junit.Test;

import java.io.FileNotFoundException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

public class VoidReportWriterTest extends AbstractReportWriterTest {

    @Test
    public void shouldNotWriteToConsole() {
        VoidReportWriter writer = new VoidReportWriter();
        writer.setWriteToFile(false);

        writer.writeReport(someHtmlPage(), someRuleSet(), someViolations());

        assertEquals("", getConsoleOutput());
    }

    @Test(expected = FileNotFoundException.class)
    public void shouldNotWriteToFile() throws Exception {
        VoidReportWriter writer = new VoidReportWriter();
        writer.setWriteToFile(true);

        writer.writeReport(someHtmlPage(), someRuleSet(), someViolations());

        getFileOutput(writer.getOutputFilename());
        fail("Should not find the given file!");
    }

}
