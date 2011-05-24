package de.codescape.bitvunit.rule;

import com.gargoylesoftware.htmlunit.html.*;

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
            result.add((T) element);
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

    @Override
    public String toString() {
        return getName();
    }

    protected List<HtmlLabel> findAllLabelTags(HtmlPage htmlPage) {
        return getElementsByTagName(htmlPage, HtmlLabel.TAG_NAME);
    }

    protected List<HtmlInput> findAllInputTags(HtmlPage htmlPage) {
        return getElementsByTagName(htmlPage, HtmlInput.TAG_NAME);
    }

    protected List<HtmlSelect> findAllSelectTags(HtmlPage htmlPage) {
        return getElementsByTagName(htmlPage, HtmlSelect.TAG_NAME);
    }

    protected List<HtmlTextArea> findAllTextareaTags(HtmlPage htmlPage) {
        return getElementsByTagName(htmlPage, HtmlTextArea.TAG_NAME);
    }

    protected List<HtmlImage> findAllImageTags(HtmlPage htmlPage) {
        return getElementsByTagName(htmlPage, HtmlImage.TAG_NAME);
    }

    protected List<HtmlTable> findAllTableTags(HtmlPage htmlPage) {
        return getElementsByTagName(htmlPage, HtmlTable.TAG_NAME);
    }

    protected List<HtmlBlink> findAllBlinkTags(HtmlPage htmlPage) {
        return getElementsByTagName(htmlPage, HtmlBlink.TAG_NAME);
    }

    protected List<HtmlMarquee> findAllMarqueeTags(HtmlPage htmlPage) {
        return getElementsByTagName(htmlPage, HtmlMarquee.TAG_NAME);
    }

    protected List<HtmlAbbreviated> findAllAbbreviationTags(HtmlPage htmlPage) {
        return getElementsByTagName(htmlPage, HtmlAbbreviated.TAG_NAME);
    }

    protected List<HtmlAcronym> findAllAcronymTags(HtmlPage htmlPage) {
        return getElementsByTagName(htmlPage, HtmlAcronym.TAG_NAME);
    }

}
