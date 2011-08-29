package de.codescape.bitvunit.util.html;

import com.gargoylesoftware.htmlunit.html.HtmlLabel;

import java.util.List;

/**
 * Utility class to work with {@link HtmlLabel} instances.
 *
 * @author Stefan Glase
 */
public final class HtmlLabelUtil {

    private HtmlLabelUtil() {
        throw new UnsupportedOperationException("Utility class should not be instantiated.");
    }

    /**
     * Returns <code>true</code> for a label in the given list of labels with the value of the <code>for</code>
     * attribute equal to the given id.
     *
     * @param labels list of all labels
     * @param id     id for which a label should exist
     * @return <true>true</true> if a label referencing the given id is found, otherwise <code>false</code>
     */
    public static boolean containsLabelForId(List<HtmlLabel> labels, String id) {
        for (HtmlLabel label : labels) {
            if (label.getForAttribute() != null && label.getForAttribute().equals(id)) {
                return true;
            }
        }
        return false;
    }

}
