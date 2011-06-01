package de.codescape.bitvunit.rule.tables;

import com.gargoylesoftware.htmlunit.html.HtmlTableCell;
import de.codescape.bitvunit.model.Page;
import de.codescape.bitvunit.rule.AbstractRule;
import de.codescape.bitvunit.rule.Violation;
import de.codescape.bitvunit.rule.Violations;

import java.util.List;

/**
 * TableHeaderContainsTextRule ensures that every <code>&lt;th /&gt;</code> element within the given HTML document
 * contains text content. Table headers always should always describe a given row or column with text.
 *
 * @since 0.2
 */
public class TableHeaderContainsTextRule extends AbstractRule {

    public static final String RULE_NAME = "TableHeaderContainsText";
    private static final String RULE_MESSAGE = "Every table cell that is a table header should contain text.";

    @Override
    public String getName() {
        return RULE_NAME;
    }

    @Override
    protected void applyTo(Page page, Violations violations) {
        for (HtmlTableCell cell : page.findAllTableHeaders()) {
            if (cell.getTextContent() == null || cell.getTextContent().isEmpty()) {
                violations.add(createViolation(cell.getStartLineNumber(), RULE_MESSAGE));
            }
        }
    }

}
