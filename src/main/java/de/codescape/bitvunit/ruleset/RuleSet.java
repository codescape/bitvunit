package de.codescape.bitvunit.ruleset;

import com.gargoylesoftware.htmlunit.html.HtmlPage;
import de.codescape.bitvunit.rule.Rule;
import de.codescape.bitvunit.rule.Violation;

import java.util.List;

public interface RuleSet {

    List<Rule> getRules();

    List<Violation> applyTo(HtmlPage htmlPage);

}
