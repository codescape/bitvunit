BitvUnit
=============

This library aims to automate accessibility checks for HTML pages.

Technology
-------------

The project makes use of the following frameworks to do its work:

* [Apache Maven](http://maven.apache.org/) for build and dependency management
* [HtmlUnit](http://htmlunit.sourceforge.net/) for HTML code inspection

Rules
-------------

For a list of all rules currently supported by BitvUnit please refer to the RuleSet [all-rules.xml](https://github.com/codescape/bitvunit/blob/master/src/main/resources/rulesets/all-rules.xml) which lists all rules.

Change Log
-------------

* Release 0.2 (????-??-??)
    * easier selection of elements with new Page decorator
    * easier creation of violations in rule implementations
    * added new rules
        * AvoidBoldTagRule
        * AvoidItalicTagRule
        * LanguageForHtmlTagRule
        * TableHeaderContainsTextRule
    * introduced XmlRuleSet to create a RuleSet based on a XML document

* Release 0.1 (2011-05-24)
    * introduced Rule to check a single HTML page against a single rule
    * introduced RuleSet to check a single HTML page against multiple rules
    * added first bunch of rules
