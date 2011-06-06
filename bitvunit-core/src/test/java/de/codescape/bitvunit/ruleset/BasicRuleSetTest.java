package de.codescape.bitvunit.ruleset;

import de.codescape.bitvunit.rule.Rule;
import de.codescape.bitvunit.rule.Violations;
import de.codescape.bitvunit.rule.text.AvoidBlinkTextRule;
import de.codescape.bitvunit.rule.text.AvoidMarqueeTextRule;
import org.junit.Before;
import org.junit.Test;

import static de.codescape.bitvunit.test.Assertions.assertRules;
import static de.codescape.bitvunit.test.Assertions.assertViolations;
import static de.codescape.bitvunit.test.HtmlPageCreator.createHtmlPage;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class BasicRuleSetTest {

    private BasicRuleSet ruleSet;

    @Before
    public void setUp() throws Exception {
        ruleSet = new BasicRuleSet();
    }

    @Test
    public void listOfRulesShouldNeverReturnNull() throws Exception {
        assertNotNull(ruleSet.getRules());
    }

    @Test
    public void getRulesShouldReturnAllRules() throws Exception {
        ruleSet.addRule(new AvoidBlinkTextRule());
        ruleSet.addRule(new AvoidMarqueeTextRule());
        assertRules(ruleSet, 2);
    }

    @Test
    public void applyToReturnsViolationsOfAllRules() throws Exception {
        AvoidBlinkTextRule firstRule = new AvoidBlinkTextRule();
        ruleSet.addRule(firstRule);
        AvoidMarqueeTextRule secondRule = new AvoidMarqueeTextRule();
        ruleSet.addRule(secondRule);

        String content = "<html><body><blink>Blinking!</blink><marquee>Running!</marquee></body></html>";
        Violations violations = ruleSet.applyTo(createHtmlPage(content));

        assertViolations(violations, 2);
        assertViolations(violations, firstRule, 1);
        assertViolations(violations, secondRule, 1);
    }

    @Test
    public void toStringReturnsReadableStringForEmptyListOfRules() {
        String expectedString = "[<Empty RuleSet>]";
        assertEquals(expectedString, ruleSet.toString());
    }

    @Test
    public void toStringReturnsReadableStringForSingleRule() {
        String expectedString = "[SingleRuleName]";

        ruleSet.addRule(createRuleWithName("SingleRuleName"));

        assertEquals(expectedString, ruleSet.toString());
    }

    @Test
    public void toStringReturnsReadableStringForMultipleRules() {
        String expectedString = "[FirstRuleName, SecondRuleName]";

        ruleSet.addRule(createRuleWithName("FirstRuleName"));
        ruleSet.addRule(createRuleWithName("SecondRuleName"));

        assertEquals(expectedString, ruleSet.toString());
    }

    private Rule createRuleWithName(String ruleName) {
        Rule secondRule = mock(Rule.class);
        when(secondRule.toString()).thenReturn(ruleName);
        return secondRule;
    }

}
