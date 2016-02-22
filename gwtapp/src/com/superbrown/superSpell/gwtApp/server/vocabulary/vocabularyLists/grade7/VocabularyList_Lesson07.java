package com.superbrown.superSpell.gwtApp.server.vocabulary.vocabularyLists.grade7;


import com.superbrown.superSpell.gwtApp.shared.vocabulary.VocabularyList_ver2;

import java.util.ArrayList;
import java.util.List;

public class VocabularyList_Lesson07 extends VocabularyList_ver2
{
    public VocabularyList_Lesson07()
    {
    }

    public VocabularyList_Lesson07(String name)
    {
        super(name);

        List<String> words = getWords();
        addVocabularyWordsToInventory(words);
   }

    public List<String> getWords()
    {
        List<String> words = new ArrayList<String>();

        words.add("|aesthetic| adj. artistic; having to do with art or beauty; sensitive to art and beauty");
        words.add("|charisma| n. a special quality of leadership or a special charm that captures the public imagination and inspires devotion");
        words.add("|cliche| n. an overused idea or expression; a trite saying");
        words.add("|conceive| v. to imagine or develop as an idea; to understand or grasp; to become pregnant with");
        words.add("|emphatically| adv. forcefully, assertively");
        words.add("|martial| adj. military; suggesting war; warlike");
        words.add("|paradox| n. a statement that may be true but that seems to say contradictory things");
        words.add("|prolific| adj. producing a great deal of something; producing many young or much fruit");
        words.add("|recipient| n. a person who receives something");
        words.add("|wan| adj. sickly pale or faint; colorless");
        
        return words;
    }
}
