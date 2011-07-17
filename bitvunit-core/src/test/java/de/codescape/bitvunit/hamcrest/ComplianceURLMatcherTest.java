package de.codescape.bitvunit.hamcrest;

import org.junit.Test;

import java.net.URL;

import static de.codescape.bitvunit.hamcrest.ComplianceURLMatcher.compliantTo;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsNot.not;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.fail;

public class ComplianceURLMatcherTest extends AbstractHamcrestMatcherTest {

    @Test
    public void matchesPositivelyToComplianceOfRule() throws Exception {
        assertThat(someURL(), is(compliantTo(satisfiedRule)));

        try {
            assertThat(someURL(), is(not(compliantTo(satisfiedRule))));
            fail();
        } catch (AssertionError e) { /* should be thrown */ }
    }

    @Test
    public void matchesNegativelyToViolationOfRule() throws Exception {
        assertThat(someURL(), is(not(compliantTo(unsatisfiedRule))));

        try {
            assertThat(someURL(), is(compliantTo(unsatisfiedRule)));
            fail();
        } catch (AssertionError e) { /* should be thrown */ }
    }

    @Test
    public void matchesPositivelyToComplianceOfRuleSet() throws Exception {
        assertThat(someURL(), is(compliantTo(satisfiedRuleSet)));

        try {
            assertThat(someURL(), is(not(compliantTo(satisfiedRuleSet))));
            fail();
        } catch (AssertionError e) { /* should be thrown */ }
    }

    @Test
    public void matchesNegativelyToViolationOfRuleSet() throws Exception {
        assertThat(someURL(), is(not(compliantTo(unsatisfiedRuleSet))));

        try {
            assertThat(someURL(), is(compliantTo(unsatisfiedRuleSet)));
            fail();
        } catch (AssertionError e) { /* should be thrown */ }
    }

    private URL someURL() {
        return getClass().getClassLoader().getResource("samplePage.html");
    }


}
