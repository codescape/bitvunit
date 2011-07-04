package de.codescape.bitvunit.hamcrest;

import com.gargoylesoftware.htmlunit.html.HtmlPage;
import de.codescape.bitvunit.rule.Rule;
import de.codescape.bitvunit.rule.Violation;
import de.codescape.bitvunit.rule.Violations;

import static de.codescape.bitvunit.util.HtmlPageUtil.htmlPageFromString;

public class AbstractHamcrestTest {

    protected HtmlPage somePage() {
        return htmlPageFromString("<html><body><p>Hello Hamcrest!</p></body></html>");
    }

    protected Violations someViolation(Rule rule) {
        Violations violations = new Violations();
        violations.add(new Violation(rule, somePage().getBody(), "uh oh"));
        return violations;
    }

    protected Violations noViolation() {
        return new Violations();
    }

}
