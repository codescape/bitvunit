package de.codescape.bitvunit.report;

import com.gargoylesoftware.htmlunit.html.HtmlPage;
import de.codescape.bitvunit.rule.Rule;
import de.codescape.bitvunit.rule.Violation;
import de.codescape.bitvunit.rule.Violations;
import de.codescape.bitvunit.ruleset.RuleSet;

import java.io.PrintStream;

/**
 * Writes reports to the Console.
 */
public class ConsoleReportWriter extends AbstractReportWriter {

    @Override
    public void writeReport(HtmlPage htmlPage, RuleSet ruleSet, Violations violations) {
        PrintStream out = System.out;

        printTitle(htmlPage, out);
        printViolations(violations, out);
        printRules(ruleSet, out);
    }

    private void printTitle(HtmlPage htmlPage, PrintStream out) {
        out.println("BitvUnit Report - " + htmlPage.getUrl() + " - " + getFormattedDate());
    }

    private void printRules(RuleSet ruleSet, PrintStream out) {
        out.println("Rules checked:");
        for (Rule rule : ruleSet.getRules()) {
            out.println(" - " + rule);
        }
    }

    private void printViolations(Violations violations, PrintStream out) {
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
