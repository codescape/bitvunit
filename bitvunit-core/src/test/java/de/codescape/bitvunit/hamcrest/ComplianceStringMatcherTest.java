package de.codescape.bitvunit.hamcrest;

import org.junit.Test;

import static de.codescape.bitvunit.hamcrest.ComplianceStringMatcher.compliantTo;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsNot.not;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.fail;

public class ComplianceStringMatcherTest extends AbstractHamcrestMatcherTest {

    @Test
    public void matchesPositivelyToComplianceOfRule() throws Exception {
        assertThat(somePageString(), is(compliantTo(satisfiedRule)));

        try {
            assertThat(somePageString(), is(not(compliantTo(satisfiedRule))));
            fail();
        } catch (AssertionError e) { /* should be thrown */ }
    }

    @Test
    public void matchesNegativelyToViolationOfRule() throws Exception {
        assertThat(somePageString(), is(not(compliantTo(unsatisfiedRule))));

        try {
            assertThat(somePageString(), is(compliantTo(unsatisfiedRule)));
            fail();
        } catch (AssertionError e) { /* should be thrown */ }
    }

    @Test
    public void matchesPositivelyToComplianceOfRuleSet() throws Exception {
        assertThat(somePageString(), is(compliantTo(satisfiedRuleSet)));

        try {
            assertThat(somePageString(), is(not(compliantTo(satisfiedRuleSet))));
            fail();
        } catch (AssertionError e) { /* should be thrown */ }
    }

    @Test
    public void matchesNegativelyToViolationOfRuleSet() throws Exception {
        assertThat(somePageString(), is(not(compliantTo(unsatisfiedRuleSet))));

        try {
            assertThat(somePageString(), is(compliantTo(unsatisfiedRuleSet)));
            fail();
        } catch (AssertionError e) { /* should be thrown */ }
    }

}
