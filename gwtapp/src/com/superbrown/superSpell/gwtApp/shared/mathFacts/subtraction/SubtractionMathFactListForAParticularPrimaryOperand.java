package com.superbrown.superSpell.gwtApp.shared.mathFacts.subtraction;


import com.superbrown.superSpell.gwtApp.shared.mathFacts.MathFactListForAParticularPrimaryOperand;
import com.superbrown.superSpell.gwtApp.shared.mathFacts.MathFact;

/**
 */
public class SubtractionMathFactListForAParticularPrimaryOperand
        extends MathFactListForAParticularPrimaryOperand
{
    public SubtractionMathFactListForAParticularPrimaryOperand(Integer primaryOperand, String optionalTitle, int timeLimit)
    {
        super(primaryOperand, optionalTitle, timeLimit);
    }

    public SubtractionMathFactListForAParticularPrimaryOperand()
    {
        // This is only here because GWT requires it.
    }

    @Override
    protected MathFact createMathFact(int secondaryOperand, int timeLimit)
    {
        return new SubtractionMathFact(this, primaryOperand, secondaryOperand, timeLimit);
    }

}