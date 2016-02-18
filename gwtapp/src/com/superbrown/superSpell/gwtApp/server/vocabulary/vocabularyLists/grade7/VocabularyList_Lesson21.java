package com.superbrown.superSpell.gwtApp.server.vocabulary.vocabularyLists.grade7;

import com.superbrown.superSpell.gwtApp.shared.vocabulary.VocabularyList_ver2;

import java.util.ArrayList;
import java.util.List;

public class VocabularyList_Lesson21 extends VocabularyList_ver2
{
    public VocabularyList_Lesson21()
    {
    }

    public VocabularyList_Lesson21(String name)
    {
        super(name);

        List<String> words = getWords();
        addVocabularyWordsToInventory(words);
    }

    public List<String> getWords()
    {
        List<String> words = new ArrayList<String>();

        words.add("|bestride| v. to sit on something wiht a leg on each side; to straddle");
        words.add("|casement| n. a window that opens on hinges along its side");
        words.add("|debut| v. to present for the first time; to make a debut; n. a first public appearance, as of an actor; a formal entrance into society");
        words.add("|documentary| adj. based on or consisting of documents; employing facts rather than fiction; n. a presentation (such as a film) built on factual conditions or historical events");
        words.add("|fluctuate| v. to rise and fall or move back and forth; to change continually in an irregular way");
        words.add("|melancholy| adj. sad; depressed; causing or suggesting sadness; n. gloom; low spirits");
        words.add("|reprieve| n. a temporary relief (from pain or trouble); an order temporarily delaying punishment or the execution of a sentence");
        words.add("|requiem| n. music or a mass for the dead");
        words.add("|theoretical| adj. hypothetical; unproved or uncertain");
        words.add("|vehement| adj. marked by great emotion, force, or conviction; intense; violent");
        
        return words;
    }
}


