package com.superbrown.superSpell.gwtApp.client.mastermind.pegs;

import com.google.gwt.user.client.ui.Label;

/**
 */
public class PegLabel extends Label
{
    private Peg peg;

    public PegLabel(Peg peg)
    {
        super();
        this.peg = peg;
        this.setText(peg.toString());
    }

    public Peg getPeg()
    {
        return peg;
    }
}