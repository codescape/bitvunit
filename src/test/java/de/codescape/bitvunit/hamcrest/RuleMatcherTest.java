package de.codescape.bitvunit.hamcrest;

import com.gargoylesoftware.htmlunit.html.HtmlPage;
import de.codescape.bitvunit.rule.Rule;
import de.codescape.bitvunit.rule.Violation;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static de.codescape.bitvunit.hamcrest.RuleMatcher.complaintToRule;
import static de.codescape.bitvunit.test.HtmlPageCreator.createHtmlPage;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsNot.not;
import static org.junit.Assert.assertThat;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class RuleMatcherTest {

    private Rule satisfiedRule;
    private Rule unsatisfiedRule;

    @Before
    public void setUp() throws Exception {
        satisfiedRule = mock(Rule.class);
        when(satisfiedRule.applyTo(any(HtmlPage.class))).thenReturn(noViolation());

        unsatisfiedRule = mock(Rule.class);
        when(unsatisfiedRule.applyTo(any(HtmlPage.class))).thenReturn(someViolation());
    }

    @Test
    public void matchesPositivelyToComplianceOfRule() throws Exception {
        assertThat(somePage(), is(complaintToRule(satisfiedRule)));
    }

    @Test
    public void matchesNegativelyToViolationOfRule() throws Exception {
        assertThat(somePage(), not(complaintToRule(unsatisfiedRule)));
    }

    private HtmlPage somePage() {
        return createHtmlPage("<html></html>");
    }


    private List<Violation> someViolation() {
        return Arrays.asList(new Violation(unsatisfiedRule, 1, "uh oh"));
    }

    private ArrayList<Violation> noViolation() {
        return new ArrayList<Violation>();
    }

}
