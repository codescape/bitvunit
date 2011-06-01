package de.codescape.bitvunit.hamcrest;

import com.gargoylesoftware.htmlunit.html.HtmlPage;
import de.codescape.bitvunit.rule.Rule;
import de.codescape.bitvunit.rule.Violation;
import de.codescape.bitvunit.rule.Violations;
import de.codescape.bitvunit.ruleset.RuleSet;
import org.junit.Before;
import org.junit.Test;

import static de.codescape.bitvunit.hamcrest.RuleSetMatcher.complaintTo;
import static de.codescape.bitvunit.test.HtmlPageCreator.createHtmlPage;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsNot.not;
import static org.junit.Assert.assertThat;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class RuleSetMatcherTest {

    private Rule someRule;

    private RuleSet satisfiedRuleSet;
    private RuleSet unsatisfiedRuleSet;

    @Before
    public void setUp() throws Exception {
        someRule = mock(Rule.class);

        satisfiedRuleSet = mock(RuleSet.class);
        when(satisfiedRuleSet.applyTo(any(HtmlPage.class))).thenReturn(noViolation());

        unsatisfiedRuleSet = mock(RuleSet.class);
        when(unsatisfiedRuleSet.applyTo(any(HtmlPage.class))).thenReturn(someViolation(someRule));
    }

    @Test
    public void matchesPositivelyToComplianceOfRuleSet() throws Exception {
        assertThat(somePage(), is(complaintTo(satisfiedRuleSet)));
    }

    @Test
    public void matchesNegativelyToViolationOfRuleSet() throws Exception {
        assertThat(somePage(), not(complaintTo(unsatisfiedRuleSet)));
    }

    private HtmlPage somePage() {
        return createHtmlPage("<html></html>");
    }


    private Violations someViolation(Rule rule) {
        Violations violations = new Violations();
        violations.add(new Violation(rule, 1, "uh oh"));
        return violations;
    }

    private Violations noViolation() {
        return new Violations();
    }

}
