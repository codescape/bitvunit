package de.codescape.bitvunit.util;

import com.gargoylesoftware.htmlunit.html.HtmlElement;

public final class HtmlElementUtil {

    private HtmlElementUtil() {
        throw new UnsupportedOperationException("Utility class should not be instantiated.");
    }

    /**
     * Returns <code>true</code> when the given element contains a non empty attribute with the given attribute name.
     *
     * @param element       element to check for an attribute
     * @param attributeName name of the attribute
     * @return <code>true</code> if an attribute with the given name exists and is not empty, otherwise
     *         <code>false</code>
     */
    public static boolean hasNonEmptyAttribute(HtmlElement element, String attributeName) {
        return hasAttribute(element, attributeName) && !element.getAttribute(attributeName).isEmpty();
    }

    /**
     * Returns <code>true</code> when the given element contains an attribute with the given attribute name.
     *
     * @param element       element to check for an attribute
     * @param attributeName name of the attribute
     * @return <code>true</code> if an attribute with the given name exists, otherwise <code>false</code>
     */
    public static boolean hasAttribute(HtmlElement element, String attributeName) {
        return element.getAttributesMap().containsKey(attributeName);
    }

}
