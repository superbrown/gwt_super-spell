package com.superbrown.superSpell.gwtApp.client.spelling.vocabularyWord;

import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;
import com.superbrown.superSpell.gwtApp.client.common.IPanelInitializationListener;
import com.superbrown.superSpell.gwtApp.shared.spelling.SpellingWord;

/**
 */
public class MultipleChoiceTestQuestionPanel extends com.superbrown.superSpell.gwtApp.client.spelling.MultipleChoiceTestQuestionPanel
{
    public MultipleChoiceTestQuestionPanel(SpellingWord spellingWord, IPanelInitializationListener panelInitializationListener)
    {
        super(spellingWord, panelInitializationListener);
    }

    protected Widget createTestQuestionWidget()
    {
        VerticalPanel panel= new VerticalPanel();

        final SpellingWord spellingWord = (SpellingWord) getTestableItem();

        Label phoneticSpellingLabel = new Label(("How do you spell the word with the following definition?"));
        phoneticSpellingLabel.addStyleName("bold");

        Label sampleSentenceLabel = new Label((spellingWord.getSampleSentenceToDisplayToUser()));
        sampleSentenceLabel.addStyleName("bold bottomMargin");

        VerticalPanel verticalPanel_1 = new VerticalPanel();
        verticalPanel_1.add(phoneticSpellingLabel);
        verticalPanel_1.add(sampleSentenceLabel);
        verticalPanel_1.setWidth("400px");

        HorizontalPanel horizontalPanel = new HorizontalPanel();
        horizontalPanel.add(verticalPanel_1);

        panel.add(horizontalPanel);

        return panel;
    }

    protected void onAttachAction()
    {
    }
}
