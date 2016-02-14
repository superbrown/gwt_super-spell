package com.superbrown.superSpell.gwtApp.client.mastermind;

import com.superbrown.superSpell.gwtApp.client.mastermind.pegs.PegSequence;
import com.superbrown.superSpell.gwtApp.client.mastermind.pegs.ResultPeg;

import java.util.ArrayList;
import java.util.List;

/**
 */
public class Guess extends PegSequence
{
    public List<ResultPeg> getResults(PegSequence correctPegSequence)
    {
        List<ResultPeg> results = new ArrayList<ResultPeg>();

        List correctSequence = new ArrayList(correctPegSequence.getPegs());
        List mySequence = new ArrayList(getPegs());

        for (int pegIndex = 0; pegIndex < correctSequence.size(); pegIndex++)
        {
            Object myPeg = mySequence.get(pegIndex);
            Object correctPeg = correctSequence.get(pegIndex);

            if (myPeg == correctPeg)
            {
                results.add(ResultPeg.RIGHT_POSITION);
                mySequence.remove(pegIndex);
                correctSequence.remove(pegIndex);
                pegIndex--;
            }
        }

        for (int pegIndex = 0; pegIndex < mySequence.size(); pegIndex++)
        {
            Object myPeg = mySequence.get(pegIndex);

            if (correctSequence.contains(myPeg))
            {
                results.add(ResultPeg.WRONG_POSITION);
                correctSequence.remove(myPeg);
            }
        }

        sortResults(results);

        return results;
    }

    private void sortResults(List<ResultPeg> results)
    {
        for (int resultIndex = 0; resultIndex < results.size(); resultIndex++)
        {
            if (results.get(resultIndex) == ResultPeg.RIGHT_POSITION)
            {
                ResultPeg removedPeg = results.remove(resultIndex);
                results.add(0, removedPeg);
            }
        }
    }
}
