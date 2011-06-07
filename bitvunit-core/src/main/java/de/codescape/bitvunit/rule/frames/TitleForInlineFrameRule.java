package de.codescape.bitvunit.rule.frames;

import com.gargoylesoftware.htmlunit.html.HtmlInlineFrame;
import de.codescape.bitvunit.model.Page;
import de.codescape.bitvunit.rule.AbstractRule;
import de.codescape.bitvunit.rule.Violations;

import java.util.List;

import static de.codescape.bitvunit.util.ElementInspector.elementHasNonEmptyAttribute;

/**
 * TODO add javadoc
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
        List<HtmlInlineFrame> inlineFrames = page.findAllInlineFrameTags();
        for (HtmlInlineFrame inlineFrame : inlineFrames) {
            if (!elementHasNonEmptyAttribute(inlineFrame, "title")) {
                violations.add(createViolation(inlineFrame.getStartLineNumber(), RULE_MESSAGE));
            }
        }
    }

}
