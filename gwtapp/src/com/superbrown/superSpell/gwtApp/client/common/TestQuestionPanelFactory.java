package com.superbrown.superSpell.gwtApp.client.common;

import com.google.gwt.user.client.rpc.IsSerializable;
import com.superbrown.superSpell.gwtApp.shared.TestableItem;
import com.superbrown.superSpell.gwtApp.shared.mathFacts.MathFact;
import com.superbrown.superSpell.gwtApp.shared.spelling.QuestionType;
import com.superbrown.superSpell.gwtApp.shared.spelling.SpellingWord;
import com.superbrown.superSpell.gwtApp.shared.spelling.VocabularySpellingWord;
import com.superbrown.superSpell.gwtApp.shared.vocabulary.VocabularyWord;

/**
 */
public class TestQuestionPanelFactory implements IsSerializable
{
    public static TestQuestionPanel createTestQuestionPanel(
            IPanelInitializationListener panelInitializationListener,
            TestableItem testableItem,
            QuestionType currentTestQuestionPanelType)
    {
        if (testableItem.getClass().getName().equals(SpellingWord.class.getName()))
        {
            if (currentTestQuestionPanelType == QuestionType.OPEN_ENDED)
            {
                TestQuestionPanel testQuestionPanel;
                testQuestionPanel = new com.superbrown.superSpell.gwtApp.client.spelling.OpenEndedTestQuestionPanel((SpellingWord)testableItem, panelInitializationListener);
                return testQuestionPanel;
            }
            else if (currentTestQuestionPanelType == QuestionType.MULTIPLE_CHOICE)
            {
                TestQuestionPanel testQuestionPanel;
                testQuestionPanel = new com.superbrown.superSpell.gwtApp.client.spelling.MultipleChoiceTestQuestionPanel((SpellingWord)testableItem, panelInitializationListener);
                return testQuestionPanel;
            }

        }
        else if (testableItem.getClass().getName().equals(VocabularySpellingWord.class.getName()))
        {
            if (currentTestQuestionPanelType == QuestionType.OPEN_ENDED)
            {
                TestQuestionPanel testQuestionPanel;
                testQuestionPanel = new com.superbrown.superSpell.gwtApp.client.spelling.vocabularyWord.OpenEndedTestQuestionPanel((SpellingWord)testableItem, panelInitializationListener);
                return testQuestionPanel;
            }
            else if (currentTestQuestionPanelType == QuestionType.MULTIPLE_CHOICE)
            {
                TestQuestionPanel testQuestionPanel;
                testQuestionPanel = new com.superbrown.superSpell.gwtApp.client.spelling.vocabularyWord.MultipleChoiceTestQuestionPanel((SpellingWord)testableItem, panelInitializationListener);
                return testQuestionPanel;
            }

        }
        else if (testableItem instanceof VocabularyWord)
        {
//            if (currentTestQuestionPanelType == QuestionType.OPEN_ENDED)
//            {
//                TestQuestionPanel testQuestionPanel;
//                testQuestionPanel = new com.superbrown.superSpell.gwtApp.client.vocabulary.OpenEndedTestQuestionPanel((VocabularyWord)testableItem, panelInitializationListener);
//                return testQuestionPanel;
//            }
//            else if (currentTestQuestionPanelType == QuestionType.MULTIPLE_CHOICE)
            if (currentTestQuestionPanelType == QuestionType.MULTIPLE_CHOICE)
            {
                TestQuestionPanel testQuestionPanel;
                testQuestionPanel = new com.superbrown.superSpell.gwtApp.client.vocabulary.MultipleChoiceTestQuestionPanel((VocabularyWord)testableItem, panelInitializationListener);
                return testQuestionPanel;
            }
        }

        else if (testableItem instanceof MathFact)
        {
            if (currentTestQuestionPanelType == QuestionType.OPEN_ENDED)
            {
                TestQuestionPanel testQuestionPanel;
                testQuestionPanel = new com.superbrown.superSpell.gwtApp.client.mathFacts.OpenEndedTestQuestionPanel((MathFact)testableItem, panelInitializationListener);
                return testQuestionPanel;
            }
//            else if (currentTestQuestionPanelType == QuestionType.MULTIPLE_CHOICE)
//            {
//                TestQuestionPanel testQuestionPanel;
//                testQuestionPanel = new com.superbrown.superSpell.gwtApp.client.mathFacts.MultipleChoiceTestQuestionPanel((MathFact)testableItem, panelInitializationListener);
//                return testQuestionPanel;
//            }
        }

        throw new Error();
    }
}
