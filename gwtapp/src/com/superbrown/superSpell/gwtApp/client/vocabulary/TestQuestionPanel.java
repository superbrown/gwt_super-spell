package com.superbrown.superSpell.gwtApp.client.vocabulary;

import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;
import com.superbrown.superSpell.gwtApp.client.common.IPanelInitializationListener;
import com.superbrown.superSpell.gwtApp.shared.vocabulary.VocabularyWord;

/**
 */
public abstract class TestQuestionPanel extends com.superbrown.superSpell.gwtApp.client.common.TestQuestionPanel
{
    public TestQuestionPanel(VocabularyWord vocabularyWord, IPanelInitializationListener panelInitializationListener)
    {
        super(vocabularyWord, panelInitializationListener);
    }

    protected Widget createTestQuestionWidget()
    {
        VerticalPanel panel = new VerticalPanel();

        VocabularyWord vocabularyWord = (VocabularyWord)getTestableItem();

        Label label = new Label("Pick the word that has the definition below.");
        label.setStyleName("yellowChalk fontSize150");
        panel.add(label);

        String testQuestion = vocabularyWord.getTestQuestion();
        String[] testQuestionLines = testQuestion.split("\n");

        for (String testQuestionLine : testQuestionLines)
        {
            label = new Label(testQuestionLine);
            label.addStyleName("bold");
            label.setWidth("300");
            label.setWordWrap(true);
            panel.add(label);
        }
        label.addStyleName("bottomMargin");

        return panel;
    }
}