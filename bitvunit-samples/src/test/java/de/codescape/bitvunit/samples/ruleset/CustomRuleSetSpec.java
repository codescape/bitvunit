package de.codescape.bitvunit.samples.ruleset;

import de.codescape.bitvunit.rule.forms.*;
import de.codescape.bitvunit.ruleset.BasicRuleSet;
import de.codescape.bitvunit.ruleset.RuleSet;
import de.codescape.bitvunit.ruleset.XmlRuleSet;
import de.codescape.bitvunit.samples.AbstractBaseSpec;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;

import static de.codescape.bitvunit.BitvUnit.compliantTo;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

/**
 * Test case demonstrating the possibility of creating custom {@link de.codescape.bitvunit.ruleset.RuleSet} instances.
 *
 * @author Stefan Glase
 * @since 0.12
 */
public class CustomRuleSetSpec extends AbstractBaseSpec {

    private WebDriver driver;

    @Before
    public void before() {
        driver = new HtmlUnitDriver();
        driver.navigate().to(urlForPage("index.html"));
    }

    @Test
    public void shouldVerifyAgainstCustomRuleSetCreatedByCode() {
        assertThat(driver, is(compliantTo(ruleSetBasedOnCode())));
    }

    private BasicRuleSet ruleSetBasedOnCode() {
        return new BasicRuleSet(
                new LabelContainsTextRule(),
                new LabelForInputFieldRule(),
                new LabelForSelectTagRule(),
                new LabelForTextareaRule(),
                new LabelWithoutFormElementRule(),
                new UniqueLabelForFormElementRule());
    }

    @Test
    public void shouldVerifyAgainstCustomRuleSetCreatedByXml() {
        assertThat(driver, is(compliantTo(ruleSetBasedOnXml())));
    }

    private RuleSet ruleSetBasedOnXml() {
        return new XmlRuleSet("rulesets/label-rules.xml");
    }

}
