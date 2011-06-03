package de.codescape.bitvunit.test.integration;

import com.gargoylesoftware.htmlunit.WebClient;
import org.junit.Before;

public abstract class AbstractIntegrationTest {

    protected static final String BITVUNIT_DOCUMENTATION_URL = "http://bitvunit.codescape.de";

    protected WebClient webClient;

    @Before
    public void setUp() throws Exception {
        webClient = new WebClient();
    }

}
