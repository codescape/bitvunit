package de.codescape.bitvunit.ruleset;

import de.codescape.bitvunit.rule.Rule;
import org.junit.Test;

import java.io.File;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.List;

import static de.codescape.bitvunit.test.Assertions.assertRules;

public class XmlRuleSetTest {

    @Test
    public void creationOfRuleSetAllRulesFindsAllRules() throws Exception {
        RuleSet ruleSet = new XmlRuleSet("/rulesets/all-rules.xml");
        assertRules(ruleSet, countAllNonAbstractNonInnerClassesImplementingRuleInterface());
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

    @Test(expected = XmlRuleSetException.class)
    public void missingRuleSetLeadsToXmlRuleSetException() throws Exception {
        new XmlRuleSet("/rulesets/missing-ruleset.xml");
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
