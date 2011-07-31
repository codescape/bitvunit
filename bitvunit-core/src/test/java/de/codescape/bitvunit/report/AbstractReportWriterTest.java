package de.codescape.bitvunit.report;

import com.gargoylesoftware.htmlunit.html.HtmlPage;
import de.codescape.bitvunit.rule.Violations;
import de.codescape.bitvunit.ruleset.RuleSet;
import org.junit.Test;

import java.io.Writer;

import static org.junit.Assert.assertNotNull;

public class AbstractReportWriterTest {

    @Test
    public void shouldBeAbleToGetBitvUnitVersionInUse() throws Exception {
        assertNotNull(new ReportWriterUnderTest().getBitvUnitVersion());
    }

    private class ReportWriterUnderTest extends AbstractReportWriter {

        @Override
        protected String getOutputFilename() {
            throw new UnsupportedOperationException("Should not be called in tests.");
        }

        @Override
        protected void writeReport(Writer writer, HtmlPage htmlPage, RuleSet ruleSet, Violations violations) {
            throw new UnsupportedOperationException("Should not be called in tests.");
        }

    }

}
