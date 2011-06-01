package de.codescape.bitvunit.rule;

import org.junit.Test;

import static de.codescape.bitvunit.test.Assertions.assertViolations;
import static org.junit.Assert.assertFalse;
import static org.mockito.Mockito.mock;

public class ViolationsTest {

    @Test
    public void returnFalseForViolationsInstanceWithoutViolations() throws Exception {
        assertFalse(new Violations().hasViolations());
    }

    @Test
    public void returnsTrueForViolationsInstanceWithOneViolation() throws Exception {
        Violations violations = new Violations();
        violations.add(someViolation());
        assertViolations(violations);
    }

    private Violation someViolation() {
        return mock(Violation.class);
    }

}
