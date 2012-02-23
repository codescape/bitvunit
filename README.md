BitvUnit
========

BitvUnit is an open source accessibility testing framework that makes it easy to automate accessibility checking for pages and applications that run in a web browser. Read more about it in the official documentation available at http://bitvunit.codescape.de!

### Build latest version

To build the latest version of the framework just checkout the sources and run `mvn install` on your local machine and it will create and install the latest version of the framework to your local maven repository.

### Implement new rules

To implement a new rule you can run the Groovy based wizard with the command `groovy bitvunit.groovy create-rule` from the root folder of the project. It prompts for your full name to be listed in the JavaDoc and the name for your new rule. Valid names must start with an uppercase letter, contain only characters and end with the word `Rule`.

After that you can choose from one of the existing rule categories and the wizard will set everything up. You are ready to implement the test cases and the rule itself!
