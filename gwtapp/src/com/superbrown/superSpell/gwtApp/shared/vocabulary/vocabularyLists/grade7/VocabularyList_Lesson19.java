package com.superbrown.superSpell.gwtApp.shared.vocabulary.vocabularyLists.grade7;

import com.superbrown.superSpell.gwtApp.shared.vocabulary.VocabularyList_ver2;

import java.util.ArrayList;
import java.util.List;

public class VocabularyList_Lesson19 extends VocabularyList_ver2
{
    public VocabularyList_Lesson19()
    {
    }

    public VocabularyList_Lesson19(String optionalTitle)
    {
        super(optionalTitle);

        List<String> words = getWords();
        addVocabularyWordsToInventory(words);
    }

    public List<String> getWords()
    {
        List<String> words = new ArrayList<String>();
        
        words.add("|abound| v. to teem; to exist in large numbers; to have plenty");
        words.add("|admirably| adj. in a worthy manner; commendably; excellently");
        words.add("|affidavit| n. a written statement made under oath");
        words.add("|amnesty| n. a general pardon, especially for political prisoners");
        words.add("|bias| n. a line slanting diagonally across the weave of a fabric; a prejudice or personal tendency; v. to prejudice or sway");
        words.add("|censure| v. to criticize or blame; n. a condemning judgment; a reprimand");
        words.add("|diminutive| n. referring to suffices or altered word forms that designate smallness, affection, or familiarity; a very small person or thing; adj. tiny; of small size");
        words.add("|inalienable| adj. not capable of being given or withdrawn");
        words.add("|rift| v. to break open; to split; n. a split; a break in friendly relations");
        words.add("|timorous| adj. easily frightened; fearful; timid; cowardly");

        return words;
    }
}
