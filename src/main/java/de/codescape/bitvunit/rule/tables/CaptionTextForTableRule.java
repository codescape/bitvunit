package de.codescape.bitvunit.rule.tables;

import com.gargoylesoftware.htmlunit.html.HtmlPage;
import com.gargoylesoftware.htmlunit.html.HtmlTable;
import de.codescape.bitvunit.rule.AbstractRule;
import de.codescape.bitvunit.rule.Violation;

import java.util.ArrayList;
import java.util.List;

/**
 * CaptionTextForTableRule ensures that every table within the given HTML document provides a title by having a
 * <code>&lt;caption&gt;Some title&lt;/caption&gt;</code> element as the first child element inside the table
 * declaration.
 *
 * @since 0.1
 */
public class CaptionTextForTableRule extends AbstractRule {

    public static final String RULE_NAME = "CaptionTextForTable";
    private static final String RULE_MESSAGE = "Every tables must have a <caption/> element to provide a title of that table's content.";

    @Override
    public String getName() {
        return RULE_NAME;
    }

    @Override
    public List<Violation> applyTo(HtmlPage htmlPage) {
        List<Violation> violations = new ArrayList<Violation>();

        List<HtmlTable> tables = getElementsByTagName(htmlPage, HtmlTable.TAG_NAME);
        for (HtmlTable table : tables) {
            if (table.getCaptionText() == null || table.getCaptionText().isEmpty()) {
                violations.add(createViolation(this, table.getStartLineNumber(), RULE_MESSAGE));
            }
        }

        return violations;
    }

}
