package de.codescape.bitvunit.hamcrest;

import com.gargoylesoftware.htmlunit.html.HtmlPage;
import de.codescape.bitvunit.rule.Rule;
import de.codescape.bitvunit.ruleset.RuleSet;
import org.hamcrest.Factory;
import org.hamcrest.Matcher;

public class ComplianceHtmlPageMatcher extends AbstractComplianceMatcher<HtmlPage> {

    private ComplianceHtmlPageMatcher(Rule rule) {
        super(rule);
    }

    private ComplianceHtmlPageMatcher(RuleSet ruleSet) {
        super(ruleSet);
    }

    @Override
    protected HtmlPage getAsHtmlPage(HtmlPage htmlPage) {
        return htmlPage;
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
