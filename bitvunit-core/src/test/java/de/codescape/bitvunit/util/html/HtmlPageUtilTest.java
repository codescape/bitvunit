package de.codescape.bitvunit.util.html;

import com.gargoylesoftware.htmlunit.html.HtmlPage;
import de.codescape.bitvunit.test.TestPage;
import org.apache.commons.io.IOUtils;
import org.junit.Test;

import java.io.InputStream;
import java.io.Reader;
import java.io.StringReader;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class HtmlPageUtilTest {

    /* expected title of the HTML page */
    private static final String EXPECTED_TITLE = "Hello World - BitvUnit";

    /* id of the paragraph in the HTML page */
    private static final String PARAGRAPH_ID = "greeting";

    /* expected text content of the paragraph */
    private static final String EXPECTED_PARAGRAPH = "Hello World";

    @Test
    public void createValidHtmlPageFromString() throws Exception {
        assertCorrectHtmlPage(HtmlPageUtil.toHtmlPage(TestPage.asString()));
    }

    @Test
    public void createValidHtmlPageFromInputStream() throws Exception {
        InputStream is = IOUtils.toInputStream(TestPage.asString());
        assertCorrectHtmlPage(HtmlPageUtil.toHtmlPage(is));
    }

    @Test
    public void createValidHtmlPageFromReader() throws Exception {
        Reader reader = new StringReader(TestPage.asString());
        assertCorrectHtmlPage(HtmlPageUtil.toHtmlPage(reader));
    }

    @Test
    public void createValidHtmlPageFromURL() throws Exception {
        assertCorrectHtmlPage(HtmlPageUtil.toHtmlPage(TestPage.asURL()));
    }

    @Test
    public void createValidHtmlPageFromURLAsObject() throws Exception {
        assertCorrectHtmlPage(HtmlPageUtil.toHtmlPage((Object) TestPage.asURL()));
    }

    @Test
    public void createValidHtmlPageFromSeleniumWebDriver() throws Exception {
        assertCorrectHtmlPage(HtmlPageUtil.toHtmlPage(TestPage.asWebDriver()));
    }

    @Test
    public void createValidHtmlPageFromSeleniumWebDriverAsObject() throws Exception {
        assertCorrectHtmlPage(HtmlPageUtil.toHtmlPage((Object) TestPage.asWebDriver()));
    }

    @Test(expected = UnsupportedOperationException.class)
    public void createHtmlPageFromUnsupportedTypeThrowsException() throws Exception {
        HtmlPageUtil.toHtmlPage(new Object());
    }

    private void assertCorrectHtmlPage(HtmlPage htmlPage) {
        assertNotNull(htmlPage);
        assertEquals(EXPECTED_TITLE, htmlPage.getTitleText());

        assertNotNull(htmlPage.getElementById(PARAGRAPH_ID));
        assertEquals(EXPECTED_PARAGRAPH, htmlPage.getElementById("greeting").getTextContent().trim());
    }

}
