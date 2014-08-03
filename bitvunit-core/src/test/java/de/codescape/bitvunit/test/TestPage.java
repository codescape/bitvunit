package de.codescape.bitvunit.test;

import com.gargoylesoftware.htmlunit.html.HtmlPage;
import org.apache.commons.io.IOUtils;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;

import java.io.*;
import java.net.URL;

import static de.codescape.bitvunit.util.html.HtmlPageUtil.toHtmlPage;

/**
 * Java abstraction over a given sample page that can be accessed through multiple different Java types.
 */
public class TestPage {

    /* path to the file that is used in this test */
    private static final String FILE_NAME = "sample-page.html";

    /**
     * Returns the given sample page as String
     *
     * @return given sample page as String
     */
    public static String asString() {
        try {
            return IOUtils.toString(asURL());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Returns the given sample page as HtmlPage
     *
     * @return given sample page as HtmlPage
     */
    public static HtmlPage asHtmlPage() {
        return toHtmlPage(asString());
    }

    /**
     * Returns the given sample page as Reader
     *
     * @return given sample page as Reader
     */
    public static Reader asReader() {
        return new StringReader(asString());
    }

    /**
     * Returns the given sample page as InputStream
     *
     * @return given sample page as InputStream
     */
    public static InputStream asInputStream() {
        return IOUtils.toInputStream(asString());
    }

    /**
     * Returns the given sample page as URL
     *
     * @return given sample page as URL
     */
    public static URL asURL() {
        return TestPage.class.getClassLoader().getResource(FILE_NAME);
    }

    /**
     * Returns the given sample page as WebDriver
     *
     * @return given sample page as WebDriver
     */
    public static WebDriver asWebDriver() {
        WebDriver webDriver = new HtmlUnitDriver();
        webDriver.navigate().to(asURL());
        return webDriver;
    }

}
