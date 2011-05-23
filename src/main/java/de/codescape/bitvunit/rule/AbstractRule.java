package de.codescape.bitvunit.rule;

import com.gargoylesoftware.htmlunit.html.HtmlElement;
import com.gargoylesoftware.htmlunit.html.HtmlPage;

import java.util.ArrayList;
import java.util.List;

public abstract class AbstractRule implements Rule {

    public abstract String getName();

    public abstract List<Violation> applyTo(HtmlPage htmlPage);

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
