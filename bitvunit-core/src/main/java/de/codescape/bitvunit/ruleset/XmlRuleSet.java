package de.codescape.bitvunit.ruleset;

import de.codescape.bitvunit.rule.Priority;
import de.codescape.bitvunit.rule.Rule;
import de.codescape.bitvunit.util.Assert;
import de.codescape.bitvunit.util.io.ClassPathResource;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;

/**
 * Implementation of a {@link RuleSet} that is backed by a XML document declaring the list of rules to be applied.
 *
 * @author Stefan Glase
 * @since 0.2
 */
public class XmlRuleSet extends BasicRuleSet implements RuleSet {

    private static final DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();

    /**
     * Constructs a new instance with the given location to the XML document.
     *
     * @param location location of the XML document
     */
    public XmlRuleSet(String location) {
        Assert.notNull("Location to the XML document is required.", location);
        addRulesFromDocument(buildDocument(location));
    }

    /**
     * Parses the file at the given location and creates a {@link Document} instance to read the rules from.
     *
     * @param location location of the XML file
     * @return {@link Document} instance representing the XML file
     */
    private Document buildDocument(String location) {
        try {
            return documentBuilderFactory.newDocumentBuilder().parse(ClassPathResource.asInputStream(location));
        } catch (ParserConfigurationException | SAXException | IOException | RuntimeException e) {
            throw new XmlRuleSetException("Could not parse RuleSet from given location '" + location + "'.", e);
        }
    }

    /**
     * Walks through the {@link Document} and tries to instantiate and add a {@link Rule} instance for every listed
     * rule to the rules contained in this {@link RuleSet}.
     *
     * @param document {@link Document} to read the rules from
     */
    private void addRulesFromDocument(Document document) {
        NodeList ruleNodes = document.getElementsByTagName("rule");
        for (int i = 0; i < ruleNodes.getLength(); i++) {
            addRule(extractClassAttributeFromNode(ruleNodes.item(i)), extractPriorityAttributeFromNode(ruleNodes.item(i)));
        }
    }

    /**
     * Gets the priority String from the {@link Node} representing a single rule element in the XML file.
     *
     * @param node {@link Node} that is can optionally contain an attribute <code>priority</code>
     * @return {@link Priority} value according to the value of the attribute; defaults to {@link Priority#NORMAL}
     */
    private Priority extractPriorityAttributeFromNode(Node node) {
        Node priority = node.getAttributes().getNamedItem("priority");
        return priority == null ? Priority.NORMAL : Priority.valueOf(priority.getTextContent().toUpperCase());
    }

    /**
     * Gets the full qualified name of the {@link Rule} class from the {@link Node} representing a single rule element
     * in the XML file.
     *
     * @param node {@link Node} that is expected to contain an attribute <code>class</code>
     * @return value of the <code>class</code> attribute of the given {@link Node}
     */
    private String extractClassAttributeFromNode(Node node) {
        return node.getAttributes().getNamedItem("class").getTextContent();
    }

    /**
     * Instantiates and adds an instance of a {@link Rule} with the given class name to the list of rules contained in
     * this {@link RuleSet} and configures the {@link Priority} accordingly.
     *
     * @param className class name of the {@link Rule} to be added
     * @param priority  priority of the {@link Rule} to be added
     */
    private void addRule(String className, Priority priority) {
        Rule rule;
        try {
            rule = (Rule) Class.forName(className).newInstance();
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException e) {
            throw new XmlRuleSetException("Could not instantiate rule " + className + ".", e);
        }
        rule.setPriority(priority);
        addRule(rule);
    }

}
