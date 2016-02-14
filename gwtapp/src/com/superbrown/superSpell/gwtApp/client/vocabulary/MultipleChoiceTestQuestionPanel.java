package com.superbrown.superSpell.gwtApp.client.vocabulary;

import com.google.gwt.event.dom.client.KeyPressHandler;
import com.google.gwt.user.client.ui.Label;
import com.superbrown.superSpell.gwtApp.client.ICompletedListener;
import com.superbrown.superSpell.gwtApp.client.common.AnswerResultType;
import com.superbrown.superSpell.gwtApp.client.common.IPanelInitializationListener;
import com.superbrown.superSpell.gwtApp.client.common.StringValueRadioButtonPanel;
import com.superbrown.superSpell.gwtApp.shared.vocabulary.VocabularyWord;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 */
public class MultipleChoiceTestQuestionPanel extends TestQuestionPanel
{
    private List<String> allWords;
    private StringValueRadioButtonPanel radioButtonPanel;
    private String correctAnswer;


    public MultipleChoiceTestQuestionPanel(VocabularyWord vocabularyWord, IPanelInitializationListener panelInitializationListener)
    {
        super(vocabularyWord, panelInitializationListener);
        panelInitializationListener.initializationCallback(this);        
    }

    @Override
    protected String getUserAnswer()
    {
        return radioButtonPanel.getSelectedValue().trim();
    }

    public void addQuestionToPanel()
    {
        clear();

        allWords = new ArrayList<String>();
        correctAnswer = getTestableItem().getCorrectAnswer();
        allWords.add(correctAnswer);
        allWords.addAll(getTestableItem().getIncorrectAnswers());
        Collections.sort(allWords);

        add(createTestQuestionWidget());

        setRadioButtonPanel(new StringValueRadioButtonPanel(allWords, "bold courier fontSize120"));
        add(getRadioButtonPanel());

        add(createSubmitAnswerButton());
    }

    private void setRadioButtonPanel(StringValueRadioButtonPanel radioButtonPanel)
    {
        this.radioButtonPanel = radioButtonPanel;

        if (getKeyPressHandler() != null)
        {
            radioButtonPanel.setKeyPressHandler(getKeyPressHandler());
        }
    }

    public StringValueRadioButtonPanel getRadioButtonPanel()
    {
        return radioButtonPanel;
    }

    @Override
    public void setKeyPressHandler(KeyPressHandler keyPressHandler)
    {
        super.setKeyPressHandler(keyPressHandler);

        if (radioButtonPanel != null)
        {
            radioButtonPanel.setKeyPressHandler(keyPressHandler);
        }
    }

    public void highlightCorrectAnswer()
    {
        radioButtonPanel.highlightSelection(correctAnswer);
    }

    @Override
    public void displayResult(
            final AnswerResultType answerResultType,
            final ICompletedListener completedListener)
    {
        super.displayResult(
                answerResultType,
                new ICompletedListener()
                {
                    public void completed()
                    {
                        if (answerResultType == AnswerResultType.CORRECT)
                        {
                            Label messageLabel = new Label(getARandomGotTheAnswerCorrectMessage() + " That's correct.");
                            messageLabel.setStyleName("yellowChalk topMargin10 fontSize150");
                            insert(messageLabel, getWidgetCount() - 1);
                        }
                        else
                        {
                            highlightCorrectAnswer();

                            Label messageLabel = null;

                            if (answerResultType == AnswerResultType.INCORRECT)
                            {
                                messageLabel = new Label(getARandomGotTheAnswerIncorrectMessage());

                            }
                            else if (answerResultType == AnswerResultType.USER_RAN_OUT_OF_TIME)
                            {
                                messageLabel = new Label("You ran out of time.");
                            }

                            messageLabel.setStyleName("yellowChalk topMargin10 fontSize150");
                            insert(messageLabel, getWidgetCount() - 1);
        
                            messageLabel = new Label("The correct answer is highlighted.");
                            messageLabel.setStyleName("yellowChalk fontSize150");
                            insert(messageLabel, getWidgetCount() - 1);
                        }

                        completedListener.completed();
                    }
                });
    }

    @Override
    public void setToDefaultFocus()
    {
        if (getRadioButtonPanel() == null)
        {
            return;
        }

        getRadioButtonPanel().setFocusOnAndSelectFirstButton();
    }

    @Override
    public boolean hasQuestionBeenAnswered()
    {
        String userAnswer = getUserAnswer();
        return userAnswer != null && !userAnswer.equals("");
    }    
}