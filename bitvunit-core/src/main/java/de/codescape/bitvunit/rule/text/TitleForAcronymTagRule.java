package de.codescape.bitvunit.rule.text;

import com.gargoylesoftware.htmlunit.html.HtmlAcronym;
import de.codescape.bitvunit.model.Page;
import de.codescape.bitvunit.rule.AbstractRule;
import de.codescape.bitvunit.rule.Violations;

import static de.codescape.bitvunit.util.html.HtmlElementUtil.hasNonEmptyAttribute;

/**
 * TitleForAcronymTagRule ensures that every acronym that is marked through the <code>&lt;acronym/&gt;</code> element
 * within the given HTML document provides a description of that acronym through it's <code>title</code> attribute.
 *
 * @author Stefan Glase
 * @since 0.1
 */
public class TitleForAcronymTagRule extends AbstractRule {

    private static final String RULE_NAME = "TitleForAcronymTag";
    private static final String RULE_MESSAGE = "Every <acronym /> tag should describe the marked acronym with its title attribute.";

    @Override
    public String getName() {
        return RULE_NAME;
    }

    @Override
    protected void applyTo(Page page, Violations violations) {
        for (HtmlAcronym acronym : page.findAllAcronymTags()) {
            if (!hasNonEmptyAttribute(acronym, "title")) {
                violations.add(createViolation(acronym, page, RULE_MESSAGE));
            }
        }
    }

}
