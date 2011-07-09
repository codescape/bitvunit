package de.codescape.bitvunit.hamcrest;

import com.gargoylesoftware.htmlunit.html.HtmlPage;
import de.codescape.bitvunit.rule.Rule;
import de.codescape.bitvunit.ruleset.RuleSet;
import org.hamcrest.Factory;
import org.hamcrest.Matcher;

import java.io.Reader;

import static de.codescape.bitvunit.util.HtmlPageUtil.htmlPageFromReader;

public class ComplianceReaderMatcher extends AbstractComplianceMatcher<Reader> {

    private ComplianceReaderMatcher(Rule rule) {
        super(rule);
    }

    private ComplianceReaderMatcher(RuleSet ruleSet) {
        super(ruleSet);
    }

    @Override
    protected HtmlPage getAsHtmlPage(Reader htmlPage) {
        return htmlPageFromReader(htmlPage);
    }

    @Factory
    public static Matcher<Reader> compliantTo(Rule rule) {
        return new ComplianceReaderMatcher(rule);
    }

    @Factory
    public static Matcher<Reader> compliantTo(RuleSet ruleSet) {
        return new ComplianceReaderMatcher(ruleSet);
    }

}
