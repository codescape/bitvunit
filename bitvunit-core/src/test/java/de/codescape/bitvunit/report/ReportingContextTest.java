package de.codescape.bitvunit.report;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class ReportingContextTest {

    private ReportWriter reportWriter;

    @Before
    public void setUp() throws Exception {
        reportWriter = ReportingContext.getReportWriter();
    }

    @After
    public void tearDown() throws Exception {
        ReportingContext.setReportWriter(reportWriter);
    }

    @Test
    public void currentReportWriterCanBeChanged() throws Exception {
        ReportWriter newReportWriter = new XmlReportWriter();
        ReportingContext.setReportWriter(newReportWriter);
        assertEquals(newReportWriter, ReportingContext.getReportWriter());
    }

    @Test(expected = IllegalArgumentException.class)
    public void setReportWriterDoesNotAcceptNull() throws Exception {
        ReportingContext.setReportWriter(null);
    }

}
