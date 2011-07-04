package de.codescape.bitvunit.util;

import com.gargoylesoftware.htmlunit.StringWebResponse;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HTMLParser;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import org.apache.commons.io.IOUtils;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

public class HtmlPageUtil {

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

}
