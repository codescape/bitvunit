package de.codescape.bitvunit.report;

/**
 * Singleton class that holds the configured {@link ReportWriter} to write out reports for any check that is executed.
 *
 * @author Stefan Glase
 */
public class ReportingContext {

    private static ReportingContext instance = new ReportingContext();

    private ReportWriter reportWriter;

    private ReportingContext() {
        super();
        reportWriter = new TextReportWriter();
    }

    private static ReportingContext getInstance() {
        return instance;
    }

    public static ReportWriter getReportWriter() {
        return getInstance().reportWriter;
    }

    public static void setReportWriter(ReportWriter reportWriter) {
        getInstance().reportWriter = reportWriter;
    }

}
