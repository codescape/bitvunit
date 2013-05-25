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
        String expectedMessage = someMessage();
        assertEquals(expectedMessage, new Violation(someRule(), someElement(), expectedMessage).getMessage());
    }

    @Test(expected = IllegalArgumentException.class)
    public void creatingViolationShouldFailWithoutRuleReference() {
        new Violation(null, someElement(), someMessage());
    }

    @Test(expected = IllegalArgumentException.class)
    public void creatingViolationShouldFailWithoutElementReference() {
        new Violation(someRule(), null, someMessage());
    }

    @Test(expected = IllegalArgumentException.class)
    public void creatingViolationShouldFailWithoutMessage() {
        new Violation(someRule(), someElement(), null);
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

    private String someMessage() {
        return "Some wise words.";
    }

}
