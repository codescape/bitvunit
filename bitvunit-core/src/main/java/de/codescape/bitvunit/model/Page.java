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

    public List<HtmlBold> findAllBoldTags() {
        return findHtmlElementsByTagName(HtmlBold.TAG_NAME);
    }

    public List<HtmlItalic> findAllItalicTags() {
        return findHtmlElementsByTagName(HtmlItalic.TAG_NAME);
    }

    public List<HtmlHtml> findAllHtmlTags() {
        return findHtmlElementsByTagName(HtmlHtml.TAG_NAME);
    }

    public List<HtmlTableCell> findAllTableHeaders() {
        return findHtmlElementsByTagName("th");
    }

    @SuppressWarnings("unchecked")
    public List<HtmlElement> findAllHeaderTags() {
        return (List<HtmlElement>) htmlPage.getByXPath("//*[name()='h1' or name()='h2' or name()='h3' or name()='h4' or name() = 'h5' or name()='h6']");
    }

    public List<HtmlMeta> findAllMetaTags() {
        return findHtmlElementsByTagName(HtmlMeta.TAG_NAME);
    }

    public List<HtmlUnorderedList> findAllUnorderedLists() {
        return findHtmlElementsByTagName(HtmlUnorderedList.TAG_NAME);
    }

    public List<HtmlOrderedList> findAllOrderedLists() {
        return findHtmlElementsByTagName(HtmlOrderedList.TAG_NAME);
    }

    public List<HtmlInlineFrame> findAllInlineFrameTags() {
        return findHtmlElementsByTagName(HtmlInlineFrame.TAG_NAME);
    }

    public HtmlElement findHtmlElementById(String elementId) {
        return htmlPage.getElementById(elementId);
    }

    public List<HtmlFrame> findAllFrameTags() {
        return findHtmlElementsByTagName(HtmlFrame.TAG_NAME);
    }

    public List<HtmlArea> findAllAreaTags() {
        return findHtmlElementsByTagName(HtmlArea.TAG_NAME);
    }

}
