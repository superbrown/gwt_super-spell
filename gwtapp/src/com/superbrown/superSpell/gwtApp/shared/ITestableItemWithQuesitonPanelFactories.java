package com.superbrown.superSpell.gwtApp.shared;

import com.superbrown.superSpell.gwtApp.client.common.TestQuestionPanel;

/**
 */
public interface ITestableItemWithQuesitonPanelFactories
{
    TestQuestionPanel createOpenEndedTestQuestionPanel();

    TestQuestionPanel createMultipleChoiceTestQuestionPanel();
}
