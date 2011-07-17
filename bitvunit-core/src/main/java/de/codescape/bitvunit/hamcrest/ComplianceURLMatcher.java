package de.codescape.bitvunit.hamcrest;

import com.gargoylesoftware.htmlunit.html.HtmlPage;
import de.codescape.bitvunit.rule.Rule;
import de.codescape.bitvunit.ruleset.RuleSet;
import org.hamcrest.Factory;
import org.hamcrest.Matcher;

import java.net.URL;

import static de.codescape.bitvunit.util.HtmlPageUtil.htmlPageFromURL;

public class ComplianceURLMatcher extends AbstractComplianceMatcher<URL> {

    protected ComplianceURLMatcher(Rule rule) {
        super(rule);
    }

    protected ComplianceURLMatcher(RuleSet ruleSet) {
        super(ruleSet);
    }

    @Override
    protected HtmlPage getAsHtmlPage(URL htmlPage) {
        return htmlPageFromURL(htmlPage);
    }

    @Factory
    public static Matcher<URL> compliantTo(Rule rule) {
        return new ComplianceURLMatcher(rule);
    }

    @Factory
    public static Matcher<URL> compliantTo(RuleSet ruleSet) {
        return new ComplianceURLMatcher(ruleSet);
    }

}
