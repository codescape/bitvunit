package de.codescape.bitvunit.rule;

import com.gargoylesoftware.htmlunit.html.HtmlPage;

/**
 * Interface that must be implemented by all rules that should be checked with this framework. You should always extend
 * {@link AbstractRule} which gives you some convenience functionality instead of implementing this interface directly.
 *
 * @author Stefan Glase
 */
public interface Rule {

    /**
     * Returns the name of that rule.
     *
     * @return name of that rule
     */
    String getName();

    /**
     * Applies that rule to the given {@link HtmlPage} and returns all {@link Violations} of that rule.
     *
     * @param htmlPage {@link HtmlPage} under test
     * @return all {@link Violations} of that rule
     */
    Violations applyTo(HtmlPage htmlPage);

}
