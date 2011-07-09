package de.codescape.bitvunit.hamcrest;

import com.gargoylesoftware.htmlunit.html.HtmlPage;
import de.codescape.bitvunit.rule.Rule;
import de.codescape.bitvunit.ruleset.BasicRuleSet;
import de.codescape.bitvunit.ruleset.RuleSet;
import org.hamcrest.Description;
import org.hamcrest.TypeSafeMatcher;

public abstract class AbstractComplianceMatcher<T> extends TypeSafeMatcher<T> {

    private final RuleSet ruleSet;

    protected AbstractComplianceMatcher(Rule rule) {
        super();
        this.ruleSet = new BasicRuleSet(rule);
    }

    protected AbstractComplianceMatcher(RuleSet ruleSet) {
        super();
        this.ruleSet = ruleSet;
    }

    @Override
    protected final boolean matchesSafely(T item) {
        return !ruleSet.applyTo(getAsHtmlPage(item)).hasViolations();
    }

    protected abstract HtmlPage getAsHtmlPage(T htmlPage);

    @Override
    public final void describeTo(Description description) {
        description.appendText("compliant to ").appendText(ruleSet.toString());
    }

}
