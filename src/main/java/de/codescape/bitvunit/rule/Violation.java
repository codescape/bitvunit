package de.codescape.bitvunit.rule;

public class Violation {

    private Rule rule;

    private String message;

    private Integer lineNumber;

    public void setRule(Rule rule) {
        this.rule = rule;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setLineNumber(Integer lineNumber) {
        this.lineNumber = lineNumber;
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
