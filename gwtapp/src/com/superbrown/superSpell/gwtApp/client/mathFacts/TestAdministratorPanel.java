package com.superbrown.superSpell.gwtApp.client.mathFacts;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.KeyPressEvent;
import com.google.gwt.event.dom.client.KeyPressHandler;
import com.google.gwt.user.client.Timer;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.superbrown.superSpell.gwtApp.client.ICompletedListener;
import com.superbrown.superSpell.gwtApp.client.Settings;
import com.superbrown.superSpell.gwtApp.client.SuperSpell;
import com.superbrown.superSpell.gwtApp.client.common.*;
import com.superbrown.superSpell.gwtApp.client.common.TestQuestionPanel;
import com.superbrown.superSpell.gwtApp.client.common.timer.CountdownTimer;
import com.superbrown.superSpell.gwtApp.client.common.timer.ICountdownTimerListener;
import com.superbrown.superSpell.gwtApp.client.util.Shuffler;
import com.superbrown.superSpell.gwtApp.client.util.UiUtilities;
import com.superbrown.superSpell.gwtApp.shared.ITestableItem;
import com.superbrown.superSpell.gwtApp.shared.TestableItem;
import com.superbrown.superSpell.gwtApp.shared.mathFacts.MathFact;
import com.superbrown.superSpell.gwtApp.shared.spelling.QuestionType;

import java.util.ArrayList;
import java.util.List;

public class TestAdministratorPanel extends VerticalPanel
{
    private IResetable caller;

    private final List<ITestableItem> testableItems;
    private int numberOfQuestionsAsked = 0;
    private boolean firstQuestion = true;


    public TestAdministratorPanel(IResetable caller, List<ITestableItem> testableItems)
    {
        this.caller = caller;
        this.testableItems = testableItems;
    }

    @Override
    protected void onAttach()
    {
        super.onAttach();
        shuffleItemsAndAskQuestion();
    }

    private void shuffleItemsAndAskQuestion()
    {
        askQuestion(0, Shuffler.shuffle(testableItems));
    }

    private void askQuestion(final int indexOfTestableItem, final List<TestableItem> testableItems)
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

        final TestableItem testableItem = testableItems.get(indexOfTestableItem);

        IPanelInitializationListener panelInitializationListener =
                getThingsToDoAfterPanelIsInitialized(indexOfTestableItem, testableItems, testableItem);

