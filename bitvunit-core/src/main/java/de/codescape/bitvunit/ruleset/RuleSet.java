package de.codescape.bitvunit.ruleset;

import de.codescape.bitvunit.Testable;
import de.codescape.bitvunit.rule.Rule;

import java.util.List;

/**
 * Interface that must be implemented by all rule sets that should be checked with this library. You should always
 * extend {@link BasicRuleSet} which gives you some convenience functionality instead of implementing this interface
 * directly.
 *
 * @author Stefan Glase
 */
public interface RuleSet extends Testable {

    /**
     * Returns a list of all rules contained in that rule set.
     *
     * @return list of all rules contained in that rule set
     */
    List<Rule> getRules();

}
