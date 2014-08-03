package de.codescape.bitvunit;

import com.gargoylesoftware.htmlunit.html.HtmlPage;
import de.codescape.bitvunit.rule.Violations;

/**
 * Common interface for {@link de.codescape.bitvunit.rule.Rule} and {@link de.codescape.bitvunit.ruleset.RuleSet} that
 * can both be used to verify a given {@link com.gargoylesoftware.htmlunit.html.HtmlPage} against.
 *
 * @author Stefan Glase
 * @since 0.13
 */
public interface Testable {

    /**
     * Applies the provided rule or set of rules to the given {@link com.gargoylesoftware.htmlunit.html.HtmlPage} and
     * returns all {@link de.codescape.bitvunit.rule.Violations} against the rules provided.
     *
     * @param htmlPage {@link com.gargoylesoftware.htmlunit.html.HtmlPage} under test
     * @return all {@link de.codescape.bitvunit.rule.Violations} against the rules provided
     */
    Violations applyTo(HtmlPage htmlPage);

}
