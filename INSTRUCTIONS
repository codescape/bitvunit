This file contains a collection of instructions to accomplish different tasks that are repeatedly performed from time to time. 

Updating dependency versions
----------------------------
1. Run `mvn versions:display-dependency-updates` to produce a list of all dependencies where a newer versions exists.
2. Update POMs to use new dependency versions where accurate. Release candidates and milestone releases should be used carefully.
3. Run `mvn verify` to make sure everything works fine with the new versions of the dependencies.

Releasing a new version
-----------------------
1. Run `mvn clean test` and verify that all test are running.
2. Run `mvn release:prepare` and set the release version (e.g.: 0.5.0), the SCM tag (e.g.: v0.5.0) and the development version (e.g.: 0.6.0-SNAPSHOT).
3. Verify the results and run `mvn release:perform` if everything is looking fine.
4. Login at https://oss.sonatype.org and verify the generated artifacts in the `Staging Repository` are okay.
5. `Close` the Build in the `Staging Repository` and then `Release` the new version to Maven Central.
