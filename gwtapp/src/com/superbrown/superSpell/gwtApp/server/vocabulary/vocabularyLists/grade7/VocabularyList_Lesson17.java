package com.superbrown.superSpell.gwtApp.server.vocabulary.vocabularyLists.grade7;


import com.superbrown.superSpell.gwtApp.shared.vocabulary.VocabularyList_ver2;

import java.util.ArrayList;
import java.util.List;

public class VocabularyList_Lesson17 extends VocabularyList_ver2
{
    public VocabularyList_Lesson17()
    {
    }

    public VocabularyList_Lesson17(String name)
    {
        super(name);

        List<String> words = getWords();
        addVocabularyWordsToInventory(words);
    }

    public List<String> getWords()
    {
        List<String> words = new ArrayList<String>();
        
        words.add("|annihilate| v. to destroy completely; to vanquish; to make ineffectual or unimportant; to kill");
        words.add("|concession| n. an act of granting or yielding; something granted or yielded; a privilege, such as the right to use land, ranted for a specific purpose");
        words.add("|decimate| v. to destroy or kill a large part of");
        words.add("|diversion| n. a turning aside, as of traffic or attention; a game or pastime; a recreation");
        words.add("|evade| v. to escape or avoid something. especially by using deceit or cleverness; to avoid doing or answering something directly");
        words.add("|flagrant| adj. obviously bad; notorious; outrageous");
        words.add("|insolence| n. quality or instance of being disrespectful and insulting; impudence");
        words.add("|moderation| n. state of being restrained. avoidance of excesses; presiding over, as a meeting; mediation");
        words.add("|prone| adj. having a tendency, inclined; lying flat, face down");
        words.add("|purge| v. to remove undesirable elements from; to rid; to remove by cleansing; n. the removal of members by a higher authority");

        return words;
    }
}
