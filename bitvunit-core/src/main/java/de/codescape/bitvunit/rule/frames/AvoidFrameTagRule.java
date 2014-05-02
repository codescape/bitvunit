package de.codescape.bitvunit.rule.frames;

import com.gargoylesoftware.htmlunit.html.HtmlFrame;
import de.codescape.bitvunit.model.Page;
import de.codescape.bitvunit.rule.AvoidElementRule;

import java.util.List;

/**
 * AvoidFrameTagRule ensures that every <code>&lt;frame/&gt;</code> element within the given HTML document leads to a
 * violation because frames are not supported anymore in HTML 5 and tend to create badly accessible pages.
 *
 * @author Stefan Glase
 * @since 0.10
 */
public class AvoidFrameTagRule extends AvoidElementRule<HtmlFrame> {

    private static final String RULE_NAME = "AvoidFrameTag";
    private static final String RULE_MESSAGE = "The <frame> tag is not supported in HTML 5 anymore and should not be used.";

    @Override
    public String getName() {
        return RULE_NAME;
    }

    @Override
    protected String getMessage() {
        return RULE_MESSAGE;
    }

    @Override
    protected List<HtmlFrame> violatingElements(Page page) {
        return page.findAllFrameTags();
    }

}
