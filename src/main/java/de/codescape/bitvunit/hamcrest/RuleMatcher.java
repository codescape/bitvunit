package de.codescape.bitvunit.hamcrest;

import com.gargoylesoftware.htmlunit.html.HtmlPage;
import de.codescape.bitvunit.rule.Rule;
import de.codescape.bitvunit.rule.Violations;
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
        Violations violations = rule.applyTo(htmlPage);
        if (violations.hasViolations()) {
            System.out.println(violations);
        }
        return !violations.hasViolations();
    }

    @Override
    public void describeTo(Description description) {
        description.appendText("compliant to rule ").appendText(rule.toString());
    }

    @Factory
    public static <T> Matcher<HtmlPage> complaintToRule(Rule rule) {
        return new RuleMatcher(rule);
    }

}
