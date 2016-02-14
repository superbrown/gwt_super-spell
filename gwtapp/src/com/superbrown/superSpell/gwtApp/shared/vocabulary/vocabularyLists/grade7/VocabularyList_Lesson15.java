package com.superbrown.superSpell.gwtApp.shared.vocabulary.vocabularyLists.grade7;


import com.superbrown.superSpell.gwtApp.shared.vocabulary.VocabularyList_ver2;

import java.util.ArrayList;
import java.util.List;

public class VocabularyList_Lesson15 extends VocabularyList_ver2
{
    public VocabularyList_Lesson15()
    {
    }

    public VocabularyList_Lesson15(String optionalTitle)
    {
        super(optionalTitle);

        List<String> words = getWords();
        addVocabularyWordsToInventory(words);
    }

    public List<String> getWords()
    {
        List<String> words = new ArrayList<String>();
        
        words.add("|buffet| n. a piece of dining room furniture with cupboards and drawers; a meal set out so that people may serve themselves");
        words.add("|delectable| adj. very pleasing; delightful; delicious");
        words.add("|ensue| v. to come next, or follow; to happen as a consequence");
        words.add("|expedient| adj. suitable; advantageous; based on self-interest");
        words.add("|facilitate| v. to make easier or more convenient' to aid or assist in");
        words.add("|hors d'oeuvre| n. a food served either before a meal or with drinks");
        words.add("|lapse| n. to fall fro a higher level (as of manners) to a lower; to pass gradually; to come to an end; n. a slight, temporary error or slip; a fall from a better to a worse condition");
        words.add("|palatable| adj. acceptable to the mind; fit to be eaten; pleasant-tasting");
        words.add("|steppe| n. a vast, almost treeless plain in southeastern Europe or Asia");
        words.add("|succulent| adj. juicy; not dry or dull; having fleshy plant tissue that conserves moisture; n. a plant that has such tissues");

        return words;
    }
}
