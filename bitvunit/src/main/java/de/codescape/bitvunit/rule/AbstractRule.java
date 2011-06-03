package de.codescape.bitvunit.rule;

import com.gargoylesoftware.htmlunit.html.HtmlPage;
import de.codescape.bitvunit.model.Page;

/**
 * Base class to all implementations of the Rule interface. It provides convenience methods to create violations within
 * the implementation of a rule and allows to apply a rule to a given HtmlPage object.
 */
public abstract class AbstractRule implements Rule {

    /**
     * Returns the name of that rule.
     *
     * @return name of that rule
     */
    @Override
    public abstract String getName();

    /**
     * Applies that rule to the given HtmlPage and returns a list of violations.
     *
     * @param htmlPage HtmlPage under test
     * @return list of violations
     */
    @Override
    public final Violations applyTo(HtmlPage htmlPage) {
        Violations violations = new Violations();
        applyTo(new Page(htmlPage), violations);
        return violations;
    }

    /**
     * Internal method to apply that rule to a given Page which provides some convenience methods to allocate tags.
     *
     * @param page       page under test
     * @param violations list of violations
     */
    protected abstract void applyTo(Page page, Violations violations);

    /**
     * Creates a new violation for that rule with the line number of the violating element in the given page and a
     * message that describes the violation in detail.
     *
     * @param lineNumber line number where the violation occurs
     * @param message    description of the violation
     * @return new violation to be added to the list of violations
     */
    protected Violation createViolation(int lineNumber, String message) {
        return new Violation(this, lineNumber, message);
    }

    /**
     * Returns a String representation of the name of that rule. Per default this results in a call of the getName()
     * method.
     *
     * @return name of that rule
     */
    @Override
    public String toString() {
        return getName();
    }

}
