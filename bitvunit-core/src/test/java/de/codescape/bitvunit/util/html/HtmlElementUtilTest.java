package de.codescape.bitvunit.util.html;

import com.gargoylesoftware.htmlunit.html.HtmlElement;
import org.junit.Test;

import static de.codescape.bitvunit.util.html.HtmlPageUtil.toHtmlPage;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class HtmlElementUtilTest {

    @Test
    public void hasAttributeReturnsTrueIfAnEmptyAttributeExists() throws Exception {
        HtmlElement elementWithEmptyAttribute = createHtmlElement("<img src=\"\" alt=\"\"/>");
        assertTrue(HtmlElementUtil.hasAttribute(elementWithEmptyAttribute, "alt"));
    }

    @Test
    public void hasAttributeReturnsTrueIfAnNonEmptyAttributeExists() throws Exception {
        HtmlElement elementWithNonEmptyAttribute = createHtmlElement("<img src=\"\" alt=\"Hello World\"/>");
        assertTrue(HtmlElementUtil.hasAttribute(elementWithNonEmptyAttribute, "alt"));
    }

    @Test
    public void hasAttributeReturnsFalseIfTheAttributeDoesNotExist() throws Exception {
        HtmlElement elementWithMissingAttribute = createHtmlElement("<img src=\"\"/>");
        assertFalse(HtmlElementUtil.hasAttribute(elementWithMissingAttribute, "alt"));
    }

    @Test
    public void hasNonEmptyAttributeReturnsFalseIfAnEmptyAttributeExists() throws Exception {
        HtmlElement elementWithEmptyAttribute = createHtmlElement("<img src=\"\" alt=\"\"/>");
        assertFalse(HtmlElementUtil.hasNonEmptyAttribute(elementWithEmptyAttribute, "alt"));
    }

    @Test
    public void hasNonEmptyAttributeReturnsTrueIfAnNonEmptyAttributeExists() throws Exception {
        HtmlElement elementWithNonEmptyAttribute = createHtmlElement("<img src=\"\" alt=\"Hello World\"/>");
        assertTrue(HtmlElementUtil.hasNonEmptyAttribute(elementWithNonEmptyAttribute, "alt"));
    }

    @Test
    public void hasNonEmptyAttributeReturnsFalseIfTheAttributeDoesNotExist() throws Exception {
        HtmlElement elementWithMissingAttribute = createHtmlElement("<img src=\"\"/>");
        assertFalse(HtmlElementUtil.hasNonEmptyAttribute(elementWithMissingAttribute, "alt"));
    }

    private HtmlElement createHtmlElement(String element) {
        return toHtmlPage("<html><body>" + element + "</body></html>").getBody().getHtmlElementDescendants().iterator().next();
    }

}
