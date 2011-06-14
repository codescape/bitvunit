package de.codescape.bitvunit.test.integration;

import com.gargoylesoftware.htmlunit.html.HtmlPage;
import de.codescape.bitvunit.rule.text.LanguageForHtmlTagRule;
import de.codescape.bitvunit.ruleset.XmlRuleSet;
import org.junit.Test;

import static de.codescape.bitvunit.hamcrest.RuleMatcher.compliantTo;
import static de.codescape.bitvunit.hamcrest.RuleSetMatcher.compliantTo;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class JUnitAndHamcrestIntegrationTest extends AbstractIntegrationTest {

    @Test
    public void checkFirstPageOfDocumentationAgainstRuleSet() throws Exception {
        HtmlPage page = webClient.getPage(BITVUNIT_DOCUMENTATION_URL);
        assertThat(page, is(compliantTo(new XmlRuleSet("/rulesets/all-rules.xml"))));
    }

    @Test
    public void checkFirstPageOfDocumentationAgainstRule() throws Exception {
        HtmlPage page = webClient.getPage(BITVUNIT_DOCUMENTATION_URL);
        assertThat(page, is(compliantTo(new LanguageForHtmlTagRule())));
    }

}