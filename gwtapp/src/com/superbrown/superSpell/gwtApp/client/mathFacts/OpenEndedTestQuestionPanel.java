package com.superbrown.superSpell.gwtApp.client.mathFacts;

import com.google.gwt.event.dom.client.*;
import com.google.gwt.user.client.ui.*;
import com.superbrown.superSpell.gwtApp.client.ICompletedListener;
import com.superbrown.superSpell.gwtApp.client.SuperSpell;
import com.superbrown.superSpell.gwtApp.client.common.AnswerResultType;
import com.superbrown.superSpell.gwtApp.client.common.IPanelInitializationListener;
import com.superbrown.superSpell.gwtApp.shared.mathFacts.MathFact;
import com.superbrown.superSpell.gwtApp.shared.mathFacts.addition.AdditionMathFact;
import com.superbrown.superSpell.gwtApp.shared.mathFacts.division.DivisionMathFact;
import com.superbrown.superSpell.gwtApp.shared.mathFacts.subtraction.SubtractionMathFact;

/**
 */
public class OpenEndedTestQuestionPanel extends TestQuestionPanel
{
    private HorizontalPanel horizontalPanel;
    private TextBox textBox;
    private boolean theCurrentCharacterIsAControlCharacterThatWeWantToReactTo = false;
    private String peterMathAnswer;

    public OpenEndedTestQuestionPanel(MathFact mathFact, IPanelInitializationListener panelInitializationListener)
    {
        super(mathFact, panelInitializationListener);
        panelInitializationListener.initializationCallback(this);
    }

    @Override
    public void addQuestionToPanel()
    {
        clear();

        add(createTestQuestionWidget());

        add(createSubmitAnswerButton());
    }

    @Override
    protected Widget createTestQuestionWidget()
    {       
        VerticalPanel verticalPanel = new VerticalPanel();

        final MathFact mathFact = (MathFact)getTestableItem();

        Label label = new Label("Fill in the blank.");
        label.setStyleName("yellowChalk fontSize150");
        verticalPanel.add(label);

        horizontalPanel = new HorizontalPanel();
        horizontalPanel.setVerticalAlignment(HasVerticalAlignment.ALIGN_MIDDLE);

        label = new Label(mathFact.getTestQuestion());
        label.addStyleName("bold bottomMargin fontSize150");
        label.setWidth("250");
        label.setWordWrap(true);
        horizontalPanel.add(label);

        final TextBox textBox = new TextBox();

        setTextBox(textBox);

        textBox.addStyleName("leftMargin10");
        textBox.setWidth("40px");
        
        horizontalPanel.add(getTextBox());

        verticalPanel.add(horizontalPanel);

        addKeyPressHandlerThatWillFilterOutKeysHitByMistake(textBox, mathFact);

        return verticalPanel;
    }

