package de.codescape.bitvunit.rule.forms;

import com.gargoylesoftware.htmlunit.html.HtmlTextArea;
import de.codescape.bitvunit.model.Page;
import de.codescape.bitvunit.rule.AbstractRule;
import de.codescape.bitvunit.rule.Violations;

import static de.codescape.bitvunit.util.HtmlElementUtil.hasNonEmptyAttribute;
import static de.codescape.bitvunit.util.HtmlLabelUtil.containsLabelForId;

/**
 * LabelForTextareaRule ensures that every <code>&lt;textarea /&gt;</code> within the given HTML document is associated
 * with a <code>&lt;label /&gt;</code> element that references the <code>&lt;textarea /&gt;</code> element through its
 * <code>for</code> attribute.
 *
 * @since 0.1
 */
public class LabelForTextareaRule extends AbstractRule {

    private static final String RULE_NAME = "LabelForTextarea";
    private static final String RULE_MESSAGE = "For every textarea there must be exactly one associated label tag.";

    @Override
    public String getName() {
        return RULE_NAME;
    }

    @Override
    protected void applyTo(Page page, Violations violations) {
        for (HtmlTextArea textarea : page.findAllTextareaTags()) {
            if (hasNonEmptyAttribute(textarea, "id") && !containsLabelForId(page.findAllLabelTags(), textarea.getId())) {
                violations.add(createViolation(textarea, RULE_MESSAGE));
            }
        }
    }

}
