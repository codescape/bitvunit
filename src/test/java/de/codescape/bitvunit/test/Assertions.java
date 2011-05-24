package de.codescape.bitvunit.test;

import de.codescape.bitvunit.rule.Rule;
import de.codescape.bitvunit.rule.Violation;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public abstract class Assertions {

    public static void assertNoViolation(List<Violation> violations) {
        assertTrue("Expected no violations but found " + violations.size() + " violations.", violations.isEmpty());
    }

    public static void assertViolation(List<Violation> violations, Rule rule) {
        boolean foundViolation = false;
        for (Violation violation : violations) {
            if (violation.getRule().equals(rule)) {
                foundViolation = true;
            }
        }
        assertTrue("Expected violation of rule " + rule + " but found no violation of that rule.", foundViolation);
    }

    public static void assertViolationCount(List<Violation> violations, int count) {
        assertEquals("Expected " + count + "violations but found " + violations.size() + " violations.", count, violations.size());
    }

}
