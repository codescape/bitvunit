package de.codescape.bitvunit.hamcrest;

import de.codescape.bitvunit.rule.Rule;
import de.codescape.bitvunit.ruleset.BasicRuleSet;
import de.codescape.bitvunit.ruleset.RuleSet;
import org.hamcrest.Description;
import org.hamcrest.Factory;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;

import static de.codescape.bitvunit.util.HtmlPageUtil.htmlPageFromString;

public class ComplianceStringMatcher extends TypeSafeMatcher<String> {

    private final RuleSet ruleSet;

    private ComplianceStringMatcher(Rule rule) {
        super();
        this.ruleSet = new BasicRuleSet(rule);
    }

    private ComplianceStringMatcher(RuleSet ruleSet) {
        super();
        this.ruleSet = ruleSet;
    }

    @Override
    protected boolean matchesSafely(String item) {
        return !ruleSet.applyTo(htmlPageFromString(item)).hasViolations();
    }

    @Override
    public void describeTo(Description description) {
        description.appendText("compliant to ").appendText(ruleSet.toString());
    }

    @Factory
    public static Matcher<String> compliantTo(Rule rule) {
        return new ComplianceStringMatcher(rule);
    }

    @Factory
    public static Matcher<String> compliantTo(RuleSet ruleSet) {
        return new ComplianceStringMatcher(ruleSet);
    }

}
