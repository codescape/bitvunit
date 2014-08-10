package de.codescape.bitvunit.rule.page;

import com.gargoylesoftware.htmlunit.html.HtmlTitle;
import de.codescape.bitvunit.model.Page;
import de.codescape.bitvunit.rule.AbstractRule;
import de.codescape.bitvunit.rule.Violations;

import java.util.List;

/**
 * SingleTitlePerPageRule ensures that every page has exactly one <code>&lt;title&gt;</code> tag and will fail if there
 * is more than one title tag on a single page or no title tag at all.
 *
 * @author Stefan Glase
 * @since 0.13
 */
public class SingleTitlePerPageRule extends AbstractRule {

    private static final String RULE_NAME = "SingleTitlePerPage";
    private static final String RULE_MESSAGE = "Every page should have exactly one title.";

    @Override
    public String getName() {
        return RULE_NAME;
    }

    @Override
    protected void applyTo(Page page, Violations violations) {
        List<HtmlTitle> allTitleTags = page.findAllTitleTags();
        if (allTitleTags.isEmpty()) {
            violations.add(createViolation(null, page, RULE_MESSAGE));
        } else if (allTitleTags.size() > 1) {
            for (HtmlTitle title : allTitleTags) {
                violations.add(createViolation(title, page, RULE_MESSAGE));
            }
        }
    }

}
