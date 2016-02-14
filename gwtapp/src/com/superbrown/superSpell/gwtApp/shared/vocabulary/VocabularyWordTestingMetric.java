package com.superbrown.superSpell.gwtApp.shared.vocabulary;

import com.superbrown.superSpell.gwtApp.shared.TestingMetric;
import com.superbrown.superSpell.gwtApp.shared.spelling.QuestionType;


public class VocabularyWordTestingMetric extends TestingMetric
{
    public VocabularyWordTestingMetric()
    {
        super();
    }

    protected void init()
    {
        super.init();
        setNumberOfRequiredSuccesses(2);
        setCurrentTestQuestionPanelType(QuestionType.MULTIPLE_CHOICE);
    }

    public void incrementSuccessCounter()
    {
        successCount++;

        if (successCount == getNumberOfRequiredSuccesses())
        {
            setTestingComplete(true);
        }
    }

    public void incrementFailureCounter()
    {
        failureCount++;
    }
}