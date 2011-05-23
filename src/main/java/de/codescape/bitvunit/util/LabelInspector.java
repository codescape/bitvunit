package de.codescape.bitvunit.util;

import com.gargoylesoftware.htmlunit.html.HtmlLabel;

import java.util.List;

public class LabelInspector {

    public static boolean labelForIdExists(String id, List<HtmlLabel> labels) {
        for (HtmlLabel label : labels) {
            if (label.getForAttribute() != null && label.getForAttribute().equals(id)) {
                return true;
            }
        }
        return false;
    }

}
