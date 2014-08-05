package de.codescape.bitvunit;

import com.gargoylesoftware.htmlunit.html.HtmlPage;
import de.codescape.bitvunit.hamcrest.ComplianceMatcher;
import de.codescape.bitvunit.report.ReportWriter;
import de.codescape.bitvunit.report.ReportingContext;
import de.codescape.bitvunit.report.XmlReportWriter;
import de.codescape.bitvunit.rule.Rule;
import de.codescape.bitvunit.ruleset.AllRules;
import de.codescape.bitvunit.ruleset.BasicRuleSet;
import de.codescape.bitvunit.ruleset.RuleSet;
import org.hamcrest.Matcher;
import org.openqa.selenium.WebDriver;

import java.io.InputStream;
import java.io.Reader;
import java.net.URL;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * Main class of this library providing all commonly used methods when interacting with the library from tests. So a
 * single static import is required inside your tests to perform accessibility testing.
 *
 * @author Stefan Glase
 * @since 0.13
 */
public class BitvUnit {

    /* JUnit Assertions */

    /**
     * JUnit Assertion to verify a {@link org.openqa.selenium.WebDriver} instance for accessibility.
     *
     * @param webDriver {@link org.openqa.selenium.WebDriver} instance
     * @param testable  rule(s) to apply
     */
    public static void assertAccessibility(WebDriver webDriver, Testable testable) {
        assertThat(webDriver, is(compliantTo(testable)));
    }

    /**
     * JUnit Assertion to verify a {@link java.lang.String} containing the HTML page for accessibility.
     *
     * @param htmlString {@link java.lang.String} containing the HTML page
     * @param testable   rule(s) to apply
     */
    public static void assertAccessibility(String htmlString, Testable testable) {
        assertThat(htmlString, is(compliantTo(testable)));
    }

    /**
     * JUnit Assertion to verify a {@link com.gargoylesoftware.htmlunit.html.HtmlPage} instance for accessibility.
     *
     * @param htmlPage {@link com.gargoylesoftware.htmlunit.html.HtmlPage} instance
     * @param testable rule(s) to apply
     */
    public static void assertAccessibility(HtmlPage htmlPage, Testable testable) {
        assertThat(htmlPage, is(compliantTo(testable)));
    }

    /**
     * JUnit Assertion to verify a {@link java.io.Reader} instance for accessibility.
     *
     * @param reader   {@link java.io.Reader} instance
     * @param testable rule(s) to apply
     */
    public static void assertAccessibility(Reader reader, Testable testable) {
        assertThat(reader, is(compliantTo(testable)));
    }

    /**
     * JUnit Assertion to verify a {@link java.io.InputStream} instance for accessibility.
     *
     * @param inputStream {@link java.io.InputStream} instance
     * @param testable    rule(s) to apply
     */
    public static void assertAccessibility(InputStream inputStream, Testable testable) {
        assertThat(inputStream, is(compliantTo(testable)));
    }

    /**
     * JUnit Assertion to verify a {@link URL} instance for accessibility.
     *
     * @param url      {@link java.net.URL} instance
     * @param testable rule(s) to apply
     */
    public static void assertAccessibility(URL url, Testable testable) {
        assertThat(url, is(compliantTo(testable)));
    }

    /* Hamcrest Matchers */

    /**
     * Hamcrest Matcher to verify accessibility of a page against the provided rule(s).
     *
     * @param testable rule(s) to apply
     * @param <T>      page to verify
     * @return {@link de.codescape.bitvunit.hamcrest.ComplianceMatcher}
     */
    public static <T> Matcher<T> compliantTo(Testable testable) {
        return ComplianceMatcher.compliantTo(testable);
    }

    /* RuleSet configuration */

    /**
     * {@link de.codescape.bitvunit.ruleset.RuleSet} containing all rules provided by the library itself.
     *
     * @return {@link de.codescape.bitvunit.ruleset.RuleSet}
     */
    public static RuleSet allRules() {
        return AllRules.allRules();
    }

    /**
     * Creates and returns a {@link de.codescape.bitvunit.ruleset.RuleSet} containing all provided rules.
     *
     * @param rules provided rule(s)
     * @return {@link de.codescape.bitvunit.ruleset.RuleSet} containing all provided rules
     */
    public static RuleSet withRules(Rule... rules) {
        return new BasicRuleSet(rules);
    }

    /* ReportWriter configuration */

    /**
     * Configure the {@link de.codescape.bitvunit.report.ReportWriter} to be used while testing.
     *
     * @param reportWriter {@link de.codescape.bitvunit.report.ReportWriter} to be used
     */
    public static void useReportWriter(ReportWriter reportWriter) {
        ReportingContext.setReportWriter(reportWriter);
    }

}
