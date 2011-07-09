package de.codescape.bitvunit.hamcrest;

import com.gargoylesoftware.htmlunit.html.HtmlPage;
import de.codescape.bitvunit.rule.Rule;
import de.codescape.bitvunit.ruleset.BasicRuleSet;
import de.codescape.bitvunit.ruleset.RuleSet;
import org.hamcrest.Description;
import org.hamcrest.Factory;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;

public class ComplianceHtmlPageMatcher extends TypeSafeMatcher<HtmlPage> {

    private final RuleSet ruleSet;

    private ComplianceHtmlPageMatcher(Rule rule) {
        super();
        this.ruleSet = new BasicRuleSet(rule);
    }

    private ComplianceHtmlPageMatcher(RuleSet ruleSet) {
        super();
        this.ruleSet = ruleSet;
    }

    @Override
    protected boolean matchesSafely(HtmlPage item) {
        return !ruleSet.applyTo(item).hasViolations();
    }

    @Override
    public void describeTo(Description description) {
        description.appendText("compliant to ").appendText(ruleSet.toString());
    }

    @Factory
    public static Matcher<HtmlPage> compliantTo(Rule rule) {
        return new ComplianceHtmlPageMatcher(rule);
    }

    @Factory
    public static Matcher<HtmlPage> compliantTo(RuleSet ruleSet) {
        return new ComplianceHtmlPageMatcher(ruleSet);
    }


}
