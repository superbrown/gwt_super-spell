package com.superbrown.superSpell.gwtApp.shared.mathFacts.division;

import com.superbrown.superSpell.gwtApp.client.Settings;
import com.superbrown.superSpell.gwtApp.shared.mathFacts.MathFactListForAParticularOperator;
import com.superbrown.superSpell.gwtApp.shared.mathFacts.MathFactListForAParticularPrimaryOperand;

public class DivisionMathFacts extends MathFactListForAParticularOperator
{
    public DivisionMathFacts(String optionalTitle, int timeLimit)
    {
        super(optionalTitle, timeLimit);
    }

    public DivisionMathFacts()
    {
        // This is only here because GWT requires it.
    }

    protected double getHighestLevelToGo()
    {
        return Settings.HIGHEST_LEVEL_OF_DIVISION_MATH_FACTS;
    }

    protected MathFactListForAParticularPrimaryOperand createMathFactList(int primaryOperand, int timeLimit, String optionalTitle)
    {
        return new DivisionMathFactListForAParticularPrimaryOperand(primaryOperand, optionalTitle, timeLimit);
    }
}