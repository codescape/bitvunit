Instructions
============
This file contains a collection of instructions to accomplish different tasks that are repeatedly performed from time to time. 

Updating dependency versions
----------------------------
1. Run `mvn versions:display-dependency-updates` to produce a list of all dependencies that are up to date and a second list of all dependencies where a newer versions exists. See [versions-maven-plugin documentation](http://mojo.codehaus.org/versions-maven-plugin/) for further details. 
2. Update all POMs to use newer dependency versions where accurate. Release candidates and milestone releases should be used carefully.
3. Run `mvn verify` to make sure everything works fine with the newer versions of the updated dependencies.

Releasing a new version
-----------------------
TBD
