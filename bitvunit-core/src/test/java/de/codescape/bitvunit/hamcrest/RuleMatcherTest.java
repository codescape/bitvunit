package de.codescape.bitvunit.hamcrest;

import com.gargoylesoftware.htmlunit.html.HtmlPage;
import de.codescape.bitvunit.rule.Rule;
import de.codescape.bitvunit.rule.Violation;
import de.codescape.bitvunit.rule.Violations;
import org.junit.Before;
import org.junit.Test;

import static de.codescape.bitvunit.hamcrest.RuleMatcher.compliantTo;
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
        assertThat(somePage(), is(compliantTo(satisfiedRule)));
    }

    @Test
    public void matchesNegativelyToViolationOfRule() throws Exception {
        assertThat(somePage(), not(compliantTo(unsatisfiedRule)));
    }

    private HtmlPage somePage() {
        return createHtmlPage("<html></html>");
    }


    private Violations someViolation() {
        Violations violations = new Violations();
        violations.add(new Violation(unsatisfiedRule, 1, "uh oh"));
        return violations;
    }

    private Violations noViolation() {
        return new Violations();
    }

}
