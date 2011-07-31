package de.codescape.bitvunit.hamcrest;

import com.gargoylesoftware.htmlunit.html.HtmlPage;
import de.codescape.bitvunit.rule.Rule;
import de.codescape.bitvunit.rule.Violation;
import de.codescape.bitvunit.rule.Violations;
import de.codescape.bitvunit.ruleset.RuleSet;
import org.apache.commons.io.IOUtils;
import org.junit.Before;
import org.junit.Test;

import java.io.InputStream;
import java.io.Reader;
import java.io.StringReader;
import java.net.URL;

import static de.codescape.bitvunit.hamcrest.ComplianceMatcher.compliantTo;
import static de.codescape.bitvunit.util.HtmlPageUtil.toHtmlPage;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.fail;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class ComplianceMatcherTest {

    private Rule satisfiedRule;
    private Rule unsatisfiedRule;

    private RuleSet satisfiedRuleSet;
    private RuleSet unsatisfiedRuleSet;

    @Before
    public void setUp() throws Exception {
        satisfiedRule = mock(Rule.class);
        when(satisfiedRule.applyTo(any(HtmlPage.class))).thenReturn(noViolation());

        unsatisfiedRule = mock(Rule.class);
        when(unsatisfiedRule.applyTo(any(HtmlPage.class))).thenReturn(someViolation(unsatisfiedRule));

        satisfiedRuleSet = mock(RuleSet.class);
        when(satisfiedRuleSet.applyTo(any(HtmlPage.class))).thenReturn(noViolation());

        unsatisfiedRuleSet = mock(RuleSet.class);
        when(unsatisfiedRuleSet.applyTo(any(HtmlPage.class))).thenReturn(someViolation(unsatisfiedRule));
    }

    @Test
    public void compliantToForHtmlPageAgainstRuleSet() throws Exception {
        assertThat(somePage(), is(compliantTo(satisfiedRuleSet)));
        assertThat(somePage(), is(not(compliantTo(unsatisfiedRuleSet))));
    }

    @Test
    public void compliantToForHtmlPageAgainstRule() throws Exception {
        assertThat(somePage(), is(compliantTo(satisfiedRule)));
        assertThat(somePage(), is(not(compliantTo(unsatisfiedRule))));
    }

    @Test
    public void compliantToForStringAgainstRuleSet() throws Exception {
        assertThat(someString(), is(compliantTo(satisfiedRuleSet)));
        assertThat(someString(), is(not(compliantTo(unsatisfiedRuleSet))));
    }

    @Test
    public void compliantToForStringAgainstRule() throws Exception {
        assertThat(someString(), is(compliantTo(satisfiedRule)));
        assertThat(someString(), is(not(compliantTo(unsatisfiedRule))));
    }

    @Test
    public void compliantToForReaderAgainstRuleSet() throws Exception {
        assertThat(someReader(), is(compliantTo(satisfiedRuleSet)));
        assertThat(someReader(), is(not(compliantTo(unsatisfiedRuleSet))));
    }

    @Test
    public void compliantToForReaderAgainstRule() throws Exception {
        assertThat(someReader(), is(compliantTo(satisfiedRule)));
        assertThat(someReader(), is(not(compliantTo(unsatisfiedRule))));
    }

    @Test
    public void compliantToForURLAgainstRuleSet() throws Exception {
        assertThat(someURL(), is(compliantTo(satisfiedRuleSet)));
        assertThat(someURL(), is(not(compliantTo(unsatisfiedRuleSet))));
    }

    @Test
    public void compliantToForURLAgainstRule() throws Exception {
        assertThat(someURL(), is(compliantTo(satisfiedRule)));
        assertThat(someURL(), is(not(compliantTo(unsatisfiedRule))));
    }

    @Test
    public void compliantToForInputStreamAgainstRuleSet() throws Exception {
        assertThat(someInputStream(), is(compliantTo(satisfiedRuleSet)));
        assertThat(someInputStream(), is(not(compliantTo(unsatisfiedRuleSet))));
    }

    @Test
    public void compliantToForInputStreamAgainstRule() throws Exception {
        assertThat(someInputStream(), is(compliantTo(satisfiedRule)));
        assertThat(someInputStream(), is(not(compliantTo(unsatisfiedRule))));
    }

    @Test
    public void compliantToForObjectAgainstRuleSet() throws Exception {
        try {
            assertThat(new Object(), is(compliantTo(satisfiedRuleSet)));
            fail();
        } catch (UnsupportedOperationException e) { /* should fail */ }
        try {
            assertThat(new Object(), is(not(compliantTo(unsatisfiedRuleSet))));
            fail();
        } catch (UnsupportedOperationException e) { /* should fail */ }
    }

    @Test
    public void compliantToForObjectPageAgainstRule() throws Exception {
        try {
            assertThat(new Object(), is(compliantTo(satisfiedRule)));
            fail();
        } catch (UnsupportedOperationException e) { /* should fail */ }
        try {
            assertThat(new Object(), is(not(compliantTo(unsatisfiedRule))));
            fail();
        } catch (UnsupportedOperationException e) { /* should fail */ }
    }

    private String someString() {
        return "<html><body><p>Hello Hamcrest!</p></body></html>";
    }

    private HtmlPage somePage() {
        return toHtmlPage(someString());
    }

    private Reader someReader() {
        return new StringReader(someString());
    }

    private URL someURL() {
        return getClass().getClassLoader().getResource("samplePage.html");
    }

    private InputStream someInputStream() {
        return IOUtils.toInputStream(someString());
    }

    private Violations someViolation(Rule rule) {
        Violations violations = new Violations();
        violations.add(new Violation(rule, somePage().getBody(), "uh oh"));
        return violations;
    }

    private Violations noViolation() {
        return new Violations();
    }

}
