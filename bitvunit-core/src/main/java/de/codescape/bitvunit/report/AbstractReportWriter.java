package de.codescape.bitvunit.report;

import java.text.DateFormat;
import java.util.Date;

/**
 * Abstract class that should be extended by all {@link ReportWriter} implementations.
 *
 * @author Stefan Glase
 */
public abstract class AbstractReportWriter implements ReportWriter {

    protected String getFormattedDate() {
        return DateFormat.getDateTimeInstance().format(new Date());
    }

}
