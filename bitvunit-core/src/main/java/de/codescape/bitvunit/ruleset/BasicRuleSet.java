package de.codescape.bitvunit.ruleset;

import com.gargoylesoftware.htmlunit.html.HtmlPage;
import de.codescape.bitvunit.rule.Rule;
import de.codescape.bitvunit.rule.Violations;
import org.apache.commons.lang.StringUtils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Basic implementation of a {@link RuleSet} that provides some convenience methods. This class should normally be
 * extended by other {@link RuleSet} implementations.
 */
public class BasicRuleSet implements RuleSet {

    private List<Rule> rules = new ArrayList<Rule>();

    /**
     * Adds the given {@link Rule} to that rule set.
     *
     * @param rule {@link Rule} to be added to the rule set
     */
    public void addRule(Rule rule) {
        rules.add(rule);
    }

    @Override
    public List<Rule> getRules() {
        return Collections.unmodifiableList(rules);
    }

    @Override
    public Violations applyTo(HtmlPage htmlPage) {
        Violations violations = new Violations();
        for (Rule rule : rules) {
            violations.addAll(rule.applyTo(htmlPage));
        }
        return violations;
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + "[" + (rules.isEmpty() ? "<Empty RuleSet>" : StringUtils.join(collectRuleNames(), ", ")) + "]";
    }

    private List<String> collectRuleNames() {
        List<String> ruleNames = new ArrayList<String>();
        for (Rule rule : rules) {
            ruleNames.add(rule.getName());
        }
        Collections.sort(ruleNames);
        return ruleNames;
    }

}
