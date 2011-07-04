package de.codescape.bitvunit.util;

import com.gargoylesoftware.htmlunit.html.HtmlPage;
import org.apache.commons.io.IOUtils;
import org.junit.Test;

import java.io.InputStream;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class HtmlPageUtilTest {

    @Test
    public void creatingValidHtmlPageFromString() throws Exception {
        String content = "<html><body><p id=\"greeting\">Hello World</p></body></html>";
        HtmlPage htmlPage = HtmlPageUtil.htmlPageFromString(content);
        assertNotNull(htmlPage);
        assertEquals("Hello World", htmlPage.getElementById("greeting").getTextContent());
    }

    @Test
    public void creatingValidHtmlPageFromInputStream() throws Exception {
        InputStream is = IOUtils.toInputStream("<html><body><p id=\"greeting\">Hello World</p></body></html>");
        HtmlPage htmlPage = HtmlPageUtil.htmlPageFromInputStream(is);
        assertNotNull(htmlPage);
        assertEquals("Hello World", htmlPage.getElementById("greeting").getTextContent());
    }

}
