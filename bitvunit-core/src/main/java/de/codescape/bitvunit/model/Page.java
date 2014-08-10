package de.codescape.bitvunit.model;

import com.gargoylesoftware.htmlunit.ElementNotFoundException;
import com.gargoylesoftware.htmlunit.html.*;
import org.w3c.dom.DocumentType;

import java.util.List;

/**
 * Wrapper class around a {@link HtmlPage} instance that provides convenience operations used from {@link
 * de.codescape.bitvunit.rule.Rule} implementations.
 *
 * @author Stefan Glase
 */
public class Page {

    private final HtmlPage htmlPage;

    /**
     * Initializes the wrapper class with the {@link HtmlPage} to be wrapped.
     *
     * @param htmlPage the {@link HtmlPage} to be wrapped
     */
    public Page(HtmlPage htmlPage) {
        this.htmlPage = htmlPage;
    }

    /**
     * Returns all elements of the given HTML tag name as subtypes of {@link DomElement}.
     *
     * @param tagName HTML tag name
     * @param <T>     type of elements
     * @return list of DOM elements
     */
    @SuppressWarnings("unchecked")
    private <T extends DomElement> List<T> allByTagName(String tagName) {
        return (List<T>) htmlPage.getElementsByTagName(tagName);
    }

    /**
     * Returns all <code>&lt;label/&gt;</code> tags as {@link HtmlLabel} instances.
     *
     * @return all <code>&lt;label/&gt;</code> tags as {@link HtmlLabel} instances
     */
    public List<HtmlLabel> findAllLabelTags() {
        return allByTagName(HtmlLabel.TAG_NAME);
    }

    /**
     * Returns all <code>&lt;input/&gt;</code> tags as {@link HtmlInput} instances.
     *
     * @return all <code>&lt;input/&gt;</code> tags as {@link HtmlInput} instances
     */
    public List<HtmlInput> findAllInputTags() {
        return allByTagName(HtmlInput.TAG_NAME);
    }

    /**
     * Returns all <code>&lt;select/&gt;</code> tags as {@link HtmlSelect} instances.
     *
     * @return all <code>&lt;select/&gt;</code> tags as {@link HtmlSelect} instances
     */
    public List<HtmlSelect> findAllSelectTags() {
        return allByTagName(HtmlSelect.TAG_NAME);
    }

    /**
     * Returns all <code>&lt;textarea/&gt;</code> tags as {@link HtmlTextArea} instances.
     *
     * @return all <code>&lt;textarea/&gt;</code> tags as {@link HtmlTextArea} instances
     */
    public List<HtmlTextArea> findAllTextareaTags() {
        return allByTagName(HtmlTextArea.TAG_NAME);
    }

    /**
     * Returns all <code>&lt;img/&gt;</code> tags as {@link HtmlImage} instances.
     *
     * @return all <code>&lt;img/&gt;</code> tags as {@link HtmlImage} instances
     */
    public List<HtmlImage> findAllImageTags() {
        return allByTagName(HtmlImage.TAG_NAME);
    }

    /**
     * Returns all <code>&lt;table/&gt;</code> tags as {@link HtmlTable} instances.
     *
     * @return all <code>&lt;table/&gt;</code> tags as {@link HtmlTable} instances
     */
    public List<HtmlTable> findAllTableTags() {
        return allByTagName(HtmlTable.TAG_NAME);
    }

    /**
     * Returns all <code>&lt;blink/&gt;</code> tags as {@link HtmlBlink} instances.
     *
     * @return all <code>&lt;blink/&gt;</code> tags as {@link HtmlBlink} instances
     */
    public List<HtmlBlink> findAllBlinkTags() {
        return allByTagName(HtmlBlink.TAG_NAME);
    }

    /**
     * Returns all <code>&lt;marquee/&gt;</code> tags as {@link HtmlMarquee} instances.
     *
     * @return all <code>&lt;marquee/&gt;</code> tags as {@link HtmlMarquee} instances
     */
    public List<HtmlMarquee> findAllMarqueeTags() {
        return allByTagName(HtmlMarquee.TAG_NAME);
    }

    /**
     * Returns all <code>&lt;abbr/&gt;</code> tags as {@link HtmlAbbreviated} instances.
     *
     * @return all <code>&lt;abbr/&gt;</code> tags as {@link HtmlAbbreviated} instances
     */
    public List<HtmlAbbreviated> findAllAbbreviationTags() {
        return allByTagName(HtmlAbbreviated.TAG_NAME);
    }

    /**
     * Returns all <code>&lt;acronym/&gt;</code> tags as {@link HtmlAcronym} instances.
     *
     * @return all <code>&lt;acronym/&gt;</code> tags as {@link HtmlAcronym} instances
     */
    public List<HtmlAcronym> findAllAcronymTags() {
        return allByTagName(HtmlAcronym.TAG_NAME);
    }