    /**
     * The idea of the filtering is to prevent the text box from handling any key stokes other than
     * ones we have reason to believe the user intended.  For instance, letters and punctuation
     * characters will be ignored, as will numbers beyond a certain length.  So if we're doing
     * division or subtraction, and the user enters in more than one digit, all digits after the
     * first one will be ignored.  (For multiplication and addition, two digit numbers are allowed.)
     * Also, leading zeros are always ignored. 
     */
    private void addKeyPressHandlerThatWillFilterOutKeysHitByMistake(
            final TextBox textBox, final MathFact mathFact)
    {
        // This is sort of a hack.  We have to use two handler methods to accomplish what we want to
        // with the key press filtering.  The idea of the filtering is to only allow
        //
        // The key down handler is sent a KeyDownEvent.  We can use that to detect the pressing of
        // navigation keys (deleted, arrows, etc.).
        //
        // The key press handler sent a KeyPressEvent.  We can use that to detect the pressing of
        // character keys (for instance, number keys).
        //
        // The actual filtering is accomplished by intercepting the key press event, and putting the
        // breaks on its handling if appropriate (so the value in the text box doesn't get updated).
        //
        // Since, in order to filter, we have to know whether the key was a navigation key, (which
        // can't be determined within the key press handler), I'm having the key down handler
        // figure that out and store the answer in this class'
        // nextCharacterIsAControlCharacterWeWantToReactTo field, which the key press handler can
        // then use.


        textBox.addKeyDownHandler(new KeyDownHandler()
            {
                public void onKeyDown(KeyDownEvent keyDownEvent)
                {
                    int nativeKeyCode = keyDownEvent.getNativeKeyCode();

                    // ...else if it's a key we want action to be taken on...
                   if (nativeKeyCode == KeyCodes.KEY_BACKSPACE ||
                       nativeKeyCode == KeyCodes.KEY_HOME ||
                       nativeKeyCode == KeyCodes.KEY_END ||
                       nativeKeyCode == KeyCodes.KEY_LEFT ||
                       nativeKeyCode == KeyCodes.KEY_RIGHT ||
                       nativeKeyCode == KeyCodes.KEY_ENTER ||
                       nativeKeyCode == KeyCodes.KEY_DELETE)
                    {
                        theCurrentCharacterIsAControlCharacterThatWeWantToReactTo = true;
                    }
                    else
                    {
                        theCurrentCharacterIsAControlCharacterThatWeWantToReactTo = false;
                    }
                }
            });

        textBox.addKeyPressHandler(new KeyPressHandler()
            {
                public void onKeyPress(KeyPressEvent keyPressEvent)
                {
                    if (theCurrentCharacterIsAControlCharacterThatWeWantToReactTo)
                    {
                        return;
                    }

                    char character = keyPressEvent.getCharCode();

                    // If it's a number...
                    if (Character.isDigit(character))
                    {
                        // If it has a leading zero...
                        if (character == '0' && textBox.getText().length() == 0)
                        {
                           // ...we can assume that's not what the user intended.
                            keyPressEvent.preventDefault();
                        }
                        else
                        {
                            int maxNumberOfCharactersInAnswer;

                            if (mathFact instanceof SubtractionMathFact ||
                                mathFact instanceof DivisionMathFact)
                            {
                                maxNumberOfCharactersInAnswer = 1;
                            }
                            else
                            {
                                maxNumberOfCharactersInAnswer = 2;
                            }

                            // If we're already at the maximum number of digits...
                            if (textBox.getValue().length() == maxNumberOfCharactersInAnswer)
                            {
                                // ...then don't add any more
                                keyPressEvent.preventDefault();
                            }
                            else
                            {
                                // ...let it through
                            }
                        }
                    }
                    else
                    {
                        keyPressEvent.preventDefault();
                    }
                }
            });
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
                                MathFact mathFact = (MathFact) getTestableItem();

                                Label correctAnswer;

                                // If we have "Peter math" enabled and this is addition, then we
                                // play a little trick on the user.
                                if ((mathFact instanceof AdditionMathFact) && SuperSpell.isUsingPeterMath())
                                {
                                    correctAnswer = new Label(getPeterMathAnswer(mathFact));
                                }
                                else
                                {
                                    correctAnswer = new Label(getCorrectAnswer());
                                }                               
                                correctAnswer.addStyleName("bold leftMargin10 fontSize150 blink");
                                horizontalPanel.add(correctAnswer);

                                if (answerResultType == AnswerResultType.INCORRECT)
                                {
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


    public boolean wasAnswerRight()
    {
        MathFact mathFact = (MathFact) getTestableItem();

        // If we have "Peter math" enabled and this is addition, then we play a little trick on the
        // user.
        if (SuperSpell.isUsingPeterMath() && (mathFact instanceof AdditionMathFact))
        {
            return false;
        }

        return getUserAnswer().equals(mathFact.getCorrectAnswer());
    }

    private String getPeterMathAnswer(MathFact mathFact)
    {
        String correctAnswer = mathFact.getCorrectAnswer();

        if (getUserAnswer().equals(correctAnswer))
        {
            return mathFact.getPrimaryOperand() + "" + mathFact.getSecondaryOperand();
        }
        else
        {
            return correctAnswer;
        }
    }
}