package com.superbrown.superSpell.gwtApp.client.common;

import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.KeyPressHandler;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;
import com.superbrown.superSpell.gwtApp.client.ICompletedListener;
import com.superbrown.superSpell.gwtApp.client.SuperSpell;
import com.superbrown.superSpell.gwtApp.client.mathFacts.UserHasTimedOutEvent;
import com.superbrown.superSpell.gwtApp.client.util.RandomList;
import com.superbrown.superSpell.gwtApp.shared.TestableItem;

/**
 */
public abstract class TestQuestionPanel extends VerticalPanel
{
    protected TestableItem testableItem;
    protected Button button;
    protected KeyPressHandler keyPressHandler;
    protected ClickHandler buttonClickHandler;
    protected IPanelInitializationListener panelInitializationListener;


    private static RandomList gotTheAnswerCorrectMessages = new RandomList(new String[]
        {
            "Yes!",
            "Great job!",
            "Terrific!",
            "Cool!",
            "Excellent!",
            "Rock 'n' Roll!",
            "F.A.B.!",
            "Exactly!",
            "Way to go!",
            "Very good!",
            "Yey!",
            "You rock!",
            "Woo-whoo!"
        });

    protected static RandomList gotTheAnswerIncorrectMessages = new RandomList(new String[]
        {
            "No.",
            "Incorrect.",
            "You knucklehead!",
            "You goof-ball!",
            "Really??",
            "You think so??",
            "I thought you knew this!",
            "Whoops!",
            "=o("
        });

    public TestQuestionPanel(TestableItem testableItem,
                             IPanelInitializationListener panelInitializationListener)
    {
        this.testableItem = testableItem;
        this.panelInitializationListener = panelInitializationListener;
        addQuestionToPanel();
    }

    public abstract void addQuestionToPanel();

    public TestableItem getTestableItem()
    {
        return testableItem;
    }

    public boolean wasAnswerRight()
    {
        return getUserAnswer().equals(getCorrectAnswer());
    }

    public String getCorrectAnswer()
    {
        return testableItem.getCorrectAnswer();
    }

    protected abstract String getUserAnswer();

    public Button getButton()
    {
        return button;
    }

    public void setButton(Button button)
    {
        this.button = button;

        if (buttonClickHandler != null)
        {
            button.addClickHandler(buttonClickHandler);
        }
    }

    public void setButtonClickHandler(ClickHandler buttonClickHandler)
    {
        this.buttonClickHandler = buttonClickHandler;

        if (button != null)
        {
            button.addClickHandler(buttonClickHandler);
        }
    }

    public void swapOutButton(Button newButton)
    {
        VerticalPanel parent = (VerticalPanel)this.button.getParent();
        button.removeFromParent();
        parent.add(newButton);
    }

    public void setKeyPressHandler(KeyPressHandler keyPressHandler)
    {
        this.keyPressHandler = keyPressHandler;
    }

    protected Widget createSubmitAnswerButton()
    {
        setButton(new Button("Submit Answer"));
        VerticalPanel buttonPanel = new VerticalPanel();
        buttonPanel.add(getButton());
        buttonPanel.addStyleName("topMargin10");
        return buttonPanel;
    }

    public KeyPressHandler getKeyPressHandler()
    {
        return keyPressHandler;
    }

    public abstract void setToDefaultFocus();

    protected abstract Widget createTestQuestionWidget();

    public abstract boolean hasQuestionBeenAnswered();

    protected String getARandomGotTheAnswerCorrectMessage()
    {
        return (String) gotTheAnswerCorrectMessages.getNextElement();
    }

    protected String getARandomGotTheAnswerIncorrectMessage()
    {
        return (String) gotTheAnswerIncorrectMessages.getNextElement();
    }

    public AnswerResultType determineAnswerResultType(boolean userHasTimedOut)
    {
        AnswerResultType answerResultType;

        if (userHasTimedOut)
        {
            answerResultType = AnswerResultType.USER_RAN_OUT_OF_TIME;
        }
        else if (wasAnswerRight())
        {
            answerResultType = AnswerResultType.CORRECT;
        }
        else
        {
            answerResultType = AnswerResultType.INCORRECT;
        }

        return answerResultType;
    }

    public void displayResult(
            AnswerResultType answerResultType, ICompletedListener completedListener)
    {
        if (answerResultType == AnswerResultType.CORRECT)
        {
            testableItem.getTestingMetric().incrementSuccessCounter();
        }
        else if (answerResultType == AnswerResultType.INCORRECT ||
                 answerResultType == AnswerResultType.USER_RAN_OUT_OF_TIME)
        {
            testableItem.getTestingMetric().incrementFailureCounter();
        }

        if (answerResultType == AnswerResultType.CORRECT)
        {
            SuperSpell.getActiveSoundPalette().playSuccessSound();
            completedListener.completed();
        }
        else if (answerResultType == AnswerResultType.INCORRECT)
        {
            SuperSpell.flashColorsOnScreen(completedListener);
            SuperSpell.getActiveSoundPalette().playFailureSound();
        }
        else if (answerResultType == AnswerResultType.USER_RAN_OUT_OF_TIME)
        {
            SuperSpell.flashColorsOnScreen(completedListener);
            SuperSpell.getActiveSoundPalette().playFailureSound();
        }
    }

    public void userHasTimedOut()
    {
        buttonClickHandler.onClick(new UserHasTimedOutEvent());
    }
}
