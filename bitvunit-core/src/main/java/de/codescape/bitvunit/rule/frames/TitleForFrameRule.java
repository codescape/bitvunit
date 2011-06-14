package de.codescape.bitvunit.rule.frames;

import com.gargoylesoftware.htmlunit.html.HtmlFrame;
import de.codescape.bitvunit.model.Page;
import de.codescape.bitvunit.rule.AbstractRule;
import de.codescape.bitvunit.rule.Violations;

import java.util.List;

import static de.codescape.bitvunit.util.ElementInspector.elementHasNonEmptyAttribute;

/**
 * TitleForFrameRule ensures that every frame that is marked through the <code>&lt;frame /&gt;</code> element within the
 * given HTML document provides a description of that frame through its <code>title</code> attribute.
 *
 * @since 0.3
 */
public class TitleForFrameRule extends AbstractRule {

    private static final String RULE_NAME = "TitleForFrame";
    private static final String RULE_MESSAGE = "Every frame should include a title describing the role of that frame.";

    @Override
    public String getName() {
        return RULE_NAME;
    }

    @Override
    protected void applyTo(Page page, Violations violations) {
        List<HtmlFrame> frames = page.findAllFrameTags();
        for (HtmlFrame frame : frames) {
            if (!elementHasNonEmptyAttribute(frame, "title")) {
                violations.add(createViolation(frame, RULE_MESSAGE));
            }
        }
    }

}
