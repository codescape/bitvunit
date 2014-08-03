package de.codescape.bitvunit.hamcrest;

import com.gargoylesoftware.htmlunit.html.HtmlPage;
import de.codescape.bitvunit.rule.Rule;
import de.codescape.bitvunit.rule.Violation;
import de.codescape.bitvunit.rule.Violations;
import de.codescape.bitvunit.ruleset.RuleSet;
import de.codescape.bitvunit.test.TestPage;
import org.hamcrest.Description;
import org.hamcrest.StringDescription;
import org.junit.Before;
import org.junit.Test;

import static de.codescape.bitvunit.hamcrest.ComplianceMatcher.compliantTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;
import static org.junit.Assert.*;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class ComplianceMatcherTest {

    private Rule satisfiedRule;
    private Rule unsatisfiedRule;

    private RuleSet satisfiedRuleSet;
    private RuleSet unsatisfiedRuleSet;

    @Before
    public void setUp() throws Exception {
        satisfiedRule = mock(Rule.class);
        when(satisfiedRule.applyTo(any(HtmlPage.class))).thenReturn(noViolation());

        unsatisfiedRule = mock(Rule.class);
        when(unsatisfiedRule.applyTo(any(HtmlPage.class))).thenReturn(someViolation(unsatisfiedRule));

        satisfiedRuleSet = mock(RuleSet.class);
        when(satisfiedRuleSet.applyTo(any(HtmlPage.class))).thenReturn(noViolation());

        unsatisfiedRuleSet = mock(RuleSet.class);
        when(unsatisfiedRuleSet.applyTo(any(HtmlPage.class))).thenReturn(someViolation(unsatisfiedRule));
    }

    @Test
    public void compliantToForHtmlPageAgainstRuleSet() throws Exception {
        assertThat(TestPage.asHtmlPage(), is(compliantTo(satisfiedRuleSet)));
        assertThat(TestPage.asHtmlPage(), is(not(compliantTo(unsatisfiedRuleSet))));
    }

    @Test
    public void compliantToForHtmlPageAgainstRule() throws Exception {
        assertThat(TestPage.asHtmlPage(), is(compliantTo(satisfiedRule)));
        assertThat(TestPage.asHtmlPage(), is(not(compliantTo(unsatisfiedRule))));
    }

    @Test
    public void compliantToForStringAgainstRuleSet() throws Exception {
        assertThat(TestPage.asString(), is(compliantTo(satisfiedRuleSet)));
        assertThat(TestPage.asString(), is(not(compliantTo(unsatisfiedRuleSet))));
    }

    @Test
    public void compliantToForStringAgainstRule() throws Exception {
        assertThat(TestPage.asString(), is(compliantTo(satisfiedRule)));
        assertThat(TestPage.asString(), is(not(compliantTo(unsatisfiedRule))));
    }

    @Test
    public void compliantToForReaderAgainstRuleSet() throws Exception {
        assertThat(TestPage.asReader(), is(compliantTo(satisfiedRuleSet)));
        assertThat(TestPage.asReader(), is(not(compliantTo(unsatisfiedRuleSet))));
    }

    @Test
    public void compliantToForReaderAgainstRule() throws Exception {
        assertThat(TestPage.asReader(), is(compliantTo(satisfiedRule)));
        assertThat(TestPage.asReader(), is(not(compliantTo(unsatisfiedRule))));
    }

    @Test
    public void compliantToForURLAgainstRuleSet() throws Exception {
        assertThat(TestPage.asURL(), is(compliantTo(satisfiedRuleSet)));
        assertThat(TestPage.asURL(), is(not(compliantTo(unsatisfiedRuleSet))));
    }

    @Test
    public void compliantToForURLAgainstRule() throws Exception {
        assertThat(TestPage.asURL(), is(compliantTo(satisfiedRule)));
        assertThat(TestPage.asURL(), is(not(compliantTo(unsatisfiedRule))));
    }

    @Test
    public void compliantToForInputStreamAgainstRuleSet() throws Exception {
        assertThat(TestPage.asInputStream(), is(compliantTo(satisfiedRuleSet)));
        assertThat(TestPage.asInputStream(), is(not(compliantTo(unsatisfiedRuleSet))));
    }

    @Test
    public void compliantToForInputStreamAgainstRule() throws Exception {
        assertThat(TestPage.asInputStream(), is(compliantTo(satisfiedRule)));
        assertThat(TestPage.asInputStream(), is(not(compliantTo(unsatisfiedRule))));
    }

    @Test
    public void compliantToForObjectAgainstRuleSet() throws Exception {
        try {
            assertThat(new Object(), is(compliantTo(satisfiedRuleSet)));
            fail();
        } catch (UnsupportedOperationException e) { /* should fail */ }
        try {
            assertThat(new Object(), is(not(compliantTo(unsatisfiedRuleSet))));
            fail();
        } catch (UnsupportedOperationException e) { /* should fail */ }
    }

    @Test
    public void compliantToForObjectPageAgainstRule() throws Exception {
        try {
            assertThat(new Object(), is(compliantTo(satisfiedRule)));
            fail();
        } catch (UnsupportedOperationException e) { /* should fail */ }
        try {
            assertThat(new Object(), is(not(compliantTo(unsatisfiedRule))));
            fail();
        } catch (UnsupportedOperationException e) { /* should fail */ }
    }

    @Test
    public void describeToContainsTheRuleSetThatIsUsed() throws Exception {
        Description description = new StringDescription();
        compliantTo(satisfiedRuleSet).describeTo(description);
        assertEquals("compliant to " + satisfiedRuleSet.toString(), description.toString());
    }

    @Test(expected = IllegalArgumentException.class)
    public void callToDescribeMismatchDoesNotThrowNullPointerException() throws Exception {
        compliantTo(satisfiedRuleSet).describeMismatch(TestPage.asHtmlPage(), new StringDescription());
    }

    private Violations someViolation(Rule rule) {
        Violations violations = new Violations();
        violations.add(new Violation(rule, TestPage.asHtmlPage().getBody(), "uh oh"));
        return violations;
    }

    private Violations noViolation() {
        return new Violations();
    }

}
