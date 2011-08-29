package de.codescape.bitvunit.report;

import com.gargoylesoftware.htmlunit.html.HtmlPage;
import de.codescape.bitvunit.rule.Violations;
import de.codescape.bitvunit.ruleset.RuleSet;
import de.codescape.bitvunit.util.io.ClassPathResource;

import java.io.*;
import java.text.DateFormat;
import java.util.Date;

/**
 * Abstract class that should be extended by all {@link ReportWriter} implementations to support writing reports to
 * files and writing to console.
 *
 * @author Stefan Glase
 */
public abstract class AbstractReportWriter implements ReportWriter {

    protected static final String DEFAULT_FILENAME = "BitvUnitReport";

    private static final String VERSION_FILE = "bitvunit-version.txt";

    private boolean writeToFile = false;

    /**
     * Set this to <code>true</code> to write reports as files or to <code>false</code> to write to console.
     *
     * @param writeToFile <code>true</code> to write reports as files or to <code>false</code> to write to console
     */
    public void setWriteToFile(boolean writeToFile) {
        this.writeToFile = writeToFile;
    }

    @Override
    public void writeReport(HtmlPage htmlPage, RuleSet ruleSet, Violations violations) {
        if (writeToFile) {
            writeReportToFile(htmlPage, ruleSet, violations);
        } else {
            writeReportToConsole(htmlPage, ruleSet, violations);
        }
    }

    private void writeReportToFile(HtmlPage htmlPage, RuleSet ruleSet, Violations violations) {
        try {
            writeReport(new FileWriter(new File(getOutputFilename())), htmlPage, ruleSet, violations);
        } catch (IOException e) {
            throw new RuntimeException("Could not write report to file.", e);
        }
    }

    private void writeReportToConsole(HtmlPage htmlPage, RuleSet ruleSet, Violations violations) {
        writeReport(new PrintWriter(System.out), htmlPage, ruleSet, violations);
    }

    /**
     * Returns the filename to be used for the report.
     *
     * @return filename to be used for the report
     */
    protected abstract String getOutputFilename();

    /**
     * Writes the report for the <code>htmlPage</code> checked against the <code>ruleSet</code> and with the
     * <code>violations</code> result to the <code>writer</code>.
     *
     * @param writer     {@link Writer} that is used to write the report
     * @param htmlPage   {@link HtmlPage} that was checked
     * @param ruleSet    {@link RuleSet} that was used
     * @param violations {@link Violations} that were found
     */
    protected abstract void writeReport(Writer writer, HtmlPage htmlPage, RuleSet ruleSet, Violations violations);

    /**
     * Returns the current date and time as a String to be used in reports.
     *
     * @return current date and time as a String to be used in reports
     */
    protected String getFormattedDate() {
        return DateFormat.getDateTimeInstance().format(new Date());
    }

    /**
     * Returns the version of the BitvUnit framework in use.
     *
     * @return version of the BitvUnit framework in use
     */
    protected String getBitvUnitVersion() {
        return ClassPathResource.asString(VERSION_FILE);
    }

}
