package de.codescape.bitvunit.test;

import de.codescape.bitvunit.rule.Rule;
import de.codescape.bitvunit.rule.Violation;
import de.codescape.bitvunit.rule.Violations;
import de.codescape.bitvunit.ruleset.RuleSet;

import static org.junit.Assert.assertTrue;

/**
 * Assertions class that is used in tests that check the correctness of {@link Rule} implementations.
 */
public final class Assertions {

    private Assertions() {
        throw new UnsupportedOperationException("Assertions class should not be instantiated.");
    }

    /**
     * Verifies that the given {@link Violations} instance contains one or more {@link Violation} instances.
     *
     * @param violations {@link Violations} to be checked
     */
    public static void assertViolations(Violations violations) {
        assertTrue("Expected minimum of one violation but found none.", violations.hasViolations());
    }

    /**
     * Verifies that the given {@link Violations} instance contains exactly <code>expectedCount</code> number of {@link
     * Violation} instances.
     *
     * @param violations    {@link Violations} to be checked
     * @param expectedCount expected number of {@link Violation} instances
     */
    public static void assertViolations(Violations violations, int expectedCount) {
        int count = violations.size();
        assertTrue("Expected " + expectedCount + " violations but found " + count + " violations.", expectedCount == count);
    }

    /**
     * Verifies that the given {@link Violations} instance contains exactly <code>expectedCount</code> number of {@link
     * Violation} instances of the given {@link Rule} instance.
     *
     * @param violations    {@link Violations} to be checked
     * @param rule          {@link Rule} to be checked
     * @param expectedCount expected number of {@link Violation} instances for that {@link Rule}
     */
    public static void assertViolations(Violations violations, Rule rule, int expectedCount) {
        int count = numberOfViolations(violations, rule);
        assertTrue("Expected " + expectedCount + " violations of rule " + rule + " but found " + count + " violations.", expectedCount == count);
    }

    /**
     * Verifies that the given {@link Violations} instance contains no violations.
     *
     * @param violations {@link Violations} to be checked
     */
    public static void assertNoViolations(Violations violations) {
        assertViolations(violations, 0);
    }

    /**
     * Verifies that the given {@link Violations} instance contains no violation of the given {@link Rule}.
     *
     * @param violations {@link Violations} to be checked
     * @param rule       {@link Rule} to be checked
     */
    public static void assertNoViolations(Violations violations, Rule rule) {
        assertViolations(violations, rule, 0);
    }

    /**
     * Verifies that the given {@link RuleSet} contains the <code>expectedCount</code> number of {@link Rule}
     * instances.
     *
     * @param ruleSet       {@link RuleSet} to be checked
     * @param expectedCount expected number of {@link Rule} instances to be contained
     */
    public static void assertRules(RuleSet ruleSet, int expectedCount) {
        int count = ruleSet.getRules().size();
        assertTrue("Expected " + expectedCount + " rules in rule set but found " + count + " rules.", expectedCount == count);
    }

    private static int numberOfViolations(Violations violations, Rule rule) {
        int found = 0;
        for (Violation violation : violations.asList()) {
            if (violation.getRule().equals(rule)) {
                found++;
            }
        }
        return found;
    }

}
