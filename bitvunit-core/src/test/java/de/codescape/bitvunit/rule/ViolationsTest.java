package de.codescape.bitvunit.rule;

import org.junit.Test;

import static de.codescape.bitvunit.test.Assertions.assertNoViolations;
import static de.codescape.bitvunit.test.Assertions.assertViolations;
import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.CoreMatchers.startsWith;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.mock;

public class ViolationsTest {

    @Test
    public void newViolationsInstanceHasNoViolations() throws Exception {
        assertNoViolations(new Violations());
    }

    @Test
    public void violationsInstanceWithOneViolationHasViolations() throws Exception {
        Violations violations = new Violations();
        violations.add(someViolation());
        assertViolations(violations);
    }

    @Test
    public void toStringForNoViolationsGivesCorrectFeedback() throws Exception {
        Violations violations = new Violations();
        assertEquals("No violations found.", violations.toString());
    }

    @Test
    public void toStringForOneViolationGivesCorrectFeedback() throws Exception {
        Violations violations = new Violations();
        Violation violation = someViolation();
        violations.add(violation);
        assertThat(violations.toString(), startsWith("Found violations:"));
        assertThat(violations.toString(), containsString(violation.toString()));
    }

    private Violation someViolation() {
        return mock(Violation.class);
    }

}
