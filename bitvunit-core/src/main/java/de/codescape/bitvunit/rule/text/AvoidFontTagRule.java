package de.codescape.bitvunit.rule.text;

import com.gargoylesoftware.htmlunit.html.HtmlFont;
import de.codescape.bitvunit.model.Page;
import de.codescape.bitvunit.rule.AvoidElementRule;

import java.util.List;

/**
 * AvoidFontTagRule ensures that every <code>&lt;font/&gt;</code> element within the given page leads to a violation
 * because formatting and layout should be done by CSS to create semantic documents for better accessibility.
 *
 * @author Stefan Glase
 * @since 0.10
 */
public class AvoidFontTagRule extends AvoidElementRule<HtmlFont> {

    private static final String RULE_NAME = "AvoidFontTag";
    public static final String RULE_MESSAGE = "The <font/> element should not be used in favor of styling text by CSS.";

    @Override
    public String getName() {
        return RULE_NAME;
    }

    @Override
    protected String getMessage() {
        return RULE_MESSAGE;
    }

    @Override
    protected List<HtmlFont> violatingElements(Page page) {
        return page.findAllFontTags();
    }

}
