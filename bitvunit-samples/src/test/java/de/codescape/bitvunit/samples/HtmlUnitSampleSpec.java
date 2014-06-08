package de.codescape.bitvunit.samples;

import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import org.junit.Test;

import static de.codescape.bitvunit.hamcrest.ComplianceMatcher.compliantTo;
import static de.codescape.bitvunit.ruleset.AllRules.allRules;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * This test demonstrates testing for accessibility with HtmlUnit.
 *
 * @author Stefan Glase
 * @since 0.7
 */
public class HtmlUnitSampleSpec extends AbstractBaseSpec {

    @Test
    public void testingAccessibilityWithHtmlUnit() throws Exception {
        // create a HtmlUnit WebClient instance
        WebClient webClient = new WebClient();

        // navigate to a page or interact with a page
        HtmlPage page = webClient.getPage(urlForPage("index.html"));

        // assert accessibility
        assertThat(page, is(compliantTo(allRules())));
    }

}
