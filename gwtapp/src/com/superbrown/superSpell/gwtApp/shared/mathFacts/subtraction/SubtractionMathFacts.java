package com.superbrown.superSpell.gwtApp.shared.mathFacts.subtraction;

import com.superbrown.superSpell.gwtApp.client.Settings;
import com.superbrown.superSpell.gwtApp.shared.mathFacts.MathFactListForAParticularOperator;
import com.superbrown.superSpell.gwtApp.shared.mathFacts.MathFactListForAParticularPrimaryOperand;

public class SubtractionMathFacts extends MathFactListForAParticularOperator
{
    public SubtractionMathFacts(String name)
    {
        super(name);
    }

    public SubtractionMathFacts()
    {
        // This is only here because GWT requires it.
    }


    protected double getHighestLevelToGo()
    {
        return Settings.HIGHEST_LEVEL_OF_SUBTRACTION_MATH_FACTS;
    }

    protected MathFactListForAParticularPrimaryOperand createMathFactList(int primaryOperand, String name)
    {
        return new SubtractionMathFactListForAParticularPrimaryOperand(primaryOperand, name);
    }
}
