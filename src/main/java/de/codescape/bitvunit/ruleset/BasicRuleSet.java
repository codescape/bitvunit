package de.codescape.bitvunit.ruleset;

import com.gargoylesoftware.htmlunit.html.HtmlPage;
import de.codescape.bitvunit.rule.Rule;
import de.codescape.bitvunit.rule.Violation;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class BasicRuleSet implements RuleSet {

    private List<Rule> rules = new ArrayList<Rule>();

    protected void addRule(Rule rule) {
        rules.add(rule);
    }

    @Override
    public List<Rule> getRules() {
        return Collections.unmodifiableList(rules);
    }

    @Override
    public List<Violation> applyTo(HtmlPage htmlPage) {
        List<Violation> violations = new ArrayList<Violation>();
        for (Rule rule : rules) {
            violations.addAll(rule.applyTo(htmlPage));
        }
        return violations;
    }

}
