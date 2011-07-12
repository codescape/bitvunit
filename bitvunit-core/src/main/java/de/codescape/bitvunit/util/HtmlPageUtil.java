package de.codescape.bitvunit.util;

import com.gargoylesoftware.htmlunit.StringWebResponse;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HTMLParser;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import org.apache.commons.io.IOUtils;

import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;
import java.net.URL;

public final class HtmlPageUtil {

    private HtmlPageUtil() {
        throw new UnsupportedOperationException("Utility class should not be instantiated.");
    }

    /**
     * Creates a {@link HtmlPage} from a given {@link String} that contains the HTML code for that page.
     *
     * @param string {@link String} that contains the HTML code
     * @return {@link HtmlPage} for this {@link String}
     */
    public static HtmlPage htmlPageFromString(String string) {
        try {
            URL url = new URL("http://bitvunit.codescape.de/some_page.html");
            StringWebResponse response = new StringWebResponse(string, url);
            return HTMLParser.parseHtml(response, new WebClient().getCurrentWindow());
        } catch (IOException e) {
            throw new RuntimeException("Error creating HtmlPage from String.", e);
        }
    }

    /**
     * Creates a {@link HtmlPage} from a given {@link InputStream} that reads the HTML code for that page.
     *
     * @param inputStream {@link InputStream} that reads the HTML code
     * @return {@link HtmlPage} for this {@link InputStream}
     */
    public static HtmlPage htmlPageFromInputStream(InputStream inputStream) {
        try {
            return htmlPageFromString(IOUtils.toString(inputStream));
        } catch (IOException e) {
            throw new RuntimeException("Error creating HtmlPage from InputStream.", e);
        }
    }

    /**
     * Creates a {@link HtmlPage} from a given {@link Reader} that reads the HTML code for that page.
     *
     * @param reader {@link Reader} that reads the HTML code
     * @return {@link HtmlPage} for this {@link Reader}
     */
    public static HtmlPage htmlPageFromReader(Reader reader) {
        try {
            return htmlPageFromString(IOUtils.toString(reader));
        } catch (IOException e) {
            throw new RuntimeException("Error creating HtmlPage from Reader.", e);
        }
    }

    /**
     * Creates a {@link HtmlPage} from a given {@link URL} that points to the HTML code for that page.
     *
     * @param url {@link URL} that points to the HTML code
     * @return {@link HtmlPage} for this {@link URL}
     */
    public static HtmlPage htmlPageFromURL(URL url) {
        try {
            return (HtmlPage) new WebClient().getPage(url);
        } catch (IOException e) {
            throw new RuntimeException("Error creating HtmlPage from URL.", e);
        }
    }

}
