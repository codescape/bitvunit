# BitvUnit [![Build Status](https://travis-ci.org/codescape/bitvunit.png?branch=master)](https://travis-ci.org/codescape/bitvunit)

BitvUnit is an open source accessibility testing library that makes it easy to automate accessibility checking for pages and applications that run in your browser. It integrates well with Selenium Webdriver and HtmlUnit and provides Hamcrest matchers.

BitvUnit is free, open source software licensed under the [Apache License, Version 2.0](http://www.apache.org/licenses/LICENSE-2.0.html).

## Table of Contents

* [Getting started](#getting-started)
* [Contributing](#contributing)
   * [Configuring rule set](#configuring-rule-set)
* [Development](#development)
    * [Building the project](#building-the-project)
    * [Implementing new rules](#implementing-new-rules)
    * [Updating dependency versions](#updating-dependency-versions)
    * [Releasing a new version](#releasing-a-new-version)
    * [Documenting changes](#documenting-changes)
 
## Getting started

BitvUnit is available from the [Maven Central Repository](http://repo1.maven.org/maven2/de/codescape/bitvunit/). To include and use this library from your Maven project put the following dependency into the `<dependencies/>` section of your project `pom.xml` and you are already set up to get started:

    <dependency>
        <groupId>de.codescape.bitvunit</groupId>
        <artifactId>bitvunit-core</artifactId>
        <version>0.11</version>
        <scope>test</scope>
    </dependency>

Inside your test you can use the Hamcrest matcher to verify your website against the rules provided by the library or in combination with your own rules.

    @Test
    public void checkBitvUnitHomepageAgainstAllRules() {
        HtmlPage pageUnderTest = new WebClient().getPage("http://bitvunit.codescape.de");
        assertThat(pageUnderTest, is(compliantTo(allRules())));
    }

To learn about other ways of using BitvUnit have a look at the tests inside the `bitvunit-samples` project that are written to show other ways of testing against the rules of this library.

### Configuring rule set

There are two supported ways to configure the set of rules that shall be applied to verify accessibility. The statement typically looks like this:

    assertThat(pageUnderTest, is(compliantTo(rulesToApply)));

The matcher `compliantTo()` accepts instances of type `Rule` and `RuleSet`. As you usually want to assert against multiple rules you will need to supply that matcher with a configured `RuleSet` instance.

TODO: describe ways of creating the RuleSet instance

## Contributing

There are many ways to support this project. Here is an incomplete list of ways how to contribute:

* fork the project and contribute your code (e.g. new rules) with a pull request
* raise an issue for new rules or improvements of the library
* contact me and tell me about your experiences with the library
* write a blog post, send a tweet or promote this project
* use the library for your project and tell others about it

BitvUnit is open source and every kind of contribution is welcome!

## Development

Adding functionality, building the software or doing some housekeeping should be easy. So here is a list of common tasks and how to perform them:

### Building the project

To build the latest version of the library checkout the sources and run `mvn clean install` on your local machine. This will create and install the latest version of the library to your local maven repository.

### Implementing new rules

To implement a new rule you can run the Groovy based wizard with the command `groovy bitvunit create-rule` from the root folder of the project. It prompts for your full name to be listed in the JavaDoc and the name for your new rule. Valid names must start with an uppercase letter, contain only characters and end with the word `Rule`.

After that you can choose from one of the existing rule categories and the wizard will set everything up. You are ready to implement the test cases and the rule itself!

    > groovy bitvunit create-rule
    Author name: Stefan Glase
    Rule name [must end with Rule]: FooRule
    Rule category [forms, frames, images, interactions, links, lists, media, page, tables, text]: page
    Created rule at ./bitvunit-core/src/main/java/de/codescape/bitvunit/rule/page/FooRule.java
    Created test at ./bitvunit-core/src/test/java/de/codescape/bitvunit/rule/page/FooRuleTest.java
    Added new rule to ruleset all-rules.xml
    Done. Happy Coding!

### Updating dependency versions

Run `mvn versions:display-dependency-updates` to produce a list of all dependencies where a newer versions exists. Update POMs to use new dependency versions where accurate. Release candidates and milestone releases should be used carefully. After that run `mvn clean verify` to make sure everything works fine with the new versions of the dependencies.

### Releasing a new version

To release a new version the the Central Maven Repository first run `mvn clean verify` and check that all test are running. After a successful build run `mvn release:clean release:prepare` and answer the prompts for release version (e.g.: 0.11), the SCM tag (e.g.: v0.11) and the development version (e.g.: 0.12-SNAPSHOT).

Verify the results and run `mvn release:perform` if everything is looking fine. The nexus-staging-maven-plugin will do the rest and close the release in the Sonatype OSSRH and synchronization to Maven Central will pick up the new version.

The complete process to bring out a new release currently includes:

* `mvn clean verify` to check that all tests are running
* update [changelog.md](/changelog.md) and update release information and date
* update [readme.md](/readme.md) and update version number in maven coordinates
* update version number in _config.yml in the gh-pages branch
* `mvn release:clean release:prepare` to prepare the release
* `mvn release:perform` to deploy to sonatype and close release
* `git clean -f` to remove all .backup files created during release process

### Documenting changes

Any changes applied to the library should be documented in the changelog markdown document on the root level of the project. Please make sure to add all changes that are worth mentioning to the [changelog.md](/changelog.md) file.
