package de.codescape.bitvunit.ruleset;

import de.codescape.bitvunit.rule.Rule;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilderFactory;

public class XmlRuleSet extends BasicRuleSet implements RuleSet {

    private DocumentBuilderFactory documentFactory = DocumentBuilderFactory.newInstance();

    public XmlRuleSet(String location) throws XmlRuleSetException {
        super();
        addAllRulesFromDocument(createDocument(location));
    }

    private void addAllRulesFromDocument(Document document) {
        NodeList ruleNodes = document.getElementsByTagName("rule");
        for (int i = 0; i < ruleNodes.getLength(); i++) {
            addRuleFromNode(ruleNodes.item(i));
        }
    }

    private void addRuleFromNode(Node node) {
        String ruleClassName = node.getAttributes().getNamedItem("class").getTextContent();
        try {
            addRule((Rule) Class.forName(ruleClassName).newInstance());
        } catch (Exception e) {
            throw new XmlRuleSetException("Could not create Rule.", e);
        }
    }

    private Document createDocument(String location) {
        try {
            return documentFactory.newDocumentBuilder().parse(this.getClass().getResource(location).getFile());
        } catch (Exception e) {
            throw new XmlRuleSetException("Could not parse RuleSet from given location.", e);
        }
    }

}
