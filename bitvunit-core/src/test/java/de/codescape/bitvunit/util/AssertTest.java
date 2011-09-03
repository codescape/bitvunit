package de.codescape.bitvunit.util;

import org.junit.Test;

public class AssertTest {

    @Test(expected = IllegalArgumentException.class)
    public void notNullForNullThrowsIllegalArgumentException() throws Exception {
        Assert.notNull(null);
    }

    @Test
    public void notNullForNonNullThrowsNoException() throws Exception {
        Assert.notNull(new Object());
        Assert.notNull("Hello World");
        Assert.notNull(1);
    }

    @Test
    public void notEmptyForNonEmptyStringThrowsNoException() throws Exception {
        Assert.notEmpty("Hello World");
    }

    @Test(expected = IllegalArgumentException.class)
    public void notEmptyForNullThrowsIllegalArgumentException() throws Exception {
        Assert.notEmpty(null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void notEmptyForEmptyStringThrowsIllegalArgumentException() throws Exception {
        Assert.notEmpty("");
    }

    @Test(expected = IllegalArgumentException.class)
    public void notEmptyForWhitespacesOnlyThrowsIllegalArgumentException() throws Exception {
        Assert.notEmpty("    ");
    }

}
