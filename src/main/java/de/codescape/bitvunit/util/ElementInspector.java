package de.codescape.bitvunit.util;

import com.gargoylesoftware.htmlunit.html.HtmlElement;

public abstract class ElementInspector {

    public static boolean elementHasValidId(HtmlElement element) {
        return element.getId() != null && !element.getId().isEmpty();
    }

}
