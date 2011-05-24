package de.codescape.bitvunit.rule.forms;

import com.gargoylesoftware.htmlunit.html.HtmlPage;
import com.gargoylesoftware.htmlunit.html.HtmlSelect;
import de.codescape.bitvunit.rule.AbstractRule;
import de.codescape.bitvunit.rule.Violation;

import java.util.List;

import static de.codescape.bitvunit.util.ElementInspector.elementHasValidId;
import static de.codescape.bitvunit.util.LabelInspector.labelForIdExists;

/**
 * LabelForInputFieldRule ensures that every <code>&lt;select /&gt;</code> tag within the given HTML document is
 * associated with a <code>&lt;label /&gt;</code> element that references the <code>&lt;select /&gt;</code> tag through
 * its <code>for</code> attribute.
 *
 * @since 0.1
 */
public class LabelForSelectTagRule extends AbstractRule {

    public static final String RULE_NAME = "LabelForSelectTag";
    private static final String RULE_MESSAGE = "For every <select /> tag there must be exactly one associated label tag.";

    @Override
    public String getName() {
        return RULE_NAME;
    }

    @Override
    protected void applyTo(HtmlPage htmlPage, List<Violation> violations) {
        for (HtmlSelect select : findAllSelectTags(htmlPage)) {
            if (elementHasValidId(select) && !labelForIdExists(select.getId(), findAllLabelTags(htmlPage))) {
                violations.add(createViolation(this, select.getStartLineNumber(), RULE_MESSAGE));
            }
        }
    }

}
