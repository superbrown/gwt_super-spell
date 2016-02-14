package com.superbrown.superSpell.gwtApp.shared.spelling;

import com.google.gwt.user.client.rpc.IsSerializable;

/**
 */
public class Syllable implements IsSerializable
{
    String string;

    public Syllable(String string)
    {
        this.string = string;
    }

    public Syllable()
    {
    }

    @Override
    public String toString()
    {
        return string;
    }
}
