package de.codescape.bitvunit.ruleset;

import de.codescape.bitvunit.util.io.ClassPathResource;
import org.custommonkey.xmlunit.jaxp13.Validator;
import org.junit.Test;
import org.xml.sax.SAXException;

import javax.xml.transform.stream.StreamSource;

import static org.junit.Assert.assertTrue;

public class XmlRuleSetValidationTest {

    @Test
    public void schemaRuleSetSchemaIsValid() throws Exception {
        assertValidSchema("ruleset-schema.xsd");
    }

    @Test
    public void documentAllRulesIsValid() throws Exception {
        assertValidDocument("/rulesets/all-rules.xml", "ruleset-schema.xsd");
    }

    private void assertValidSchema(String schemaDocument) {
        Validator validator = new Validator();
        validator.addSchemaSource(new StreamSource(ClassPathResource.asInputStream(schemaDocument)));
        assertTrue(validator.isSchemaValid());
    }

    /**
     * Assertion that is valid if the given XML document can be validated against the given XSD document.
     *
     * @param xmlDocument    the XML document to be validated
     * @param schemaDocument the XSD document used for validation
     * @throws SAXException
     */
    private void assertValidDocument(String xmlDocument, String schemaDocument) throws SAXException {
        Validator validator = new Validator();
        validator.addSchemaSource(new StreamSource(ClassPathResource.asInputStream(schemaDocument)));
        StreamSource xmlInstance = new StreamSource(ClassPathResource.asInputStream(xmlDocument));
        assertTrue(validator.isInstanceValid(xmlInstance));
    }

}
