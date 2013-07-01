# BitvUnit [![Build Status](https://travis-ci.org/codescape/bitvunit.png?branch=master)](https://travis-ci.org/codescape/bitvunit)

BitvUnit is an open source accessibility testing library that makes it easy to automate accessibility checking for pages and applications that run in your browser. Read more about it in the official documentation available at http://bitvunit.codescape.de!

## Getting started

Visit the website under http://bitvunit.codescape.de and find out how to download or reference from your project and implement tests runs in your application.

## Contributing

BitvUnit is open source and every contribution is welcome! This section holds information that helps you to contribute to BitvUnit and add functionality, build the software or do some housekeeping.

### Build latest version

To build the latest version of the library checkout the sources and run `mvn clean install` on your local machine. This will create and install the latest version of the library to your local maven repository.

### Implement new rules

To implement a new rule you can run the Groovy based wizard with the command `groovy bitvunit.groovy create-rule` from the root folder of the project. It prompts for your full name to be listed in the JavaDoc and the name for your new rule. Valid names must start with an uppercase letter, contain only characters and end with the word `Rule`.

After that you can choose from one of the existing rule categories and the wizard will set everything up. You are ready to implement the test cases and the rule itself!

### Updating dependency versions

Run `mvn versions:display-dependency-updates` to produce a list of all dependencies where a newer versions exists. Update POMs to use new dependency versions where accurate. Release candidates and milestone releases should be used carefully. After that run `mvn verify` to make sure everything works fine with the new versions of the dependencies.

### Releasing a new version

First run `mvn clean verify` and check that all test are running. After a successful build run `mvn release:prepare` and set the release version (e.g.: 0.5.0), the SCM tag (e.g.: v0.5.0) and the development version (e.g.: 0.6.0-SNAPSHOT).

Verify the results and run `mvn release:perform` if everything is looking fine. Login at https://oss.sonatype.org and verify that the generated artifacts in the `Staging Repository` are okay. `Close` the Build in the `Staging Repository` and then `Release` the new version to Maven Central.
