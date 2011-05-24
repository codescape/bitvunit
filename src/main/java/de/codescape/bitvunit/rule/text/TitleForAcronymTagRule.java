package de.codescape.bitvunit.rule.text;

import com.gargoylesoftware.htmlunit.html.HtmlAcronym;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import de.codescape.bitvunit.rule.AbstractRule;
import de.codescape.bitvunit.rule.Violation;

import java.util.List;

import static de.codescape.bitvunit.util.ElementInspector.elementHasNonEmptyAttribute;

public class TitleForAcronymTagRule extends AbstractRule {

    public static final String RULE_NAME = "TitleForAcronymTag";
    private static final String RULE_MESSAGE = "Every <acronym /> tag should describe the marked acronym with its title attribute.";

    @Override
    public String getName() {
        return RULE_NAME;
    }

    @Override
    protected void applyTo(HtmlPage htmlPage, List<Violation> violations) {
        for (HtmlAcronym acronym : findAllAcronymTags(htmlPage)) {
            if (!elementHasNonEmptyAttribute(acronym, "title")) {
                violations.add(createViolation(this, acronym.getStartLineNumber(), RULE_MESSAGE));
            }
        }
    }

}
