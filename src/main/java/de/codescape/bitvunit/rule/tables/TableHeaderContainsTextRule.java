package de.codescape.bitvunit.rule.tables;

import com.gargoylesoftware.htmlunit.html.HtmlTableCell;
import de.codescape.bitvunit.model.Page;
import de.codescape.bitvunit.rule.AbstractRule;
import de.codescape.bitvunit.rule.Violation;

import java.util.List;

/**
 * TODO document me
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
    protected void applyTo(Page page, List<Violation> violations) {
        for (HtmlTableCell cell : page.findAllTableHeaders()) {
            if (cell.getTextContent() == null || cell.getTextContent().isEmpty()) {
                violations.add(createViolation(cell.getStartLineNumber(), RULE_MESSAGE));
            }
        }
    }

}
