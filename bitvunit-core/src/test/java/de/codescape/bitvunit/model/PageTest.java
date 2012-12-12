package de.codescape.bitvunit.model;

import com.gargoylesoftware.htmlunit.html.HtmlElement;
import com.gargoylesoftware.htmlunit.html.HtmlTitle;
import org.junit.Test;

import java.util.List;

import static de.codescape.bitvunit.util.html.HtmlPageUtil.toHtmlPage;
import static org.junit.Assert.*;

public class PageTest {

    public static final String EXPECTED_TITLE = "Expected Title";

    @Test
    public void findAllHeadingTagsReturnsHeadingsOfEveryLevel() throws Exception {
        String content = "<html><body>" + "<h1>First Heading</h1>" + "<h2>Second Heading</h2>" +
                "<h3>Third Heading</h3>" + "<h4>Fourth Heading</h4>" + "<h5>Fifth Heading</h5>" +
                "<h6>Sixth Heading</h6>" + "</body></html>";
        List<HtmlElement> headings = new Page(toHtmlPage(content)).findAllHeadingTags();
        assertNotNull(headings);
        assertEquals(6, headings.size());

        for (int i = 1; i < 7; i++) {
            boolean found = false;
            for (HtmlElement heading : headings) {
                if (heading.getTagName().equals("h" + i)) {
                    found = true;
                }
            }
            assertTrue(found);
        }
    }

    @Test
    public void findTitleTagShouldReturnTheCorrectTitle() {
        String content = "<html><head><title>" + EXPECTED_TITLE + "</title><body></body></html>";
        HtmlTitle titleTag = new Page(toHtmlPage(content)).findTitleTag();
        assertNotNull(titleTag);
        assertEquals(EXPECTED_TITLE, titleTag.getTextContent());
    }

    @Test
    public void findHtmlElementByIdShouldReturnNullForNoElementWithGivenId() {
        String content = "<html><body></body></html>";
        Page page = new Page(toHtmlPage(content));
        assertNull(page.findHtmlElementById("missingId"));
    }

}
