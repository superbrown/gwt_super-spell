package com.superbrown.superSpell.gwtApp.server.vocabulary.vocabularyLists.grade7;


import com.superbrown.superSpell.gwtApp.shared.vocabulary.VocabularyList_ver2;

import java.util.ArrayList;
import java.util.List;

public class VocabularyList_Lesson14 extends VocabularyList_ver2
{
    public VocabularyList_Lesson14()
    {
    }

    public VocabularyList_Lesson14(String name)
    {
        super(name);

        List<String> words = getWords();
        addVocabularyWordsToInventory(words);
    }

    public List<String> getWords()
    {
        List<String> words = new ArrayList<String>();
        
        words.add("|apex| n. the highest point of something; vertex; the culmination or point of climax");
        words.add("|bourgeois| adj. of or like the middle class -- often referring to a focus upon material things and sepectability; n. a middle-class person; the middle class");
        words.add("|canine| n. any animal belonging to the dog family; adj. of or like a dog");
        words.add("|defunct| adj. no longer in existence or functioning; dead");
        words.add("|influx| n. a flowing in, as in people or things");
        words.add("|meager| adj. noticeably inadequate scanty; lean; thin");
        words.add("|obliterate|  v. to blot or wipe out completely; to erase, leaving no trace");
        words.add("|ossify| v. to change into bone; to harden like bone; to become rigid, hardhearted, or opposed to change");
        words.add("|perceive| v. to become aware of or to understand by means of the senses; to detect; to observe; to grasp the meaning of");
        words.add("|ravage| v. to inflict terrible destruction; to ruin violently; n. the act of destroying; great damage");

        return words;
    }
}
