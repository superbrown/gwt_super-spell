package com.superbrown.superSpell.gwtApp.shared.mathFacts.division;


import com.superbrown.superSpell.gwtApp.shared.mathFacts.MathFactListForAParticularPrimaryOperand;
import com.superbrown.superSpell.gwtApp.shared.mathFacts.MathFact;

/**
 */
public class DivisionMathFactListForAParticularPrimaryOperand
        extends MathFactListForAParticularPrimaryOperand
{
    public DivisionMathFactListForAParticularPrimaryOperand(Integer primaryOperand, String name)
    {
        super(primaryOperand, name);
    }

    public DivisionMathFactListForAParticularPrimaryOperand()
    {
        // This is only here because GWT requires it.
    }

    @Override
    protected MathFact createMathFact(int secondaryOperand)
    {
        return new DivisionMathFact(this, primaryOperand, secondaryOperand, null);
    }

}
