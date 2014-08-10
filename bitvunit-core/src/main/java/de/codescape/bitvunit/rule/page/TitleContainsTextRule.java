package de.codescape.bitvunit.rule.page;

import com.gargoylesoftware.htmlunit.html.HtmlTitle;
import de.codescape.bitvunit.model.Page;
import de.codescape.bitvunit.rule.AbstractRule;
import de.codescape.bitvunit.rule.Violations;

/**
 * TitleContainsTextRule ensures that page titles defined by the <code>&lt;title/&gt;</code> tag to describe that page
 * contain text. This rule is satisfied by a <code>&lt;title/&gt;</code> tag that contains minimum one readable
 * character inside it's body.
 * <p/>
 * A good example would look like this:
 * <pre><code>&lt;title&gt;BitvUnit - User Manual&lt;/title&gt;</code></pre>
 *
 * @author Stefan Glase
 * @since 0.6
 */
public class TitleContainsTextRule extends AbstractRule {

    private static final String RULE_NAME = "TitleContainsText";
    private static final String RULE_MESSAGE = "Every title tag should contain text to describe that page!";

    @Override
    public String getName() {
        return RULE_NAME;
    }

    @Override
    protected void applyTo(Page page, Violations violations) {
        for (HtmlTitle title : page.findAllTitleTags()) {
            if (title.getTextContent() == null || title.getTextContent().trim().isEmpty()) {
                violations.add(createViolation(title, page, RULE_MESSAGE));
            }
        }
    }

}
