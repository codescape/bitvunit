Changelog for BitvUnit
----------------------

### Release 0.13 (????-??-??)

* internal structure
    * new interface Testable as common basis for Rule and RuleSet that both can be verified against
    * simplifying ComplianceMatcher to make use of the new interface Testable
* external api
    * new class BitvUnit with all commonly used methods to interact with the library
* updated plugins
    * all plugins use the latest available version
* updates dependencies
    * org.seleniumhq.selenium:selenium-htmlunit-driver:2.44.0
    * org.seleniumhq.selenium:selenium-api:2.44.0
    * junit:junit:4.12
    * org.mockito:mockito:1.10.8
* rules
    * added new rule AvoidUnderlineTagRule

### Release 0.12 (2014-08-02)

* added AllRules class and allRules factory to ease testing against all configured rules
* remove project bitvunit-cli to ease development
* support fluent way to add rules to a BasicRuleSet with the new method `withRule(Rule rule)`
* updated dependencies
    * org.seleniumhq.selenium:selenium-api:2.42.2
    * org.seleniumhq.selenium:selenium-htmlunit-driver:2.42.2
* rules
    * removed rule DoctypeForPageRule due to incompatibility with webdriver
    * split rule TitleForPageRule into TitleContainsTextRule and SingleTitlePerPageRule

### Release 0.11 (2014-06-08)

* updated dependencies
    * net.sourceforge.htmlunit:htmlunit:2.15
    * org.apache.httpcomponents:httpclient:4.3.4
    * org.codehaus.groovy:groovy-all:2.3.2
    * org.seleniumhq.selenium:selenium-api:2.42.0
    * org.seleniumhq.selenium:selenium-htmlunit-driver:2.42.0

### Release 0.10 (2014-06-08)

* rules
    * added new rule DoctypeForPageRule
    * added new rule AvoidBasefontTagRule
    * added new rule AvoidCenterTagRule
    * added new rule AvoidFontTagRule
    * added new rule AvoidFrameTagRule
    * added new rule AvoidTeletypeTagRule
    * improve rule LabelForInputFieldRule to validate new HTML 5 input types
* infrastructure
    * continuous integration against Oracle JDK 8 and Oracle JDK 7
    * faster build not generating javadoc on every build but only for releases
    * building against Java 7 instead of Java 6 language level
    * use Java 7 features where appropriate
    * fixed release specific configuration for maven-release-plugin
* documentation
    * javadoc is fixed so that it can be generated with latest maven-javadoc-plugin
* updated dependencies
    * now using org.seleniumhq:selenium-api:2.41.0
    * now using org.seleniumhq:selenium-htmlunit-driver:2.41.0
    * now using net.sourceforge.htmlunit:htmlunit:2.14
    * now using org.apache.httpcomponents:httpclient:4.3.3

### Release 0.9 (2013-11-23)

* Hamcrest Matcher returns violations in case of errors
* documentation
    * improved readme for the starting page on GitHub
    * downsizing website for easier maintainability
* infrastructure
    * added continuous integration against Oracle JDK 7 and OpenJDK 6 and 7
    * added verification of source code with PMD
    * updated all plugin versions
* updated dependencies
    * now using org.seleniumhq.selenium:selenium-api:2.35.0
    * now using org.seleniumhq.selenium:selenium-htmlunit-driver:2.35.0
    * now using xmlunit:xmlunit:1.5

### Release 0.8 (2013-05-25)

* added new rules
    * AvoidAppletTagRule
    * AvoidBorderAttributeForImageRule
    * SelectFieldWithValidOptionRule
* improved robustness of library
    * fixing a bug that causes a NPE when rules raise a violation without an element
    * added tests for correct behaviour
* new infrastructure
    * now using travis ci instead of jenkins for continuous integration
* updated dependencies
    * now using commons-io:commons-io:2.4
    * now using org.seleniumhq.selenium:selenium-api:2.33.0
    * now using org.seleniumhq.selenium:selenium-htmlunit-driver:2.33.0
    * now using org.hamcrest:hamcrest-core:1.3
    * now using net.sourceforge.htmlunit:htmlunit:2.12
    * now using xmlunit:xmlunit:1.4
* improved templates with better implementation instructions

### Release 0.7 (2012-03-17)

* added support for Selenium WebDriver
* added priority concept to rules
    * rules have a default priority <code>NORMAL</code>
    * rules can be promoted to priority <code>HIGH</code> or demoted to priority <code>LOW</code> individually
* improved XML based configuration
    * the priority of every rule listed in a XML based ruleset can be configured
* improved reporting functionality
    * <code>TextReportWriter</code> and <code>XmlReportWriter</code> list the priority for every rule
* increased test coverage
    * testing validity of XSD schema document <code>ruleset-schema.xsd</code>
    * testing validity of XML document <code>/rulesets/all-rules.xml</code>
