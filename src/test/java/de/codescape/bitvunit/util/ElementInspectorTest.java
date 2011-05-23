package de.codescape.bitvunit.util;

import com.gargoylesoftware.htmlunit.html.HtmlElement;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class ElementInspectorTest {

    private HtmlElement htmlElement;

    @Before
    public void setUp() throws Exception {
        htmlElement = mock(HtmlElement.class);
    }

    @Test
    public void htmlElementWithNoId() throws Exception {
        when(htmlElement.getId()).thenReturn(null);
        assertFalse(ElementInspector.elementHasValidId(htmlElement));
    }

    @Test
    public void htmlElementWithEmptyId() throws Exception {
        when(htmlElement.getId()).thenReturn("");
        assertFalse(ElementInspector.elementHasValidId(htmlElement));
    }

    @Test
    public void htmlElementWithId() throws Exception {
        when(htmlElement.getId()).thenReturn("lastname");
        assertTrue(ElementInspector.elementHasValidId(htmlElement));
    }

}
