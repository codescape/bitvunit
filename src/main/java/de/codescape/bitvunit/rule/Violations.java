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
        return violations.isEmpty();
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

}
