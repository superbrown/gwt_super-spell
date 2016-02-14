package com.superbrown.superSpell.gwtApp.shared.vocabulary.vocabularyLists.grade7;


import com.superbrown.superSpell.gwtApp.shared.vocabulary.VocabularyList_ver2;

import java.util.ArrayList;
import java.util.List;

public class VocabularyList_Lesson09 extends VocabularyList_ver2
{
    public VocabularyList_Lesson09()
    {
    }

    public VocabularyList_Lesson09(String optionalTitle)
    {
        super(optionalTitle);

        List<String> words = getWords();
        addVocabularyWordsToInventory(words);
    }

    public List<String> getWords()
    {
        List<String> words = new ArrayList<String>();
        
        words.add("|axiom| n. a statement assumed to be true; a self-evident truth; an established principle");
        words.add("|compatible| adj. able to get along; agreeing; in agreement with; harmonious");
        words.add("|compliance| n. the act of adapting to or giving in; a tendency to yield; conformity with official requirements");
        words.add("|inanimate| adj. dull; spiritless; lifeless");
        words.add("|indestructible| adj. not capable of being destroyed");
        words.add("|innate| adj. inborn; natural; inherent");
        words.add("|mutable| adj. capable of change; inconstant");
        words.add("|perception| n. an observation, a concept, or an awareness gained by use of the senses; an understanding or impression of something; appreciation; discernment");
        words.add("|prevalent| adj. widespread; in general use; having general acceptance");
        words.add("|recourse| n. a person or thing looked to for help, safety, and so on; a turning for help, safety, and the like, to a person or thing");
        
        return words;
    }
}
