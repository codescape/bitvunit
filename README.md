BitvUnit
=============

This library aims to automate accessibility checks for HTML pages.

Technology
-------------

The project makes use of the following frameworks:

* [Apache Maven](http://maven.apache.org/) for build and dependency management
* [HtmlUnit](http://htmlunit.sourceforge.net/) for HTML code inspection

Rules
-------------

At the moment the following rules are provided by the framework:

* Forms
    * LabelContainsTextRule (since 0.1)
    * LabelForInputFieldRule (since 0.1)
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
