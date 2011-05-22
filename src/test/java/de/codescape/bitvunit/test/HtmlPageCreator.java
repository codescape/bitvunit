package de.codescape.bitvunit.test;

import com.gargoylesoftware.htmlunit.MockWebConnection;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlPage;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

public class HtmlPageCreator {

    public static HtmlPage create(String content) {
        WebClient webClient = new WebClient();
        MockWebConnection mockWebConnection = new MockWebConnection();
        URL fakeURL = createFakeURL();
        mockWebConnection.setResponse(fakeURL, content);
        webClient.setWebConnection(mockWebConnection);

        try {
            return webClient.getPage(fakeURL);
        } catch (IOException e) {
            throw new RuntimeException("Error creating HtmlPage with given content.", e);
        }
    }

    private static URL createFakeURL() {
        try {
            return new URL("http://localhost:8080/page.htm");
        } catch (MalformedURLException e) {
            throw new RuntimeException("Error creating fake URL.", e);
        }
    }

}
