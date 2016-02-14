package com.superbrown.superSpell.gwtApp.shared.vocabulary.vocabularyLists.grade7;


import com.superbrown.superSpell.gwtApp.shared.vocabulary.VocabularyList_ver2;

import java.util.ArrayList;
import java.util.List;

public class VocabularyList_WordMaster01 extends VocabularyList_ver2
{
    public VocabularyList_WordMaster01()
    {
    }

    public VocabularyList_WordMaster01(String optionalTitle)
    {
        super(optionalTitle);

        List<String> words = getWords();
        addVocabularyWordsToInventory(words);
    }

    public List<String> getWords()
    {
        List<String> words = new ArrayList<String>();
        
        words.add("|zenith| n. a highest point or state; culmination");
        words.add("|flout| v. to scorn, to scoff at, to mock");
        words.add("|laconic| adj. using few words; concise");
        words.add("|escarpment| n. a long cliff-like ridge of land or rock, formed by the vaulting of the earth's crust");
        words.add("|culminate| v. to reach the highest point; to arrive at a final stage; to rise to or form an apex");
        words.add("|loath| adj. unwilling; reluctant; disinclined; averse");
        words.add("|maelstrom| n. a large, powerful. or violent whirlpool");
        words.add("|simper| v. to smile in a silly, self-conscious way; n. a silly, self-conscious smile");
        words.add("|gregarious| adj. fond of the company of others; sociable");
        words.add("|node| n. a knot or knob; a knot-like mass of tissue; a joint in a stem; center part of components");
        words.add("|spurn| v. to treat with contempt; to scorn; to trample with the foot");
        words.add("|choleric| adj. extremely irritable; irascible");
        words.add("|confluence| n. a coming together of two rivers to form one; a junction; a coming together of people or things");
        words.add("|rail| n. bars of wood or metal used for support, boundaries, or running transportation; v. to vehemently complain");
        words.add("|ostensible| adj. outwardly appearing as such; apparent, evident");
        words.add("|wellspring| n. a source of a stream; an inexhaustible supply of anything");
        words.add("|stem| n. a stalk which supports a leaf, flower, or fruit; v. a cut flower; to dam up, to stop the flow; to make progress against");
        words.add("|garrulous| adj. excessively talkative about trivial matters");
        words.add("|nadir| n. the lowest point; point of greatest adversity or despair");
        words.add("|flaunt| v. to boldly display oneself");
        words.add("|staunch| adj. steadfast in loyalty, characterized by firmness; v. to stem the flow of liquid especially blood");
        words.add("|recluse| n. a person who lives in seclusion or apart from society");
        words.add("|chortle| v. to chuckle gleefully; n. a deep chuckle or laugh");
        words.add("|sheer| adj. transparently thin; unmixed with anything else; unqualified, utter; extending up or down very steeply; v. to cut fur or hair from");
        words.add("|ostentation| n. pretentious show of wealth or importance; display intended to impress others");

        return words;
    }
}
