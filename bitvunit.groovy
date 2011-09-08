/**
 * Script that helps you to create new rules from the scratch. Run the script without any
 * parameters to see the list of supported commands.
 *
 * @author Stefan Glase
 * @since 0.6
 */
def availableCommands = ['create-rule': 'Wizard that helps you to create new rules.']

if (args.size() != 1 || !(args[0] in availableCommands.keySet())) {
    println 'Unknown or missing command.\nUsage:'
    availableCommands.each { println "\t${it.key}\t${it.value}" }
    System.exit 1
}

if (args[0] == 'create-rule') {
    print 'Author name: '
    def author = getUserInput({ it })

    print 'Rule name: '
    def name = getUserInput({ it && it ==~ '^[A-Z]([A-Za-z])+Rule$' })

    print "Rule category ${getAvailableCategories()}: "
    def category = getUserInput({ it in getAvailableCategories()})

    def rule = ['author': author, 'name': name, 'category': category, 'version': getNextBitvUnitVersion()]

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
    createFromTemplate(rule, target, 'Rule.template')
    target
}

def createTest(rule) {
    def target = "./bitvunit-core/src/test/java/de/codescape/bitvunit/rule/${rule.category}/${rule.name}Test.java"
    createFromTemplate(rule, target, 'Test.template')
    target
}

def createFromTemplate(rule, target, templateName) {
    def file = new File(target)
    file.createNewFile()

    def template = new File("./bitvunit-templates/$templateName").text
    def result = new groovy.text.SimpleTemplateEngine().createTemplate(template).make(rule)

    file.text = result.toString()
}

def getUserInput(validation = { true }) {
    def value
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
    new File('./bitvunit-core/src/main/java/de/codescape/bitvunit/rule/').eachDir() { dir->
        categories << dir.name
    }
    categories
}

def getNextBitvUnitVersion() {
    new XmlSlurper().parse(new File('./pom.xml')).version.text().substring(0,3)
}
