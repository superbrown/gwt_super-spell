package com.superbrown.superSpell.gwtApp.server.vocabulary.vocabularyLists.grade7;

import com.superbrown.superSpell.gwtApp.shared.vocabulary.VocabularyList_ver2;

import java.util.ArrayList;
import java.util.List;


public class VocabularyList_Lesson19Thru21Review extends VocabularyList_ver2
{
    public VocabularyList_Lesson19Thru21Review()
    {
    }

    public VocabularyList_Lesson19Thru21Review(String name)
    {
        super(name);

        List<String> words = getWords();
        addVocabularyWordsToInventory(words);
   }

    public List<String> getWords()
    {
        List<String> words = new ArrayList<String>();

        words.addAll(new VocabularyList_Lesson19().getWords());
        words.addAll(new VocabularyList_Lesson20().getWords());
        words.addAll(new VocabularyList_Lesson21().getWords());

        return words;
    }
}
