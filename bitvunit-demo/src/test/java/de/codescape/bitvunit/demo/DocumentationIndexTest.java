package de.codescape.bitvunit.demo;

import de.codescape.bitvunit.ruleset.RuleSet;
import de.codescape.bitvunit.ruleset.XmlRuleSet;
import org.junit.Test;

import java.net.URL;

import static de.codescape.bitvunit.hamcrest.ComplianceMatcher.compliantTo;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * This test demonstrates how to use the Hamcrest matcher provided by the framework to check the starting page of the
 * documentation page for this framework against all accessibility rules that are currently supported.
 *
 * @author Stefan Glase
 */
public class DocumentationIndexTest {

    private static final String DOCUMENTATION_INDEX_PAGE = "http://bitvunit.codescape.de";
    private static final RuleSet ALL_RULES = new XmlRuleSet("/rulesets/all-rules.xml");

    @Test
    public void documentationIndexPageShouldBeCompliantToAllRules() throws Exception {
        assertThat(new URL(DOCUMENTATION_INDEX_PAGE), is(compliantTo(ALL_RULES)));
    }

}
