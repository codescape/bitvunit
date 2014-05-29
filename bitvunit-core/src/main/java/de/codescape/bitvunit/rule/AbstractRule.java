package de.codescape.bitvunit.rule;

import com.gargoylesoftware.htmlunit.html.HtmlElement;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import de.codescape.bitvunit.model.Page;

/**
 * Base class to all implementations of the {@link Rule} interface. It provides convenience methods to create new
 * {@link Violation} instances within the implementation of a {@link Rule} and allows to apply a {@link Rule} to a
 * given {@link HtmlPage} object.
 *
 * @author Stefan Glase
 */
public abstract class AbstractRule implements Rule {

    private Priority priority = Priority.NORMAL;

    @Override
    public abstract String getName();

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
     * @param htmlElement the {@link com.gargoylesoftware.htmlunit.html.HtmlElement} where the violation occurs
     * @param page        the page where the violation occurs
     * @param message     description of the violation  @return new violation to be added to the list of violations
     * @return Violation describing the error
     */
    protected Violation createViolation(HtmlElement htmlElement, Page page, String message) {
        if (htmlElement == null)
            htmlElement = page.findHtmlTag();
        return new Violation(this, htmlElement, message);
    }

    /**
     * Returns the {@link String} representation of a common {@link Rule} implementation.
     *
     * @return {@link String} representation of a common {@link Rule} implementation
     */
    @Override
    public String toString() {
        return getClass().getSimpleName() + "[name=" + getName() + ", priority=" + getPriority() + "]";
    }

    @Override
    public Priority getPriority() {
        return priority;
    }

    @Override
    public void setPriority(Priority priority) {
        this.priority = priority;
    }

}
