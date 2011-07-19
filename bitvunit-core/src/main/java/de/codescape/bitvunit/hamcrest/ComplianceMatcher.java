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

public class ComplianceMatcher<T> extends TypeSafeMatcher<T> {

    private RuleSet ruleSet;

    public ComplianceMatcher(RuleSet ruleSet) {
        this.ruleSet = ruleSet;
    }

    @Override
    protected boolean matchesSafely(T item) {
        return !ruleSet.applyTo(createHtmlPage(item)).hasViolations();
    }

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

    @Factory
    public static <T> Matcher<T> compliantTo(RuleSet ruleSet) {
        return new ComplianceMatcher<T>(ruleSet);
    }

    @Factory
    public static <T> Matcher<T> compliantTo(Rule rule) {
        return compliantTo(new BasicRuleSet(rule));
    }

}
