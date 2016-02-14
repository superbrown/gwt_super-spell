package com.superbrown.superSpell.gwtApp.shared.mathFacts.subtraction;

import com.superbrown.superSpell.gwtApp.client.Settings;
import com.superbrown.superSpell.gwtApp.shared.mathFacts.MathFactListForAParticularOperator;
import com.superbrown.superSpell.gwtApp.shared.mathFacts.MathFactListForAParticularPrimaryOperand;

public class SubtractionMathFacts extends MathFactListForAParticularOperator
{
    public SubtractionMathFacts(String optionalTitle, int timeLimit)
    {
        super(optionalTitle, timeLimit);
    }

    public SubtractionMathFacts()
    {
        // This is only here because GWT requires it.
    }


    protected double getHighestLevelToGo()
    {
        return Settings.HIGHEST_LEVEL_OF_SUBTRACTION_MATH_FACTS;
    }

    protected MathFactListForAParticularPrimaryOperand createMathFactList(int primaryOperand, int timeLimit, String optionalTitle)
    {
        return new SubtractionMathFactListForAParticularPrimaryOperand(primaryOperand, optionalTitle, timeLimit);
    }
}