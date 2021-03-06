package de.codescape.bitvunit.ruleset;

import com.gargoylesoftware.htmlunit.html.HtmlPage;
import de.codescape.bitvunit.rule.Rule;
import de.codescape.bitvunit.rule.Violations;
import org.apache.commons.lang.StringUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static de.codescape.bitvunit.report.ReportingContext.getReportWriter;

/**
 * Basic implementation of a {@link RuleSet} that provides some convenience methods. This class should normally be
 * extended by other {@link RuleSet} implementations.
 *
 * @author Stefan Glase
 */
public class BasicRuleSet implements RuleSet {

    private final List<Rule> rules = new ArrayList<>();

    /**
     * Create a BasicRuleSet from the optionally provided list of {@link Rule} instances.
     *
     * @param rules optional list of {@link Rule} instances
     */
    public BasicRuleSet(Rule... rules) {
        this.rules.addAll(Arrays.asList(rules));
    }

    /**
     * Adds the given {@link Rule} to that rule set.
     *
     * @param rule {@link Rule} to be added to the rule set
     */
    public void addRule(Rule rule) {
        rules.add(rule);
    }

    /**
     * Adds the given {@link Rule} to that rule set and returns itself.
     *
     * @param rule {@link Rule} to be added to the rule set
     * @return {@link RuleSet} itself
     */
    public BasicRuleSet withRule(Rule rule) {
        addRule(rule);
        return this;
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
        getReportWriter().writeReport(htmlPage, this, violations);
        return violations;
    }

    /**
     * Returns the name of the rule set and a comma separated list of the names of all contained rules.
     *
     * @return name of the rule set and a comma separated list of the names of all contained rules
     */
    @Override
    public String toString() {
        return getClass().getSimpleName() + "[" + (rules.isEmpty() ? "<Empty RuleSet>" : StringUtils.join(collectRuleNames(), ", ")) + "]";
    }

    /**
     * Returns a list of the names of all rules in this {@link RuleSet} sorted by name.
     *
     * @return names of all rules in this {@link RuleSet} sorted by name
     */
    private List<String> collectRuleNames() {
        List<String> ruleNames = new ArrayList<>();
        for (Rule rule : rules) {
            ruleNames.add(rule.getName());
        }
        Collections.sort(ruleNames);
        return ruleNames;
    }

}
