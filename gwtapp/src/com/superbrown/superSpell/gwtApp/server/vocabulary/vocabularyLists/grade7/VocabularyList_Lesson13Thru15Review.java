package com.superbrown.superSpell.gwtApp.server.vocabulary.vocabularyLists.grade7;


import com.superbrown.superSpell.gwtApp.shared.vocabulary.VocabularyList_ver2;

import java.util.ArrayList;
import java.util.List;

public class VocabularyList_Lesson13Thru15Review extends VocabularyList_ver2
{
    public VocabularyList_Lesson13Thru15Review()
    {
    }

    public VocabularyList_Lesson13Thru15Review(String name)
    {
        super(name);

        List<String> words = getWords();
        addVocabularyWordsToInventory(words);
   }

    public List<String> getWords()
    {
        List<String> words = new ArrayList<String>();

        words.addAll(new VocabularyList_Lesson13().getWords());
        words.addAll(new VocabularyList_Lesson14().getWords());
        words.addAll(new VocabularyList_Lesson15().getWords());

        return words;
    }
}
