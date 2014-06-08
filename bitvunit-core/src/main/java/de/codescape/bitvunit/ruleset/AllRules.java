package de.codescape.bitvunit.ruleset;

/**
 * Simple abstraction to check against all rules that are currently configured in /rulesets/all-rules.xml.
 *
 * @author Stefan Glase
 * @since 0.11
 */
public class AllRules extends XmlRuleSet {

    private static final String ALL_RULES_XML = "/rulesets/all-rules.xml";

    /**
     * Construct a rule set containing all rules.
     */
    public AllRules() {
        super(ALL_RULES_XML);
    }

    /**
     * Return a rule set containing all rules.
     *
     * @return rule set containing all rules
     */
    public static AllRules allRules() {
        return new AllRules();
    }

}
