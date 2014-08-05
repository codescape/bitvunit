package de.codescape.bitvunit.rule.text;

import com.gargoylesoftware.htmlunit.html.HtmlUnderlined;
import de.codescape.bitvunit.model.Page;
import de.codescape.bitvunit.rule.AvoidElementRule;

import java.util.List;

/**
 * AvoidUnderlineTagRule ensures that every <code>&lt;u/&gt;</code> element within the given page leads to a violation
 * because formatting and layout should be done by CSS to create semantic documents for better accessibility.
 *
 * @author Stefan Glase
 * @since 0.13
 */
public class AvoidUnderlineTagRule extends AvoidElementRule<HtmlUnderlined> {

    private static final String RULE_NAME = "AvoidUnderlineTag";
    private static final String RULE_MESSAGE = "Do not use the <u/> element to style text.";

    @Override
    public String getName() {
        return RULE_NAME;
    }

    @Override
    protected String getMessage() {
        return RULE_MESSAGE;
    }

    @Override
    protected List<HtmlUnderlined> violatingElements(Page page) {
        return page.findAllUnderlineTags();
    }

}
