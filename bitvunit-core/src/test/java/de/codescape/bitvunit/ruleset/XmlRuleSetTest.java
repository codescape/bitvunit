package de.codescape.bitvunit.ruleset;

import de.codescape.bitvunit.rule.Priority;
import de.codescape.bitvunit.rule.Rule;
import org.junit.Test;

import java.io.File;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.List;

import static de.codescape.bitvunit.test.Assertions.assertRules;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

public class XmlRuleSetTest {

    private static final String RULESET_ALL_RULES_XML = "/rulesets/all-rules.xml";

    @Test
    public void creationOfRuleSetAllRulesFindsAllRules() throws Exception {
        RuleSet ruleSet = new XmlRuleSet(RULESET_ALL_RULES_XML);
        assertRules(ruleSet, countAllNonAbstractNonInnerClassesImplementingRuleInterface());
    }

    @Test
    public void creationOfRuleSetWithoutRulePrioritiesShouldConfigureAllRulesWithNormalPriority() {
        RuleSet ruleSet = new XmlRuleSet(RULESET_ALL_RULES_XML);
        for (Rule rule : ruleSet.getRules()) {
            assertEquals(Priority.NORMAL, rule.getPriority());
        }
    }

    @Test
    public void creationOfRuleSetWithRulePrioritiesShouldConfigureRulesAccordingly() {
        RuleSet ruleSet = new XmlRuleSet("/rulesets/with-priorities.xml");
        for (Rule rule : ruleSet.getRules()) {
            if (rule.getName().equals("AlternativeTextForImage")) {
                assertEquals(Priority.NORMAL, rule.getPriority());
            } else if (rule.getName().equals("AlternativeTextForLinkedImage")) {
                assertEquals(Priority.HIGH, rule.getPriority());
            } else if (rule.getName().equals("AlternativeTextForAreaOfImageMap")) {
                assertEquals(Priority.LOW, rule.getPriority());
            } else {
                fail("Unexpected rule name: " + rule.getName());
            }
        }
    }

    private int countAllNonAbstractNonInnerClassesImplementingRuleInterface() {
        List<Class<?>> allSubClasses = ClassPathAnalyzer.findAllSubClasses("de.codescape.bitvunit.rule", Rule.class);
        int count = 0;
        for (Class<?> implementingClass : allSubClasses) {
            if (isNoAbstractClass(implementingClass) && isNoInnerClass(implementingClass)) {
                count++;
            }
        }
        return count;
    }

    private boolean isNoInnerClass(Class<?> implementingClass) {
        return !implementingClass.getName().contains("$");
    }

    private boolean isNoAbstractClass(Class<?> implementingClass) {
        return !Modifier.isAbstract(implementingClass.getModifiers());
    }

    @Test
    public void missingRuleSetLeadsToXmlRuleSetException() throws Exception {
        String missingLocation = "/rulesets/missing-ruleset.xml";
        try {
            new XmlRuleSet(missingLocation);
            fail();
        } catch (XmlRuleSetException e) {
            assertEquals("Could not parse RuleSet from given location '" + missingLocation + "'.", e.getMessage());
        }
    }

    @Test
    public void invalidRuleClassNameLeadsToXmlRuleSetException() throws Exception {
        String missingRuleName = "de.codescape.bitvunit.rule.InvalidRuleClassName";
        try {
            new XmlRuleSet("/rulesets/invalid-rulename.xml");
            fail();
        } catch (XmlRuleSetException e) {
            assertEquals("Could not instantiate rule " + missingRuleName + ".", e.getMessage());
        }
    }

    @Test(expected = IllegalArgumentException.class)
    public void noLocationLeadsToIllegalArgumentException() throws Exception {
        new XmlRuleSet(null);
    }

    /**
     * Utility class to find all implementing sub classes of a given class in a given package. It is used to find all
     * {@link Rule} implementations in this test class.
     *
     * @author Stefan Glase
     * @since 0.5
     */
    private static class ClassPathAnalyzer {

        private static final String JAVA_CLASS_PATH = System.getProperty("java.class.path");
        private static final String PATH_SEPARATOR = System.getProperty("path.separator");
        private static final String FILE_SEPARATOR = System.getProperty("file.separator");

        /**
         * Searches for implementations of the given superClass in the given packageName in all entries in the Java
         * class path. (Note: This implementation ignores JARs listed in the class path because this functionality is
         * not needed here.)
         *
         * @param packageName package name where to look for implementing classes
         * @param superClass  super class that should be implemented
         * @return list of all sub classes
         */
        public static List<Class<?>> findAllSubClasses(String packageName, Class<?> superClass) {
            List<Class<?>> subClasses = new ArrayList<Class<?>>();

            for (String classPathEntry : JAVA_CLASS_PATH.split(PATH_SEPARATOR)) {
                if (!classPathEntry.endsWith(".jar")) {
                    subClasses.addAll(findAllSubClassesInClassPath(classPathEntry, packageName, superClass));
                }
            }

            return subClasses;
        }

        private static List<Class<?>> findAllSubClassesInClassPath(String classPath, String packageName, Class<?> superClass) {
            List<Class<?>> subClasses = new ArrayList<Class<?>>();

            File[] files = new File(classPath + FILE_SEPARATOR + packageName.replace(".", FILE_SEPARATOR)).listFiles();
            if (files != null) {
                for (File file : files) {
                    if (file.isDirectory()) {
                        subClasses.addAll(findAllSubClassesInClassPath(classPath, packageName + "." + file.getName(), superClass));
                    } else {
                        try {
                            Class<?> clazz = Class.forName(packageName + "." + file.getName().replace(".class", ""));
                            if (superClass.isAssignableFrom(clazz)) {
                                subClasses.add(clazz);
                            }
                        } catch (ClassNotFoundException e) {
                            /* This should never happen for valid class-files, so it will not be handled. */
                        }
                    }
                }
            }

            return subClasses;
        }
    }

}
