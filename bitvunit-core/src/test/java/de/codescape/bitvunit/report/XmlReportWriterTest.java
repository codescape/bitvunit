package de.codescape.bitvunit.report;


import org.custommonkey.xmlunit.XMLUnit;
import org.custommonkey.xmlunit.XpathEngine;
import org.junit.Test;
import org.w3c.dom.Document;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class XmlReportWriterTest extends AbstractReportWriterTest {

    @Test
    public void writesToConsole() throws Exception {
        XmlReportWriter writer = new XmlReportWriter();
        writer.setWriteToFile(false);

        writer.writeReport(someHtmlPage(), someRuleSet(), someViolations());

        assertNotNull(getConsoleOutput());

        Document xml = XMLUnit.buildControlDocument(getConsoleOutput());
        XpathEngine xpath = XMLUnit.newXpathEngine();

        assertEquals(1, xpath.getMatchingNodes("//BitvUnit/Report", xml).getLength());
        assertEquals(someViolations().size(), xpath.getMatchingNodes("//BitvUnit/Violations/Violation", xml).getLength());
        assertEquals(someRuleSet().getRules().size(), xpath.getMatchingNodes("//BitvUnit/Rules/Rule", xml).getLength());
    }

    @Test
    public void writesToFile() throws Exception {
        XmlReportWriter writer = new XmlReportWriter();
        writer.setWriteToFile(true);

        writer.writeReport(someHtmlPage(), someRuleSet(), someViolations());

        assertNotNull(getFileOutput(writer.getOutputFilename()));

        Document xml = XMLUnit.buildControlDocument(getFileOutput(writer.getOutputFilename()));
        XpathEngine xpath = XMLUnit.newXpathEngine();

        assertEquals(1, xpath.getMatchingNodes("//BitvUnit/Report", xml).getLength());
        assertEquals(someViolations().size(), xpath.getMatchingNodes("//BitvUnit/Violations/Violation", xml).getLength());
        assertEquals(someRuleSet().getRules().size(), xpath.getMatchingNodes("//BitvUnit/Rules/Rule", xml).getLength());
    }

}
