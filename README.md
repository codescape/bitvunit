BitvUnit
=============

This library aims to automate accessibility checking for HTML pages and web applications. Based on the idea that some accessibility checks can be automated and find suspicious or malicious code that potentially violates rules for accessibility.
In the current stadium the BitvUnit framework gives you the ability to apply one or multiple rules to a given page that you navigated to with the help of [HtmlUnit].

Technology
----------

The project makes use of the following frameworks to do its work:

* [Apache Maven] for build and dependency management
* [HtmlUnit] for HTML code inspection and navigation through the pages

The project is inspired by the following projects, blogs and resources:

* [CodeNarc](http://codenarc.sourceforge.net) for the Rule and RuleSet structure
* [WebTestPraxis](http://webtestpraxis.de/blog/?tag=barrierefreiheit) for its great articles on accessibility testing

Getting Started
---------------

_This chapter should include a small but helpful step-by-step guide to get started with the project._

How to contribute
-----------------

There are several way how you can help this project. Here is an incomplete list of ideas on how to contribute:

* fork the project and contribute code (e.g. new rules)
* raise an issue for new rules or improvements of the framework
* contact me and tell me about your experiences with the framework
* write a blog post, send a tweet or promote the project

Change Log
----------

### Release 0.2 (????-??-??)

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
[HtmlUnit]: http://htmlunit.sourceforge.net