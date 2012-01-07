package de.codescape.bitvunit.rule;

/**
 * Rules can run under different priorities. For example this enables the user to set a threshold on specific amount of
 * violations with different weighting.
 *
 * @author Stefan Glase
 * @since 0.7
 */
public enum Priority {

    LOW, NORMAL, HIGH;

    @Override
    public String toString() {
        return name().toLowerCase();
    }

}
