package de.codescape.bitvunit.test;

import com.gargoylesoftware.htmlunit.html.HtmlPage;
import org.junit.Test;

import static de.codescape.bitvunit.test.HtmlPageCreator.createHtmlPage;
import static org.junit.Assert.assertEquals;

public class HtmlPageCreatorTest {

    @Test
    public void creatingAHtmlPage() throws Exception {
        String content = "<html><body><p id=\"text\">Hello World</p></body></html>";
        HtmlPage htmlPage = createHtmlPage(content);
        assertEquals("Hello World", htmlPage.getElementById("text").getTextContent());
    }

}
