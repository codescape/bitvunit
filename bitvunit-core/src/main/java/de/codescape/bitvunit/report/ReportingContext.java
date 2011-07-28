package de.codescape.bitvunit.report;

/**
 * Singleton class holding the configured {@link ReportWriter} to write out reports for any accessibility check that is
 * executed by the framework.
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

    /**
     * Returns the {@link ReportWriter} to be used.
     *
     * @return {@link ReportWriter} to be used
     */
    public static ReportWriter getReportWriter() {
        return getInstance().reportWriter;
    }

    /**
     * Sets the {@link ReportWriter} to be used.
     *
     * @param reportWriter {@link ReportWriter} to be used
     */
    public static void setReportWriter(ReportWriter reportWriter) {
        getInstance().reportWriter = reportWriter;
    }

}
