package de.codescape.bitvunit.util;

import com.gargoylesoftware.htmlunit.html.HtmlPage;
import org.apache.commons.io.IOUtils;
import org.junit.Test;

import java.io.InputStream;
import java.io.Reader;
import java.io.StringReader;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class HtmlPageUtilTest {

    private final String html = "<html><body><p id=\"greeting\">Hello World</p></body></html>";

    @Test
    public void creatingValidHtmlPageFromString() throws Exception {
        assertCorrectHtmlPage(HtmlPageUtil.htmlPageFromString(html));
    }

    @Test
    public void creatingValidHtmlPageFromInputStream() throws Exception {
        InputStream is = IOUtils.toInputStream(html);
        assertCorrectHtmlPage(HtmlPageUtil.htmlPageFromInputStream(is));
    }

    @Test
    public void creatingValidHtmlPageFromReader() throws Exception {
        Reader reader = new StringReader(html);
        assertCorrectHtmlPage(HtmlPageUtil.htmlPageFromReader(reader));
    }

    private void assertCorrectHtmlPage(HtmlPage htmlPage) {
        assertNotNull(htmlPage);
        assertEquals("Hello World", htmlPage.getElementById("greeting").getTextContent());
    }

}
