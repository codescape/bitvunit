package de.codescape.bitvunit.ruleset;

import com.gargoylesoftware.htmlunit.html.HtmlPage;
import de.codescape.bitvunit.rule.Rule;
import de.codescape.bitvunit.rule.Violations;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class BasicRuleSet implements RuleSet {

    private List<Rule> rules = new ArrayList<Rule>();

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
        StringBuilder sb = new StringBuilder("RuleSet with rules:");
        for (Rule rule : rules) {
            sb.append("\n").append(rule);
        }
        return sb.toString();
    }

}
