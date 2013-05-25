package de.codescape.bitvunit.util;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class AssertTest {

    @Test(expected = IllegalArgumentException.class)
    public void notNullForNullThrowsIllegalArgumentException() {
        Assert.notNull((Object)null);
    }

    @Test
    public void notNullForMessageAndNullThrowsIllegalArgumentException() {
        try {
            Assert.notNull("Message", null);
        } catch (IllegalArgumentException e) {
            assertEquals("Message", e.getMessage());
        }
    }

    @Test(expected = IllegalArgumentException.class)
    public void notNullForMultipleObjectsWithNullObjectThrowsIllegalArgumentException() {
        Assert.notNull("", null, 1, null);
    }

    @Test
    public void notNullForMultipleNonNullObjectsThrowsNoException() {
        Assert.notNull(1, 1, 1, 1, 1);
        Assert.notNull("", "2", "saSASD");
        Assert.notNull(new Object(), new Object(), new Object());
    }

    @Test
    public void notNullForNonNullThrowsNoException() {
        Assert.notNull(new Object());
        Assert.notNull("Hello World");
        Assert.notNull(1);
    }

    @Test
    public void notEmptyForNonEmptyStringThrowsNoException() {
        Assert.notEmpty("Hello World");
    }

    @Test(expected = IllegalArgumentException.class)
    public void notEmptyForNullThrowsIllegalArgumentException() {
        Assert.notEmpty(null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void notEmptyForEmptyStringThrowsIllegalArgumentException() {
        Assert.notEmpty("");
    }

    @Test(expected = IllegalArgumentException.class)
    public void notEmptyForWhitespacesOnlyThrowsIllegalArgumentException() {
        Assert.notEmpty("    ");
    }

}
