package de.codescape.bitvunit.hamcrest;

import com.gargoylesoftware.htmlunit.html.HtmlPage;
import de.codescape.bitvunit.rule.Violations;
import de.codescape.bitvunit.ruleset.RuleSet;
import org.hamcrest.Description;
import org.hamcrest.Factory;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;

/**
 * Hamcrest Matcher that checks a given {@link HtmlPage} for compliance to a complete {@link RuleSet}.
 *
 * @since 0.2
 */
public class RuleSetMatcher extends TypeSafeMatcher<HtmlPage> {

    private final RuleSet ruleSet;

    public RuleSetMatcher(RuleSet ruleSet) {
        this.ruleSet = ruleSet;
    }

    @Override
    protected boolean matchesSafely(HtmlPage htmlPage) {
        Violations violations = ruleSet.applyTo(htmlPage);
        if (violations.hasViolations()) {
            System.out.println(violations);
        }
        return !violations.hasViolations();
    }

    @Override
    public void describeTo(Description description) {
        description.appendText("compliant to ruleSet ").appendText(ruleSet.toString());
    }

    @Factory
    public static Matcher<HtmlPage> compliantTo(RuleSet ruleSet) {
        return new RuleSetMatcher(ruleSet);
    }

}
