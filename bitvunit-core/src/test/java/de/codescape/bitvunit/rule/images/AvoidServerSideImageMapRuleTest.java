package de.codescape.bitvunit.rule.images;

import de.codescape.bitvunit.rule.Violations;
import org.junit.Test;

import static de.codescape.bitvunit.test.Assertions.assertNoViolations;
import static de.codescape.bitvunit.test.Assertions.assertViolations;
import static de.codescape.bitvunit.util.html.HtmlPageUtil.toHtmlPage;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class AvoidServerSideImageMapRuleTest {

    private AvoidServerSideImageMapRule rule = new AvoidServerSideImageMapRule();

    @Test
    public void ruleHasAName() throws Exception {
        assertNotNull(rule.getName());
        assertEquals("AvoidServerSideImageMap", rule.getName());
    }

    @Test
    public void serverSideImageMapPresent() throws Exception {
        String content = "<html><body><img src=\"map.gif\" ismap=\"ismap\"/></body></html>";
        Violations violations = rule.applyTo(toHtmlPage(content));
        assertViolations(violations, rule, 1);
    }

    @Test
    public void clientSideImageMapPresent() throws Exception {
        String content = "<html><body><img src=\"map.gif\" usemap=\"somemap\" /></body></html>";
        Violations violations = rule.applyTo(toHtmlPage(content));
        assertNoViolations(violations, rule);
    }

    @Test
    public void normalImagePresent() throws Exception {
        String content = "<html><body><img src=\"image.gif\" /></body></html>";
        Violations violations = rule.applyTo(toHtmlPage(content));
        assertNoViolations(violations, rule);
    }

}
