package com.superbrown.superSpell.gwtApp.shared.mathFacts.addition;

import com.superbrown.superSpell.gwtApp.client.Settings;
import com.superbrown.superSpell.gwtApp.shared.mathFacts.MathFactListForAParticularOperator;
import com.superbrown.superSpell.gwtApp.shared.mathFacts.MathFactListForAParticularPrimaryOperand;

public class AdditionMathFacts extends MathFactListForAParticularOperator
{
    public AdditionMathFacts(String optionalTitle, int timeLimit)
    {
        super(optionalTitle, timeLimit);
    }

    public AdditionMathFacts()
    {
        // This is only here because GWT requires it.
    }


    protected double getHighestLevelToGo()
    {
        return Settings.HIGHEST_LEVEL_OF_ADDITION_MATH_FACTS;
    }

    protected MathFactListForAParticularPrimaryOperand createMathFactList(int primaryOperand, int timeLimit, String optionalTitle)
    {
        return new AdditionMathFactListForAParticulaPrimaryrOperand(primaryOperand, optionalTitle, timeLimit);
    }
}