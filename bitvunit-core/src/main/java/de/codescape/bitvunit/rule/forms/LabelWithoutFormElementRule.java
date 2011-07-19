package de.codescape.bitvunit.rule.forms;

import com.gargoylesoftware.htmlunit.html.*;
import de.codescape.bitvunit.model.Page;
import de.codescape.bitvunit.rule.AbstractRule;
import de.codescape.bitvunit.rule.Violations;

import java.util.List;

/**
 * LabelWithoutFormElementRule ensures that every <code>&lt;label /&gt;</code> tag within the given HTML document is
 * referencing a valid form element. The id of the form element must be equal to the for attribute of the label. Valid
 * form elements are <code>&lt;select /&gt;</code>, <code>&lt;input /&gt;</code> and <code>&lt;textarea /&gt;</code>
 * elements.
 *
 * @since 0.3
 * @author Stefan Glase
 */
public class LabelWithoutFormElementRule extends AbstractRule {

    private static final String RULE_NAME = "LabelWithoutFormElement";
    private static final String RULE_MESSAGE = "A label always has to reference a valid form element with its for attribute.";

    @Override
    public String getName() {
        return RULE_NAME;
    }

    @Override
    protected void applyTo(Page page, Violations violations) {
        List<HtmlLabel> labels = page.findAllLabelTags();
        for (HtmlLabel label : labels) {
            if (!associatedFormElementExists(page, label.getForAttribute())) {
                violations.add(createViolation(label, RULE_MESSAGE));
            }
        }
    }

    private boolean associatedFormElementExists(Page page, String elementId) {
        HtmlElement foundElement = page.findHtmlElementById(elementId);
        return foundElement != null && isFormElement(foundElement);
    }

    private boolean isFormElement(HtmlElement element) {
        return (element instanceof HtmlInput || element instanceof HtmlSelect || element instanceof HtmlTextArea);
    }

}
