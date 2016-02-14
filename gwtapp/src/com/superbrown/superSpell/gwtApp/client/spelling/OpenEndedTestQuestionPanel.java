package com.superbrown.superSpell.gwtApp.client.spelling;

import com.google.gwt.event.dom.client.KeyPressHandler;
import com.google.gwt.user.client.ui.HasVerticalAlignment;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.TextBox;
import com.superbrown.superSpell.gwtApp.client.ICompletedListener;
import com.superbrown.superSpell.gwtApp.client.common.AnswerResultType;
import com.superbrown.superSpell.gwtApp.client.common.IPanelInitializationListener;
import com.superbrown.superSpell.gwtApp.shared.spelling.SpellingWord;

/**
 */
public class OpenEndedTestQuestionPanel extends TestQuestionPanel
{
    private TextBox textBox;
    private HorizontalPanel horizontalPanel;

    public OpenEndedTestQuestionPanel(SpellingWord spellingWord, 
                                      IPanelInitializationListener panelInitializationListener)
    {
        super(spellingWord, panelInitializationListener);
        panelInitializationListener.initializationCallback(this);        
    }

    @Override
    public void addQuestionToPanel()
    {
        clear();

        add(createTestQuestionWidget());

        horizontalPanel = new HorizontalPanel();
        horizontalPanel.setVerticalAlignment(HasVerticalAlignment.ALIGN_MIDDLE);

        TextBox textBox = new TextBox();
        setTextBox(textBox);
        horizontalPanel.add(getTextBox());

        add(horizontalPanel);

        add(createSubmitAnswerButton());

        textBox.setFocus(true);
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
                            Label messageLabel = null;

                            if (answerResultType == AnswerResultType.CORRECT)
                            {
                                messageLabel = new Label(getARandomGotTheAnswerCorrectMessage() +
                                        " That's correct.");
                            }
                            else
                            {
                                Label correctAnswer = new Label(getCorrectAnswer());
                                correctAnswer.addStyleName("bold leftMargin10 fontSize150 blink");
                                horizontalPanel.add(correctAnswer);

                                if (answerResultType == AnswerResultType.INCORRECT)
                                {
                                    // add this answer to the list of incorrect spellings
                                    getTestableItem().getIncorrectAnswers().add(getUserAnswer());

                                    messageLabel = new Label(getARandomGotTheAnswerIncorrectMessage());
                                }
                                else if (answerResultType == AnswerResultType.USER_RAN_OUT_OF_TIME)
                                {
                                    messageLabel = new Label("You ran out of time.");
                                }
                            }

                            messageLabel.setStyleName("yellowChalk topMargin10 fontSize150");
                            insert(messageLabel, getWidgetCount() - 1);

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

    @Override
    public boolean wasAnswerRight()
    {
        SpellingWord spellingWord = (SpellingWord) getTestableItem();

        // if the word is at the beginning of the sentence and the word
        // isn't a proper noun, don't worry about the case used
        if (spellingWord.wordIsAtTheBeginningOfTheSampleSentence() &&
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