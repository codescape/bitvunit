package de.codescape.bitvunit.ruleset;

/**
 * Exception that is thrown when the creation of a {@link XmlRuleSet} fails.
 *
 * @author Stefan Glase
 * @since 0.2
 */
public class XmlRuleSetException extends RuntimeException {

    /**
     * Constructs a new <code>XmlRuleSetException</code> with a given <code>message</code> and the underlying
     * <code>cause</code> of that exception.
     *
     * @param message message that describes the exception
     * @param cause   underlying cause for that exception
     */
    public XmlRuleSetException(String message, Throwable cause) {
        super(message, cause);
    }

}
