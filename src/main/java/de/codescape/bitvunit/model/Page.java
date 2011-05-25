package de.codescape.bitvunit.model;

import com.gargoylesoftware.htmlunit.html.*;

import java.util.ArrayList;
import java.util.List;

public class Page {

    private final HtmlPage htmlPage;

    public Page(HtmlPage htmlPage) {
        this.htmlPage = htmlPage;
    }

    @SuppressWarnings("unchecked")
    private <T extends HtmlElement> List<T> findHtmlElementsByTagName(String tagName) {
        List<T> result = new ArrayList<T>();
        for (HtmlElement element : htmlPage.getElementsByTagName(tagName)) {
            result.add((T) element);
        }
        return result;
    }

    public List<HtmlLabel> findAllLabelTags() {
        return findHtmlElementsByTagName(HtmlLabel.TAG_NAME);
    }

    public List<HtmlInput> findAllInputTags() {
        return findHtmlElementsByTagName(HtmlInput.TAG_NAME);
    }

    public List<HtmlSelect> findAllSelectTags() {
        return findHtmlElementsByTagName(HtmlSelect.TAG_NAME);
    }

    public List<HtmlTextArea> findAllTextareaTags() {
        return findHtmlElementsByTagName(HtmlTextArea.TAG_NAME);
    }

    public List<HtmlImage> findAllImageTags() {
        return findHtmlElementsByTagName(HtmlImage.TAG_NAME);
    }

    public List<HtmlTable> findAllTableTags() {
        return findHtmlElementsByTagName(HtmlTable.TAG_NAME);
    }

    public List<HtmlBlink> findAllBlinkTags() {
        return findHtmlElementsByTagName(HtmlBlink.TAG_NAME);
    }

    public List<HtmlMarquee> findAllMarqueeTags() {
        return findHtmlElementsByTagName(HtmlMarquee.TAG_NAME);
    }

    public List<HtmlAbbreviated> findAllAbbreviationTags() {
        return findHtmlElementsByTagName(HtmlAbbreviated.TAG_NAME);
    }

    public List<HtmlAcronym> findAllAcronymTags() {
        return findHtmlElementsByTagName(HtmlAcronym.TAG_NAME);
    }

}
