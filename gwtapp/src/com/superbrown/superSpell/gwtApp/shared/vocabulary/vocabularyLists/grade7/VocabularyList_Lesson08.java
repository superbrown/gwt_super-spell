package com.superbrown.superSpell.gwtApp.shared.vocabulary.vocabularyLists.grade7;


import com.superbrown.superSpell.gwtApp.shared.vocabulary.VocabularyList_ver2;

import java.util.ArrayList;
import java.util.List;

public class VocabularyList_Lesson08 extends VocabularyList_ver2
{
    public VocabularyList_Lesson08()
    {
    }

    public VocabularyList_Lesson08(String optionalTitle)
    {
        super(optionalTitle);

        List<String> words = getWords();
        addVocabularyWordsToInventory(words);
   }

    public List<String> getWords()
    {
        List<String> words = new ArrayList<String>();

        words.add("|aura| n. a distinctive quality surrounding a person or thing; an invisible, enveloping glow");
        words.add("|fabricate| v. to build or manufacture; to make up or invent (as a story or an excuse)");
        words.add("|impediment| n. a speech disorder; an obstruction of some kind");
        words.add("|mediocre| adj. only average or ordinary; medium; neither bad nor good");
        words.add("|opportune| adj. right for the purpose; advantageously timed");
        words.add("|qualm| n. an uneasiness, misgiving, or doubt; a slight feeling of sickness or nausea");
        words.add("|reactionary| adj. advocating a return to an earlier social, political, or economic policy or condition; n. one who advocates a return to an earlier policy or condition");
        words.add("|stamina| n. vigor; strength; endurance");
        words.add("|zealous| adj. intensely devoted; eager; enthusiastic");
        words.add("|zephyr| n. a gentle wind; a mild breeze");
        
        return words;
    }
}
