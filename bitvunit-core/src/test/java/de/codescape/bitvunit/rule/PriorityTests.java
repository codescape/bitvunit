package de.codescape.bitvunit.rule;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class PriorityTests {

    @Test
    public void canBeCreatedFromUppercaseStringNORMAL() throws Exception {
        assertEquals(Priority.NORMAL, Priority.valueOf("NORMAL"));
    }

    @Test
    public void canBeCreatedFromUppercaseStringHIGH() throws Exception {
        assertEquals(Priority.HIGH, Priority.valueOf("HIGH"));
    }

    @Test
    public void canBeCreatedFromUppercaseStringLOW() throws Exception {
        assertEquals(Priority.LOW, Priority.valueOf("LOW"));
    }

}
