package de.codescape.bitvunit.rule.page;

import de.codescape.bitvunit.rule.Violations;
import org.junit.Test;

import static de.codescape.bitvunit.test.Assertions.assertNoViolations;
import static de.codescape.bitvunit.test.Assertions.assertViolations;
import static de.codescape.bitvunit.util.html.HtmlPageUtil.toHtmlPage;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class AvoidAbstractRoleRuleTest {

    private AvoidAbstractRoleRule rule = new AvoidAbstractRoleRule();

    @Test
    public void ruleHasAName() throws Exception {
        assertNotNull(rule.getName());
        assertEquals("AvoidAbstractRole", rule.getName());
    }

    @Test
    public void abstractRoleCommandPresentCausesViolation() throws Exception {
        abstractRoleWithRoleNamePresentCausesViolation("command");
    }

    @Test
    public void abstractRoleCompositePresentCausesViolation() throws Exception {
        abstractRoleWithRoleNamePresentCausesViolation("composite");
    }

    @Test
    public void abstractRoleInputPresentCausesViolation() throws Exception {
        abstractRoleWithRoleNamePresentCausesViolation("input");
    }

    @Test
    public void abstractRoleLandmarkPresentCausesViolation() throws Exception {
        abstractRoleWithRoleNamePresentCausesViolation("landmark");
    }

    @Test
    public void abstractRoleRangePresentCausesViolation() throws Exception {
        abstractRoleWithRoleNamePresentCausesViolation("range");
    }

    @Test
    public void abstractRoleRoletypePresentCausesViolation() throws Exception {
        abstractRoleWithRoleNamePresentCausesViolation("roletype");
    }

    @Test
    public void abstractRoleSectionPresentCausesViolation() throws Exception {
        abstractRoleWithRoleNamePresentCausesViolation("section");
    }

    @Test
    public void abstractRoleSectionheadPresentCausesViolation() throws Exception {
        abstractRoleWithRoleNamePresentCausesViolation("sectionhead");
    }

    @Test
    public void abstractRoleSelectPresentCausesViolation() throws Exception {
        abstractRoleWithRoleNamePresentCausesViolation("select");
    }

    @Test
    public void abstractRoleStructurePresentCausesViolation() throws Exception {
        abstractRoleWithRoleNamePresentCausesViolation("structure");
    }

    @Test
    public void abstractRoleWidgetPresentCausesViolation() throws Exception {
        abstractRoleWithRoleNamePresentCausesViolation("widget");
    }

    @Test
    public void abstractRoleWindowPresentCausesViolation() throws Exception {
        abstractRoleWithRoleNamePresentCausesViolation("window");
    }

    private void abstractRoleWithRoleNamePresentCausesViolation(String roleName) {
        String content = "<html><body><div role=\"" + roleName + "\">Content</div></body></html>";
        Violations violations = rule.applyTo(toHtmlPage(content));
        assertViolations(violations, rule, 1);
    }

    @Test
    public void landmarkRoleBannerPresentCausesNoViolation() throws Exception {
        String content = "<html><body><div role=\"banner\">Content</div></body></html>";
        Violations violations = rule.applyTo(toHtmlPage(content));
        assertNoViolations(violations, rule);
    }

}
