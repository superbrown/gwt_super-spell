package com.superbrown.superSpell.gwtApp.server.vocabulary.vocabularyLists.grade7;


import com.superbrown.superSpell.gwtApp.shared.vocabulary.VocabularyList_ver2;

import java.util.ArrayList;
import java.util.List;

public class VocabularyList_Lesson16Thru18Review extends VocabularyList_ver2
{
    public VocabularyList_Lesson16Thru18Review()
    {
    }

    public VocabularyList_Lesson16Thru18Review(String name)
    {
        super(name);

        List<String> words = getWords();
        addVocabularyWordsToInventory(words);
   }

    public List<String> getWords()
    {
        List<String> words = new ArrayList<String>();

        words.addAll(new VocabularyList_Lesson16().getWords());
        words.addAll(new VocabularyList_Lesson17().getWords());
        words.addAll(new VocabularyList_Lesson18().getWords());

        return words;
    }
}
