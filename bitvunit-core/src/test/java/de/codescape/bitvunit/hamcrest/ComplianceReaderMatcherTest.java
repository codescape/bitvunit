package de.codescape.bitvunit.hamcrest;

import org.junit.Test;

import java.io.Reader;
import java.io.StringReader;

import static de.codescape.bitvunit.hamcrest.ComplianceReaderMatcher.compliantTo;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsNot.not;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.fail;

public class ComplianceReaderMatcherTest extends AbstractHamcrestMatcherTest {

    @Test
    public void matchesPositivelyToComplianceOfRule() throws Exception {
        assertThat(somePageReader(), is(compliantTo(satisfiedRule)));

        try {
            assertThat(somePageReader(), is(not(compliantTo(satisfiedRule))));
            fail();
        } catch (AssertionError e) { /* should be thrown */ }
    }

    @Test
    public void matchesNegativelyToViolationOfRule() throws Exception {
        assertThat(somePageReader(), is(not(compliantTo(unsatisfiedRule))));

        try {
            assertThat(somePageReader(), is(compliantTo(unsatisfiedRule)));
            fail();
        } catch (AssertionError e) { /* should be thrown */ }
    }

    @Test
    public void matchesPositivelyToComplianceOfRuleSet() throws Exception {
        assertThat(somePageReader(), is(compliantTo(satisfiedRuleSet)));

        try {
            assertThat(somePageReader(), is(not(compliantTo(satisfiedRuleSet))));
            fail();
        } catch (AssertionError e) { /* should be thrown */ }
    }

    @Test
    public void matchesNegativelyToViolationOfRuleSet() throws Exception {
        assertThat(somePageReader(), is(not(compliantTo(unsatisfiedRuleSet))));

        try {
            assertThat(somePageReader(), is(compliantTo(unsatisfiedRuleSet)));
            fail();
        } catch (AssertionError e) { /* should be thrown */ }
    }

    private Reader somePageReader() {
        return new StringReader(somePageString());
    }

}
