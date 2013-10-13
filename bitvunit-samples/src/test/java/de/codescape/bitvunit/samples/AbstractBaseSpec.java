package de.codescape.bitvunit.samples;

import de.codescape.bitvunit.ruleset.RuleSet;
import de.codescape.bitvunit.ruleset.XmlRuleSet;
import de.codescape.bitvunit.util.io.ClassPathResource;

import java.io.IOException;
import java.util.Properties;

/**
 * Abstract base class to our tests that provides some convenience methods to access all configuration
 * necessary to perform tests against a locally instantiated Jetty server.
 *
 * @author Stefan Glase
 * @since 0.7
 */
public abstract class AbstractBaseSpec {

    protected static final RuleSet ALL_RULES = new XmlRuleSet("/rulesets/all-rules.xml");

    private static final String CONFIGURATION_FILE = "test.properties";

    private final Properties properties = new Properties();

    /**
     * Construct a new instance and load the properties file.
     */
    protected AbstractBaseSpec() {
        try {
            properties.load(ClassPathResource.asInputStream(CONFIGURATION_FILE));
        } catch (IOException e) {
            throw new RuntimeException("Unable to read configuration.", e);
        }
    }

    /**
     * Return the full URL for a given page with respect to the configured jetty port and url.
     *
     * @param page part of the URL after the domain and port
     * @return URL
     */
    protected String urlForPage(String page) {
        return getProperty("jetty.url") + ":" + getProperty("jetty.port") + "/" + page;
    }

    private String getProperty(String key) {
        return properties.getProperty(key);
    }

}
