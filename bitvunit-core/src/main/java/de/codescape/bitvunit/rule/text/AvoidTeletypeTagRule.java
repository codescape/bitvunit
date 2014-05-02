package de.codescape.bitvunit.rule.text;

import com.gargoylesoftware.htmlunit.html.HtmlTeletype;
import de.codescape.bitvunit.model.Page;
import de.codescape.bitvunit.rule.AvoidElementRule;

import java.util.List;

/**
 * AvoidTeletypeTagRule ensures that every <code>&lt;tt/&gt;</code> element within the given page leads to a violation
 * because formatting and layout should be done by CSS to create semantic documents for better accessibility.
 *
 * @author Stefan Glase
 * @since 0.10
 */
public class AvoidTeletypeTagRule extends AvoidElementRule<HtmlTeletype> {

    private static final String RULE_NAME = "AvoidTeletypeTag";
    private static final String RULE_MESSAGE = "The <tt> tag should not be used. Define teletype or monospace text via CSS.";

    @Override
    public String getName() {
        return RULE_NAME;
    }

    @Override
    protected String getMessage() {
        return RULE_MESSAGE;
    }

    @Override
    protected List<HtmlTeletype> violatingElements(Page page) {
        return page.findAllTeletypeTags();
    }

}
