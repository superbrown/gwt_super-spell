package com.superbrown.superSpell.gwtApp.shared.vocabulary;

import com.superbrown.superSpell.gwtApp.shared.TestingMetric;
import com.superbrown.superSpell.gwtApp.shared.TestableItem;
import com.superbrown.superSpell.gwtApp.shared.ThingWithPoolOfIncorrectAnswers;

import java.util.HashSet;
import java.util.Set;

public class VocabularyWord extends TestableItem implements ThingWithPoolOfIncorrectAnswers
{
    private String definition;
    private IVocabularyList IVocabularyList;
    private String testQuestion;
    private boolean isAProperNoun;

    public VocabularyWord()
    {
        super(new VocabularyWordTestingMetric());
    }

    @Override
    public Set<String> getIncorrectAnswers()
    {
        Set<String> incorrectAnswers =
                new HashSet<String>(IVocabularyList.getAllWords());
        incorrectAnswers.remove(correctAnswer);
        return incorrectAnswers;
    }

    public VocabularyWord(String definition, IVocabularyList IVocabularyList, boolean isAProperNoun)
    {
        this();

        this.definition = definition;
        this.IVocabularyList = IVocabularyList;
        this.isAProperNoun = isAProperNoun;

        int indexOfFirstBar = definition.indexOf("|");
        int indexOfSecondBar = definition.indexOf("|", indexOfFirstBar + 1);

        String textBetweenBars = definition.substring(indexOfFirstBar + 1, indexOfSecondBar);
        this.testQuestion = definition.replace("|" + textBetweenBars + "| ", "");

        if (isAProperNoun)
        {
            this.correctAnswer = capitalize(textBetweenBars);
        }
        else
        {
            this.correctAnswer = textBetweenBars.toLowerCase();
        }

    }

    private String capitalize(String string)
    {
        return Character.toUpperCase(string.charAt(0)) + string.substring(1);
    }

    private boolean startsWithAnUppercaseCharacter(String correctSpelling)
    {
        return Character.isUpperCase(correctSpelling.charAt(0));
    }

    public Set<String> getAllWords()
    {
        return IVocabularyList.getAllWords();
    }

    public String getDefinition()
    {
        return definition;
    }

    public void setDefinition(String definition)
    {
        this.definition = definition;
    }

    public TestingMetric getTestMetric()
    {
        return testingMetric;
    }

    public void setTestMetric(TestingMetric testingMetric)
    {
        this.testingMetric = testingMetric;
    }

    public String getTestQuestion()
    {
        return testQuestion;
    }

    public boolean isAProperNoun()
    {
        return isAProperNoun;
    }

    public com.superbrown.superSpell.gwtApp.shared.vocabulary.IVocabularyList getIVocabularyList() { return IVocabularyList; }

    public void setIVocabularyList(com.superbrown.superSpell.gwtApp.shared.vocabulary.IVocabularyList IVocabularyList) { this.IVocabularyList = IVocabularyList; }

    public void setTestQuestion(String testQuestion) { this.testQuestion = testQuestion; }

    public void setAProperNoun(boolean AProperNoun) { isAProperNoun = AProperNoun; }

    public boolean wordIsAtTheBeginningOfTheTestQuestion() { return this.getTestQuestion().startsWith("__"); }
}