    /**
     * Returns all <code>&lt;b/&gt;</code> tags as {@link HtmlBold} instances.
     *
     * @return all <code>&lt;b/&gt;</code> tags as {@link HtmlBold} instances
     */
    public List<HtmlBold> findAllBoldTags() {
        return allByTagName(HtmlBold.TAG_NAME);
    }

    /**
     * Returns all <code>&lt;i/&gt;</code> tags as {@link HtmlItalic} instances.
     *
     * @return all <code>&lt;i/&gt;</code> tags as {@link HtmlItalic} instances
     */
    public List<HtmlItalic> findAllItalicTags() {
        return allByTagName(HtmlItalic.TAG_NAME);
    }

    /**
     * Returns all <code>&lt;html/&gt;</code> tags as {@link HtmlHtml} instances.
     *
     * @return all <code>&lt;html/&gt;</code> tags as {@link HtmlHtml} instances
     */
    public List<HtmlHtml> findAllHtmlTags() {
        return allByTagName(HtmlHtml.TAG_NAME);
    }

    /**
     * Returns all <code>&lt;th/&gt;</code> tags as {@link HtmlTableCell} instances.
     *
     * @return all <code>&lt;th/&gt;</code> tags as {@link HtmlTableCell} instances
     */
    public List<HtmlTableCell> findAllTableHeaders() {
        return allByTagName("th");
    }

    /**
     * Returns all <code>&lt;h1/&gt;</code>, <code>&lt;h2/&gt;</code>, <code>&lt;h3/&gt;</code>,
     * <code>&lt;h4/&gt;</code>, <code>&lt;h5/&gt;</code> and <code>&lt;h6/&gt;</code> tags as {@link HtmlElement}
     * instances.
     *
     * @return all <code>&lt;h1/&gt;</code>, <code>&lt;h2/&gt;</code>, <code>&lt;h3/&gt;</code>,
     * <code>&lt;h4/&gt;</code>, <code>&lt;h5/&gt;</code> and <code>&lt;h6/&gt;</code> tags as {@link
     * HtmlElement} instances
     */
    @SuppressWarnings("unchecked")
    public List<HtmlElement> findAllHeadingTags() {
        return (List<HtmlElement>) htmlPage.getByXPath("//*[name()='h1' or name()='h2' or name()='h3' or name()='h4' or name() = 'h5' or name()='h6']");
    }

    /**
     * Returns all <code>&lt;meta/&gt;</code> tags as {@link HtmlMeta} instances.
     *
     * @return all <code>&lt;meta/&gt;</code> tags as {@link HtmlMeta} instances
     */
    public List<HtmlMeta> findAllMetaTags() {
        return allByTagName(HtmlMeta.TAG_NAME);
    }

    /**
     * Returns all <code>&lt;ul/&gt;</code> tags as {@link HtmlUnorderedList} instances.
     *
     * @return all <code>&lt;ul/&gt;</code> tags as {@link HtmlUnorderedList} instances
     */
    public List<HtmlUnorderedList> findAllUnorderedLists() {
        return allByTagName(HtmlUnorderedList.TAG_NAME);
    }

    /**
     * Returns all <code>&lt;ol/&gt;</code> tags as {@link HtmlOrderedList} instances.
     *
     * @return all <code>&lt;ol/&gt;</code> tags as {@link HtmlOrderedList} instances
     */
    public List<HtmlOrderedList> findAllOrderedLists() {
        return allByTagName(HtmlOrderedList.TAG_NAME);
    }

    /**
     * Returns all <code>&lt;iframe/&gt;</code> tags as {@link HtmlInlineFrame} instances.
     *
     * @return all <code>&lt;iframe/&gt;</code> tags as {@link HtmlInlineFrame} instances
     */
    public List<HtmlInlineFrame> findAllInlineFrameTags() {
        return allByTagName(HtmlInlineFrame.TAG_NAME);
    }

    /**
     * Returns the {@link HtmlElement} with the given id.
     *
     * @param elementId id to search for on the HTML page
     * @return {@link HtmlElement} with the given id
     */
    public HtmlElement findHtmlElementById(String elementId) {
        try {
            return htmlPage.getHtmlElementById(elementId);
        } catch (ElementNotFoundException e) {
            return null;
        }
    }

    /**
     * Returns all <code>&lt;frame/&gt;</code> tags as {@link HtmlFrame} instances.
     *
     * @return all <code>&lt;frame/&gt;</code> tags as {@link HtmlFrame} instances
     */
    public List<HtmlFrame> findAllFrameTags() {
        return allByTagName(HtmlFrame.TAG_NAME);
    }

    /**
     * Returns all <code>&lt;area/&gt;</code> tags as {@link HtmlArea} instances.
     *
     * @return all <code>&lt;area/&gt;</code> tags as {@link HtmlArea} instances
     */
    public List<HtmlArea> findAllAreaTags() {
        return allByTagName(HtmlArea.TAG_NAME);
    }

