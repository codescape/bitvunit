package de.codescape.bitvunit.ruleset;

import org.junit.Test;

import static de.codescape.bitvunit.test.Assertions.assertRules;

public class XmlRuleSetTest {

    // TODO calculate automatically based on the amount of non abstract classes implementing the rule interface
    public static final int NUMBER_OF_RULES = 25;

    @Test
    public void creationOfRuleSetAllRulesFindsAllRules() throws Exception {
        RuleSet ruleSet = new XmlRuleSet("/rulesets/all-rules.xml");
        assertRules(ruleSet, NUMBER_OF_RULES);
    }

    @Test(expected = XmlRuleSetException.class)
    public void missingRuleSetLeadsToXmlRuleSetException() throws Exception {
        new XmlRuleSet("/rulesets/missing-ruleset.xml");
    }

}
