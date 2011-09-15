package de.codescape.bitvunit.rule.frames;

import com.gargoylesoftware.htmlunit.html.HtmlInlineFrame;
import de.codescape.bitvunit.model.Page;
import de.codescape.bitvunit.rule.AbstractRule;
import de.codescape.bitvunit.rule.Violations;

/**
 * AlternativeTextForInlineFrameRule ensures that every inline frame marked through <code>&lt;iframe/&gt;</code> within
 * the given HTML document provides an alternative textual description inside it's body to explain the inline frame or a
 * hyperlink to an alternative version of that inline frame.
 *
 * @author Stefan Glase
 * @since 0.6
 */
public class AlternativeTextForInlineFrameRule extends AbstractRule {

    private static final String RULE_NAME = "AlternativeTextForInlineFrame";
    private static final String RULE_MESSAGE = "Every inline frame should contain an alternative text or hyperlink for browsers that do not support inline frames.";

    @Override
    public String getName() {
        return RULE_NAME;
    }

    @Override
    protected void applyTo(Page page, Violations violations) {
        for (HtmlInlineFrame inlineFrame : page.findAllInlineFrameTags()) {
            if (inlineFrame.getTextContent() == null || inlineFrame.getTextContent().trim().isEmpty()) {
                violations.add(createViolation(inlineFrame, RULE_MESSAGE));
            }
        }
    }

}
