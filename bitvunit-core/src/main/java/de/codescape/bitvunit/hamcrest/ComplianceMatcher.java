package de.codescape.bitvunit.hamcrest;

import com.gargoylesoftware.htmlunit.html.HtmlPage;
import de.codescape.bitvunit.rule.Rule;
import de.codescape.bitvunit.ruleset.BasicRuleSet;
import de.codescape.bitvunit.ruleset.RuleSet;
import org.hamcrest.Description;
import org.hamcrest.Factory;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;

import java.io.InputStream;
import java.io.Reader;
import java.net.URL;

import static de.codescape.bitvunit.util.HtmlPageUtil.*;

/**
 * Hamcrest matcher to be used to run accessibility checks against a single {@link Rule} or a {@link RuleSet}.<p/>
 * <p/>
 * <b>Usage examples:</b>
 * <pre><code>
 * assertThat(supportedType, is(compliantTo(ruleSet)));
 * assertThat(supportedType, is(compliantTo(rule)));
 * </code></pre>
 * <p/>
 * <b>Supported types:</b>
 * <pre><code>
 * com.gargoylesoftware.htmlunit.html.HtmlPage
 * java.io.InputStream
 * java.io.Reader
 * java.lang.String
 * java.net.URL
 * </code></pre>
 *
 * @param <T> one of the supported types
 * @author Stefan Glase
 * @since 0.4
 */
public class ComplianceMatcher<T> extends TypeSafeMatcher<T> {

    private RuleSet ruleSet;

    /**
     * Creates a new {@link ComplianceMatcher} against the provided {@link RuleSet}.
     *
     * @param ruleSet
     */
    public ComplianceMatcher(RuleSet ruleSet) {
        this.ruleSet = ruleSet;
    }

    @Override
    protected boolean matchesSafely(T item) {
        return !ruleSet.applyTo(createHtmlPage(item)).hasViolations();
    }

    /**
     * Creates a {@link HtmlPage} from the provided <code>item</code> if it is an instance of one of the supported
     * types.
     *
     * @param item
     * @return
     */
    private HtmlPage createHtmlPage(T item) {
        if (item instanceof HtmlPage) {
            return (HtmlPage) item;
        }
        if (item instanceof String) {
            return htmlPageFromString((String) item);
        }
        if (item instanceof Reader) {
            return htmlPageFromReader((Reader) item);
        }
        if (item instanceof URL) {
            return htmlPageFromURL((URL) item);
        }
        if (item instanceof InputStream) {
            return htmlPageFromInputStream((InputStream) item);
        }
        throw new UnsupportedOperationException("Creation of HtmlPage from " + item.getClass() + " not supported.");
    }

    @Override
    public void describeTo(Description description) {
        description.appendText("compliant to ").appendText(ruleSet.toString());
    }

    /**
     * Returns a {@link ComplianceMatcher} that checks one of the supported types (see JavaDoc at class level) against
     * the given {@link RuleSet}.
     *
     * @param ruleSet {@@link RuleSet} that should be used
     * @param <T>     supported types are contained in JavaDoc at class level
     * @return {@link ComplianceMatcher} to check against the {@link RuleSet}
     */
    @Factory
    public static <T> Matcher<T> compliantTo(RuleSet ruleSet) {
        return new ComplianceMatcher<T>(ruleSet);
    }

    /**
     * Returns a {@link ComplianceMatcher} that checks one of the supported types (see JavaDoc at class level) against
     * the given {@link Rule}.
     *
     * @param rule {@@link Rule} that should be used
     * @param <T>  supported types are contained in JavaDoc at class level
     * @return {@link ComplianceMatcher} to check against the {@link Rule}
     */
    @Factory
    public static <T> Matcher<T> compliantTo(Rule rule) {
        return compliantTo(new BasicRuleSet(rule));
    }

}
