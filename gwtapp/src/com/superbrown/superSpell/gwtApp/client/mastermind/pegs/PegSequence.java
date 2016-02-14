package com.superbrown.superSpell.gwtApp.client.mastermind.pegs;

import java.util.ArrayList;
import java.util.List;

/**
 */
public class PegSequence
{
    private List<Peg> pegs = new ArrayList<Peg>();

    public List<Peg> getPegs()
    {
        return pegs;
    }

    public void addPeg(Peg peg)
    {
        pegs.add(peg);
    }

    public void addPegs(List<Peg> pegs)
    {
        for (Peg peg : pegs)
        {
            addPeg(peg);
        }
    }

    @Override
    public String toString()
    {
        String string = "";

        for (Peg peg : getPegs())
        {
            string += peg.toString() + " ";
        }

        return string;
    }
}


