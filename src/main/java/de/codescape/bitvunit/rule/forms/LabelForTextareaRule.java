package de.codescape.bitvunit.rule.forms;

import com.gargoylesoftware.htmlunit.html.HtmlLabel;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import com.gargoylesoftware.htmlunit.html.HtmlTextArea;
import de.codescape.bitvunit.rule.AbstractRule;
import de.codescape.bitvunit.rule.Violation;

import java.util.List;

import static de.codescape.bitvunit.util.ElementInspector.elementHasValidId;
import static de.codescape.bitvunit.util.LabelInspector.labelForIdExists;

/**
 * LabelForTextareaRule ensures that every <code>&lt;textarea /&gt;</code> within the given HTML document is associated
 * with a <code>&lt;label /&gt;</code> element that references the <code>&lt;textarea /&gt;</code> element through its
 * <code>for</code> attribute.
 *
 * @since 0.1
 */
public class LabelForTextareaRule extends AbstractRule {

    public static final String RULE_NAME = "LabelForTextarea";
    private static final String RULE_MESSAGE = "For every textarea there must be exactly one associated label tag.";

    @Override
    public String getName() {
        return RULE_NAME;
    }

    @Override
    protected void applyTo(HtmlPage htmlPage, List<Violation> violations) {
        List<HtmlTextArea> textareas = getElementsByTagName(htmlPage, HtmlTextArea.TAG_NAME);
        List<HtmlLabel> labels = getElementsByTagName(htmlPage, HtmlLabel.TAG_NAME);
        for (HtmlTextArea textarea : textareas) {
            if (elementHasValidId(textarea) && !labelForIdExists(textarea.getId(), labels)) {
                violations.add(createViolation(this, textarea.getStartLineNumber(), RULE_MESSAGE));
            }
        }
    }

}
