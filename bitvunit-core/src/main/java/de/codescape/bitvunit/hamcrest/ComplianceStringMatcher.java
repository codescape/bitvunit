package de.codescape.bitvunit.hamcrest;

import com.gargoylesoftware.htmlunit.html.HtmlPage;
import de.codescape.bitvunit.rule.Rule;
import de.codescape.bitvunit.ruleset.RuleSet;
import org.hamcrest.Factory;
import org.hamcrest.Matcher;

import static de.codescape.bitvunit.util.HtmlPageUtil.htmlPageFromString;

public class ComplianceStringMatcher extends AbstractComplianceMatcher<String> {


    private ComplianceStringMatcher(Rule rule) {
        super(rule);
    }

    private ComplianceStringMatcher(RuleSet ruleSet) {
        super(ruleSet);
    }

    @Override
    protected HtmlPage getAsHtmlPage(String htmlPage) {
        return htmlPageFromString(htmlPage);
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
