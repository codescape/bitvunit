package de.codescape.bitvunit.ruleset;

import de.codescape.bitvunit.rule.Rule;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilderFactory;

public class XmlRuleSet extends BasicRuleSet implements RuleSet {

    private static DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();

    public XmlRuleSet(String location) {
        super();
        addRulesFromDocument(buildDocument(location));
    }

    private Document buildDocument(String location) {
        try {
            return documentBuilderFactory.newDocumentBuilder().parse(this.getClass().getResource(location).getFile());
        } catch (Exception e) {
            throw new XmlRuleSetException("Could not parse RuleSet from given location.", e);
        }
    }

    private void addRulesFromDocument(Document document) {
        NodeList ruleNodes = document.getElementsByTagName("rule");
        for (int i = 0; i < ruleNodes.getLength(); i++) {
            addRule(extractClassAttributeFromNode(ruleNodes.item(i)));
        }
    }

    private String extractClassAttributeFromNode(Node node) {
        return node.getAttributes().getNamedItem("class").getTextContent();
    }

    private void addRule(String className) {
        try {
            addRule((Rule) Class.forName(className).newInstance());
        } catch (Exception e) {
            throw new XmlRuleSetException("Could not instantiate rule " + className + ".", e);
        }
    }

}