        // the callback will attach it
        TestQuestionPanelFactory.createTestQuestionPanel(
                panelInitializationListener,
                testableItem,
                testableItem.getTestingMetric().getCurrentTestQuestionPanelType());
    }

    private IPanelInitializationListener getThingsToDoAfterPanelIsInitialized(
            final int indexOfTestableItem,
            final List<TestableItem> testableItems,
            final TestableItem testableItem)
    {
        IPanelInitializationListener thingsToDoAfterPanelIsInitialized =
                new IPanelInitializationListener()
                {
                    @Override
                    public void initializationCallback(final TestQuestionPanel testQuestionPanel)
                    {
                        if (firstQuestion)
                        {
                            final CountdownTimer preTestCountdownTimer =
                                    new CountdownTimer(3, "topMargin10 whiteColor chalkFont fontSize600", false);

                            ICountdownTimerListener thingsToDoAfterThePreTestCountdown1 = new ICountdownTimerListener()
                            {
                                public void timerComplete()
                                {
                                    remove(preTestCountdownTimer);
                                    askQuestion(testQuestionPanel, testableItem, testableItems, indexOfTestableItem);
                                }
                            };

                            ICountdownTimerListener thingsToDoAfterThePreTestCountdown =
                                    thingsToDoAfterThePreTestCountdown1;

                            preTestCountdownTimer.setCountdownTimerListener(thingsToDoAfterThePreTestCountdown);

                            add(preTestCountdownTimer);
                            preTestCountdownTimer.start();

                            firstQuestion = false;
                        }
                        else
                        {
                            askQuestion(testQuestionPanel, testableItem, testableItems, indexOfTestableItem);
                        }
                    }
                };

        return thingsToDoAfterPanelIsInitialized;
    }

    private void askQuestion(
            final TestQuestionPanel testQuestionPanel,
            final TestableItem testableItem,
            final List<TestableItem> testableItems,
            final int indexOfWord)
    {
        int timeLimit = ((MathFact)testQuestionPanel.getTestableItem()).getTimeLimit();

        final CountdownTimer countdownTimer =
                new CountdownTimer(timeLimit, "topMargin10 whiteColor chalkFont fontSize150", false);
//                        final CountdownTimerButton countdownTimer = new CountdownTimerButton(7, testQuestionPanel.getButton());
        countdownTimer.setCountdownTimerListener(
                new ICountdownTimerListener()
                {
                    public void timerComplete()
                    {
                        testQuestionPanel.userHasTimedOut();
                    }
                });

        testQuestionPanel.setButtonClickHandler(new ClickHandler()
            {
                public void onClick(ClickEvent event)
                {
                    boolean userHasTimedOut;

                    if (event instanceof UserHasTimedOutEvent)
                    {
                        userHasTimedOut = true;
                    }
                    else
                    {
                        userHasTimedOut = false;
                    }

                    handleUserSubmission(
                            testableItem,
                            testQuestionPanel,
                            testableItems,
                            indexOfWord,
                            userHasTimedOut,
                            countdownTimer);
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
                        }
                    }
                }
            );

        testQuestionPanel.add(countdownTimer);

        add(testQuestionPanel);

        countdownTimer.start();

        testQuestionPanel.setToDefaultFocus();
    }

    private void displayTimeRanOutSplashScreen()
    {
        final HTML html = new HTML(
//                "<div id=\"timedOutSplash\"><img src=\"images/alarm-clock-ringing.gif\" /></div>");
                  "<div id=\"timedOutSplash\"><img src=\"images/flipping_alarm_clock.gif\" /></div>");

        Timer timer = new Timer()
        {
            public void run()
            {
                html.removeFromParent();
            }
        };
        timer.schedule(1500);

        add(html);
    }

    private void handleUserSubmission(
            final TestableItem testableItem,
            final TestQuestionPanel testQuestionPanel,
            final List<TestableItem> itemsToTest,
            final int indexOfItem,
            boolean userHasTimedOut,
            CountdownTimer countdownTimer)
    {
        // If the user didn't input anything, interpret it as a user mistake and forgive him.
        if (!userHasTimedOut && !testQuestionPanel.hasQuestionBeenAnswered())
        {
            return;
        }

        if (!userHasTimedOut)
        {
            countdownTimer.stop();
        }

        testQuestionPanel.getButton().setEnabled(false);

        final boolean wasAnOpenEndedQuestion =
                (testableItem.getTestingMetric().getCurrentTestQuestionPanelType() == QuestionType.OPEN_ENDED);

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
                                    indexOfItem,
                                    wasAnOpenEndedQuestion);
                        }
                    });
    }

    private void setUpForNextQuestion(TestQuestionPanel testQuestionPanel, List<TestableItem> itmesToTest, TestableItem mathFact, int indexOfMathFact, boolean wasAnOpenEndedQuestion)
    {
        int indexOfNextMathFace;

        if (mathFact.getTestingMetric().isTestingComplete())
        {
            itmesToTest.remove(indexOfMathFact);

            if (itmesToTest.size() == 0)
            {
                setButtonToNextAndWaitForUserInput(testQuestionPanel, 0, itmesToTest);
            }

            indexOfNextMathFace = indexOfMathFact;
        }
        else
        {
            if (mathFact.getTestingMetric().getCurrentTestQuestionPanelType() == QuestionType.OPEN_ENDED)
            {
                indexOfNextMathFace = indexOfMathFact + 1;
            }
            else
            {
                // if we just switched over to multiple choice,
                // go on to the next word
                if (wasAnOpenEndedQuestion)
                {
                    indexOfNextMathFace = indexOfMathFact + 1;
                }
                else
                {
                    // otherwise stay on this word
                    indexOfNextMathFace = indexOfMathFact;
                }
            }
        }

        if (indexOfNextMathFace == itmesToTest.size())
        {
            // start back at the beginning
            indexOfNextMathFace = 0;

            final int finalIndexOfNextWord = indexOfNextMathFace;

            List<String> answers = new ArrayList<String>();
            for (TestableItem testableItem : itmesToTest)
            {
                answers.add(testableItem.getCorrectAnswer());
            }

            shuffleMathFacts(testQuestionPanel, itmesToTest, finalIndexOfNextWord);
        }
        else
        {
            setButtonToNextAndWaitForUserInput(testQuestionPanel, indexOfNextMathFace, itmesToTest);
        }
    }

    private void shuffleMathFacts(
            final TestQuestionPanel testQuestionPanel,
            final List<TestableItem> itemsToTest,
            final int finalIndexOfNextWord)
    {
        setButtonToNextAndWaitForUserInput(
                testQuestionPanel,
                finalIndexOfNextWord,
                Shuffler.shuffle(itemsToTest));
    }

    protected void setButtonToNextAndWaitForUserInput(
            TestQuestionPanel testQuestionPanel,
            final int indexOfNextTestableItem,
            final List<TestableItem> itemsToTest)
    {
        Button button = new Button("Next");

        button.addClickHandler(new ClickHandler()
            {
                public void onClick(ClickEvent clickEvent)
                {
                    if (itemsToTest.size() == 0)
                    {
                        caller.reset();
                        return;
                    }
                    askQuestion(indexOfNextTestableItem, itemsToTest);
                }
            });

        testQuestionPanel.swapOutButton(button);
        button.setFocus(true);
    }
}
