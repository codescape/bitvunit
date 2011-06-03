package de.codescape.bitvunit.test.integration;

import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import de.codescape.bitvunit.ruleset.XmlRuleSet;
import org.junit.Test;

import static org.junit.Assert.assertFalse;

public class JUnitIntegrationTest extends AbstractIntegrationTest {

    @Test
    public void checkFirstPageOfDocumentation() throws Exception {
        HtmlPage page = webClient.getPage(BITVUNIT_DOCUMENTATION_URL);
        assertFalse(new XmlRuleSet("/rulesets/all-rules.xml").applyTo(page).hasViolations());
    }

}
