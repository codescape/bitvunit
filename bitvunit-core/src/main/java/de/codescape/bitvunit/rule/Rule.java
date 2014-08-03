package de.codescape.bitvunit.rule;

import de.codescape.bitvunit.Testable;

/**
 * Interface that must be implemented by all rules that should be checked with this library. You should always extend
 * {@link AbstractRule} which gives you some convenience functionality instead of implementing this interface directly.
 *
 * @author Stefan Glase
 */
public interface Rule extends Testable {

    /**
     * Returns the name of that rule.
     *
     * @return name of that rule
     */
    String getName();


    /**
     * Returns the {@link Priority} for that rule.
     *
     * @return {@link Priority} for that rule
     */
    Priority getPriority();

    /**
     * Sets the {@link Priority} for that rule.
     *
     * @param priority {@link Priority} for that rule
     */
    void setPriority(Priority priority);

}
