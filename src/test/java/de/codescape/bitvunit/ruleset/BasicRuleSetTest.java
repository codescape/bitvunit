package de.codescape.bitvunit.ruleset;

import de.codescape.bitvunit.rule.Violation;
import de.codescape.bitvunit.rule.Violations;
import de.codescape.bitvunit.rule.text.AvoidBlinkTextRule;
import de.codescape.bitvunit.rule.text.AvoidMarqueeTextRule;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static de.codescape.bitvunit.test.Assertions.assertRules;
import static de.codescape.bitvunit.test.Assertions.assertViolations;
import static de.codescape.bitvunit.test.HtmlPageCreator.createHtmlPage;
import static org.junit.Assert.assertNotNull;

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

}
