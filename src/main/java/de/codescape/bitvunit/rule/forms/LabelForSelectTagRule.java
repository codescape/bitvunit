package de.codescape.bitvunit.rule.forms;

import com.gargoylesoftware.htmlunit.html.HtmlSelect;
import de.codescape.bitvunit.model.Page;
import de.codescape.bitvunit.rule.AbstractRule;
import de.codescape.bitvunit.rule.Violations;

import static de.codescape.bitvunit.util.HtmlElementUtil.elementHasNonEmptyAttribute;
import static de.codescape.bitvunit.util.HtmlLabelUtil.containsLabelForId;

/**
 * LabelForInputFieldRule ensures that every <code>&lt;select /&gt;</code> tag within the given HTML document is
 * associated with a <code>&lt;label /&gt;</code> element that references the <code>&lt;select /&gt;</code> tag through
 * its <code>for</code> attribute.
 *
 * @since 0.1
 */
public class LabelForSelectTagRule extends AbstractRule {

    private static final String RULE_NAME = "LabelForSelectTag";
    private static final String RULE_MESSAGE = "For every <select /> tag there must be exactly one associated label tag.";

    @Override
    public String getName() {
        return RULE_NAME;
    }

    @Override
    protected void applyTo(Page page, Violations violations) {
        for (HtmlSelect select : page.findAllSelectTags()) {
            if (elementHasNonEmptyAttribute(select, "id") && !containsLabelForId(page.findAllLabelTags(), select.getId())) {
                violations.add(createViolation(select, RULE_MESSAGE));
            }
        }
    }

}
