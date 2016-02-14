package com.superbrown.superSpell.gwtApp.shared.spelling;

import com.superbrown.superSpell.gwtApp.client.Settings;
import com.superbrown.superSpell.gwtApp.shared.ITestable;
import com.superbrown.superSpell.gwtApp.shared.TestableItemList;

import java.util.ArrayList;
import java.util.List;

/**
 */
public class SpellingList extends TestableItemList
{
    public SpellingList()
    {
        this("");
    }

    public SpellingList(String name)
    {
        super(name);
    }

//    protected void addSpellingWordToInventory(
//            String correctSpelling,
//            String phoneticSpelling,
//            String sampleSentence,
//            String[][] replacementDefinitions)
//    {
//        addSpellingWordToInventory(correctSpelling, phoneticSpelling, sampleSentence);
//    }


//    protected void addSpellingWordToInventory(
//            String correctSpelling,
//            String phoneticSpelling,
//            String sampleSentence)
//    {
//        SpellingWord spellingWord = new SpellingWord(
//                correctSpelling,
//                phoneticSpelling,
//                sampleSentence,
//                WordMisspeller2.generateMisspellings(new Word(phoneticSpelling, correctSpelling)));
//        getTestables().add(spellingWord);
//    }

    public void addSpellingWordToInventory(SpellingWord spellingWord)
    {
        getTestables().add(spellingWord);
    }

    public List<TestableItemList> getSpellingWordSets(Boolean breakUpIntoSets)
    {
        List<TestableItemList> spellingWordSets = new ArrayList<TestableItemList>();

        int count = 1;
        TestableItemList spellingWordSet = new TestableItemList();
        spellingWordSets.add(spellingWordSet);

        int maxNumberOfWordsInASet = Settings.MAX_NUMBER_OF_WORDS_IN_A_SET;

        for (ITestable spellingWord : getTestables())
        {
            spellingWordSet.getTestables().add(spellingWord);

            if (breakUpIntoSets)
            {
                if (((count % maxNumberOfWordsInASet) == 0) &&
                     (count <= (getTestables().size() - maxNumberOfWordsInASet + Settings.MIN_NUMBER_OF_WORDS_IN_A_SET)))
                {
                    spellingWordSet = new TestableItemList();
                    spellingWordSets.add(spellingWordSet);
                }
            }

            count++;
        }

        return spellingWordSets;
    }
}
