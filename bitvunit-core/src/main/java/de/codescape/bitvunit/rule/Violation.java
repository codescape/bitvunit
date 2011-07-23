package de.codescape.bitvunit.rule;

import com.gargoylesoftware.htmlunit.html.HtmlElement;

import java.net.URL;

/**
 * Every violation of a {@link Rule} should result in a {@link Violation} pointing to the {@link Rule} that was violated
 * providing a message explaining the problem in detail and the code in question.
 *
 * @author Stefan Glase
 */
public class Violation {

    private final Rule rule;
    private final HtmlElement htmlElement;
    private final String message;

    /**
     * Construct a new {@link Violation} providing the {@link Rule} causing this {@link Violation}, a message describing
     * this {@link Violation} in detail and the code in question.
     *
     * @param rule        {@link Rule} causing this {@link Violation}
     * @param htmlElement the {@link HtmlElement} where the {@link Violation} occurs
     * @param message     message describing this {@link Violation}
     */
    public Violation(Rule rule, HtmlElement htmlElement, String message) {
        super();
        this.rule = rule;
        this.htmlElement = htmlElement;
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
     * Returns the position of the {@link Violation} by line number and column number separated by a dash.
     *
     * @return line number and column number separated by a dash
     */
    public String getPosition() {
        return htmlElement.getStartLineNumber() + ":" + htmlElement.getStartColumnNumber();
    }

    /**
     * Returns the {@link URL} of the page where the {@link Violation} occurred.
     *
     * @return the {@link URL} of the page where the {@link Violation} occurred
     */
    public URL getURL() {
        return htmlElement.getPage().getUrl();
    }

    @Override
    public String toString() {
        return "Violation of rule " + rule.getName() + "[url=" + getURL() + ", position=" + getPosition() + ", message=" + message + "]";
    }

}
