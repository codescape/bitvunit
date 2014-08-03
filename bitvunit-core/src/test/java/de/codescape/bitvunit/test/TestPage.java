package de.codescape.bitvunit.test;

import com.gargoylesoftware.htmlunit.html.HtmlPage;
import org.apache.commons.io.IOUtils;

import java.io.InputStream;
import java.io.Reader;
import java.io.StringReader;
import java.net.URL;

import static de.codescape.bitvunit.util.html.HtmlPageUtil.toHtmlPage;

/**
 * HTML page for testing purposes only.
 */
public class TestPage {

    public static String asString() {
        return "<html><body><p>Hello Hamcrest!</p></body></html>";
    }

    public static HtmlPage asHtmlPage() {
        return toHtmlPage(asString());
    }

    public static Reader asReader() {
        return new StringReader(asString());
    }

    public static URL asURL() {
        return TestPage.class.getClassLoader().getResource("sample-page.html");
    }

    public static InputStream asInputStream() {
        return IOUtils.toInputStream(asString());
    }

}
