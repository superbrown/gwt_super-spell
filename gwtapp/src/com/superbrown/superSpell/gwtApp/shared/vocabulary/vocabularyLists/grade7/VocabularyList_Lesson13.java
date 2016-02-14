package com.superbrown.superSpell.gwtApp.shared.vocabulary.vocabularyLists.grade7;


import com.superbrown.superSpell.gwtApp.shared.vocabulary.VocabularyList_ver2;

import java.util.ArrayList;
import java.util.List;

public class VocabularyList_Lesson13 extends VocabularyList_ver2
{
    public VocabularyList_Lesson13()
    {
    }

    public VocabularyList_Lesson13(String optionalTitle)
    {
        super(optionalTitle);

        List<String> words = getWords();
        addVocabularyWordsToInventory(words);
    }

    public List<String> getWords()
    {
        List<String> words = new ArrayList<String>();
        
        words.add("|coffer| n. a box or chest, especially one used to hold money or other valuables");
        words.add("|edifice| n. a building or structure, especially an imposing or elaborate one");
        words.add("|hieroglyphic| n. a picture or symbol representing a word, syllable, or sound, used by the ancient Egyptians and others, instead of alphabetical letters; a method of writing using these pictures or symbols; picture writing; adj. pertaining to a system of writing that uses pictures or symbols");
        words.add("|inaccessible| adj. not easily reached or approached; out of the way");
        words.add("|innovation| n. a new way to do something; something new");
        words.add("|juncture| n. a point where things are joined; a crossing; a point in time; a crisis point");
        words.add("|retainer| n. a fee paid in advance for services; a servant; an attendant");
        words.add("|rivulet| n. a small stream; a brook");
        words.add("|subsidize| v. to aid or assist with a grant of money; to support; to promote");
        words.add("|tawny| adj. brownish-yellow; tan");

        return words;
    }
}
