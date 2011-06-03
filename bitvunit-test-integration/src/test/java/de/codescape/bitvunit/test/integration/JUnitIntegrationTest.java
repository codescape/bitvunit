package de.codescape.bitvunit.test.integration;

import com.gargoylesoftware.htmlunit.html.HtmlPage;
import de.codescape.bitvunit.rule.text.LanguageForHtmlTagRule;
import de.codescape.bitvunit.ruleset.XmlRuleSet;
import org.junit.Test;

import static org.junit.Assert.assertFalse;

public class JUnitIntegrationTest extends AbstractIntegrationTest {

    @Test
    public void checkFirstPageOfDocumentationAgainstRuleSet() throws Exception {
        HtmlPage page = webClient.getPage(BITVUNIT_DOCUMENTATION_URL);
        assertFalse(new XmlRuleSet("/rulesets/all-rules.xml").applyTo(page).hasViolations());
    }

    @Test
    public void checkFirstPageOfDocumentationAgainstRule() throws Exception {
        HtmlPage page = webClient.getPage(BITVUNIT_DOCUMENTATION_URL);
        assertFalse(new LanguageForHtmlTagRule().applyTo(page).hasViolations());
    }

}
