package de.codescape.bitvunit.rule.tables;

import com.gargoylesoftware.htmlunit.html.HtmlTable;
import de.codescape.bitvunit.model.Page;
import de.codescape.bitvunit.rule.AbstractRule;
import de.codescape.bitvunit.rule.Violations;

/**
 * CaptionTextForTableRule ensures that every <code>&lt;table/&gt;</code> within the given HTML document provides a
 * title by having a <code>&lt;caption&gt;description&lt;/caption&gt;</code> element as the first child element inside
 * the <code>&lt;table/&gt;</code> declaration.
 *
 * @author Stefan Glase
 * @since 0.1
 */
public class CaptionTextForTableRule extends AbstractRule {

    private static final String RULE_NAME = "CaptionTextForTable";
    private static final String RULE_MESSAGE = "Every table must have a <caption/> element to provide a title for its content.";

    @Override
    public String getName() {
        return RULE_NAME;
    }

    @Override
    protected void applyTo(Page page, Violations violations) {
        for (HtmlTable table : page.findAllTableTags()) {
            if (table.getCaptionText() == null || table.getCaptionText().isEmpty()) {
                violations.add(createViolation(table, RULE_MESSAGE));
            }
        }
    }

}
