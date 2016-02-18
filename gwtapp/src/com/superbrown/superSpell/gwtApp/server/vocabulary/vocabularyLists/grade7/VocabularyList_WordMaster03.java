package com.superbrown.superSpell.gwtApp.server.vocabulary.vocabularyLists.grade7;


import com.superbrown.superSpell.gwtApp.shared.vocabulary.VocabularyList_ver2;

import java.util.ArrayList;
import java.util.List;

public class VocabularyList_WordMaster03 extends VocabularyList_ver2
{
    public VocabularyList_WordMaster03()
    {
    }

    public VocabularyList_WordMaster03(String name)
    {
        super(name);

        List<String> words = getWords();
        addVocabularyWordsToInventory(words);
    }

    public List<String> getWords()
    {
        List<String> words = new ArrayList<String>();
        
        words.add("|premonition| n. a feeling of anxiety or anticipation over a future event");
        words.add("|quail| n. a small game bird; v. to shrink in fear");
        words.add("|spasmodic| adj. characterized by sudden bursts of excitement; sporadic");
        words.add("|charisma| n. a personal quality of influence and charm");
        words.add("|decry| v. to denounce as faulty or worthless; to speak disparagingly of");
        words.add("|circumspect| adj. well-thought out, cautious, prudent");
        words.add("|morass| n. marshy ground; an entanglement or tough situation");
        words.add("|daub| n. a crude, unartistic painting; v. to smear, soil, spread");
        words.add("|impetuous| ad. undertaken rashly and quickly; adv. hastily");
        words.add("|misgiving| n. a feeling of anxiety or anticipation over a future event");
        words.add("|demean| v. to lower in dignity, honor, or standing; to debase");
        words.add("|headlong| adj. undertaken rashly and quickly; adv. hastily");
        words.add("|ravage| v. to work havoc or cause ruinous damage; n. plural: what remains after the damage has been done");
        words.add("|permeate| v. to pas into or through every part of; to penetrate");
        words.add("|crotchety| adj. given to odd notions, whims, and grouchiness");
        words.add("|prowess| n. exceptional valor, bravery, skill, or ability");
        words.add("|withstand| v. to successfully stand or hold out against");
        words.add("|menial| adj. lowly, servile, submissive");
        words.add("|remorse| n. deep and painful regret for wrongdoing");
        words.add("|deign| v. to think fit according to one's dignity' condescend");
        words.add("|ersatz| n. a substitute, an artificial substance; adj. synthetic, artificial");
        words.add("|curtail| v. to cut short, diminish, reduce, abridge");
        words.add("|omen|  n. anything believed to be a portent to something in the future");
        words.add("|supercilious| adj. haughtily disdainful; arrogant, scornful");
        words.add("|acclaim| n. enthusiastic approval; v. to announce or proclaim with approval");

        return words;
    }
}
