package de.codescape.bitvunit.rule;

import com.gargoylesoftware.htmlunit.html.HtmlElement;
import com.gargoylesoftware.htmlunit.html.HtmlPage;

import java.util.ArrayList;
import java.util.List;

public abstract class AbstractRule implements Rule {

    @Override
    public abstract String getName();

    @Override
    public final List<Violation> applyTo(HtmlPage htmlPage) {
        List<Violation> violations = new ArrayList<Violation>();
        applyTo(htmlPage, violations);
        return violations;
    }

    protected abstract void applyTo(HtmlPage htmlPage, List<Violation> violations);

    @SuppressWarnings("unchecked")
    protected <T extends HtmlElement> List<T> getElementsByTagName(HtmlPage htmlPage, String tagName) {
        List<T> result = new ArrayList<T>();
        for (HtmlElement element : htmlPage.getElementsByTagName(tagName)) {
            result.add((T)element);
        }
        return result;
    }

    protected Violation createViolation(Rule rule, int lineNumber, String message) {
        Violation violation = new Violation();
        violation.setRule(rule);
        violation.setLineNumber(lineNumber);
        violation.setMessage(message);
        return violation;
    }

}
