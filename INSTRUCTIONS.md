This file contains a collection of instructions to accomplish different tasks that are repeatedly performed from time to time. 

### Updating dependency versions

Run `mvn versions:display-dependency-updates` to produce a list of all dependencies where a newer versions exists. Update POMs to use new dependency versions where accurate. Release candidates and milestone releases should be used carefully. After that run `mvn verify` to make sure everything works fine with the new versions of the dependencies.

### Releasing a new version

Run `mvn clean test` and verify that all test are running. After that run `mvn release:prepare` and set the release version (e.g.: 0.5.0), the SCM tag (e.g.: v0.5.0) and the development version (e.g.: 0.6.0-SNAPSHOT). Verify the results and run `mvn release:perform` if everything is looking fine. Login at https://oss.sonatype.org and verify the generated artifacts in the `Staging Repository` are okay. `Close` the Build in the `Staging Repository` and then `Release` the new version to Maven Central.
