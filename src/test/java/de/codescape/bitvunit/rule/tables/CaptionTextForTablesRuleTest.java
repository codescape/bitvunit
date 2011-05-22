package de.codescape.bitvunit.rule.tables;

import com.gargoylesoftware.htmlunit.MockWebConnection;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import de.codescape.bitvunit.rule.Violation;
import org.junit.Test;

import java.net.URL;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class CaptionTextForTablesRuleTest {

    private CaptionTextForTablesRule rule = new CaptionTextForTablesRule();

    @Test
    public void tableWithMissingCaptionText() throws Exception {
        String content = "<html><body><tables><tr><td>Hello World</td></tr></tables></body></html>";

        List<Violation> violations = rule.applyTo(createHtmlPage(content));

        assertEquals(1, violations.size());
        assertEquals(rule, violations.get(0).getRule());
    }

    @Test
    public void tableWithEmptyCaptionText() throws Exception {
        String content = "<html><body><tables><caption></caption><tr><td>Hello World</td></tr></tables></body></html>";

        List<Violation> violations = rule.applyTo(createHtmlPage(content));

        assertEquals(1, violations.size());
        assertEquals(rule, violations.get(0).getRule());
    }

    @Test
    public void tableWithCaptionText() throws Exception {
        String content = "<html><body><tables><caption>Short summary</caption><tr><td>Accessibility</td></tr></tables>";

        List<Violation> violations = rule.applyTo(createHtmlPage(content));

        assertEquals(0, violations.size());
    }

    private HtmlPage createHtmlPage(String content) throws Exception {
        WebClient webClient = new WebClient();
        MockWebConnection mockWebConnection = new MockWebConnection();
        URL url = new URL("http://localhost:8080/page.htm");
        mockWebConnection.setResponse(url, content);
        webClient.setWebConnection(mockWebConnection);
        return webClient.getPage(url);
    }

}