* improvements to documentation
    * added documentation to XSD schema document <code>ruleset-schema.xsd</code>
    * better apidocs provided by doclava doclet for JavaDoc
* improvements to bitvunit-demo project
    * show usage in combination with WebDriver
    * show usage in combination with HtmlUnit
    * show usage in combination with simple URLs
* updated dependencies
    * now using org.mockito:mockito-all:1.9.0

### Release 0.6 (2011-10-27)

* added new rules
    * AlternativeTextForImageButtonRule
    * AlternativeTextForInlineFrameRule
    * AlternativeTextForLinkedImageRule
    * AvoidAbstractRoleRule
    * HeadingContainsTextRule
    * TitleForPageRule
* renamed HeadersInCorrectOrderRule to HeadingsInCorrectOrderRule
* Groovy console script
    * added a goal to set everything up for the implementation of new rules
    * added a goal to generate rule documentation from javadoc
    * added a goal to generate and publish apidocs on the website
* improvements to documentation website
    * the website now comes in a much cleaner layout
    * all rules are listed and documented on the website 
    * apidocs are now available from the usage page
* updated dependencies
    * now using commons-io:commons-io:2.1
    * now using junit:junit:4.10

### Release 0.5 (2011-09-03)

* added new rules
    * AnchorsWithoutTargetAttributeRule
    * FieldsetContainsLegendRule
* improved reporting functionality
    * added functionality to write report results to files and to console
    * renamed ConsoleReportWriter to TextReportWriter to reflect that it can also be used to write to files
    * added functionality to print out the library version in reports to AbstractReportWriter
    * added XmlReportWriter to write XML based reports to console and files
* fixed maven problems
    * due to wrong configuration for resource filtering all-rules.xml ruleset was not provided in the jar
    * dependency versions are now defined only in one place (in the bitvunit-project POM)
* added sample project bitvunit-demo to showcase the usage of the library
* improved JavaDoc
    * added package documentation to all packages
    * added documentation of ReportingContext
* updated dependencies
    * now using net.sourceforge.htmlunit:htmlunit:2.9
    * now using junit:junit:4.9

### Release 0.4 (2011-07-23)

* added new rules
    * AlternativeTextForObjectRule
    * AvoidServerSideImageMapRule
* added HtmlPageUtil to create HtmlPage objects from String, Reader, InputStream and URL instances
* updated Hamcrest functionality
    * replaced RuleMatcher and RuleSetMatcher by ComplianceMatcher
        * added capability to test <code>java.io.InputStream</code> representations of HTML pages
        * added capability to test <code>java.io.Reader</code> representations of HTML pages
        * added capability to test <code>java.lang.String</code> representations of HTML pages
        * added capability to test <code>java.net.URL</code> representations of HTML pages
* added reporting functionality
    * added ConsoleReportWriter as first implementation of ReportWriter to write to system console
* updated usage documentation
* added and improved JavaDoc
* increased test coverage for internal functionality

### Release 0.3 (2011-06-30)

* added new rules
    * AlternativeTextForAppletRule
    * AlternativeTextForAreaOfImageMapRule
    * DefinitionListContainsItemsRule
    * LabelWithoutFormElementRule
    * OrderedListContainsListItemsRule
    * TableHeaderForTableColumnRule
    * TitleForFrameRule
    * TitleForInlineFrameRule
    * UniqueLabelForFormElementRule
    * UnorderedListContainsListItemsRule
* fixed invalid filename of class XmlRuleSetException
* added license information to the library
* every violation knows the element that caused the violation
* availability in Maven Central Repository

### Release 0.2 (2011-06-01)

* easier selection of elements with new Page decorator
* easier creation of violations in rule implementations
* added new rules
    * AvoidBoldTagRule
    * AvoidItalicTagRule
    * AvoidMetaRefreshRule
    * HeadersInCorrectOrderRule
    * LanguageForHtmlTagRule
    * TableHeaderContainsTextRule
* introduced XmlRuleSet to create a RuleSet based on a XML document
* improved documentation
* added Hamcrest Matchers for compliance checking
    * introduced RuleMatcher for verification against a specific Rule
    * introduced RuleSetMatcher for verification against a RuleSet

### Release 0.1 (2011-05-24)

* introduced Rule to check a single HTML page against a single rule
* introduced RuleSet to check a single HTML page against multiple rules
* added first rules
    * AlternativeTextForImageRule
    * AvoidBlinkTextRule
    * AvoidMarqueeTextRule
    * CaptionTextForTableRule
    * LabelContainsTextRule
    * LabelForInputFieldRule
    * LabelForSelectTagRule
    * LabelForTextareaRule
    * TitleForAbbreviationTagRule
    * TitleForAcronymTagRule
