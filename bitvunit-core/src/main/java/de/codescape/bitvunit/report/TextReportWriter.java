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
    public void writeReport(Writer writer, HtmlPage htmlPage, RuleSet ruleSet, Violations violations) {
        PrintWriter out = new PrintWriter(writer);

        printTitle(htmlPage, out);
        printViolations(violations, out);
        printRules(ruleSet, out);
        out.flush();
    }

    private void printTitle(HtmlPage htmlPage, PrintWriter out) {
        out.println("BitvUnit Report - " + htmlPage.getUrl() + " - " + getFormattedDate());
    }

    private void printRules(RuleSet ruleSet, PrintWriter out) {
        out.println("Rules checked:");
        for (Rule rule : ruleSet.getRules()) {
            out.println(" - " + rule);
        }
    }

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
