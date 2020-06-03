package de.codescape.bitvunit.rule.media;

import com.gargoylesoftware.htmlunit.html.HtmlUnknownElement;
import de.codescape.bitvunit.model.Page;
import de.codescape.bitvunit.rule.AvoidElementRule;

import java.util.List;

/**
 * AvoidAppletTagRule ensures that there are no <code>&lt;applet /&gt;</code> tags used on the page under test. This tag
 * is not supported since HTML 5 and should be replaced by that <code>&lt;object /&gt;</code> tag.
 *
 * @author Stefan Glase
 * @since 0.8
 */
public class AvoidAppletTagRule extends AvoidElementRule<HtmlUnknownElement> {

    private static final String RULE_NAME = "AvoidAppletTag";
    private static final String RULE_MESSAGE = "The <applet> tag is not supported since HTML 5 and the <object> tag should be used instead.";

    @Override
    public String getName() {
        return RULE_NAME;
    }

    @Override
    protected String getMessage() {
        return RULE_MESSAGE;
    }

    @Override
    protected List<HtmlUnknownElement> violatingElements(Page page) {
        return page.findAllAppletTags();
    }

}
