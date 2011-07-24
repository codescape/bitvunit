package de.codescape.bitvunit.report;

import com.gargoylesoftware.htmlunit.html.HtmlPage;
import de.codescape.bitvunit.rule.Violations;
import de.codescape.bitvunit.ruleset.RuleSet;

import java.io.PrintWriter;
import java.io.Writer;
import java.text.DateFormat;
import java.util.Date;

/**
 * Abstract class that should be extended by all {@link ReportWriter} implementations.
 *
 * @author Stefan Glase
 */
public abstract class AbstractReportWriter implements ReportWriter {

    private boolean writeToFile = false;

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
        // TODO implement writing reports to file
    }

    private void writeReportToConsole(HtmlPage htmlPage, RuleSet ruleSet, Violations violations) {
        writeReport(new PrintWriter(System.out), htmlPage, ruleSet, violations);
    }

    protected abstract void writeReport(Writer writer, HtmlPage htmlPage, RuleSet ruleSet, Violations violations);

    protected String getFormattedDate() {
        return DateFormat.getDateTimeInstance().format(new Date());
    }

}
