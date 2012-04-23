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
    'update-ruledocs': 'Target that generates rule documentation from javadoc.',
    'update-apidocs': 'Generates the html based javadoc for bitvunit-core.',
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
    fileFromTemplate('./bitvunit-templates/Rule.template', rule, target)
    target
}

def createTest(rule) {
    def target = "./bitvunit-core/src/test/java/de/codescape/bitvunit/rule/${rule.category}/${rule.name}Test.java"
    fileFromTemplate('./bitvunit-templates/Test.template', rule, target)
    target
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

/* update-ruledocs */

if (args[0] == 'update-ruledocs') {
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
    def rulesIndex = fileFromTemplate("./bitvunit-templates/docs/RulesIndex.template", [:], "${target}/index.textile")

    new File('./bitvunit-core/src/main/java/de/codescape/bitvunit/rule/').eachDir { dir ->
        println "Processing category ${dir.name}"

        // create category index file
        def categoryIndex = fileFromTemplate("./bitvunit-templates/docs/CategoryIndex.template", [name: dir.name], "${target}/${dir.name}.textile")

        def rulesInCategory = 0
        dir.eachFileMatch(~/^[A-Z]([A-Za-z])+Rule.java$/) { file ->
            println "Processing file ${file.name}"
            def rule = extractRuleData(file)
            categoryIndex.text = categoryIndex.text + "\n" + fromTemplate("./bitvunit-templates/docs/RuleDoc.template", rule)
            rulesInCategory++
        }

        rulesIndex.text += """\n* "${dir.name.capitalize()}":/rules/${dir.name} (${rulesInCategory} ${rulesInCategory > 1 ? 'rules' : 'rule'})"""
    }

    target
}

def fromTemplate(template, data = [:]) {
    new groovy.text.SimpleTemplateEngine().createTemplate(new File(template).text).make(data).toString()
}

def fileFromTemplate(template, data, filename) {
    def file = new File(filename)
    file.createNewFile()
    file.text = fromTemplate(template, data)
    file
}

def extractRuleData(file) {
    def name = extractRuleName(file)
    def author = extractRuleAuthor(file)
    def version = extractRuleVersion(file)
    def description = extractRuleDescription(file)
    [name: name, author: author, version: version, description: description]
}

def extractRuleName(file) {
    (file.name - '.java').trim()
}

def extractRuleDescription(file) {
    def description = ''
    file.eachLine { line ->
        if (line.startsWith(' *') && !(line.startsWith(' * @') || line.startsWith(' */'))) {
            description += line.replaceAll('&lt;', '<').replaceAll('&gt;','>') - ' *' + '\n'
        }
    }
    description?.trim() ?: 'missing'
}

def extractRuleVersion(file) {
    def version
    file.eachLine { line ->
        if (line.startsWith(' * @since')) {
            version = line - ' * @since'
        }
    }
    version?.trim() ?: 'unknown'
}

def extractRuleAuthor(file) {
    def author
    file.eachLine { line ->
        if (line.startsWith(' * @author')) {
            author = line - ' * @author'
        }
    }
    author?.trim() ?: 'unknown'
}

/* update-apidocs */

if (args[0] == 'update-apidocs') {
    print 'Path to gh-pages: '
    def path = getUserInput({ new File(it).exists() && it.endsWith('gh-pages') })

    def source = './bitvunit-core/target/apidocs'
    def target = "${path}/apidocs"

    println 'Generating apidocs...'
    def goal = 'mvn -f ./bitvunit-core/pom.xml clean package javadoc:javadoc'.execute()
    println goal.text

    def ant = new AntBuilder()

    println "Deleting old apidocs at ${target}..."
    ant.delete(dir: "${target}", verbose: false)

    println "Moving apidocs to ${target}..."
    ant.copy(todir: target) {
        fileset(dir: source)
    }

    println 'Done.'
}
