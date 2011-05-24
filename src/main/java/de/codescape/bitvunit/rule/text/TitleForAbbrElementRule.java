package de.codescape.bitvunit.rule.text;

import com.gargoylesoftware.htmlunit.html.HtmlAbbreviated;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import de.codescape.bitvunit.rule.AbstractRule;
import de.codescape.bitvunit.rule.Violation;

import java.util.List;

/**
 * TitleForAbbrElementRule ensures that every abbreviation that is marked through the <code>&lt;abbr /&gt;</code>
 * element within the given HTML document provides a description of that abbreviation through its <code>title</code>
 * attribute.
 *
 * @since 0.1
 */
public class TitleForAbbrElementRule extends AbstractRule {

    public static final String RULE_NAME = "TitleForAbbrElement";
    private static final String RULE_MESSAGE = "Every <abbr /> element should describe the marked abbreviation with the title attribute.";

    @Override
    public String getName() {
        return RULE_NAME;
    }

    @Override
    protected void applyTo(HtmlPage htmlPage, List<Violation> violations) {
        List<HtmlAbbreviated> abbrs = getElementsByTagName(htmlPage, HtmlAbbreviated.TAG_NAME);
        for (HtmlAbbreviated abbr : abbrs) {
            if (!abbr.getAttributesMap().containsKey("title") || abbr.getAttribute("title").isEmpty()) {
                violations.add(createViolation(this, abbr.getStartLineNumber(), RULE_MESSAGE));
            }
        }
    }

}
