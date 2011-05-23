package de.codescape.bitvunit.rule.tables;

import com.gargoylesoftware.htmlunit.html.HtmlPage;
import com.gargoylesoftware.htmlunit.html.HtmlTable;
import de.codescape.bitvunit.rule.AbstractRule;
import de.codescape.bitvunit.rule.Violation;

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
    private static final String RULE_MESSAGE = "Every table must have a <caption/> element to provide a title for its content.";

    @Override
    public String getName() {
        return RULE_NAME;
    }

    @Override
    protected void applyTo(HtmlPage htmlPage, List<Violation> violations) {
        List<HtmlTable> tables = getElementsByTagName(htmlPage, HtmlTable.TAG_NAME);
        for (HtmlTable table : tables) {
            if (table.getCaptionText() == null || table.getCaptionText().isEmpty()) {
                violations.add(createViolation(this, table.getStartLineNumber(), RULE_MESSAGE));
            }
        }
    }

}
