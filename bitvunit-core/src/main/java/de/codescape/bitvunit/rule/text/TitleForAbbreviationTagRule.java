package de.codescape.bitvunit.rule.text;

import com.gargoylesoftware.htmlunit.html.HtmlAbbreviated;
import de.codescape.bitvunit.model.Page;
import de.codescape.bitvunit.rule.AbstractRule;
import de.codescape.bitvunit.rule.Violations;

import static de.codescape.bitvunit.util.html.HtmlElementUtil.hasNonEmptyAttribute;

/**
 * TitleForAbbreviationTagRule ensures that every abbreviation that is marked through the <code>&lt;abbr /&gt;</code>
 * element within the given HTML document provides a description of that abbreviation through its <code>title</code>
 * attribute.
 *
 * @author Stefan Glase
 * @since 0.1
 */
public class TitleForAbbreviationTagRule extends AbstractRule {

    private static final String RULE_NAME = "TitleForAbbreviationTag";
    private static final String RULE_MESSAGE = "Every <abbr /> tag should describe the marked abbreviation with its title attribute.";

    @Override
    public String getName() {
        return RULE_NAME;
    }

    @Override
    protected void applyTo(Page page, Violations violations) {
        for (HtmlAbbreviated abbr : page.findAllAbbreviationTags()) {
            if (!hasNonEmptyAttribute(abbr, "title")) {
                violations.add(createViolation(abbr, RULE_MESSAGE));
            }
        }
    }

}
