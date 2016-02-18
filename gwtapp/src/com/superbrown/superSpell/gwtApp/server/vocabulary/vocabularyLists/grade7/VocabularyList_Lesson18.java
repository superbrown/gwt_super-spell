package com.superbrown.superSpell.gwtApp.server.vocabulary.vocabularyLists.grade7;


import com.superbrown.superSpell.gwtApp.shared.vocabulary.VocabularyList_ver2;

import java.util.ArrayList;
import java.util.List;

public class VocabularyList_Lesson18 extends VocabularyList_ver2
{
    public VocabularyList_Lesson18()
    {
    }

    public VocabularyList_Lesson18(String name)
    {
        super(name);

        List<String> words = getWords();
        addVocabularyWordsToInventory(words);
    }

    public List<String> getWords()
    {
        List<String> words = new ArrayList<String>();
        
        words.add("|clemency| n. a lenient or merciful act; mildness, as of weather");
        words.add("|dissent| v to object or oppose, not to approve; n. a difference of opinion, protest");
        words.add("|inhibition| n. the act of holding back; an inner belief, feeling, or force that checks one's actions, thoughts, or feelings");
        words.add("|mandatory| adj. required; obligatory");
        words.add("|mannerism| n. a distinctive personal trait; an idiosyncrasy; an exaggerated or unnatural style or habit. as in speech or dress");
        words.add("|meticulous| adj. extremely careful; overly precise or fussy about details");
        words.add("|pacifist| n. a person opposed to force as a way of settling disputes; one who refuses to bear arms in a war");
        words.add("|protocol| n. the code prescribing formal etiquette and precedence for official ceremonies and dealings, as with diplomats and the military; an original draft or record of a negotiation or document");
        words.add("|submission| n. obedience; humbleness");
        words.add("|ultimatum| n. a final demand or proposition");

        return words;
    }
}
