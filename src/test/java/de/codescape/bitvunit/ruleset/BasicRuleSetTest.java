package de.codescape.bitvunit.ruleset;

import de.codescape.bitvunit.rule.Violation;
import de.codescape.bitvunit.rule.text.AvoidBlinkTextRule;
import de.codescape.bitvunit.rule.text.AvoidMarqueeTextRule;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static de.codescape.bitvunit.test.Assertions.assertViolation;
import static de.codescape.bitvunit.test.Assertions.assertViolationCount;
import static de.codescape.bitvunit.test.HtmlPageCreator.create;
import static org.junit.Assert.assertEquals;
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
        assertNotNull(ruleSet.getRules());
        assertEquals(2, ruleSet.getRules().size());
    }

    @Test
    public void applyToReturnsViolationsOfAllRules() throws Exception {
        AvoidBlinkTextRule firstRule = new AvoidBlinkTextRule();
        ruleSet.addRule(firstRule);
        AvoidMarqueeTextRule secondRule = new AvoidMarqueeTextRule();
        ruleSet.addRule(secondRule);

        String content = "<html><body><blink>Blinking!</blink><marquee>Running!</marquee></body></html>";
        List<Violation> violations = ruleSet.applyTo(create(content));

        assertViolationCount(violations, 2);
        assertViolation(violations, firstRule);
        assertViolation(violations, secondRule);
    }

}
