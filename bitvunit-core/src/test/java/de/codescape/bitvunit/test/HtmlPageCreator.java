package de.codescape.bitvunit.test;

import com.gargoylesoftware.htmlunit.StringWebResponse;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HTMLParser;
import com.gargoylesoftware.htmlunit.html.HtmlPage;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

public abstract class HtmlPageCreator {

    public static HtmlPage createHtmlPage(String content) {
        return createHtmlPage(new StringWebResponse(content, createFakeURL()));
    }

    private static HtmlPage createHtmlPage(StringWebResponse response) {
        try {
            return HTMLParser.parseHtml(response, new WebClient().getCurrentWindow());
        } catch (IOException e) {
            throw new RuntimeException("Error creating HtmlPage.", e);
        }
    }

    private static URL createFakeURL() {
        try {
            return new URL("http://bitvunit.codescape.de");
        } catch (MalformedURLException e) {
            throw new RuntimeException("Error creating fake URL.", e);
        }
    }

}
