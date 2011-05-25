package de.codescape.bitvunit.rule;

import org.junit.Test;
import org.reflections.Reflections;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.Set;

import static org.junit.Assert.*;

public class RuleImplementationTest {

    private static final String RULE_NAME_PROPERTY = "RULE_NAME";

    @Test
    public void everyNonAbstractRuleImplementationMustProvideAPublicStaticStringWithTheNameOfTheRule() throws Exception {
        Reflections reflections = new Reflections("de.codescape.bitvunit.rule");
        Set<Class<? extends Rule>> rules = reflections.getSubTypesOf(Rule.class);
        for (Class<? extends Rule> rule : rules) {
            if (!Modifier.isAbstract(rule.getModifiers()))
                checkRule(rule);
        }
    }

    private void checkRule(Class<? extends Rule> rule) {
        try {
            Field field = rule.getDeclaredField(RULE_NAME_PROPERTY);
            assertEquals(RULE_NAME_PROPERTY + " is not of type java.lang.String in rule" + rule.getSimpleName(), String.class, field.getType());
            assertTrue(RULE_NAME_PROPERTY + " is not static in rule " + rule.getSimpleName(), Modifier.isStatic(field.getModifiers()));
            assertTrue(RULE_NAME_PROPERTY + " is not public in rule " + rule.getSimpleName(), Modifier.isPublic(field.getModifiers()));
        } catch (NoSuchFieldException e) {
            fail("Rule " + rule.getSimpleName() + " does not provide a public static field called " + RULE_NAME_PROPERTY);
        }
    }

}
