package de.codescape.bitvunit.rule.text;

import de.codescape.bitvunit.rule.Violations;
import org.junit.Test;

import static de.codescape.bitvunit.test.Assertions.assertNoViolations;
import static de.codescape.bitvunit.test.Assertions.assertViolations;
import static de.codescape.bitvunit.util.HtmlPageUtil.toHtmlPage;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class LanguageForHtmlTagRuleTest {

    private LanguageForHtmlTagRule rule = new LanguageForHtmlTagRule();

    @Test
    public void ruleHasAName() throws Exception {
        assertNotNull(rule.getName());
        assertEquals("LanguageForHtmlTag", rule.getName());
    }

    @Test
    public void languageAttributeMissing() throws Exception {
        String content = "<html><body><p>Hello World</p></body></html>";
        Violations violations = rule.applyTo(toHtmlPage(content));
        assertViolations(violations, rule, 1);
    }

    @Test
    public void languageAttributeExists() throws Exception {
        String content = "<html lang=\"en\"><body><p>Hello World</p></body></html>";
        Violations violations = rule.applyTo(toHtmlPage(content));
        assertNoViolations(violations);
    }

}
