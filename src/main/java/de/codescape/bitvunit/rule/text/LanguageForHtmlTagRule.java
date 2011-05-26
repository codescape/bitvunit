package de.codescape.bitvunit.rule.text;

import com.gargoylesoftware.htmlunit.html.HtmlHtml;
import de.codescape.bitvunit.model.Page;
import de.codescape.bitvunit.rule.AbstractRule;
import de.codescape.bitvunit.rule.Violation;

import java.util.List;

import static de.codescape.bitvunit.util.ElementInspector.elementHasNonEmptyAttribute;

/**
 * LanguageForHtmlTagRule ensures that the given HTML document provides its language information through the
 * <code>lang</code> attribute on the <code>&lt;html&gt;</code> tag.
 *
 * @since 0.2
 */
public class LanguageForHtmlTagRule extends AbstractRule {

    public static final String RULE_NAME = "LanguageForHtmlTag";
    private static final String RULE_MESSAGE = "Every <html /> tag should communicate the main language of that page with its lang attribute.";

    private static final String LANG_ATTRIBUTE = "lang";

    @Override
    public String getName() {
        return RULE_NAME;
    }

    @Override
    protected void applyTo(Page page, List<Violation> violations) {
        for (HtmlHtml html : page.findAllHtmlTags()) {
            if (!elementHasNonEmptyAttribute(html, LANG_ATTRIBUTE)) {
                violations.add(createViolation(html.getStartLineNumber(), RULE_MESSAGE));
            }
        }
    }

}
