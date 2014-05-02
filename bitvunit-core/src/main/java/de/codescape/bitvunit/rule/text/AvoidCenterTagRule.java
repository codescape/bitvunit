package de.codescape.bitvunit.rule.text;

import com.gargoylesoftware.htmlunit.html.HtmlCenter;
import de.codescape.bitvunit.model.Page;
import de.codescape.bitvunit.rule.AvoidElementRule;

import java.util.List;

/**
 * AvoidCenterTagRule ensures that every <code>&lt;center/&gt;</code> element within the given page leads to a violation
 * because formatting and layout should be done by CSS to create semantic documents for better accessibility.
 *
 * @author Stefan Glase
 * @since 0.10
 */
public class AvoidCenterTagRule extends AvoidElementRule<HtmlCenter> {

    private static final String RULE_NAME = "AvoidCenterTag";
    private static final String RULE_MESSAGE = "The <center/> element should not be used in favor of styling and positioning text by CSS.";

    @Override
    public String getName() {
        return RULE_NAME;
    }

    @Override
    protected String getMessage() {
        return RULE_MESSAGE;
    }

    @Override
    protected List<HtmlCenter> violatingElements(Page page) {
        return page.findAllCenterTags();
    }

}
