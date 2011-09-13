#!/usr/bin/env groovy
/**
 * Command line script that helps you to create new rules from the scratch. Running the script without any parameters
 * results in a list of all currently supported commands that can be used.
 *
 * @author Stefan Glase
 * @since 0.6
 */
def availableCommands = [
    'create-rule': 'Wizard that helps you to create new rules.',
    'generate-docs': 'Target that generates rule documentation from javadoc.',
]

if (args.size() != 1 || !(args[0] in availableCommands.keySet())) {
    println 'Unknown or missing command.\nUsage:'
    availableCommands.each { println "\t${it.key}\t${it.value}" }
    System.exit 1
}

/* create-rule */

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
    def file = new File(target as String)
    file.createNewFile()

    def template = new File("./bitvunit-templates/$templateName").text
    def result = new groovy.text.SimpleTemplateEngine().createTemplate(template).make(rule)

    file.text = result.toString()
}

def getUserInput(validation = { true }) {
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
    new XmlSlurper().parse(new File('./pom.xml')).version.text().substring(0, 3)
}

/* generate-docs */

if (args[0] == 'generate-docs') {
    print 'Path to gh-pages: '
    def path = getUserInput({ new File(it).exists() && it.endsWith('gh-pages') })

    def docsFile = createDocs(path)
    println "Generated docs at ${docsFile}"
}

def createDocs(path) {
    def target = "${path}/rules"

    // create rules directory
    def docsDir = new File("${target}/")
    docsDir.deleteDir()
    docsDir.mkdir()

    // create rules index file
    def docsIndex = createDocsIndex(target)

    new File('./bitvunit-core/src/main/java/de/codescape/bitvunit/rule/').eachDir { dir ->
        println "Processing category ${dir.name}"
        docsIndex.text = docsIndex.text + """\n* "${dir.name.capitalize()}":/rules/${dir.name}"""

        // create category directory
        def categoryDir = new File("${target}/${dir.name}")
        categoryDir.mkdir()

        // create category index file
        def categoryIndex = createCategoryIndex(target, dir.name)
        
        dir.eachFileMatch(~/^[A-Z]([A-Za-z])+Rule.java$/) { file ->
            println "Processing file ${file.name}"
            def rule = extractRuleData(file)
            def template = new groovy.text.SimpleTemplateEngine().createTemplate(new File("./bitvunit-templates/RuleDoc.template").text)
            categoryIndex.text = categoryIndex.text + "\n" + template.make(rule).toString()
        }
    }

    target
}

def createCategoryIndex(target, category) {
    def file = new File("${target}/${category}/index.textile")
    file.createNewFile()
    file.text = "---\nlayout: default\ntitle: ${category.capitalize()}\n---\nThis is a list of all rules in the category ${category}.\n"
    file
}

def createDocsIndex(target) {
    def file = new File("${target}/index.textile")
    file.createNewFile()
    file.text = """---\nlayout: default\ntitle: Rules\n---\nThis is a list of all rule categories.\n"""
    file
}

def extractRuleData(file) {
    // grab the author from the javadoc
    def author
    file.eachLine { line ->
        if (line.startsWith(' * @author')) {
            author = line - ' * @author'
        }
    }

    // grab the version from the javadoc
    def version
    file.eachLine { line ->
        if (line.startsWith(' * @since')) {
            version = line - ' * @since'
        }
    }

    // grab the description from the javadoc
    def description = ''
    file.eachLine { line ->
        if (line.startsWith(' *') && !(line.contains('@since') || line.contains('@author') || line.startsWith(' */'))) {
            description += line.replaceAll('&lt;', '<').replaceAll('&gt;','>') - ' *'
        }
    }

    [name: file.name - '.java', author: author, version: version, description: description]
}
