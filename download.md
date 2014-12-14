---
layout: default
title: Download
permalink: download/index.html
---

Please choose your preferred way to download BitvUnit.

### GitHub Download

Download the latest version of the library from the GitHub download page:

* [bitvunit-core-{{ site.bitvunit-version }}.jar](http://repo1.maven.org/maven2/de/codescape/bitvunit/bitvunit-core/{{ site.bitvunit-version }}/bitvunit-core-{{ site.bitvunit-version }}.jar)
* [bitvunit-core-{{ site.bitvunit-version }}-sources.jar](http://repo1.maven.org/maven2/de/codescape/bitvunit/bitvunit-core/{{ site.bitvunit-version }}/bitvunit-core-{{ site.bitvunit-version }}-sources.jar)
* [bitvunit-core-{{ site.bitvunit-version }}-javadoc.jar](http://repo1.maven.org/maven2/de/codescape/bitvunit/bitvunit-core/{{ site.bitvunit-version }}/bitvunit-core-{{ site.bitvunit-version }}-javadoc.jar)

You can find previous versions on the [Maven Central Repository](http://repo1.maven.org/maven2/de/codescape/bitvunit/).

### Maven Repository

BitvUnit is available from the [Maven Central Repository](http://repo1.maven.org/maven2/de/codescape/bitvunit/). To use BitvUnit in your Maven project put the following dependency into the `<dependencies/>` section of your project pom.xml:

{% highlight xml %}
<dependency>
    <groupId>de.codescape.bitvunit</groupId>
    <artifactId>bitvunit-core</artifactId>
    <version>{{ site.bitvunit-version }}</version>
</dependency>
{% endhighlight %}
