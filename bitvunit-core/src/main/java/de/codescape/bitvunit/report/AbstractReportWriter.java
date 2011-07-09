package de.codescape.bitvunit.report;

import java.text.DateFormat;
import java.util.Date;

public abstract class AbstractReportWriter implements ReportWriter {

    protected String getFormattedDate() {
        return DateFormat.getDateTimeInstance().format(new Date());
    }

}
