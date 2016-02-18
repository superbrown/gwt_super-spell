package com.superbrown.superSpell.gwtApp.shared.mathFacts.addition;

import com.superbrown.superSpell.gwtApp.client.Settings;
import com.superbrown.superSpell.gwtApp.shared.mathFacts.MathFactListForAParticularOperator;
import com.superbrown.superSpell.gwtApp.shared.mathFacts.MathFactListForAParticularPrimaryOperand;

public class AdditionMathFacts extends MathFactListForAParticularOperator
{
    public AdditionMathFacts(String name, int timeLimit)
    {
        super(name, timeLimit);
    }

    public AdditionMathFacts()
    {
        // This is only here because GWT requires it.
    }


    protected double getHighestLevelToGo()
    {
        return Settings.HIGHEST_LEVEL_OF_ADDITION_MATH_FACTS;
    }

    protected MathFactListForAParticularPrimaryOperand createMathFactList(int primaryOperand, int timeLimit, String name)
    {
        return new AdditionMathFactListForAParticulaPrimaryrOperand(primaryOperand, name, timeLimit);
    }
}
