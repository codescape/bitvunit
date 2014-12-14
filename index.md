---
layout: default
title: About
---

BitvUnit is an open source accessibility testing library that makes it easy to automate accessibility checking for pages and applications that run in a web browser. BitvUnit is free, open source software licensed under the [Apache License, Version 2.0](http://www.apache.org/licenses/LICENSE-2.0.html).

The library uses [Apache Maven](http://maven.apache.org) for its build and dependency management. With the help of [HtmlUnit](http://htmlunit.sourceforge.net) the library inspects HTML pages that are checked against the rules of the library. It provides adapters for [Hamcrest](http://code.google.com/p/hamcrest) matchers because of their expressive syntax.

### Further Resources

If you want learn more about the library, these resources might be interesting for you:

<ul class="resources">
{% for resource in site.data.resources %}
  <li>
    <a href="{{resource.url}}"><img src="{{resource.image}}"></a>
    <h4>{{resource.title}}</h4>
    <p>{{resource.description}}</p>
  </li>
{% endfor %}
</ul>
