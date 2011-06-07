package de.codescape.bitvunit.hamcrest;

import com.gargoylesoftware.htmlunit.html.HtmlPage;
import de.codescape.bitvunit.rule.Rule;
import org.junit.Before;
import org.junit.Test;

import static de.codescape.bitvunit.hamcrest.RuleMatcher.compliantTo;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsNot.not;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class RuleMatcherTest extends AbstractHamcrestTest {

    private Rule satisfiedRule;
    private Rule unsatisfiedRule;

    @Before
    public void setUp() throws Exception {
        satisfiedRule = mock(Rule.class);
        when(satisfiedRule.applyTo(any(HtmlPage.class))).thenReturn(noViolation());

        unsatisfiedRule = mock(Rule.class);
        when(unsatisfiedRule.applyTo(any(HtmlPage.class))).thenReturn(someViolation(unsatisfiedRule));
    }

    @Test
    public void matchesPositivelyToComplianceOfRule() throws Exception {
        assertThat(somePage(), is(compliantTo(satisfiedRule)));

        boolean hasFailed = false;
        try {
            assertThat(somePage(), not(compliantTo(satisfiedRule)));
        } catch (AssertionError e) {
            hasFailed = true;
        }
        assertTrue(hasFailed);
    }

    @Test
    public void matchesNegativelyToViolationOfRule() throws Exception {
        assertThat(somePage(), not(compliantTo(unsatisfiedRule)));

        boolean hasFailed = false;
        try {
            assertThat(somePage(), is(compliantTo(unsatisfiedRule)));
        } catch (AssertionError e) {
            hasFailed = true;
        }
        assertTrue(hasFailed);
    }

}
