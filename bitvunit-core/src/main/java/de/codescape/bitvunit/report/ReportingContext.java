package de.codescape.bitvunit.report;

public class ReportingContext {

    private static ReportingContext instance = new ReportingContext();

    private ReportWriter reportWriter;

    private ReportingContext() {
        super();
        reportWriter = new ConsoleReportWriter();
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
