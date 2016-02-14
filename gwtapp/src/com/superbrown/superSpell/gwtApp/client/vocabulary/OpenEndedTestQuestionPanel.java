package com.superbrown.superSpell.gwtApp.client.vocabulary;

import com.google.gwt.event.dom.client.KeyPressHandler;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.TextBox;
import com.superbrown.superSpell.gwtApp.client.ICompletedListener;
import com.superbrown.superSpell.gwtApp.client.common.AnswerResultType;
import com.superbrown.superSpell.gwtApp.client.common.IPanelInitializationListener;
import com.superbrown.superSpell.gwtApp.shared.vocabulary.VocabularyWord;

/**
 */
public class OpenEndedTestQuestionPanel extends TestQuestionPanel
{
    private TextBox textBox;

    public OpenEndedTestQuestionPanel(VocabularyWord vocabularyWord, IPanelInitializationListener panelInitializationListener)
    {
        super(vocabularyWord, panelInitializationListener);
        panelInitializationListener.initializationCallback(this);        
    }

    @Override
    public void addQuestionToPanel()
    {
        clear();

        add(createTestQuestionWidget());

        setTextBox(new TextBox());
        add(getTextBox());

        add(createSubmitAnswerButton());
    }

    @Override
    protected String getUserAnswer()
    {
        return textBox.getValue().trim();
    }

    @Override
    public void setKeyPressHandler(KeyPressHandler keyPressHandler)
    {
        super.setKeyPressHandler(keyPressHandler);

        if (textBox != null)
        {
            textBox.addKeyPressHandler(keyPressHandler);
        }
    }

    public void setTextBox(TextBox textBox)
    {
        this.textBox = textBox;
        if (getKeyPressHandler() != null)
        {
            textBox.addKeyPressHandler(getKeyPressHandler());
        }
    }

    public TextBox getTextBox()
    {
        return textBox;
    }

    public void displayResult(
            final AnswerResultType answerResultType,
            final ICompletedListener completedListener)
    {
        getTextBox().setEnabled(false);

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
                            Label messageLabel = null;

                            if (answerResultType == AnswerResultType.INCORRECT)
                            {
                                getTestableItem().getIncorrectAnswers().add(getUserAnswer());
                                // add this answer to the list of incorrect spellings
                                messageLabel = new Label(getARandomGotTheAnswerIncorrectMessage() + " " +
                                        "The correct answer is:   " + getCorrectAnswer());
                            }
                            else if (answerResultType == AnswerResultType.USER_RAN_OUT_OF_TIME)
                            {
                                // add this answer to the list of incorrect spellings
                                getTestableItem().getIncorrectAnswers().add(getUserAnswer());
                                messageLabel = new Label("You ran out of time " +
                                        "The correct answer is:   " + getCorrectAnswer());
                            }

                            messageLabel.setStyleName("yellowChalk topMargin10 fontSize150");
                            insert(messageLabel, getWidgetCount() - 1);
                        }

                        completedListener.completed();
                    }
                });
    }

    @Override
    public void setToDefaultFocus()
    {
        if (getTextBox() == null)
        {
            return;
        }

        getTextBox().setFocus(true);
    }

    @Override
    public boolean hasQuestionBeenAnswered()
    {
        return !getUserAnswer().equals("");
    }


    public boolean wasAnswerRight()
    {
        VocabularyWord vocabularyWord = (VocabularyWord) getTestableItem();

        // if the word is at the beginning of the sentence and the word
        // isn't a proper noun, don't worry about the case used
        if (vocabularyWord.wordIsAtTheBeginningOfTheTestQuestion() &&
            !Character.isUpperCase(getCorrectAnswer().charAt(0)))
        {
            return getUserAnswer().equalsIgnoreCase(getCorrectAnswer());
        }
        else
        {
            return getUserAnswer().equals(getCorrectAnswer());
        }
    }
}