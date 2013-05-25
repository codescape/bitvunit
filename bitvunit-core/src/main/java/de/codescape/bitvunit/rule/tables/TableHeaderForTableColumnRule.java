package de.codescape.bitvunit.rule.tables;

import com.gargoylesoftware.htmlunit.html.HtmlTable;
import com.gargoylesoftware.htmlunit.html.HtmlTableCell;
import com.gargoylesoftware.htmlunit.html.HtmlTableRow;
import de.codescape.bitvunit.model.Page;
import de.codescape.bitvunit.rule.AbstractRule;
import de.codescape.bitvunit.rule.Violations;

import static de.codescape.bitvunit.util.html.HtmlTableCellUtil.isDataCell;
import static de.codescape.bitvunit.util.html.HtmlTableCellUtil.isHeaderCell;

/**
 * TableHeaderForTableColumnRule ensures that every <code>&lt;table/&gt;</code> within the given HTML document has
 * table headers declared by <code>&lt;th/&gt;</code> for each data cell declared by the <code>&lt;td/&gt;</code> tag.
 *
 * @author Stefan Glase
 * @since 0.3
 */
public class TableHeaderForTableColumnRule extends AbstractRule {

    private static final String RULE_NAME = "TableHeaderForTableColumn";
    private static final String RULE_MESSAGE = "Every data cell should have an associated column header cell.";
    private static final String RULE_MESSAGE_SINGLE_ROW = "Tables with a row count of one are missing data cells or header cells.";

    @Override
    public String getName() {
        return RULE_NAME;
    }

    @Override
    protected void applyTo(Page page, Violations violations) {
        for (HtmlTable table : page.findAllTableTags()) {
            applyTo(table, page, violations);
        }
    }

    private void applyTo(HtmlTable table, Page page, Violations violations) {
        if (table.getRowCount() < 2) {
            violations.add(createViolation(table, page, RULE_MESSAGE_SINGLE_ROW));
        } else {
            HtmlTableRow firstRow = table.getRow(0);
            HtmlTableRow secondRow = table.getRow(1);

            int index = 0;
            for (HtmlTableCell secondRowCell : secondRow.getCells()) {
                if (isDataCell(secondRowCell) && !isHeaderCell(firstRow.getCell(index))) {
                    violations.add(createViolation(secondRowCell, page, RULE_MESSAGE));
                }
                index++;
            }
        }
    }

}
