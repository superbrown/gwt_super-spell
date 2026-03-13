package com.superbrown.superSpell.gwtApp.shared.mathFacts.subtraction;


import com.superbrown.superSpell.gwtApp.shared.mathFacts.MathFactListForAParticularPrimaryOperand;
import com.superbrown.superSpell.gwtApp.shared.mathFacts.MathFact;

/**
 */
public class SubtractionMathFactListForAParticularPrimaryOperand
        extends MathFactListForAParticularPrimaryOperand
{
    public SubtractionMathFactListForAParticularPrimaryOperand(Integer primaryOperand, String name)
    {
        super(primaryOperand, name);
    }

    public SubtractionMathFactListForAParticularPrimaryOperand()
    {
        // This is only here because GWT requires it.
    }

    @Override
    protected MathFact createMathFact(int secondaryOperand)
    {
        return new SubtractionMathFact(this, primaryOperand, secondaryOperand, null);
    }
}
