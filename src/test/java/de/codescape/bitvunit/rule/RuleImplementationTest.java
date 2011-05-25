package de.codescape.bitvunit.rule;

import org.junit.Test;
import org.reflections.Reflections;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;

import static org.junit.Assert.*;

public class RuleImplementationTest {

    private static final String RULE_NAME_PROPERTY = "RULE_NAME";
    private static final String PACKAGE_FOR_RULES = "de.codescape.bitvunit.rule";

    @Test
    public void everyNonAbstractRuleImplementationMustProvideAPublicStaticStringWithTheNameOfTheRule() throws Exception {
        for (Class<? extends Rule> rule : new Reflections(PACKAGE_FOR_RULES).getSubTypesOf(Rule.class)) {
            if (ruleIsNotAbstract(rule)) {
                verifyRuleProvidesRequiredField(rule);
            }
        }
    }

    private boolean ruleIsNotAbstract(Class<? extends Rule> rule) {
        return !Modifier.isAbstract(rule.getModifiers());
    }

    private void verifyRuleProvidesRequiredField(Class<? extends Rule> rule) {
        Field field = null;
        try {
            field = rule.getDeclaredField(RULE_NAME_PROPERTY);
        } catch (NoSuchFieldException e) {
            fail("Rule " + rule.getSimpleName() + " does not provide a public static field called " + RULE_NAME_PROPERTY);
        }
        assertEquals(RULE_NAME_PROPERTY + " is not of type java.lang.String in rule" + rule.getSimpleName(), String.class, field.getType());
        assertTrue(RULE_NAME_PROPERTY + " is not static in rule " + rule.getSimpleName(), Modifier.isStatic(field.getModifiers()));
        assertTrue(RULE_NAME_PROPERTY + " is not public in rule " + rule.getSimpleName(), Modifier.isPublic(field.getModifiers()));
    }

}
