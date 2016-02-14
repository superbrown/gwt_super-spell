package com.superbrown.superSpell.gwtApp.server.spelling.spellingLists;

import com.superbrown.superSpell.gwtApp.shared.spelling.SpellingWord;
import com.superbrown.superSpell.gwtApp.shared.spelling.VocabularySpellingWord;

import java.util.Set;

/**
 */
public class VocabularySpellingListFactory extends SpellingListFactory
{
    public static void main(String[] args)
    {
        VocabularySpellingListFactory spellingListFactory = new VocabularySpellingListFactory();
        spellingListFactory.createSpellingList("grade6/Unit01.txt");
    }


    protected SpellingWord createSpellingWordObject(String sampleSentence, String phoneticSpelling, Set<String> incorrectSpellings)
    {
        return new VocabularySpellingWord(
                        sampleSentence,
                        phoneticSpelling,
                        incorrectSpellings);
    }
}
