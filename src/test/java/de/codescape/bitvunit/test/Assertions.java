package de.codescape.bitvunit.test;

import de.codescape.bitvunit.rule.Rule;
import de.codescape.bitvunit.rule.Violation;
import de.codescape.bitvunit.ruleset.RuleSet;

import java.util.List;

import static org.junit.Assert.assertTrue;

public abstract class Assertions {

    public static void assertViolations(List<Violation> violations, int expectedCount) {
        int count = violations.size();
        assertTrue("Expected " + expectedCount + " violations but found " + count + " violations.", expectedCount == count);
    }

    public static void assertViolations(List<Violation> violations, Rule rule, int expectedCount) {
        int count = numberOfViolations(violations, rule);
        assertTrue("Expected " + expectedCount + " violations of rule " + rule + " but found " + count + " violations.", expectedCount == count);
    }

    public static void assertNoViolations(List<Violation> violations) {
        assertViolations(violations, 0);
    }

    public static void assertNoViolations(List<Violation> violations, Rule rule) {
        assertViolations(violations, rule, 0);
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

    public static void assertRules(RuleSet ruleSet, int expectedCount) {
        int count = ruleSet.getRules().size();
        assertTrue("Expected " + expectedCount + " rules in rule set but found " + count + " rules.", expectedCount == count);
    }

}
