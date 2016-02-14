package com.superbrown.superSpell.gwtApp.shared.spelling;

import com.google.gwt.user.client.rpc.IsSerializable;

import java.util.ArrayList;
import java.util.List;

/**
 */
public class PossibleSyllableSpellings implements IsSerializable
{
    private Syllable phoneticSpelling;
    private List<String> possilbeSpellings = new ArrayList<String>();

    public PossibleSyllableSpellings(String phoneticSpelling, String[] possilbeSpellings)
    {
        this.phoneticSpelling = new Syllable(phoneticSpelling);

        for (int i = 0; i < possilbeSpellings.length; i++)
        {
            this.possilbeSpellings.add(possilbeSpellings[i]);
        }
    }

    public PossibleSyllableSpellings()
    {
    }

    public Syllable getPhoneticSpelling()
    {
        return phoneticSpelling;
    }

    public List<String> getPossilbeSpellings()
    {
        return possilbeSpellings;
    }
}