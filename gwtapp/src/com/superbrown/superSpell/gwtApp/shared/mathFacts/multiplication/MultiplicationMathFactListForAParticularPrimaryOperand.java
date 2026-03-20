package com.superbrown.superSpell.gwtApp.shared.mathFacts.multiplication;


import com.superbrown.superSpell.gwtApp.shared.mathFacts.MathFactListForAParticularPrimaryOperand;
import com.superbrown.superSpell.gwtApp.shared.mathFacts.MathFact;

/**
 */
public class MultiplicationMathFactListForAParticularPrimaryOperand
        extends MathFactListForAParticularPrimaryOperand
{
    public MultiplicationMathFactListForAParticularPrimaryOperand(Integer primaryOperand, String name)
    {
        super(primaryOperand, name);
    }

    public MultiplicationMathFactListForAParticularPrimaryOperand()
    {
        // This is only here because GWT requires it.
    }

    @Override
    protected MathFact createMathFact(int secondaryOperand)
    {
        MultiplicationMathFact fact =
                new MultiplicationMathFact(this, primaryOperand, secondaryOperand, null);
        return fact;
    }

}
