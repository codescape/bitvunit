package de.codescape.bitvunit.test;

import de.codescape.bitvunit.ruleset.RuleSet;
import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;

/**
 * Hamcrest matchers for testing aspects of the library.
 */
public class Matchers {

    public static Matcher<? super RuleSet> containsRules() {
        return new TypeSafeMatcher<RuleSet>() {
            @Override
            protected boolean matchesSafely(RuleSet item) {
                return !item.getRules().isEmpty();
            }

            @Override
            public void describeTo(Description description) {
                description.appendText("contains rules");
            }
        };
    }

    public static Matcher<? super RuleSet> containsRules(final int count) {
        return new TypeSafeMatcher<RuleSet>() {
            @Override
            protected boolean matchesSafely(RuleSet item) {
                return item.getRules().size() == count;
            }

            @Override
            public void describeTo(Description description) {
                description.appendText("RuleSet contains exactly ").appendValue(count).appendText(" rules");
            }

            @Override
            protected void describeMismatchSafely(RuleSet item, Description mismatchDescription) {
                mismatchDescription.appendText("RuleSet contains ").appendValue(item.getRules().size()).appendText(" rules");
            }
        };
    }

}
