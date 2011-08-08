package de.codescape.bitvunit.util.file;

import org.apache.commons.io.IOUtils;

import java.io.IOException;
import java.io.InputStream;

/**
 * Utility class that is used to handle resources available on the classpath.
 *
 * @author Stefan Glase
 * @since 0.5
 */
public class ClassPathResource {

    private final String path;

    /**
     * Constructs a new ClassPathResource and checks that that given path to the resource or file is not
     * <code>null</code> or an empty String.
     *
     * @param path the path to the resource or file
     */
    private ClassPathResource(String path) {
        if (path == null || path.trim().isEmpty()) {
            throw new IllegalArgumentException("Given path must not be null or empty.");
        }
        if (path.startsWith("/")) {
            this.path = path.substring(1);
        } else {
            this.path = path;
        }
    }

    /**
     * Returns a {@link String} that contains the content of the file.
     *
     * @param path path to the resource or file
     * @return {@link String} that contains the content of the file
     */
    public static String asString(String path) {
        return new ClassPathResource(path).asString();
    }

    /**
     * Returns a {@link String} that contains the content of the file.
     *
     * @return {@link String} that contains the content of the file
     */
    private String asString() {
        try {
            return IOUtils.toString(asInputStream());
        } catch (IOException e) {
            throw new RuntimeException("Could not read from file '" + path + "'.", e);
        }
    }

    /**
     * Returns an {@link InputStream} to read from the file.
     *
     * @param path path to the resource or file
     * @return {@link InputStream} to read from the file
     */
    public static InputStream asInputStream(String path) {
        return new ClassPathResource(path).asInputStream();
    }

    /**
     * Returns an {@link InputStream} to read from the file.
     *
     * @return {@link InputStream} to read from the file
     */
    private InputStream asInputStream() {
        InputStream inputStream = getClass().getClassLoader().getResourceAsStream(path);
        if (inputStream == null) {
            throw new RuntimeException("File '" + path + "' was not found.");
        }
        return inputStream;
    }

}