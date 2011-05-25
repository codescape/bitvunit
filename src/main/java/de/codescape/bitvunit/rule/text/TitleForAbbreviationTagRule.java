package de.codescape.bitvunit.rule.text;

import com.gargoylesoftware.htmlunit.html.HtmlAbbreviated;
import de.codescape.bitvunit.model.Page;
import de.codescape.bitvunit.rule.AbstractRule;
import de.codescape.bitvunit.rule.Violation;

import java.util.List;

import static de.codescape.bitvunit.util.ElementInspector.elementHasNonEmptyAttribute;

/**
 * TitleForAbbreviationTag ensures that every abbreviation that is marked through the <code>&lt;abbr /&gt;</code>
 * element within the given HTML document provides a description of that abbreviation through its <code>title</code>
 * attribute.
 *
 * @since 0.1
 */
public class TitleForAbbreviationTagRule extends AbstractRule {

    public static final String RULE_NAME = "TitleForAbbreviationTag";
    private static final String RULE_MESSAGE = "Every <abbr /> tag should describe the marked abbreviation with its title attribute.";

    @Override
    public String getName() {
        return RULE_NAME;
    }

    @Override
    protected void applyTo(Page page, List<Violation> violations) {
        for (HtmlAbbreviated abbr : page.findAllAbbreviationTags()) {
            if (!elementHasNonEmptyAttribute(abbr, "title")) {
                violations.add(createViolation(abbr.getStartLineNumber(), RULE_MESSAGE));
            }
        }
    }

}
