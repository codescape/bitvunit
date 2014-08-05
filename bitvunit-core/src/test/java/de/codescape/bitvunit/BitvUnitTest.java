package de.codescape.bitvunit;

import com.gargoylesoftware.htmlunit.html.HtmlPage;
import de.codescape.bitvunit.rule.Rule;
import de.codescape.bitvunit.rule.page.AvoidAbstractRoleRule;
import de.codescape.bitvunit.rule.text.AvoidBlinkTextRule;
import de.codescape.bitvunit.ruleset.RuleSet;
import de.codescape.bitvunit.test.TestPage;
import de.codescape.bitvunit.test.TestViolations;
import org.junit.Before;
import org.junit.Test;

import static de.codescape.bitvunit.test.Matchers.containsRules;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class BitvUnitTest {

    private Rule singleRule;
    private RuleSet multipleRules;

    @Before
    public void setUp() throws Exception {
        singleRule = mock(Rule.class);
        when(singleRule.applyTo(any(HtmlPage.class))).thenReturn(TestViolations.noViolations());

        multipleRules = mock(RuleSet.class);
        when(multipleRules.applyTo(any(HtmlPage.class))).thenReturn(TestViolations.noViolations());
    }

    /* Junit Assertions */

    @Test
    public void shouldProvideAssertAccessibilityForString() {
        BitvUnit.assertAccessibility(TestPage.asString(), singleRule);
        BitvUnit.assertAccessibility(TestPage.asString(), multipleRules);
    }

    @Test
    public void shouldProvideAssertAccessibilityForHtmlPage() {
        BitvUnit.assertAccessibility(TestPage.asHtmlPage(), singleRule);
        BitvUnit.assertAccessibility(TestPage.asHtmlPage(), multipleRules);
    }

    @Test
    public void shouldProvideAssertAccessibilityForReader() {
        BitvUnit.assertAccessibility(TestPage.asReader(), singleRule);
        BitvUnit.assertAccessibility(TestPage.asReader(), multipleRules);
    }

    @Test
    public void shouldProvideAssertAccessibilityForInputStream() {
        BitvUnit.assertAccessibility(TestPage.asInputStream(), singleRule);
        BitvUnit.assertAccessibility(TestPage.asInputStream(), multipleRules);
    }

    @Test
    public void shouldProvideAssertAccessibilityForURL() {
        BitvUnit.assertAccessibility(TestPage.asURL(), singleRule);
        BitvUnit.assertAccessibility(TestPage.asURL(), multipleRules);
    }

    @Test
    public void shouldProvideAssertAccessibilityForWebDriver() {
        BitvUnit.assertAccessibility(TestPage.asWebDriver(), singleRule);
        BitvUnit.assertAccessibility(TestPage.asWebDriver(), multipleRules);
    }

    /* Hamcrest Matchers */

    @Test
    public void shouldExposeCompliantToMatcher() {
        assertThat(TestPage.asString(), is(BitvUnit.compliantTo(singleRule)));
        assertThat(TestPage.asString(), is(BitvUnit.compliantTo(multipleRules)));
    }

    /* Rule Sets */

    @Test
    public void shouldProvideAllRulesAccessor() {
        assertThat(BitvUnit.allRules(), containsRules());
    }

    @Test
    public void shouldProfileCustomRuleSetAccessor() {
        assertThat(BitvUnit.withRules(new AvoidBlinkTextRule(), new AvoidAbstractRoleRule()), containsRules(2));
    }

}