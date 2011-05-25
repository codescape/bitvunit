package de.codescape.bitvunit.rule;

import com.gargoylesoftware.htmlunit.html.HtmlPage;
import de.codescape.bitvunit.model.Page;

import java.util.ArrayList;
import java.util.List;

public abstract class AbstractRule implements Rule {

    @Override
    public abstract String getName();

    @Override
    public final List<Violation> applyTo(HtmlPage htmlPage) {
        List<Violation> violations = new ArrayList<Violation>();
        applyTo(new Page(htmlPage), violations);
        return violations;
    }

    protected abstract void applyTo(Page page, List<Violation> violations);

    protected Violation createViolation(Rule rule, int lineNumber, String message) {
        return new Violation(rule, lineNumber, message);
    }

    protected Violation createViolation(int lineNumber, String message) {
        return createViolation(this, lineNumber, message);
    }

    @Override
    public String toString() {
        return getName();
    }

}
