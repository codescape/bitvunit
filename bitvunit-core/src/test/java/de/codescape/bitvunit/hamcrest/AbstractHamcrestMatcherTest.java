package de.codescape.bitvunit.hamcrest;

import com.gargoylesoftware.htmlunit.html.HtmlPage;
import de.codescape.bitvunit.rule.Rule;
import de.codescape.bitvunit.rule.Violation;
import de.codescape.bitvunit.rule.Violations;
import de.codescape.bitvunit.ruleset.RuleSet;
import org.junit.Before;

import static de.codescape.bitvunit.util.HtmlPageUtil.htmlPageFromString;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class AbstractHamcrestMatcherTest {

    protected Rule satisfiedRule;
    protected Rule unsatisfiedRule;

    protected RuleSet satisfiedRuleSet;
    protected RuleSet unsatisfiedRuleSet;

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

    protected String somePageString() {
        return "<html><body><p>Hello Hamcrest!</p></body></html>";
    }

    protected HtmlPage somePage() {
        return htmlPageFromString(somePageString());
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
