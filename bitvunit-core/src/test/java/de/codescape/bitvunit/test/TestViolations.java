package de.codescape.bitvunit.test;

import de.codescape.bitvunit.rule.Rule;
import de.codescape.bitvunit.rule.Violation;
import de.codescape.bitvunit.rule.Violations;

/**
 * {@link de.codescape.bitvunit.rule.Violations} for testing purposes.
 */
public class TestViolations {

    /**
     * Returns {@link de.codescape.bitvunit.rule.Violations} without any violation
     *
     * @return {@link de.codescape.bitvunit.rule.Violations} without any violation
     */
    public static Violations noViolations() {
        return new Violations();
    }

    /**
     * Returns {@link de.codescape.bitvunit.rule.Violations} with single violation of the given rule
     *
     * @param rule {@link Rule} that is violated
     * @return {@link de.codescape.bitvunit.rule.Violations} with single violation of the given rule
     */
    public static Violations violationOf(Rule rule) {
        Violations violations = new Violations();
        violations.add(new Violation(rule, TestPage.asHtmlPage().getBody(), "uh oh"));
        return violations;
    }

}
