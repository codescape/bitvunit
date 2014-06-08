package de.codescape.bitvunit.samples;

import org.junit.Test;

import java.net.URL;

import static de.codescape.bitvunit.hamcrest.ComplianceMatcher.compliantTo;
import static de.codescape.bitvunit.ruleset.AllRules.allRules;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * This test demonstrates testing for accessibility with simple URLs.
 *
 * @author Stefan Glase
 * @since 0.7
 */
public class URLSampleSpec extends AbstractBaseSpec {

    @Test
    public void testingAccessibilityWithSimpleURL() throws Exception {
        // create a URL
        URL url = new URL(urlForPage("index.html"));

        // assert accessibility
        assertThat(url, is(compliantTo(allRules())));
    }

}
