package de.codescape.bitvunit.util.html;

import com.gargoylesoftware.htmlunit.html.HtmlLabel;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static de.codescape.bitvunit.util.html.HtmlLabelUtil.containsLabelForId;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class HtmlLabelUtilTest {

    @Test
    public void containsLabelForIdShouldReturnTrueIfLabelForThatIdExists() {
        assertThat(containsLabelForId(labelsWithId("correctId", "wrongId"), "correctId"), equalTo(true));
    }

    @Test
    public void containsLabelForIdShouldReturnFalseIfLabelForThatIdDoesNotExists() {
        assertThat(containsLabelForId(labelsWithId("anotherId", "wrongId"), "correctId"), equalTo(false));
    }

    private List<HtmlLabel> labelsWithId(String... ids) {
        List<HtmlLabel> labels = new ArrayList<>();
        for (String id : ids) {
            HtmlLabel mock = mock(HtmlLabel.class);
            when(mock.getForAttribute()).thenReturn(id);
            labels.add(mock);
        }
        return labels;
    }

}