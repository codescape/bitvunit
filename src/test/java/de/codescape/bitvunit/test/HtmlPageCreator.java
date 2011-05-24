package de.codescape.bitvunit.test;

import com.gargoylesoftware.htmlunit.MockWebConnection;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlPage;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

public abstract class HtmlPageCreator {

    public static HtmlPage create(String content) {
        URL fakeURL = createFakeURL();
        try {
            return createWebClient(content, fakeURL).getPage(fakeURL);
        } catch (IOException e) {
            throw new RuntimeException("Error creating HtmlPage with given content.", e);
        }
    }

    private static WebClient createWebClient(String content, URL fakeURL) {
        WebClient webClient = new WebClient();
        MockWebConnection mockWebConnection = new MockWebConnection();
        mockWebConnection.setResponse(fakeURL, content);
        webClient.setWebConnection(mockWebConnection);
        return webClient;
    }

    private static URL createFakeURL() {
        try {
            return new URL("http://localhost:8080/page.htm");
        } catch (MalformedURLException e) {
            throw new RuntimeException("Error creating fake URL.", e);
        }
    }

}