    /**
     * Returns all <code>&lt;applet/&gt;</code> tags as {@link HtmlApplet} instances.
     *
     * @return all <code>&lt;applet/&gt;</code> tags as {@link HtmlApplet} instances
     */
    public List<HtmlApplet> findAllAppletTags() {
        return allByTagName(HtmlApplet.TAG_NAME);
    }

    /**
     * Returns all <code>&lt;dl/&gt;</code> tags as {@link HtmlDefinitionList} instances.
     *
     * @return all <code>&lt;dl/&gt;</code> tags as {@link HtmlDefinitionList} instances
     */
    public List<HtmlDefinitionList> findAllDefinitionLists() {
        return allByTagName(HtmlDefinitionList.TAG_NAME);
    }

    /**
     * Returns all <code>&lt;object/&gt;</code> tags as {@link HtmlObject} instances.
     *
     * @return all <code>&lt;object/&gt;</code> tags as {@link HtmlObject} instances
     */
    public List<HtmlObject> findAllObjectTags() {
        return allByTagName(HtmlObject.TAG_NAME);
    }

    /**
     * Returns all <code>&lt;a/&gt;</code> tags as {@link HtmlAnchor} instances.
     *
     * @return all <code>&lt;a/&gt;</code> tags as {@link HtmlAnchor} instances
     */
    public List<HtmlAnchor> findAllAnchorTags() {
        return allByTagName(HtmlAnchor.TAG_NAME);
    }

    /**
     * Returns all <code>&lt;fieldset/&gt;</code> tags as {@link HtmlFieldSet} instances.
     *
     * @return all <code>&lt;fieldset/&gt;</code> tags as {@link HtmlFieldSet} instances
     */
    public List<HtmlFieldSet> findAllFieldsetTags() {
        return allByTagName(HtmlFieldSet.TAG_NAME);
    }

    /**
     * Return all <code>$lt;basefont/&gt;</code> tags as {@link HtmlBaseFont} instances.
     *
     * @return all <code>$lt;basefont/&gt;</code> tags as {@link HtmlBaseFont} instances
     */
    public List<HtmlBaseFont> findAllBasefontTags() {
        return allByTagName(HtmlBaseFont.TAG_NAME);
    }

    /**
     * Returns all <code>&lt;center/&gt;</code> tags as {@link HtmlCenter} instances.
     *
     * @return all <code>&lt;center/&gt;</code> tags as {@link HtmlCenter} instances
     */
    public List<HtmlCenter> findAllCenterTags() {
        return allByTagName(HtmlCenter.TAG_NAME);
    }

    /**
     * Returns all <code>&lt;title/&gt;</code> tags as {@link HtmlTitle} instances.
     *
     * @return all <code>&lt;title/&gt;</code> tags as {@link HtmlTitle} instances
     */
    public List<HtmlTitle> findAllTitleTags() {
        return allByTagName(HtmlTitle.TAG_NAME);
    }

    /**
     * Returns all elements with the given attribute name as {@link HtmlElement} instances.
     *
     * @param attributeName name of the attribute to filter by
     * @return all elements with the given attribute name as {@link HtmlElement} instances
     */
    @SuppressWarnings("unchecked")
    public List<HtmlElement> findAllElementsWithAttribute(String attributeName) {
        return (List<HtmlElement>) htmlPage.getByXPath("//*[@" + attributeName + "]");
    }

    /**
     * Returns the whole document enclosing <code>&lt;html/&gt;</code> tag.
     *
     * @return the whole document enclosing <code>&lt;html/&gt;</code> tag
     */
    public HtmlElement findHtmlTag() {
        return htmlPage.getDocumentElement();
    }

    /**
     * Returns the document type of the page.
     *
     * @return the document type of the page
     */
    public DocumentType getDoctype() {
        return htmlPage.getDoctype();
    }

    /**
     * Returns all <code>&lt;font/&gt;</code> tags as {@link HtmlFont} instances.
     *
     * @return all <code>&lt;font/&gt;</code> tags as {@link HtmlFont} instances
     */
    public List<HtmlFont> findAllFontTags() {
        return allByTagName(HtmlFont.TAG_NAME);
    }

    /**
     * Returns all <code>&lt;tt/&gt;</code> tags as {@link HtmlTeletype} instances.
     *
     * @return all <code>&lt;tt/&gt;</code> tags as {@link HtmlTeletype} instances
     */
    public List<HtmlTeletype> findAllTeletypeTags() {
        return allByTagName(HtmlTeletype.TAG_NAME);
    }

    /**
     * Returns all <code>&lt;u/&gt;</code> tags as {@link HtmlUnderlined} instances.
     *
     * @return all <code>&lt;u/&gt;</code> tags as {@link HtmlUnderlined} instances
     */
    public List<HtmlUnderlined> findAllUnderlineTags() {
        return allByTagName(HtmlUnderlined.TAG_NAME);
    }

}
