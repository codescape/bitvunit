package de.codescape.bitvunit.util;

import com.gargoylesoftware.htmlunit.html.HtmlTableCell;

/**
 * Utility class to work with {@link HtmlTableCell} instances.
 *
 * @author Stefan Glase
 */
public final class HtmlTableCellUtil {

    private HtmlTableCellUtil() {
        throw new UnsupportedOperationException("Utility class should not be instantiated.");
    }

    /**
     * Returns <code>true</code> if the given cell is a data cell declared by the tag <code>&lt;td /&gt;</code>.
     *
     * @param cell cell to check
     * @return <code>true</code> if the given cell is a data cell, otherwise <code>false</code>
     */
    public static boolean isDataCell(HtmlTableCell cell) {
        return cell.getTagName().equals("td");
    }

    /**
     * Returns <code>true</code> if the given cell is a header cell declared by the tag <code>&lt;th /&gt;</code>.
     *
     * @param cell cell to check
     * @return <code>true</code> if the given cell is a header cell, otherwise <code>false</code>
     */
    public static boolean isHeaderCell(HtmlTableCell cell) {
        return cell.getTagName().equals("th");
    }

}
