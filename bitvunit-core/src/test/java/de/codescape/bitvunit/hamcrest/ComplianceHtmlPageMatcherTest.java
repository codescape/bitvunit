package de.codescape.bitvunit.hamcrest;

import org.junit.Test;

import static de.codescape.bitvunit.hamcrest.ComplianceHtmlPageMatcher.compliantTo;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsNot.not;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.fail;

public class ComplianceHtmlPageMatcherTest extends AbstractHamcrestMatcherTest {

    @Test
    public void matchesPositivelyToComplianceOfRule() throws Exception {
        assertThat(somePage(), is(compliantTo(satisfiedRule)));

        try {
            assertThat(somePage(), is(not(compliantTo(satisfiedRule))));
            fail();
        } catch (AssertionError e) { /* should be thrown */ }
    }

    @Test
    public void matchesNegativelyToViolationOfRule() throws Exception {
        assertThat(somePage(), is(not(compliantTo(unsatisfiedRule))));

        try {
            assertThat(somePage(), is(compliantTo(unsatisfiedRule)));
            fail();
        } catch (AssertionError e) { /* should be thrown */ }
    }

    @Test
    public void matchesPositivelyToComplianceOfRuleSet() throws Exception {
        assertThat(somePage(), is(compliantTo(satisfiedRuleSet)));

        try {
            assertThat(somePage(), is(not(compliantTo(satisfiedRuleSet))));
            fail();
        } catch (AssertionError e) { /* should be thrown */ }
    }

    @Test
    public void matchesNegativelyToViolationOfRuleSet() throws Exception {
        assertThat(somePage(), is(not(compliantTo(unsatisfiedRuleSet))));

        try {
            assertThat(somePage(), is(compliantTo(unsatisfiedRuleSet)));
            fail();
        } catch (AssertionError e) { /* should be thrown */ }
    }

}
