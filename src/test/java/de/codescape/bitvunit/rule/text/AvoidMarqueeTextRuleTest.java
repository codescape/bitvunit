package de.codescape.bitvunit.rule.text;

import de.codescape.bitvunit.rule.Violation;
import org.junit.Test;

import java.util.List;

import static de.codescape.bitvunit.test.Assertions.assertNoViolationExists;
import static de.codescape.bitvunit.test.Assertions.assertViolationExists;
import static de.codescape.bitvunit.test.HtmlPageCreator.createHtmlPage;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class AvoidMarqueeTextRuleTest {

    private AvoidMarqueeTextRule rule = new AvoidMarqueeTextRule();

    @Test
    public void ruleHasAName() throws Exception {
        assertNotNull(rule.getName());
        assertEquals("AvoidMarqueeText", rule.getName());
    }

    @Test
    public void marqueeTextPresent() throws Exception {
        String content = "<html><body><marquee>Hello World</marquee></body></html>";
        List<Violation> violations = rule.applyTo(createHtmlPage(content));
        assertViolationExists(violations, rule);
    }

    @Test
    public void marqueeTextNotPresent() throws Exception {
        String content = "<html><body><p>Hello World</p></body></html>";
        List<Violation> violations = rule.applyTo(createHtmlPage(content));
        assertNoViolationExists(violations);
    }

}
