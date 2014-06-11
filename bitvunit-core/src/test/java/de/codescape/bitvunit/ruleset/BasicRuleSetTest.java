package de.codescape.bitvunit.ruleset;

import de.codescape.bitvunit.rule.Priority;
import de.codescape.bitvunit.rule.Rule;
import de.codescape.bitvunit.rule.Violations;
import de.codescape.bitvunit.rule.text.AvoidBlinkTextRule;
import de.codescape.bitvunit.rule.text.AvoidMarqueeTextRule;
import org.junit.Before;
import org.junit.Test;

import static de.codescape.bitvunit.test.Assertions.assertRules;
import static de.codescape.bitvunit.test.Assertions.assertViolations;
import static de.codescape.bitvunit.util.html.HtmlPageUtil.toHtmlPage;
import static org.junit.Assert.*;
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
    public void withRulesShouldAddRulesToRuleSet() throws Exception {
        ruleSet.withRule(new AvoidBlinkTextRule()).withRule(new AvoidMarqueeTextRule());
        assertRules(ruleSet, 2);
    }

    @Test
    public void applyToReturnsViolationsOfAllRules() throws Exception {
        AvoidBlinkTextRule firstRule = new AvoidBlinkTextRule();
        ruleSet.addRule(firstRule);
        AvoidMarqueeTextRule secondRule = new AvoidMarqueeTextRule();
        ruleSet.addRule(secondRule);

        String content = "<html><body><blink>Blinking!</blink><marquee>Running!</marquee></body></html>";
        Violations violations = ruleSet.applyTo(toHtmlPage(content));

        assertViolations(violations, 2);
        assertViolations(violations, firstRule, 1);
        assertViolations(violations, secondRule, 1);
    }

    @Test
    public void toStringReturnsReadableStringForEmptyListOfRules() throws Exception {
        String expectedString = ruleSet.getClass().getSimpleName() + "[<Empty RuleSet>]";
        assertEquals(expectedString, ruleSet.toString());
    }

    @Test
    public void toStringReturnsReadableStringForSingleRule() throws Exception {
        String expectedString = ruleSet.getClass().getSimpleName() + "[SingleRuleName]";
        ruleSet.addRule(createRuleWithName("SingleRuleName"));
        assertEquals(expectedString, ruleSet.toString());
    }

    @Test
    public void toStringReturnsReadableStringForMultipleRules() throws Exception {
        String expectedString = ruleSet.getClass().getSimpleName() + "[FirstRuleName, SecondRuleName]";
        ruleSet.addRule(createRuleWithName("FirstRuleName"));
        ruleSet.addRule(createRuleWithName("SecondRuleName"));
        assertEquals(expectedString, ruleSet.toString());
    }

    @Test
    public void toStringListsContainedRulesInAlphabeticalOrder() throws Exception {
        String expectedString = ruleSet.getClass().getSimpleName() + "[ARule, BRule, XRule]";
        ruleSet.addRule(createRuleWithName("BRule"));
        ruleSet.addRule(createRuleWithName("XRule"));
        ruleSet.addRule(createRuleWithName("ARule"));
        assertEquals(expectedString, ruleSet.toString());
    }

    @Test
    public void getRulesShouldReturnRulesWithTheirConfiguredPriority() throws Exception {
        ruleSet.addRule(createRuleWithNameAndPriority("HighPriorityRule", Priority.HIGH));
        ruleSet.addRule(createRuleWithNameAndPriority("NormalPriorityRule", Priority.NORMAL));
        ruleSet.addRule(createRuleWithNameAndPriority("LowPriorityRule", Priority.LOW));

        assertEquals(3, ruleSet.getRules().size());
        for (Rule rule : ruleSet.getRules()) {
            if (rule.getName().equals("HighPriorityRule")) {
                assertEquals(Priority.HIGH, rule.getPriority());
            } else if (rule.getName().equals("NormalPriorityRule")) {
                assertEquals(Priority.NORMAL, rule.getPriority());
            } else if (rule.getName().equals("LowPriorityRule")) {
                assertEquals(Priority.LOW, rule.getPriority());
            } else {
                fail("Rule with unexpected name: " + rule.getName());
            }
        }
    }

    private Rule createRuleWithName(String ruleName) {
        Rule mockedRule = mock(Rule.class);
        when(mockedRule.getName()).thenReturn(ruleName);
        return mockedRule;
    }

    private Rule createRuleWithNameAndPriority(String ruleName, Priority priority) {
        Rule mockedRule = createRuleWithName(ruleName);
        when(mockedRule.getPriority()).thenReturn(priority);
        return mockedRule;
    }

}
