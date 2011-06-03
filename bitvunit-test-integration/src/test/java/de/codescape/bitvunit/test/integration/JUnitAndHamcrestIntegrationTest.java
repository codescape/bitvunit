package de.codescape.bitvunit.test.integration;

import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import de.codescape.bitvunit.ruleset.XmlRuleSet;
import org.junit.Test;

import static de.codescape.bitvunit.hamcrest.RuleSetMatcher.complaintTo;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class JUnitAndHamcrestIntegrationTest extends AbstractIntegrationTest {

    @Test
    public void checkFirstPageOfDocumentation() throws Exception {
        HtmlPage page = webClient.getPage(BITVUNIT_DOCUMENTATION_URL);
        assertThat(page, is(complaintTo(new XmlRuleSet("/rulesets/all-rules.xml"))));
    }

}
