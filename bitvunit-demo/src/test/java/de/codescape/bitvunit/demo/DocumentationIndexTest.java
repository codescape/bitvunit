package de.codescape.bitvunit.demo;

import de.codescape.bitvunit.ruleset.XmlRuleSet;
import org.junit.Test;

import java.net.URL;

import static de.codescape.bitvunit.hamcrest.ComplianceMatcher.compliantTo;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class DocumentationIndexTest {

    private static final String DOCUMENTATION_INDEX_PAGE = "http://bitvunit.codescape.de";

    @Test
    public void documentationIndexPageShouldBeCompliantToAllRules() throws Exception {
        assertThat(new URL(DOCUMENTATION_INDEX_PAGE), is(compliantTo(new XmlRuleSet("/rulesets/all-rules.xml"))));
    }

}
