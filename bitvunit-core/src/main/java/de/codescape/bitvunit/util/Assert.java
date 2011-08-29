package de.codescape.bitvunit.util;

/**
 * Utility class with reusable assertions.
 *
 * @author Stefan Glase
 * @since 0.5
 */
public final class Assert {

    private Assert() {
        throw new UnsupportedOperationException("Utility class should not be instantiated.");
    }

    /**
     * Verifies that the given object is not <code>null</code> and throws an <code>IllegalArgumentException</code> with
     * a default message otherwise.
     *
     * @param object object that should be checked
     */
    public static void notNull(Object object) {
        notNull("Parameter may not be null.", object);
    }

    /**
     * Verifies that the given object is not <code>null</code> and throws an <code>IllegalArgumentException</code> with
     * a default message otherwise.
     *
     * @param message message that should be included in case of an error
     * @param object  object that should be checked
     */
    public static void notNull(String message, Object object) {
        if (object == null) {
            throw new IllegalArgumentException(message);
        }
    }

}
