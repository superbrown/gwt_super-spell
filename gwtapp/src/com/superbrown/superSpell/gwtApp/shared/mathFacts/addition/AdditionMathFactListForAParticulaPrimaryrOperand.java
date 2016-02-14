package com.superbrown.superSpell.gwtApp.shared.mathFacts.addition;


import com.superbrown.superSpell.gwtApp.shared.mathFacts.MathFactListForAParticularPrimaryOperand;
import com.superbrown.superSpell.gwtApp.shared.mathFacts.MathFact;

/**
 */
public class AdditionMathFactListForAParticulaPrimaryrOperand
        extends MathFactListForAParticularPrimaryOperand
{
    public AdditionMathFactListForAParticulaPrimaryrOperand(Integer primaryOperand, String optionalTitle, int timeLimit)
    {
        super(primaryOperand, optionalTitle, timeLimit);
    }

    public AdditionMathFactListForAParticulaPrimaryrOperand()
    {
        // This is only here because GWT requires it.
    }

    @Override
    protected MathFact createMathFact(int secondaryOperand, int timeLimit)
    {
        return new AdditionMathFact(this, primaryOperand, secondaryOperand, timeLimit);
    }

}