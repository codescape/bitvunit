package de.codescape.bitvunit.test;

import de.codescape.bitvunit.rule.Rule;
import de.codescape.bitvunit.rule.Violation;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public abstract class Assertions {

    public static void assertNoViolationExists(List<Violation> violations) {
        assertTrue("Expected no violations but found " + violations.size() + " violations.", violations.isEmpty());
    }

    public static void assertViolationExists(List<Violation> violations) {
        assertTrue("Expected minimum of one violation but found none.", !violations.isEmpty());
    }

    public static void assertViolationExists(List<Violation> violations, Rule rule) {
        assertTrue("Expected violation of rule " + rule + " but found no violation of that rule.", numberOfViolations(violations, rule) > 0);
    }

    public static void assertViolationExists(List<Violation> violations, int expectedCount) {
        assertEquals("Expected " + expectedCount + " violations but found " + violations.size() + " violations.", expectedCount, violations.size());
    }

    public static void assertViolationExists(List<Violation> violations, Rule rule, int expectedCount) {
        int count = numberOfViolations(violations, rule);
        assertEquals("Expected " + expectedCount + " violations of rule " + rule + " but found " + count + " violations.", expectedCount, count);
    }

    private static int numberOfViolations(List<Violation> violations, Rule rule) {
        int found = 0;
        for (Violation violation : violations) {
            if (violation.getRule().equals(rule)) {
                found++;
            }
        }
        return found;
    }

}
