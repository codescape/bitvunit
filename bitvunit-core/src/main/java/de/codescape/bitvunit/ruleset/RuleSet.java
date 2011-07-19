package de.codescape.bitvunit.ruleset;

import com.gargoylesoftware.htmlunit.html.HtmlPage;
import de.codescape.bitvunit.rule.Rule;
import de.codescape.bitvunit.rule.Violations;

import java.util.List;

/**
 * Interface that must be implemented by all rule sets that should be checked with this framework. You should always
 * extend {@link BasicRuleSet} which gives you some convenience functionality instead of implementing this interface
 * directly.
 *
 * @author Stefan Glase
 */
public interface RuleSet {

    /**
     * Returns a list of all rules contained in that rule set.
     *
     * @return list of all rules contained in that rule set
     */
    List<Rule> getRules();

    /**
     * Applies that rule set to the given {@link HtmlPage} and returns all {@link Violations} of the rules contained in
     * this rule set.
     *
     * @param htmlPage {@link HtmlPage} under test
     * @return all {@link Violations} of rules contained in this rule set
     */
    Violations applyTo(HtmlPage htmlPage);

}
