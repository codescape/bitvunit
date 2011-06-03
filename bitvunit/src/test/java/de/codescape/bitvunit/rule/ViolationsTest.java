package de.codescape.bitvunit.rule;

import org.junit.Test;

import static de.codescape.bitvunit.test.Assertions.assertNoViolations;
import static de.codescape.bitvunit.test.Assertions.assertViolations;
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

    private Violation someViolation() {
        return mock(Violation.class);
    }

}
