package de.codescape.bitvunit.util.io;

import org.junit.Test;

import static org.junit.Assert.assertNotNull;

public class ClassPathResourceTest {

    private static final String MISSING_RESOURCE = "missing-resource.txt";
    private static final String EXISTING_RESOURCE = "sample-page.html";

    @Test(expected = RuntimeException.class)
    public void asInputStreamWithMissingResourceThrowsException() throws Exception {
        ClassPathResource.asInputStream(MISSING_RESOURCE);
    }

    @Test
    public void asInputStreamWithExistingResourceReturnsInputStream() throws Exception {
        assertNotNull(ClassPathResource.asInputStream(EXISTING_RESOURCE));
    }

    @Test(expected = RuntimeException.class)
    public void asStringWithMissingResourceThrowsException() throws Exception {
        ClassPathResource.asString(MISSING_RESOURCE);
    }

    @Test
    public void asStringWithExistingResourceReturnsString() throws Exception {
        assertNotNull(ClassPathResource.asString(EXISTING_RESOURCE));
    }

    @Test(expected = IllegalArgumentException.class)
    public void throwsExceptionForMissingPath() throws Exception {
        ClassPathResource.asString(null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void throwsExceptionForEmptyPath() throws Exception {
        ClassPathResource.asString("");
    }

}
