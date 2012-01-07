package de.codescape.bitvunit.report;

import org.custommonkey.xmlunit.XMLUnit;
import org.custommonkey.xmlunit.XpathEngine;
import org.custommonkey.xmlunit.exceptions.XpathException;
import org.junit.Test;
import org.w3c.dom.Document;
import org.xml.sax.SAXException;

import java.io.IOException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class XmlReportWriterTest extends AbstractReportWriterTest {

    @Test
    public void writesToConsole() throws Exception {
        XmlReportWriter writer = new XmlReportWriter();
        writer.setWriteToFile(false);

        writer.writeReport(someHtmlPage(), someRuleSet(), someViolations());

        validateOutput(getConsoleOutput());
    }

    @Test
    public void writesToFile() throws Exception {
        XmlReportWriter writer = new XmlReportWriter();
        writer.setWriteToFile(true);

        writer.writeReport(someHtmlPage(), someRuleSet(), someViolations());

        validateOutput(getFileOutput(writer.getOutputFilename()));
    }

    private void validateOutput(String xmlString) throws SAXException, IOException, XpathException {
        assertNotNull(xmlString);

        Document xml = XMLUnit.buildControlDocument(xmlString);
        XpathEngine xpath = XMLUnit.newXpathEngine();

        shouldContainReportNode(xml, xpath);
        shouldListAllViolations(xml, xpath);
        shouldListAllRules(xml, xpath);
        shouldListPriorityForEveryRule(xml, xpath);
    }

    private void shouldContainReportNode(Document xml, XpathEngine xpath) throws XpathException {
        assertEquals(1, xpath.getMatchingNodes("//BitvUnit/Report", xml).getLength());
    }

    private void shouldListAllViolations(Document xml, XpathEngine xpath) throws XpathException {
        assertEquals(someViolations().size(), xpath.getMatchingNodes("//BitvUnit/Violations/Violation", xml).getLength());
    }

    private void shouldListAllRules(Document xml, XpathEngine xpath) throws XpathException {
        assertEquals(someRuleSet().getRules().size(), xpath.getMatchingNodes("//BitvUnit/Rules/Rule", xml).getLength());
    }

    private void shouldListPriorityForEveryRule(Document xml, XpathEngine xpath) throws XpathException {
        assertEquals(someRuleSet().getRules().size(), xpath.getMatchingNodes("//BitvUnit/Rules/Rule[@priority]", xml).getLength());
    }

}
