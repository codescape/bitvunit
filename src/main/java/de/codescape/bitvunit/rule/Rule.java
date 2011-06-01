package de.codescape.bitvunit.rule;

import com.gargoylesoftware.htmlunit.html.HtmlPage;

import java.util.List;

public interface Rule {

    String getName();

    Violations applyTo(HtmlPage htmlPage);

}
