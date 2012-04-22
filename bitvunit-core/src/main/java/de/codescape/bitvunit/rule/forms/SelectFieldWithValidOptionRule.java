package de.codescape.bitvunit.rule.forms;

import com.gargoylesoftware.htmlunit.html.HtmlOption;
import com.gargoylesoftware.htmlunit.html.HtmlSelect;
import de.codescape.bitvunit.model.Page;
import de.codescape.bitvunit.rule.AbstractRule;
import de.codescape.bitvunit.rule.Violations;

import java.util.List;

/**
 * SelectFieldWithValidOptionRule ensures that every <code>&lt;select/&gt;</code> element contains minimum one enabled
 * option and that the first possible option is not disabled.
 * <p/>
 * A good example for a valid select field looks like this:
 * <pre><code>
 * &lt;select id="gender"&gt;
 *   &lt;option&gt;female&lt;option&gt;
 *   &lt;option&gt;male&lt;option&gt;
 * &lt;/select&gt;</code></pre>
 *
 * @author Stefan Glase
 * @since 0.8
 */
public class SelectFieldWithValidOptionRule extends AbstractRule {

    private static final String RULE_NAME = "SelectFieldWithValidOption";
    private static final String RULE_MESSAGE_MINIMUM_ONE_OPTION = "Minimum one option must be provided for select field.";
    private static final String RULE_MESSAGE_FIRST_OPTION_ENABLED = "First option for select field must not be disabled.";

    @Override
    public String getName() {
        return RULE_NAME;
    }

    @Override
    protected void applyTo(Page page, Violations violations) {
        for (HtmlSelect select : page.findAllSelectTags()) {
            List<HtmlOption> options = select.getOptions();
            if (options.isEmpty()) {
                violations.add(createViolation(select, RULE_MESSAGE_MINIMUM_ONE_OPTION));
            } else {
                if (options.get(0).hasAttribute("disabled")) {
                    violations.add(createViolation(select, RULE_MESSAGE_FIRST_OPTION_ENABLED));
                }
            }
        }
    }

}
