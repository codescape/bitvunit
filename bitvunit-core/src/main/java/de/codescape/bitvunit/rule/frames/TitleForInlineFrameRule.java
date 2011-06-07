package de.codescape.bitvunit.rule.frames;

import com.gargoylesoftware.htmlunit.html.HtmlInlineFrame;
import de.codescape.bitvunit.model.Page;
import de.codescape.bitvunit.rule.AbstractRule;
import de.codescape.bitvunit.rule.Violations;

import static de.codescape.bitvunit.util.ElementInspector.elementHasNonEmptyAttribute;

/**
 * TitleForInlineFrameRule ensures that every inline frame that is marked through the <code>&lt;iframe /&gt;</code>
 * element within the given HTML document provides a description of that inline frame through its <code>title</code>
 * attribute.
 *
 * @since 0.3
 */
public class TitleForInlineFrameRule extends AbstractRule {

    private static final String RULE_NAME = "TitleForInlineFrame";
    private static final String RULE_MESSAGE = "Every inline frame should include a title describing the role of that inline frame.";

    @Override
    public String getName() {
        return RULE_NAME;
    }

    @Override
    protected void applyTo(Page page, Violations violations) {
        for (HtmlInlineFrame inlineFrame : page.findAllInlineFrameTags()) {
            if (!elementHasNonEmptyAttribute(inlineFrame, "title")) {
                violations.add(createViolation(inlineFrame.getStartLineNumber(), RULE_MESSAGE));
            }
        }
    }

}
