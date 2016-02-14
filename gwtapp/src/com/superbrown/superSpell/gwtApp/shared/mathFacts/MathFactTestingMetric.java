package com.superbrown.superSpell.gwtApp.shared.mathFacts;

import com.superbrown.superSpell.gwtApp.shared.TestingMetric;
import com.superbrown.superSpell.gwtApp.shared.spelling.QuestionType;


public class MathFactTestingMetric extends TestingMetric
{
    public MathFactTestingMetric()
    {
        super();
    }

    protected void init()
    {
        super.init();
        setCurrentTestQuestionPanelType(QuestionType.OPEN_ENDED);
        setNumberOfRequiredSuccesses(1);
    }

    public void incrementSuccessCounter()
    {
        successCount++;

        if (successCount == getNumberOfRequiredSuccesses())
        {
            if (getCurrentTestQuestionPanelType() == QuestionType.OPEN_ENDED)
            {
                setTestingComplete(true);

                if (failureCount == 0)
                {
                    setGotItOnTheFirstTry(true);
                }
            }
            else
            {
                setCurrentTestQuestionPanelType(QuestionType.OPEN_ENDED);
                successCount = 0;
            }
        }
    }

    public void incrementFailureCounter()
    {
        failureCount++;

        if (getCurrentTestQuestionPanelType() == QuestionType.OPEN_ENDED)
        {
//            setCurrentTestQuestionPanelType(QuestionType.MULTIPLE_CHOICE);
            setNumberOfRequiredSuccesses(5);
            successCount = 0;
        }
    }
}

