package de.codescape.bitvunit.ruleset;

/**
 * Exception that is thrown when the creation of a {@link XmlRuleSet} fails.
 *
 * @author Stefan Glase
 * @since 0.2
 */
public class XmlRuleSetException extends RuntimeException {

    public XmlRuleSetException(String message, Throwable cause) {
        super(message, cause);
    }

}
