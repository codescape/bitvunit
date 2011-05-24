BitvUnit
=============

This library aims to automate accessibility checks for HTML pages.

Technology
-------------

The project makes use of the following frameworks to do its own work:

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
    * AvoidMarqueeTextRule (since 0.1)
    * TitleForAbbreviationTagRule (since 0.1)
    * TitleForAcronymTagRule (since 0.1)

Change Log
-------------

* Release 0.1 (release date goes here)
    * introduced idea of Rule to check a single HTML page against a single rule
    * introduced idea of RuleSet to check a single HTML page against multiple rules
    * added first bunch of rules
