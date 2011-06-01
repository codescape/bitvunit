package de.codescape.bitvunit.rule;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;

public class ViolationTest {

    @Test
    public void violationKnowsTheRule() throws Exception {
        Rule expectedRule = someRule();
        assertEquals(expectedRule, new Violation(expectedRule, 1, "").getRule());
    }

    @Test
    public void violationKnowsTheLineNumber() throws Exception {
        Integer expectedLineNumber = 4711;
        assertEquals(expectedLineNumber, new Violation(someRule(), expectedLineNumber, "").getLineNumber());
    }

    @Test
    public void violationKnowsTheMessage() throws Exception {
        String expectedMessage = "Some wise words.";
        assertEquals(expectedMessage, new Violation(someRule(), 1, expectedMessage).getMessage());
    }

    private Rule someRule() {
        return mock(Rule.class);
    }

}
