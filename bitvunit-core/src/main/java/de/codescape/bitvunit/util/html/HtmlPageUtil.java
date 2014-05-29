package de.codescape.bitvunit.util.html;

import com.gargoylesoftware.htmlunit.StringWebResponse;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HTMLParser;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import org.apache.commons.io.IOUtils;
import org.openqa.selenium.WebDriver;

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
    public static HtmlPage toHtmlPage(String string) {
        try {
            URL url = new URL("http://bitvunit.codescape.de/some_page.html");
            return HTMLParser.parseHtml(new StringWebResponse(string, url), new WebClient().getCurrentWindow());
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
    public static HtmlPage toHtmlPage(InputStream inputStream) {
        try {
            return toHtmlPage(IOUtils.toString(inputStream));
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
    public static HtmlPage toHtmlPage(Reader reader) {
        try {
            return toHtmlPage(IOUtils.toString(reader));
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
    public static HtmlPage toHtmlPage(URL url) {
        try {
            return (HtmlPage) new WebClient().getPage(url);
        } catch (IOException e) {
            throw new RuntimeException("Error creating HtmlPage from URL.", e);
        }
    }

    /**
     * Create a {@link HtmlPage} from a given {@link WebDriver} that has been navigated to a HTML page.
     *
     * @param webDriver {@link WebDriver} that has been navigated to a HTML page
     * @return {@link HtmlPage} for the {@link WebDriver}
     */
    public static HtmlPage toHtmlPage(WebDriver webDriver) {
        try {
            return HTMLParser.parseHtml(
                    new StringWebResponse(webDriver.getPageSource(), new URL(webDriver.getCurrentUrl())),
                    new WebClient().getCurrentWindow()
            );
        } catch (IOException e) {
            throw new RuntimeException("Error creating HtmlPage from WebDriver.", e);
        }
    }

    /**
     * Creates a {@link HtmlPage} from the given <code>item</code> if it is of one of the supported types.
     * <p>
     * <b>Supported types:</b>
     * <ul> <li>com.gargoylesoftware.htmlunit.html.HtmlPage</li> <li>java.io.InputStream</li> <li>java.io.Reader</li>
     * <li>java.lang.String</li> <li>java.net.URL</li><li>org.openqa.selenium.WebDriver</li> </ul>
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
            return toHtmlPage((String) item);
        }
        if (item instanceof Reader) {
            return toHtmlPage((Reader) item);
        }
        if (item instanceof URL) {
            return toHtmlPage((URL) item);
        }
        if (item instanceof InputStream) {
            return toHtmlPage((InputStream) item);
        }
        if (item instanceof WebDriver) {
            return toHtmlPage((WebDriver) item);
        }
        throw new UnsupportedOperationException("Unable to create HtmlPage from " + item.getClass() + ".");
    }

}
