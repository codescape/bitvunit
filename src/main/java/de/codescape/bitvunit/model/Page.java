package de.codescape.bitvunit.model;

import com.gargoylesoftware.htmlunit.html.*;

import java.util.List;

public class Page {

    private final HtmlPage htmlPage;

    public Page(HtmlPage htmlPage) {
        this.htmlPage = htmlPage;
    }

    private <T extends HtmlElement> List<T> allByTagName(String tagName) {
        return (List<T>) htmlPage.getElementsByTagName(tagName);
    }

    public List<HtmlLabel> findAllLabelTags() {
        return allByTagName(HtmlLabel.TAG_NAME);
    }

    public List<HtmlInput> findAllInputTags() {
        return allByTagName(HtmlInput.TAG_NAME);
    }

    public List<HtmlSelect> findAllSelectTags() {
        return allByTagName(HtmlSelect.TAG_NAME);
    }

    public List<HtmlTextArea> findAllTextareaTags() {
        return allByTagName(HtmlTextArea.TAG_NAME);
    }

    public List<HtmlImage> findAllImageTags() {
        return allByTagName(HtmlImage.TAG_NAME);
    }

    public List<HtmlTable> findAllTableTags() {
        return allByTagName(HtmlTable.TAG_NAME);
    }

    public List<HtmlBlink> findAllBlinkTags() {
        return allByTagName(HtmlBlink.TAG_NAME);
    }

    public List<HtmlMarquee> findAllMarqueeTags() {
        return allByTagName(HtmlMarquee.TAG_NAME);
    }

    public List<HtmlAbbreviated> findAllAbbreviationTags() {
        return allByTagName(HtmlAbbreviated.TAG_NAME);
    }

    public List<HtmlAcronym> findAllAcronymTags() {
        return allByTagName(HtmlAcronym.TAG_NAME);
    }

    public List<HtmlBold> findAllBoldTags() {
        return allByTagName(HtmlBold.TAG_NAME);
    }

    public List<HtmlItalic> findAllItalicTags() {
        return allByTagName(HtmlItalic.TAG_NAME);
    }

    public List<HtmlHtml> findAllHtmlTags() {
        return allByTagName(HtmlHtml.TAG_NAME);
    }

    public List<HtmlTableCell> findAllTableHeaders() {
        return allByTagName("th");
    }

    @SuppressWarnings("unchecked")
    public List<HtmlElement> findAllHeaderTags() {
        return (List<HtmlElement>) htmlPage.getByXPath("//*[name()='h1' or name()='h2' or name()='h3' or name()='h4' or name() = 'h5' or name()='h6']");
    }

    public List<HtmlMeta> findAllMetaTags() {
        return allByTagName(HtmlMeta.TAG_NAME);
    }

    public List<HtmlUnorderedList> findAllUnorderedLists() {
        return allByTagName(HtmlUnorderedList.TAG_NAME);
    }

    public List<HtmlOrderedList> findAllOrderedLists() {
        return allByTagName(HtmlOrderedList.TAG_NAME);
    }

    public List<HtmlInlineFrame> findAllInlineFrameTags() {
        return allByTagName(HtmlInlineFrame.TAG_NAME);
    }

    public HtmlElement findHtmlElementById(String elementId) {
        return htmlPage.getElementById(elementId);
    }

    public List<HtmlFrame> findAllFrameTags() {
        return allByTagName(HtmlFrame.TAG_NAME);
    }

    public List<HtmlArea> findAllAreaTags() {
        return allByTagName(HtmlArea.TAG_NAME);
    }

    public List<HtmlApplet> findAllAppletTags() {
        return allByTagName(HtmlApplet.TAG_NAME);
    }

    public List<HtmlDefinitionList> findAllDefinitionLists() {
        return allByTagName(HtmlDefinitionList.TAG_NAME);
    }

}
