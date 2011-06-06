package de.codescape.bitvunit.rule;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Violations serves as a container class that holds all {@link Violation} objects that were created during analysis
 * with a single {@link Rule} or a {@link de.codescape.bitvunit.ruleset.RuleSet}.
 */
public class Violations {

    private List<Violation> violations = new ArrayList<Violation>();

    /**
     * Add a new {@link Violation} to the list of violations.
     *
     * @param violation {@link Violation} to be added to the list
     */
    public void add(Violation violation) {
        violations.add(violation);
    }

    /**
     * Add all {@link Violations} to the current list of {@link Violation} objects.
     *
     * @param violations {@link Violations} to be added to the list
     */
    public void addAll(Violations violations) {
        this.violations.addAll(violations.asList());
    }

    /**
     * Query whether there is minimum of one {@link Violation} in the list of violations.
     *
     * @return <code>true</code> for and positive number of violations, <code>false</code> otherwise
     */
    public boolean hasViolations() {
        return !violations.isEmpty();
    }

    /**
     * Count the amount of violations collected in the underlying list.
     *
     * @return the amount of violations
     */
    public int size() {
        return violations.size();
    }

    /**
     * Return a {@link List} of {@link Violation} objects.
     *
     * @return a {@link List} of {@link Violation} objects
     */
    public List<Violation> asList() {
        return Collections.unmodifiableList(violations);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        if (!hasViolations()) {
            sb.append("No violations found.");
        } else {
            sb.append("Found violations:");
            int num = 1;
            for (Violation violation : violations) {
                sb.append("\n").append(num++).append(") ").append(violation);
            }
        }
        return sb.toString();
    }

}
