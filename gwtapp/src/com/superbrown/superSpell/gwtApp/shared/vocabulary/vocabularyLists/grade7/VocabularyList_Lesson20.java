package com.superbrown.superSpell.gwtApp.shared.vocabulary.vocabularyLists.grade7;

import com.superbrown.superSpell.gwtApp.shared.vocabulary.VocabularyList_ver2;

import java.util.ArrayList;
import java.util.List;

public class VocabularyList_Lesson20 extends VocabularyList_ver2
{
    public VocabularyList_Lesson20()
    {
    }

    public VocabularyList_Lesson20(String optionalTitle)
    {
        super(optionalTitle);

        List<String> words = getWords();
        addVocabularyWordsToInventory(words);
    }

    public List<String> getWords()
    {
        List<String> words = new ArrayList<String>();
        
        words.add("|bedlam| n. a place or condition of chaos or turmoil");
        words.add("|colloquial| adj. informal in language; conversational");
        words.add("|consolidate| v. to unite; to strengthen; to make solid or firmly established");
        words.add("|constituent| n. a necessary part or element; a voter represented by an elected official; adj. necessary in making up a whole; component");
        words.add("|curtail| v. to shorten; to decrease");
        words.add("|destitute| adj. poverty-stricken; penniless; being without");
        words.add("|emancipate| v. to set free; to release from slavery or oppression");
        words.add("|exultant| adj. rejoicing greatly; triumphant");
        words.add("|ornate| adj. elaborate; excessively adorned or ornamented");
        words.add("|prelude| n. anything serving as an introduction. such as an introductory section of a piece of music; an opening");

        return words;
    }
}
