package de.codescape.bitvunit.model;

import com.gargoylesoftware.htmlunit.html.HtmlElement;
import org.junit.Test;

import java.util.List;

import static de.codescape.bitvunit.util.HtmlPageUtil.htmlPageFromString;
import static org.junit.Assert.*;

public class PageTest {

    @Test
    public void findAllHeadingTagsReturnsHeadingsOfEveryLevel() throws Exception {
        String content = "<html><body>" + "<h1>First Heading</h1>" + "<h2>Second Heading</h2>" +
                "<h3>Third Heading</h3>" + "<h4>Fourth Heading</h4>" + "<h5>Fifth Heading</h5>" +
                "<h6>Sixth Heading</h6>" + "</body></html>";
        List<HtmlElement> headings = new Page(htmlPageFromString(content)).findAllHeadingTags();
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

}
