package de.codescape.bitvunit.model;

import com.gargoylesoftware.htmlunit.html.HtmlElement;
import com.gargoylesoftware.htmlunit.html.HtmlHtml;
import com.gargoylesoftware.htmlunit.html.HtmlTitle;
import org.junit.Test;

import java.util.List;

import static de.codescape.bitvunit.util.html.HtmlPageUtil.toHtmlPage;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
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
        List<HtmlTitle> allTitleTags = new Page(toHtmlPage(content)).findAllTitleTags();
        assertThat(allTitleTags.size(), is(equalTo(1)));
        assertThat(allTitleTags.get(0).getTextContent(), is(equalTo(EXPECTED_TITLE)));
    }

    @Test
    public void findHtmlElementByIdShouldReturnNullForNoElementWithGivenId() {
        String content = "<html><body></body></html>";
        Page page = new Page(toHtmlPage(content));
        assertNull(page.findHtmlElementById("missingId"));
    }

    @Test
    public void findAllElementsWithAttributeShouldFindElementsWithAGivenAttributeName() {
        String content = "<html><body>" +
                "<div class=\"demo\">first element with attribute</div>" +
                "<span class=\"demo\">second element with attribute</span>" +
                "</body></html>";
        Page page = new Page(toHtmlPage(content));
        assertEquals(2, page.findAllElementsWithAttribute("class").size());
    }

    @Test
    public void findHtmlTagShouldReturnTheRootHtmlElement() {
        String content = "<html></html>";
        Page page = new Page(toHtmlPage(content));
        assertEquals(HtmlHtml.class, page.findHtmlTag().getClass());
    }

}
