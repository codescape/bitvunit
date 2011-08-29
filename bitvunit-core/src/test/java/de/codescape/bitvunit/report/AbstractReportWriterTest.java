package de.codescape.bitvunit.report;

import com.gargoylesoftware.htmlunit.html.HtmlPage;
import de.codescape.bitvunit.rule.Rule;
import de.codescape.bitvunit.rule.Violation;
import de.codescape.bitvunit.rule.Violations;
import de.codescape.bitvunit.ruleset.RuleSet;
import de.codescape.bitvunit.ruleset.XmlRuleSet;
import org.apache.commons.io.IOUtils;
import org.junit.After;
import org.junit.Before;

import java.io.ByteArrayOutputStream;
import java.io.FileReader;
import java.io.OutputStream;
import java.io.PrintStream;

import static de.codescape.bitvunit.util.html.HtmlPageUtil.toHtmlPage;

/**
 * Abstract test class for testing {@link ReportWriter} implementations capturing the console and file output.
 *
 * @author Stefan Glase
 * @since 0.5
 */
public abstract class AbstractReportWriterTest {

    private OutputStream consoleOutput;
    private PrintStream originalConsole;

    @Before
    public void beforeTestsRedirectConsole() throws Exception {
        originalConsole = System.out;
        consoleOutput = new ByteArrayOutputStream();
        System.setOut(new PrintStream(consoleOutput));
    }

    @After
    public void afterTestsUndoConsoleRedirect() throws Exception {
        System.setOut(originalConsole);
    }

    protected String getConsoleOutput() {
        return consoleOutput.toString();
    }

    protected String getFileOutput(String filename) throws Exception {
        return IOUtils.toString(new FileReader(filename));
    }

    protected Violations someViolations() {
        Violations violations = new Violations();
        for (Rule rule : someRuleSet().getRules()) {
            violations.add(new Violation(rule, someHtmlPage().getBody(), "No empty body allowed!"));
        }
        return violations;
    }

    protected RuleSet someRuleSet() {
        return new XmlRuleSet("/rulesets/all-rules.xml");
    }

    protected HtmlPage someHtmlPage() {
        return toHtmlPage("<html><head><title>Hello World</title></head><body></body></html>");
    }

}
