package de.codescape.bitvunit.util;

import com.gargoylesoftware.htmlunit.html.HtmlLabel;

import java.util.List;

public abstract class HtmlLabelUtil {

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