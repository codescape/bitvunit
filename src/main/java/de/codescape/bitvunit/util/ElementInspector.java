package de.codescape.bitvunit.util;

import com.gargoylesoftware.htmlunit.html.HtmlElement;

public abstract class ElementInspector {

    public static boolean elementHasValidId(HtmlElement element) {
        return element.getId() != null && !element.getId().isEmpty();
    }

    public static boolean elementHasNonEmptyAttribute(HtmlElement element, String attribute) {
        return elementHasAttribute(element, attribute) && !element.getAttribute(attribute).isEmpty();
    }

    public static boolean elementHasAttribute(HtmlElement element, String attribute) {
        return element.getAttributesMap().containsKey(attribute);
    }

}
