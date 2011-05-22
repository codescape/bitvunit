package de.codescape.bitvunit.rule.images;

import de.codescape.bitvunit.rule.Violation;
import org.junit.Test;

import java.util.List;

import static de.codescape.bitvunit.test.HtmlPageCreator.create;
import static org.junit.Assert.assertEquals;

public class AlternativeTextForImagesRuleTest {

    private AlternativeTextForImagesRule rule = new AlternativeTextForImagesRule();

    @Test
    public void imageWithMissingAlternativeText() throws Exception {
        String content = "<html><body><img src=\"myImage.gif\" /></body></html>";

        List<Violation> violations = rule.applyTo(create(content));

        assertEquals(1, violations.size());
    }

    @Test
    public void imageWithAlternativeText() throws Exception {
        String content = "<html><body><img src=\"myImage.gif\" alt=\"Alternative Text\" /></body></html>";

        List<Violation> violations = rule.applyTo(create(content));

        assertEquals(0, violations.size());
    }

}
