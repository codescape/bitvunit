package de.codescape.bitvunit.rule;

import com.gargoylesoftware.htmlunit.html.HtmlElement;
import de.codescape.bitvunit.model.Page;

import java.util.List;

/**
 * Abstract rule that should be used for elements that should be generally avoided for accessibility reasons.
 *
 * @param <T> element that should be generally avoided
 * @author Stefan Glase
 * @since 0.10
 */
public abstract class AvoidElementRule<T extends HtmlElement> extends AbstractRule {

    @Override
    protected final void applyTo(Page page, Violations violations) {
        for (T element : violatingElements(page)) {
            violations.add(createViolation(element, page, getMessage()));
        }
    }

    /**
     * Return the error message if an element that should be avoided is found on the page.
     *
     * @return error message
     */
    protected abstract String getMessage();

    /**
     * Return the list of elements that should be reported as a violation of the given rule
     *
     * @param page page under test
     * @return forbidden elements
     */
    protected abstract List<T> violatingElements(Page page);

}
