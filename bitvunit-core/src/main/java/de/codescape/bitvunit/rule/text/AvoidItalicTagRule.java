package de.codescape.bitvunit.rule.text;

import com.gargoylesoftware.htmlunit.html.HtmlItalic;
import de.codescape.bitvunit.model.Page;
import de.codescape.bitvunit.rule.AvoidElementRule;

import java.util.List;

/**
 * AvoidItalicTagRule ensures that every <code>&lt;i/&gt;</code> element within the given HTML document leads to a
 * violation because the <code>&lt;em/&gt;</code> element should be used to outline important text.
 *
 * @author Stefan Glase
 * @since 0.2
 */
public class AvoidItalicTagRule extends AvoidElementRule<HtmlItalic> {

    private static final String RULE_NAME = "AvoidItalicTag";
    private static final String RULE_MESSAGE = "The <i /> element should not be used because <em /> is a more semantic way to outline text.";

    @Override
    public String getName() {
        return RULE_NAME;
    }

    @Override
    protected String getMessage() {
        return RULE_MESSAGE;
    }

    @Override
    protected List<HtmlItalic> violatingElements(Page page) {
        return page.findAllItalicTags();
    }

}
