package de.codescape.bitvunit.report;

import com.gargoylesoftware.htmlunit.html.HtmlPage;
import de.codescape.bitvunit.rule.Violations;
import de.codescape.bitvunit.ruleset.RuleSet;

/**
 * Common interface for any class that writes out reports for this library.
 *
 * @author Stefan Glase
 */
public interface ReportWriter {

    /**
     * Writes the report for the <code>htmlPage</code> checked against the <code>ruleSet</code> and with the
     * <code>violations</code> result.
     *
     * @param htmlPage   {@link HtmlPage} that was checked
     * @param ruleSet    {@link RuleSet} that was used
     * @param violations {@link Violations} that were found
     */
    void writeReport(HtmlPage htmlPage, RuleSet ruleSet, Violations violations);

}
