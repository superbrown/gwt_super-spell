package com.superbrown.superSpell.gwtApp.shared.spelling;

import com.google.gwt.user.client.rpc.IsSerializable;

import java.util.ArrayList;
import java.util.List;


/**
 */
public class Word implements IsSerializable
{
    private List<Syllable> syllables = new ArrayList<Syllable>();
    private String standardSpelling;

    public Word(String phoneticSpellingWithSyllablesSeparatedWithDashes, String standardSpelling)
    {
        this.standardSpelling = standardSpelling;
        String[] syllables1 = phoneticSpellingWithSyllablesSeparatedWithDashes.split("-");
        for (int i = 0; i < syllables1.length; i++)
        {
            String s = syllables1[i];
            this.syllables.add(new PhoneticSyllable(s));
        }
    }

    public Word(Word word)
    {
        this.standardSpelling = word.standardSpelling;

        for (Syllable syllable : word.getSyllables())
        {
            this.syllables.add(syllable);
        }
    }

    public Word()
    {
    }

    public List<Syllable> getSyllables()
    {
        return syllables;
    }

    public void replace(Syllable syllable, SpelledSyllable spelledSyllable)
    {
        int index = syllables.indexOf(syllable);
        syllables.remove(index);
        syllables.add(index, spelledSyllable);
    }

    @Override
    public String toString()
    {
        String string = "";

        for (Syllable syllable : syllables)
        {
            string += syllable;
        }

        return string;
    }

    public boolean containsSyllable(Syllable syllable)
    {
        for (Syllable mySyllable : syllables)
        {
            if (mySyllable.toString().equals(syllable.toString()))
            {
                return true;
            }
        }

        return false;
    }

    public boolean isFullySpelled()
    {
        for (Syllable syllable : getSyllables())
        {
            if (syllable instanceof PhoneticSyllable)
            {
                return false;
            }
        }

        return true;
    }


    public String spellingWithSyllableDashes()
    {
        String string = "";

        for (Syllable syllable : syllables)
        {
            string += syllable + "-";
        }

        return string.substring(0, string.length() - 1);
    }

    public String getStandardSpelling()
    {
        return standardSpelling;
    }
}
