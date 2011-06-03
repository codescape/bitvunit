package de.codescape.bitvunit.ruleset;

import com.gargoylesoftware.htmlunit.html.HtmlPage;
import de.codescape.bitvunit.rule.Rule;
import de.codescape.bitvunit.rule.Violations;

import java.util.List;

public interface RuleSet {

    List<Rule> getRules();

    Violations applyTo(HtmlPage htmlPage);

}
