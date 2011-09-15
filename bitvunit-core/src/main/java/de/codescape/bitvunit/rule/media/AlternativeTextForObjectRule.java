package de.codescape.bitvunit.rule.media;


import com.gargoylesoftware.htmlunit.html.HtmlObject;
import de.codescape.bitvunit.model.Page;
import de.codescape.bitvunit.rule.AbstractRule;
import de.codescape.bitvunit.rule.Violations;

/**
 * AlternativeTextForObjectRule ensures that every <code>&lt;object/&gt;</code> within the given HTML document provides
 * an alternative textual description to explain the <code>&lt;object/&gt;</code> to everyone who is not able to view
 * and display the <code>&lt;object/&gt;</code> itself.
 *
 * @author Stefan Glase
 * @since 0.4
 */
public class AlternativeTextForObjectRule extends AbstractRule {

    private static final String RULE_NAME = "AlternativeTextForObject";
    private static final String RULE_MESSAGE = "Every object should include a description to explain the purpose of the object to anybody who is not able to use the object.";

    @Override
    public String getName() {
        return RULE_NAME;
    }

    @Override
    protected void applyTo(Page page, Violations violations) {
        for (HtmlObject object : page.findAllObjectTags()) {
            if (object.getTextContent() == null || object.getTextContent().isEmpty()) {
                violations.add(createViolation(object, RULE_MESSAGE));
            }
        }
    }

}
