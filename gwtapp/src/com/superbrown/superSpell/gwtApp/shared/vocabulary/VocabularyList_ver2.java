package com.superbrown.superSpell.gwtApp.shared.vocabulary;

import com.superbrown.superSpell.gwtApp.shared.ITestable;
import com.superbrown.superSpell.gwtApp.shared.ITestableItem;
import com.superbrown.superSpell.gwtApp.shared.TestableItemList;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 */
public abstract class VocabularyList_ver2 extends TestableItemList implements IVocabularyList
{
    public VocabularyList_ver2()
    {
        this("");
    }

    public VocabularyList_ver2(String optionalTitle)
    {
        super(optionalTitle);
    }

    protected abstract List<String> getWords();

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

    protected void addVocabularyWordsToInventory(List<String> words)
    {
        for (String word : words)
        {
            addVocabularyWordToInventory(word);
        }
    }
}