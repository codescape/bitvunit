package de.codescape.bitvunit.util.html;

import com.gargoylesoftware.htmlunit.html.HtmlPage;
import de.codescape.bitvunit.util.io.ClassPathResource;
import org.apache.commons.io.IOUtils;
import org.junit.Test;

import java.io.InputStream;
import java.io.Reader;
import java.io.StringReader;
import java.net.URL;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class HtmlPageUtilTest {

    /* path to the file that is used in this test */
    private static final String FILE_NAME = "sample-page.html";

    /* expected title of the HTML page */
    private static final String EXPECTED_TITLE = "Hello World - BitvUnit";

    /* id of the paragraph in the HTML page */
    private static final String PARAGRAPH_ID = "greeting";

    /* expected text content of the paragraph */
    private static final String EXPECTED_PARAGRAPH = "Hello World";

    @Test
    public void createValidHtmlPageFromString() throws Exception {
        assertCorrectHtmlPage(HtmlPageUtil.toHtmlPage(pageAsString()));
    }

    @Test
    public void createValidHtmlPageFromInputStream() throws Exception {
        InputStream is = IOUtils.toInputStream(pageAsString());
        assertCorrectHtmlPage(HtmlPageUtil.toHtmlPage(is));
    }

    @Test
    public void createValidHtmlPageFromReader() throws Exception {
        Reader reader = new StringReader(pageAsString());
        assertCorrectHtmlPage(HtmlPageUtil.toHtmlPage(reader));
    }

    @Test
    public void createValidHtmlPageFromURL() throws Exception {
        URL url = getClass().getClassLoader().getResource(FILE_NAME);
        assertCorrectHtmlPage(HtmlPageUtil.toHtmlPage(url));
    }

    @Test(expected = UnsupportedOperationException.class)
    public void createHtmlPageFromUnsupportedTypeThrowsException() throws Exception {
        HtmlPageUtil.toHtmlPage(new Object());
    }

    private void assertCorrectHtmlPage(HtmlPage htmlPage) {
        assertNotNull(htmlPage);
        assertEquals(EXPECTED_TITLE, htmlPage.getTitleText());

        assertNotNull(htmlPage.getElementById(PARAGRAPH_ID));
        assertEquals(EXPECTED_PARAGRAPH, htmlPage.getElementById("greeting").getTextContent());
    }

    private String pageAsString() {
        return ClassPathResource.asString(FILE_NAME);
    }

}