package de.codescape.bitvunit.report;

import com.gargoylesoftware.htmlunit.html.HtmlPage;
import de.codescape.bitvunit.rule.Rule;
import de.codescape.bitvunit.rule.Violation;
import de.codescape.bitvunit.rule.Violations;
import de.codescape.bitvunit.ruleset.RuleSet;

import java.io.PrintWriter;
import java.io.Writer;

/**
 * Implementation of {@link ReportWriter} that writes XML based reports.
 *
 * @author Stefan Glase
 * @since 0.5
 */
public class XmlReportWriter extends AbstractReportWriter {

    /**
     * Creates a new {@link XmlReportWriter} that writes to file per default.
     */
    public XmlReportWriter() {
        setWriteToFile(true);
    }

    @Override
    protected String getOutputFilename() {
        return DEFAULT_FILENAME + ".xml";
    }

    @Override
    protected void writeReport(Writer writer, HtmlPage htmlPage, RuleSet ruleSet, Violations violations) {
        PrintWriter out = new PrintWriter(writer);

        printHeader(htmlPage, out);
        printViolations(violations, out);
        printRules(ruleSet, out);
        printFooter(out);

        out.flush();
    }

    /**
     * Writes the header.
     *
     * @param htmlPage {@link HtmlPage} that was inspected
     * @param out      target where the report is written to
     */
    private void printHeader(HtmlPage htmlPage, PrintWriter out) {
        out.println("<?xml version='1.0'?>");
        out.println("<BitvUnit version='" + getBitvUnitVersion() + "'>");
        out.println("<Report time='" + getFormattedDate() + "' url='" + htmlPage.getUrl().toString() + "'/>");
    }

    /**
     * Writes the part where all {@link Violations} that were found are listed.
     *
     * @param violations {@link Violations} that were found
     * @param out        target where the report is written to
     */
    private void printViolations(Violations violations, PrintWriter out) {
        out.println("<Violations>");
        for (Violation violation : violations.asList()) {
            out.println("<Violation ruleName='" + violation.getRule().getName() + "' position='" + violation.getPosition() + "'>");
            out.println("<Message><![CDATA[" + violation.getMessage() + "]]></Message>");
            out.println("</Violation>");
        }
        out.println("</Violations>");
    }

    /**
     * Writes the part where all used rules from the {@link RuleSet} are listed.
     *
     * @param ruleSet {@link RuleSet} that was used
     * @param out     target where the report is written to
     */
    private void printRules(RuleSet ruleSet, PrintWriter out) {
        out.println("<Rules>");
        for (Rule rule : ruleSet.getRules()) {
            out.println("<Rule name='" + rule.getName() + "' priority='" + rule.getPriority() + "'/>");
        }
        out.println("</Rules>");
    }

    /**
     * Writes the footer.
     *
     * @param out target where the report is written to
     */
    private void printFooter(PrintWriter out) {
        out.println("</BitvUnit>");
    }

}
