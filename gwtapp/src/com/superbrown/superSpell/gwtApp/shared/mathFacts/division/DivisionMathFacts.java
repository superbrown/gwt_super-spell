package com.superbrown.superSpell.gwtApp.shared.mathFacts.division;

import com.superbrown.superSpell.gwtApp.client.Settings;
import com.superbrown.superSpell.gwtApp.shared.mathFacts.MathFactListForAParticularOperator;
import com.superbrown.superSpell.gwtApp.shared.mathFacts.MathFactListForAParticularPrimaryOperand;

public class DivisionMathFacts extends MathFactListForAParticularOperator
{
    public DivisionMathFacts(String name)
    {
        super(name);
    }

    public DivisionMathFacts()
    {
        // This is only here because GWT requires it.
    }

    protected double getHighestLevelToGo()
    {
        return Settings.HIGHEST_LEVEL_OF_DIVISION_MATH_FACTS;
    }

    protected MathFactListForAParticularPrimaryOperand createMathFactList(int primaryOperand, String name)
    {
        return new DivisionMathFactListForAParticularPrimaryOperand(primaryOperand, name);
    }
}
