package de.codescape.bitvunit.rule;

/**
 * Every violation of a {@link Rule} should result in a {@link Violation} pointing to the {@link Rule} that was violated
 * providing a message explaining the problem in detail and a line number pointing to the code in question.
 */
public class Violation {

    private final Rule rule;
    private final String message;
    private final Integer lineNumber;

    /**
     * Construct a new {@link Violation} providing the {@link Rule} causing this {@link Violation}, a message describing
     * this {@link Violation} in detail and a line number pointing to the code in question.
     *
     * @param rule       {@link Rule} causing this {@link Violation}
     * @param lineNumber the line number where this {@link Violation} occurred in the page
     * @param message    message describing this {@link Violation}
     */
    public Violation(Rule rule, int lineNumber, String message) {
        super();
        this.rule = rule;
        this.lineNumber = lineNumber;
        this.message = message;
    }

    /**
     * Returns the {@link Rule} causing this {@link Violation}.
     *
     * @return the {@link Rule} causing this {@link Violation}
     */
    public Rule getRule() {
        return rule;
    }

    /**
     * Returns a message describing this {@link Violation}.
     *
     * @return a message describing this {@link Violation}
     */
    public String getMessage() {
        return message;
    }

    /**
     * Returns the line number where this {@link Violation} occurred in the page
     *
     * @return the line number where this {@link Violation} occurred in the page
     */
    public Integer getLineNumber() {
        return lineNumber;
    }

    @Override
    public String toString() {
        return "[Line " + lineNumber + "] " + rule.getName() + ": " + message;
    }

}
