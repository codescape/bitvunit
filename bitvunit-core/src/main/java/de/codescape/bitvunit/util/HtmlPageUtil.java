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

/**
 * Utility class to work with {@link HtmlPage} instances.
 *
 * @author Stefan Glase
 */
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

    /**
     * Creates a {@link HtmlPage} from one of the supported types that can be transformed into a {@link HtmlPage} by the
     * framework.
     *
     * @param item item that should be transformed into a {@link HtmlPage}
     * @param <T>  type of the item that should be transformed
     * @return {@link HtmlPage} constructed from the given input
     */
    public static <T> HtmlPage toHtmlPage(T item) {
        if (item instanceof HtmlPage) {
            return (HtmlPage) item;
        }
        if (item instanceof String) {
            return htmlPageFromString((String) item);
        }
        if (item instanceof Reader) {
            return htmlPageFromReader((Reader) item);
        }
        if (item instanceof URL) {
            return htmlPageFromURL((URL) item);
        }
        if (item instanceof InputStream) {
            return htmlPageFromInputStream((InputStream) item);
        }
        throw new UnsupportedOperationException("Unable to create HtmlPage from " + item.getClass() + ".");
    }

}
