package com.superbrown.superSpell.gwtApp.shared;

import com.google.gwt.user.client.rpc.IsSerializable;
import com.superbrown.superSpell.gwtApp.shared.spelling.QuestionType;

/**
 */
public abstract class TestingMetric implements IsSerializable
{
    protected int successCount;
    protected int failureCount;
    private int numberOfRequiredSuccesses;
    private boolean testingComplete = false;
    private boolean gotItOnTheFirstTry = false;
    private QuestionType currentTestQuestionPanelType;
    private TestableItem myTestableItem;

    public TestingMetric()
    {
        init();
    }

    protected void init()
    {
        successCount = 0;
        failureCount = 0;
        testingComplete = false;
        gotItOnTheFirstTry = false;
    }

    public void setNumberOfRequiredSuccesses(int numberOfRequiredSuccesses)
    {
        this.numberOfRequiredSuccesses = numberOfRequiredSuccesses;
    }

    public abstract void incrementSuccessCounter();

    public abstract void incrementFailureCounter();

    public boolean isTestingComplete()
    {
        return testingComplete;
    }

    public boolean isGotItOnTheFirstTry()
    {
        return gotItOnTheFirstTry;
    }

    public void setGotItOnTheFirstTry(boolean gotItOnTheFirstTry)
    {
        this.gotItOnTheFirstTry = gotItOnTheFirstTry;
    }

    public void setMyTestableItem(TestableItem myTestableItem)
    {
        this.myTestableItem = myTestableItem;
    }

    public void reset(boolean force)
    {
        if (this.gotItOnTheFirstTry && !force)
        {
            return;
        }

        init();
    }

    public QuestionType getCurrentTestQuestionPanelType()
    {
        return currentTestQuestionPanelType;
    }

    public void setCurrentTestQuestionPanelType(QuestionType currentTestQuestionPanelType)
    {
        this.currentTestQuestionPanelType = currentTestQuestionPanelType;
    }

    public void setTestingComplete(boolean testingComplete)
    {
        this.testingComplete = testingComplete;
    }

    public int getSuccessCount()
    {
        return successCount;
    }

    public int getFailureCount()
    {
        return failureCount;
    }

    public int getNumberOfRequiredSuccesses()
    {
        return numberOfRequiredSuccesses;
    }

    public TestableItem getMyTestableItem()
    {
        return myTestableItem;
    }
}
