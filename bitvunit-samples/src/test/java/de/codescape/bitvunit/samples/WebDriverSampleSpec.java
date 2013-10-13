package de.codescape.bitvunit.samples;

import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;

import static de.codescape.bitvunit.hamcrest.ComplianceMatcher.compliantTo;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * This test demonstrates testing for accessibility with Selenium WebDriver.
 *
 * @author Stefan Glase
 * @since 0.7
 */
public class WebDriverSampleSpec extends AbstractBaseSpec {

    @Test
    public void testingAccessibilityWithSeleniumWebDriver() throws Exception {
        // create a Selenium WebDriver instance
        WebDriver driver = new HtmlUnitDriver();

        // navigate to a page or interact with a page
        driver.navigate().to(urlForPage("index.html"));

        // assert accessibility
        assertThat(driver, is(compliantTo(ALL_RULES)));
    }

}
