package de.codescape.bitvunit.report;

import com.gargoylesoftware.htmlunit.html.HtmlPage;
import de.codescape.bitvunit.rule.Violations;
import de.codescape.bitvunit.ruleset.RuleSet;

import java.io.Writer;

/**
 * Implementation of {@link ReportWriter} that does not write reports.
 *
 * @author Stefan Glase
 * @since 0.9
 */
public class VoidReportWriter extends AbstractReportWriter {

    /**
     * Creates a new {@link VoidReportWriter}.
     */
    public VoidReportWriter() {
        setWriteToFile(false);
    }

    @Override
    protected String getOutputFilename() {
        // no filename required
        return "";
    }

    @Override
    protected void writeReport(Writer writer, HtmlPage htmlPage, RuleSet ruleSet, Violations violations) {
        // do not write any reports
    }

    @Override
    public void setWriteToFile(boolean writeToFile) {
        // always write to console to prevent creation of new files
        super.setWriteToFile(false);
    }

}
