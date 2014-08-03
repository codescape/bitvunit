package de.codescape.bitvunit.ruleset;

import org.junit.Test;

import static de.codescape.bitvunit.test.Matchers.containsRules;
import static org.junit.Assert.assertThat;

public class AllRulesTest {

    @Test
    public void shouldConstructRuleSetWithMinimumOneRule() {
        assertThat(new AllRules(), containsRules());
    }

}