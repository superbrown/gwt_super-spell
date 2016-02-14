package com.superbrown.superSpell.gwtApp.client.vocabulary;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.KeyPressEvent;
import com.google.gwt.event.dom.client.KeyPressHandler;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.superbrown.superSpell.gwtApp.client.ICompletedListener;
import com.superbrown.superSpell.gwtApp.client.Settings;
import com.superbrown.superSpell.gwtApp.client.SuperSpell;
import com.superbrown.superSpell.gwtApp.client.common.*;
import com.superbrown.superSpell.gwtApp.client.common.TestQuestionPanel;
import com.superbrown.superSpell.gwtApp.client.mathFacts.UserHasTimedOutEvent;
import com.superbrown.superSpell.gwtApp.client.util.Shuffler;
import com.superbrown.superSpell.gwtApp.client.util.UiUtilities;
import com.superbrown.superSpell.gwtApp.shared.ITestableItem;
import com.superbrown.superSpell.gwtApp.shared.TestableItem;

import java.util.List;

public class TestAdministratorPanel extends VerticalPanel
{
    private IResetable caller;

    private final List<ITestableItem> testableItems;
    private int numberOfQuestionsAsked = 0;

    public TestAdministratorPanel(IResetable caller, List<ITestableItem> testableItems)
    {
        this.caller = caller;
        this.testableItems = testableItems;

        shuffleItemsAndAskQuestion();
    }

    private void shuffleItemsAndAskQuestion()
    {
        askQuestion(0, Shuffler.shuffle(testableItems));
    }

    private void askQuestion(final int indexOfWord, final List<TestableItem> testableItems)
    {
        clear();

        if (numberOfQuestionsAsked % Settings.NUMBER_OF_ACTIONS_BETWEEN_DOODLE_MESSAGE_RESETS == 0)
        {
            SuperSpell.addAnotherPersonToStayAfterSchoolList();
            SuperSpell.resetDoodleMessage();
        }
        numberOfQuestionsAsked++;
        
        if (testableItems.size() == 0)
        {
            return;
        }

        final TestableItem testableItem = testableItems.get(indexOfWord);


        IPanelInitializationListener panelInitializationListener =
        new IPanelInitializationListener()
        {
            @Override
            public void initializationCallback(final TestQuestionPanel testQuestionPanel)
            {
                testQuestionPanel.setButtonClickHandler(new ClickHandler()
                    {
                        public void onClick(ClickEvent event)
                        {
                            boolean userHasTimedOut = false;

                            if (event instanceof UserHasTimedOutEvent)
                            {
                                userHasTimedOut = true;
                            }

                            handleUserSubmission(testQuestionPanel, testableItems, testableItem, indexOfWord, userHasTimedOut);
                        }
                    });

                // add an event handler for when the user his the Enter key
                testQuestionPanel.setKeyPressHandler(
                        new KeyPressHandler()
                        {
                            public void onKeyPress(KeyPressEvent keyPressEvent)
                            {
                                if (UiUtilities.isTheEnterKey(keyPressEvent))
                                {
                                    testQuestionPanel.getButton().click();
                                    // This is here because we were having a sound element play when
                                    // the user hit the enter key.  
                                    keyPressEvent.preventDefault();
                                }
                            }
                        }
                    );

                add(testQuestionPanel);
                testQuestionPanel.setToDefaultFocus();
            }
        };

        // the callback will attach it
        TestQuestionPanelFactory.createTestQuestionPanel(
                    panelInitializationListener, testableItem,
                    testableItem.getTestingMetric().getCurrentTestQuestionPanelType());
    }

    private void handleUserSubmission(
            final TestQuestionPanel testQuestionPanel,
            final List<TestableItem> itemsToTest,
            final TestableItem testableItem,
            final int indexOfItem,
            boolean userHasTimedOut)
    {
        // If the user didn't input anything, interpret it as a user mistake and forgive him.
        if (!userHasTimedOut && !testQuestionPanel.hasQuestionBeenAnswered())
        {
            return;
        }

//        if (!userHasTimedOut)
//        {
//            countdownTimer.stop();
//        }

        testQuestionPanel.getButton().setEnabled(false);

        AnswerResultType answerResultType =
                testQuestionPanel.determineAnswerResultType(userHasTimedOut);

        testQuestionPanel.displayResult(
                answerResultType,
                new ICompletedListener()
                {
                    public void completed()
                    {
                        setUpForNextQuestion(
                                testQuestionPanel,
                                itemsToTest, 
                                testableItem,
                                indexOfItem);
                    }
                });

    }

    private void setUpForNextQuestion(TestQuestionPanel testQuestionPanel, List<TestableItem> itmesToTest, TestableItem spellingWord, int indexOfWord)
    {
        for (ITestableItem testableItem : testableItems)
        {
            if (testableItem.getCorrectAnswer().equals(spellingWord.getCorrectAnswer()))
            {
                int i = testableItems.indexOf(testableItem);
                testableItems.remove(i);
                testableItems.add(i, spellingWord);
                break;
            }
        }

        int indexOfNextWord;

        if (spellingWord.getTestingMetric().isTestingComplete())
        {
            itmesToTest.remove(indexOfWord);

            if (itmesToTest.size() == 0)
            {
                setButtonToNextAndWaitForUserInput(testQuestionPanel, 0, itmesToTest);
            }

            indexOfNextWord = indexOfWord;
        }
        else
        {
            indexOfNextWord = indexOfWord + 1;
        }

        if (indexOfNextWord == itmesToTest.size())
        {
            // start back at the beginning
            indexOfNextWord = 0;

            final int finalIndexOfNextWord = indexOfNextWord;

            shuffleWords(testQuestionPanel, itmesToTest, finalIndexOfNextWord);
        }
        else
        {
            setButtonToNextAndWaitForUserInput(testQuestionPanel, indexOfNextWord, itmesToTest);
        }
    }

    private void shuffleWords(
            TestQuestionPanel testQuestionPanel,
            List<TestableItem> spellingWordsToTest,
            final int finalIndexOfNextWord1)
    {
        setButtonToNextAndWaitForUserInput(
                testQuestionPanel,
                finalIndexOfNextWord1,
                Shuffler.shuffle(spellingWordsToTest));
    }

    protected void setButtonToNextAndWaitForUserInput(
            TestQuestionPanel testQuestionPanel,
            final int indexOfNextWord,
            final List<TestableItem> spellingWordsToTest)
    {
        Button button = new Button("Next");

        button.addClickHandler(new ClickHandler()
            {
                public void onClick(ClickEvent clickEvent)
                {
                    if (spellingWordsToTest.size() == 0)
                    {
                        caller.reset();
                        return;
                    }
                    askQuestion(indexOfNextWord, spellingWordsToTest);
                }
            });

        testQuestionPanel.swapOutButton(button);
        button.setFocus(true);
    }
}
