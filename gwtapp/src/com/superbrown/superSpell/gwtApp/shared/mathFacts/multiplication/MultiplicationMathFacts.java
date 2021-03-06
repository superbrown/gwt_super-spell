package com.superbrown.superSpell.gwtApp.shared.mathFacts.multiplication;

import com.superbrown.superSpell.gwtApp.client.Settings;
import com.superbrown.superSpell.gwtApp.shared.mathFacts.MathFactListForAParticularOperator;
import com.superbrown.superSpell.gwtApp.shared.mathFacts.MathFactListForAParticularPrimaryOperand;

public class MultiplicationMathFacts extends MathFactListForAParticularOperator
{
    public MultiplicationMathFacts(String name, int timeLimit)
    {
        super(name, timeLimit);
    }

    public MultiplicationMathFacts()
    {
        // This is only here because GWT requires it.
    }


    protected double getHighestLevelToGo()
    {
        return Settings.HIGHEST_LEVEL_OF_MULTIPLICATION_MATH_FACTS;
    }

    protected MathFactListForAParticularPrimaryOperand createMathFactList(int primaryOperand, int timeLimit, String name)
    {
        return new MultiplicationMathFactListForAParticularPrimaryOperand(primaryOperand, name, timeLimit);
    }
}
