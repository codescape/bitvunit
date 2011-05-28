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

* Forms
    * LabelContainsTextRule (since 0.1)
    * LabelForInputFieldRule (since 0.1)
    * LabelForSelectTagRule (since 0.1)
    * LabelForTextareaRule (since 0.1)
* Images
    * AlternativeTextForImageRule (since 0.1)
* Tables
    * CaptionTextForTableRule (since 0.1)
* Text
    * AvoidBlinkTextRule (since 0.1)
    * AvoidBoldTagRule (since 0.2)
    * AvoidItalicTagRule (since 0.2)
    * AvoidMarqueeTextRule (since 0.1)
    * LanguageForHtmlTagRule (since 0.2)
    * TitleForAbbreviationTagRule (since 0.1)
    * TitleForAcronymTagRule (since 0.1)

Change Log
-------------

* Release 0.2 (????-??-??)
    * easier selection of elements with new Page decorator
    * easier creation of violations in rule implementations
    * added new rules
        * AvoidBoldTagRule
        * AvoidItalicTagRule
        * LanguageForHtmlTagRule
    * introduced XmlRuleSet to create a RuleSet from a XML document

* Release 0.1 (2011-05-24)
    * introduced Rule to check a single HTML page against a single rule
    * introduced RuleSet to check a single HTML page against multiple rules
    * added first bunch of rules
