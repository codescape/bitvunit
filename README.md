BitvUnit
=============

BitvUnit is an open source accessibility testing framework that makes it easy to automate accessibility checking for pages and applications that run in a web browser.

Read me about the framework in the [BitvUnit Documentation]!


Technology
----------

The project makes use of the following frameworks to do its work:

* [Apache Maven] for build and dependency management
* [HtmlUnit] for HTML code inspection and navigation through the pages
* [Hamcrest] for compliance checking with an expressive syntax

Change Log
----------

### Release 0.3 (????-??-??)

* added new rules
    * OrderedListContainsListItemsRule
    * UnorderedListContainsListItemsRule
* fixed invalid filename of class XmlRuleSetException

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
* added [Hamcrest] Matchers for compliance checking
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

[Apache Maven]: http://maven.apache.org
[Hamcrest]: http://code.google.com/p/hamcrest
[HtmlUnit]: http://htmlunit.sourceforge.net
[BitvUnit Documentation]: http://bitvunit.codescape.de