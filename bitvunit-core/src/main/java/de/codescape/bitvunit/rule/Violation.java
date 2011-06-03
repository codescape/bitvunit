package de.codescape.bitvunit.rule;

public class Violation {

    private final Rule rule;
    private final String message;
    private final Integer lineNumber;

    public Violation(Rule rule, int lineNumber, String message) {
        super();
        this.rule = rule;
        this.lineNumber = lineNumber;
        this.message = message;
    }

    public Rule getRule() {
        return rule;
    }

    public String getMessage() {
        return message;
    }

    public Integer getLineNumber() {
        return lineNumber;
    }

    @Override
    public String toString() {
        return "[Line " + lineNumber + "] " + rule.getName() + ": " + message;
    }

}
