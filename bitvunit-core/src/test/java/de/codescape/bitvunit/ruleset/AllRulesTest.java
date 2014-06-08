package de.codescape.bitvunit.ruleset;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class AllRulesTest {

    @Test
    public void shouldConstructRuleSetWithMinimumOneRule() {
        assertThat(new AllRules().getRules().isEmpty(), is(false));
    }

}