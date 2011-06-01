package de.codescape.bitvunit.rule;

import com.gargoylesoftware.htmlunit.html.HtmlPage;

public interface Rule {

    String getName();

    Violations applyTo(HtmlPage htmlPage);

}
