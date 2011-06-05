package de.codescape.bitvunit.rule.text;

import com.gargoylesoftware.htmlunit.html.HtmlAcronym;
import de.codescape.bitvunit.model.Page;
import de.codescape.bitvunit.rule.AbstractRule;
import de.codescape.bitvunit.rule.Violations;

import static de.codescape.bitvunit.util.ElementInspector.elementHasNonEmptyAttribute;

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
            if (!elementHasNonEmptyAttribute(acronym, "title")) {
                violations.add(createViolation(acronym.getStartLineNumber(), RULE_MESSAGE));
            }
        }
    }

}
