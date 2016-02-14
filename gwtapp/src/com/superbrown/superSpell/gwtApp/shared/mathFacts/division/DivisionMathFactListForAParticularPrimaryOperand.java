package com.superbrown.superSpell.gwtApp.shared.mathFacts.division;


import com.superbrown.superSpell.gwtApp.shared.mathFacts.MathFactListForAParticularPrimaryOperand;
import com.superbrown.superSpell.gwtApp.shared.mathFacts.MathFact;

/**
 */
public class DivisionMathFactListForAParticularPrimaryOperand
        extends MathFactListForAParticularPrimaryOperand
{
    public DivisionMathFactListForAParticularPrimaryOperand(Integer primaryOperand, String optionalTitle, int timeLimit)
    {
        super(primaryOperand, optionalTitle, timeLimit);
    }

    public DivisionMathFactListForAParticularPrimaryOperand()
    {
        // This is only here because GWT requires it.
    }

    @Override
    protected MathFact createMathFact(int secondaryOperand, int timeLimit)
    {
        return new DivisionMathFact(this, primaryOperand, secondaryOperand, timeLimit);
    }

}