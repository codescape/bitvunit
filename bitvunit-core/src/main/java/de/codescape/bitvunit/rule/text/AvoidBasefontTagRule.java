package de.codescape.bitvunit.rule.text;

import com.gargoylesoftware.htmlunit.html.HtmlBaseFont;
import de.codescape.bitvunit.model.Page;
import de.codescape.bitvunit.rule.AvoidElementRule;

import java.util.List;

/**
 * AvoidBasefontTagRule ensures that every <code>&lt;basefont/&gt;</code> element in the given HTML document leads to a
 * violation since this tag is marked as deprecated and should not be used anymore.
 *
 * @author Stefan Glase
 * @since 0.10
 */
public class AvoidBasefontTagRule extends AvoidElementRule<HtmlBaseFont> {

    private static final String RULE_NAME = "AvoidBasefontTag";
    private static final String RULE_MESSAGE = "The <basefont /> tag is marked as deprecated and should not be used.";

    @Override
    public String getName() {
        return RULE_NAME;
    }

    @Override
    protected String getMessage() {
        return RULE_MESSAGE;
    }

    @Override
    protected List<HtmlBaseFont> violatingElements(Page page) {
        return page.findAllBasefontTags();
    }

}
