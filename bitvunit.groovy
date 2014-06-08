#!/usr/bin/env groovy

import groovy.text.SimpleTemplateEngine

/**
 * Command line script that allows to create new rules from the scratch. Running the script without any parameters
 * results in a list of all currently supported commands available.
 *
 * @author Stefan Glase
 * @since 0.6
 */

def availableCommands = [
        'create-rule': 'Wizard that helps you to create new rules.',
]

if (args.size() != 1 || !(args[0] in availableCommands.keySet())) {
    println 'Unknown or missing command.\nUsage:'
    availableCommands.each { println "\t${it.key}\t${it.value}" }
    System.exit 1
}

if (args[0] == 'create-rule') {
    def author = getUserInput('Author name: ', { it })
    def name = getUserInput('Rule name [must end with Rule]: ', { it && it ==~ '^[A-Z]([A-Za-z])+Rule$' })
    def category = getUserInput("Rule category ${getAvailableCategories()}: ", { it in availableCategories })

    def rule = ['author': author, 'name': name, 'category': category, 'version': nextBitvUnitVersion]

    def ruleFile = createRule(rule)
    println "Created rule at ${ruleFile}"

    def testFile = createTest(rule)
    println "Created test at ${testFile}"

    addToRuleSet(rule)
    println "Added new rule to ruleset all-rules.xml"

    println 'Done. Happy Coding!'
}

def addToRuleSet(rule) {
    def ruleSetFile = new File('./bitvunit-core/src/main/resources/rulesets/all-rules.xml')
    def content = ruleSetFile.text
    def categoryString = "<!-- ${rule.category.capitalize()} -->"
    def ruleNode = "<rule class=\"de.codescape.bitvunit.rule.${rule.category}.${rule.name}\"/>"
    ruleSetFile.text = content.replace(categoryString, "${categoryString}\n    ${ruleNode}")
}

def createRule(rule) {
    def target = "./bitvunit-core/src/main/java/de/codescape/bitvunit/rule/${rule.category}/${rule.name}.java"
    fileFromTemplate('./bitvunit-core/templates/Rule.template', rule, target)
    target
}

def createTest(rule) {
    def target = "./bitvunit-core/src/test/java/de/codescape/bitvunit/rule/${rule.category}/${rule.name}Test.java"
    fileFromTemplate('./bitvunit-core/templates/Test.template', rule, target)
    target
}

def getUserInput(text, validation = { true }) {
    print text
    def value = ''
    while (!value || !validation(value)) {
        value = new Scanner(System.in).nextLine()
        if (!validation(value)) {
            print "Value '${value}' is not a valid input: "
        }
    }
    value
}

def getAvailableCategories() {
    def categories = []
    new File('./bitvunit-core/src/main/java/de/codescape/bitvunit/rule/').eachDir { dir ->
        categories << dir.name
    }
    categories
}

def getNextBitvUnitVersion() {
    String version = new XmlSlurper().parse(new File('./pom.xml')).version.text()
    version.substring(0, version.lastIndexOf('-'))
}

def fileFromTemplate(template, data, filename) {
    def file = new File(filename)
    file.createNewFile()
    file.text = new SimpleTemplateEngine().createTemplate(new File(template).text).make(data).toString()
    file
}
