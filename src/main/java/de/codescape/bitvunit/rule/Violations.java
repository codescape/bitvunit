package de.codescape.bitvunit.rule;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Violations {

    private List<Violation> violations = new ArrayList<Violation>();

    public void add(Violation violation) {
        violations.add(violation);
    }

    public boolean hasViolations() {
        return !violations.isEmpty();
    }

    public int size() {
        return violations.size();
    }

    public List<Violation> asList() {
        return Collections.unmodifiableList(violations);
    }

    public void addAll(Violations violations) {
        this.violations.addAll(violations.asList());
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
