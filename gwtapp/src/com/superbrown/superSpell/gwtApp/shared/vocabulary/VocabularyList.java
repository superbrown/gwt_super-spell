package com.superbrown.superSpell.gwtApp.shared.vocabulary;

import com.superbrown.superSpell.gwtApp.shared.ITestable;
import com.superbrown.superSpell.gwtApp.shared.ITestableItem;
import com.superbrown.superSpell.gwtApp.shared.TestableItemList;

import java.util.HashSet;
import java.util.Set;

/**
 */
public class VocabularyList extends TestableItemList implements IVocabularyList
{
    public VocabularyList()
    {
        this("");
    }

    public VocabularyList(IVocabularyList vocabularyList)
    {
        super(vocabularyList.getName());
        for (ITestable testable : vocabularyList.getTestables())
        {
            VocabularyWord vocabularyWord = (VocabularyWord) testable;
            vocabularyWord.setIVocabularyList(this);
            testables.add(vocabularyWord);
        }
    }

    public VocabularyList(String name)
    {
        super(name);
    }

    protected void addVocabularyWordToInventory(String sampleSentence, boolean isAProperNoun)
    {
        VocabularyWord vocabularyWord = new VocabularyWord(sampleSentence, this, isAProperNoun);
        testables.add(vocabularyWord);
    }

    protected void addVocabularyWordToInventory(String sampleSentence)
    {
        addVocabularyWordToInventory(sampleSentence, false);
    }

    public Set<String> getAllWords()
    {
        Set<String> allWords = new HashSet<String>();

        for (ITestable vocabularyWord : testables)
        {
            allWords.add(((ITestableItem)vocabularyWord).getCorrectAnswer());
        }

        return allWords;
    }
}
