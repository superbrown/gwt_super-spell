package com.superbrown.superSpell.gwtApp.server.vocabulary.vocabularyLists.grade7;


import com.superbrown.superSpell.gwtApp.shared.vocabulary.VocabularyList_ver2;

import java.util.ArrayList;
import java.util.List;

public class VocabularyList_WordMaster02 extends VocabularyList_ver2
{
    public VocabularyList_WordMaster02()
    {
    }

    public VocabularyList_WordMaster02(String name)
    {
        super(name);

        List<String> words = getWords();
        addVocabularyWordsToInventory(words);
    }

    public List<String> getWords()
    {
        List<String> words = new ArrayList<String>();
        
        words.add("|suterfuge| n. a cedeption, a scheme, a ruse");
        words.add("|recoil| v. to draw or shrink back as if in fear or alarm; to spring back");
        words.add("|impervious| adj. incapable of bein penetrated, influenced, or injured");
        words.add("|chicanery| n. trickery or deception by quibbling");
        words.add("|blanch| v. to whiten by removing color; to bleach; to turn pale");
        words.add("|opaque| adj. dark or dull; impenetrable to light; not clear or lucid");
        words.add("|crescendo| n. a gradual increase in loudness or force; v. to grow in force");
        words.add("|muzzle| n. the discharge end of a gun; the head of an animal; a device to restrain an animal; v. to restrain an animal");
        words.add("|palpable| adj. easily perceived; tangible; capable of being felt or touched");
        words.add("|ambience| n. the atmosphere of a place");
        words.add("|vaunt| v. to boast or brag");
        words.add("|fitful| adj. adj. sporadic; recurring irregularly");
        words.add("|milieu| n. social or cultural background; surroundings; sphere");
        words.add("|acquiesce| v. to silently comply or agree without protest");
        words.add("|compliant| adj. yielding in a submissive way, obeying rules");
        words.add("|genesis| n. an origin, creation, or beginning");
        words.add("|slough| n. a muddy pond or mire, a condition of despair; to cast off or shed; to pass off a unimportant");
        words.add("|apprehensive| adj. uneasy that something bad might happen; perceptive");
        words.add("|rapport| n. relation, connection, fellowship, camaraderie");
        words.add("|fritter| n. a fragment, small piece, or shred; v. to waste little by little, to dwindle or shrink, to break into small pieces");
        words.add("|diaphanous| adj. very sheer and light; delicately hazy; translucent");
        words.add("|pallor| n. unusual or extreme paleness");
        words.add("|flush| n. a rosy flow, a rushing flow, glowing freshness; to to redden; v. to flood with water; to wash out");
        words.add("|nostalgic| adj. a sentimental or wistful yearning for a former place or time or situation");
        words.add("|pinnacle| n. a lofty peak; the culminating point of success or power");
        
        return words;
    }
}
