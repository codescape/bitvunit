package de.codescape.bitvunit.rule;

import com.gargoylesoftware.htmlunit.html.HtmlElement;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class ViolationTest {

    @Test
    public void violationKnowsTheRule() throws Exception {
        Rule expectedRule = someRule();
        assertEquals(expectedRule, new Violation(expectedRule, someElement(), "").getRule());
    }

    @Test
    public void violationKnowsThePosition() throws Exception {
        Integer lineNumber = 4711;
        Integer columnNumber = 815;
        String expectedPosition = lineNumber + ":" + columnNumber;
        assertEquals(expectedPosition, new Violation(someRule(), someElement(lineNumber, columnNumber), "").getPosition());
    }

    @Test
    public void violationKnowsTheMessage() throws Exception {
        String expectedMessage = "Some wise words.";
        assertEquals(expectedMessage, new Violation(someRule(), someElement(), expectedMessage).getMessage());
    }

    private Rule someRule() {
        return mock(Rule.class);
    }

    private HtmlElement someElement(int lineNumber, int columnNumber) {
        HtmlElement element = someElement();
        when(element.getStartLineNumber()).thenReturn(lineNumber);
        when(element.getStartColumnNumber()).thenReturn(columnNumber);
        return element;
    }

    private HtmlElement someElement() {
        return mock(HtmlElement.class);
    }

}
