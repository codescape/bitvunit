package de.codescape.bitvunit.hamcrest;

import com.gargoylesoftware.htmlunit.html.HtmlPage;
import de.codescape.bitvunit.rule.Rule;
import org.hamcrest.Description;
import org.hamcrest.Factory;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;

/**
 * Hamcrest Matcher that checks a given HtmlPage for compliance to a specific Rule.
 *
 * @since 0.2
 */
public class RuleMatcher extends TypeSafeMatcher<HtmlPage> {

    private final Rule rule;

    public RuleMatcher(Rule rule) {
        this.rule = rule;
    }

    @Override
    protected boolean matchesSafely(HtmlPage htmlPage) {
        return rule.applyTo(htmlPage).hasViolations();
    }

    public void describeTo(Description description) {
        description.appendText("compliant to rule ").appendText(rule.toString());
    }

    @Override
    protected void describeMismatchSafely(HtmlPage htmlPage, Description mismatchDescription) {
        // TODO change to a more helpful message after refactoring of List<Violation> to Violations in Rule and RuleSet
        super.describeMismatchSafely(htmlPage, mismatchDescription);
    }

    @Factory
    public static <T> Matcher<HtmlPage> complaintToRule(Rule rule) {
        return new RuleMatcher(rule);
    }

}
