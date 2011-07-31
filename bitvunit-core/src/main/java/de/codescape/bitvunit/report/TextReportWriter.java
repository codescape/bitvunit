package de.codescape.bitvunit.report;

import com.gargoylesoftware.htmlunit.html.HtmlPage;
import de.codescape.bitvunit.rule.Rule;
import de.codescape.bitvunit.rule.Violation;
import de.codescape.bitvunit.rule.Violations;
import de.codescape.bitvunit.ruleset.RuleSet;

import java.io.PrintWriter;
import java.io.Writer;

/**
 * Implementation of {@link ReportWriter} that writes text only based reports.
 *
 * @author Stefan Glase
 */
public class TextReportWriter extends AbstractReportWriter {

    public TextReportWriter() {
        setWriteToFile(false);
    }

    @Override
    protected String getOutputFilename() {
        return DEFAULT_FILENAME + ".txt";
    }

    @Override
    public void writeReport(Writer writer, HtmlPage htmlPage, RuleSet ruleSet, Violations violations) {
        PrintWriter out = new PrintWriter(writer);

        printTitle(htmlPage, out);
        printViolations(violations, out);
        printRules(ruleSet, out);

        out.flush();
    }

    /**
     * Writes the header.
     *
     * @param htmlPage {@link HtmlPage} that was inspected
     * @param out      target where the report is written to
     */
    private void printTitle(HtmlPage htmlPage, PrintWriter out) {
        out.println(String.format("BitvUnit %s Report - %s - %s", getBitvUnitVersion(), htmlPage.getUrl(), getFormattedDate()));
    }

    /**
     * Writes the part where all used rules from the {@link RuleSet} are listed.
     *
     * @param ruleSet {@link RuleSet} that was used
     * @param out     target where the report is written to
     */
    private void printRules(RuleSet ruleSet, PrintWriter out) {
        out.println("Rules checked:");
        for (Rule rule : ruleSet.getRules()) {
            out.println(" - " + rule);
        }
    }

    /**
     * Writes the part where all {@link Violations} that were found are listed.
     *
     * @param violations {@link Violations} that were found
     * @param out        target where the report is written to
     */
    private void printViolations(Violations violations, PrintWriter out) {
        out.println("Violations found:");
        if (violations.hasViolations()) {
            for (Violation violation : violations.asList()) {
                out.println(" - " + violation);
            }
        } else {
            out.println(" - No violations found.");
        }
    }

}
