package de.codescape.bitvunit.rule;

import de.codescape.bitvunit.model.Page;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class AbstractRuleTest {

    @Test
    public void toStringReturnsReadableStringRepresentation() throws Exception {
        String specificRule = "MyRuleName";
        String expectedToString = "SpecificRule[name=" + specificRule + "]";
        assertEquals(expectedToString, new SpecificRule(specificRule).toString());
    }

    @Test
    public void getPriorityShouldReturnNormalIfNotOverridden() throws Exception {
        assertEquals(Priority.NORMAL, new SpecificRule("SomeRuleName").getPriority());
    }

    private class SpecificRule extends AbstractRule {

        private final String name;

        private SpecificRule(String name) {
            this.name = name;
        }

        @Override
        public String getName() {
            return name;
        }

        @Override
        protected void applyTo(Page page, Violations violations) {
            throw new UnsupportedOperationException("should not be called in tests");
        }

    }

}
