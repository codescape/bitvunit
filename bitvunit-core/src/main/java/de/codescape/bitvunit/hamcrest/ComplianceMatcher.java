package de.codescape.bitvunit.hamcrest;

import de.codescape.bitvunit.Testable;
import de.codescape.bitvunit.rule.Violations;
import org.hamcrest.Description;
import org.hamcrest.Factory;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;

import static de.codescape.bitvunit.util.Assert.notNull;
import static de.codescape.bitvunit.util.html.HtmlPageUtil.toHtmlPage;

/**
 * Hamcrest matcher to be used to run accessibility checks against the {@link de.codescape.bitvunit.Testable} rule(s).
 * <p/>
 * <b>Usage examples:</b>
 * <pre><code>
 * assertThat(supportedType, is(compliantTo(ruleSet)));
 * assertThat(supportedType, is(compliantTo(rule)));
 * </code></pre>
 * <p/>
 * Have a look at {@link de.codescape.bitvunit.util.html.HtmlPageUtil} methods for supported types.
 *
 * @param <T> one of the supported types
 * @author Stefan Glase
 * @see de.codescape.bitvunit.util.html.HtmlPageUtil
 * @since 0.4
 */
public class ComplianceMatcher<T> extends TypeSafeMatcher<T> {

    private final Testable testable;
    private Violations violations;

    /**
     * Creates a new {@link ComplianceMatcher} against the provided {@link de.codescape.bitvunit.Testable}.
     *
     * @param testable the {@link de.codescape.bitvunit.Testable} rule(s) to be used
     */
    public ComplianceMatcher(Testable testable) {
        this.testable = testable;
    }

    @Override
    protected boolean matchesSafely(T item) {
        violations = testable.applyTo(toHtmlPage(item));
        return !violations.hasViolations();
    }

    @Override
    public void describeTo(Description description) {
        description.appendText("compliant to ").appendText(testable.toString());
    }

    @Override
    protected void describeMismatchSafely(T item, Description mismatchDescription) {
        notNull("Method may only be called after preceding call to matches method.", violations);
        mismatchDescription.appendText(violations.toString());
    }

    /**
     * Returns a {@link ComplianceMatcher} that checks one of the supported types against the
     * {@link de.codescape.bitvunit.Testable} rule(s).
     *
     * @param testable {@link de.codescape.bitvunit.Testable} that should be used
     * @param <T>      supported types are contained in JavaDoc at class level
     * @return {@link ComplianceMatcher} to check against the {@link de.codescape.bitvunit.Testable} rule(s)
     */
    @Factory
    public static <T> Matcher<T> compliantTo(Testable testable) {
        return new ComplianceMatcher<>(testable);
    }

}
