package com.superbrown.superSpell.gwtApp.shared.vocabulary.vocabularyLists.grade7;


import com.superbrown.superSpell.gwtApp.shared.vocabulary.VocabularyList_ver2;

import java.util.ArrayList;
import java.util.List;

public class VocabularyList_Lesson16 extends VocabularyList_ver2
{
    public VocabularyList_Lesson16()
    {
    }

    public VocabularyList_Lesson16(String optionalTitle)
    {
        super(optionalTitle);

        List<String> words = getWords();
        addVocabularyWordsToInventory(words);
    }

    public List<String> getWords()
    {
        List<String> words = new ArrayList<String>();
        
        words.add("|aptitude| n. a natural ability ; the ability to learn");
        words.add("|astute| adj. showing a clever or shrewd mind; crafty");
        words.add("|conducive| adj. helpful; tending to promote as a result; contributory");
        words.add("|erratic| adj. irregular; not steady; uncertain");
        words.add("|mosque| n. a Muslim house of worship");
        words.add("|pastoral| adj. relating to a simple country way of life; relating to shepherds; rural");
        words.add("|quantitative| ajf. capable of being measured or expressed as an amount");
        words.add("|recur| v. to return in thought or speech; to happen or appear again; to occur at intervals");
        words.add("|requisite| n. a necessity; a requirement; adj. absolutely necessary; required by circumstances");
        words.add("|zenith| n. the highest point of any course or path; the point in the sky directly above an observer");

        return words;
    }
}
