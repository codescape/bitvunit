package de.codescape.bitvunit.report;

import de.codescape.bitvunit.util.Assert;

/**
 * Singleton class holding the configured {@link ReportWriter} to write out reports for any accessibility check that is
 * executed by the framework.
 *
 * @author Stefan Glase
 */
public class ReportingContext {

    private static final ReportingContext instance = new ReportingContext();

    private ReportWriter reportWriter;

    /**
     * Constructs a new {@link ReportingContext} and assigns the default {@link ReportWriter}.
     */
    private ReportingContext() {
        super();
        reportWriter = new TextReportWriter();
    }

    /**
     * Returns the singleton instance of the {@link ReportingContext}.
     *
     * @return singleton instance of the {@link ReportingContext}
     */
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
        Assert.notNull(reportWriter);
        getInstance().reportWriter = reportWriter;
    }

}
