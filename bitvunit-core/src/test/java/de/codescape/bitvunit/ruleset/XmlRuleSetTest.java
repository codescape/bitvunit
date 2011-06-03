package de.codescape.bitvunit.ruleset;

import de.codescape.bitvunit.rule.Rule;
import org.junit.Test;
import org.reflections.Reflections;

import java.lang.reflect.Modifier;

import static de.codescape.bitvunit.test.Assertions.assertRules;

public class XmlRuleSetTest {

    private static final String PACKAGE_FOR_RULES = "de.codescape.bitvunit.rule";

    @Test
    public void creationOfRuleSetAllRulesFindsAllRules() throws Exception {
        RuleSet ruleSet = new XmlRuleSet("/rulesets/all-rules.xml");
        assertRules(ruleSet, numberOfRulesInClasspath());
    }

    @Test(expected = XmlRuleSetException.class)
    public void missingRuleSetLeadsToXmlRuleSetException() throws Exception {
        new XmlRuleSet("/rulesets/missing-ruleset.xml");
    }

    private int numberOfRulesInClasspath() {
        int count = 0;
        for (Class<? extends Rule> ruleClass : new Reflections(PACKAGE_FOR_RULES).getSubTypesOf(Rule.class)) {
            if (!Modifier.isAbstract(ruleClass.getModifiers())) {
                count++;
            }
        }
        return count;
    }

}
