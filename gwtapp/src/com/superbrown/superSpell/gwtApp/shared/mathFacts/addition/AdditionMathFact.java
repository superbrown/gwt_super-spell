package com.superbrown.superSpell.gwtApp.shared.mathFacts.addition;

import com.superbrown.superSpell.gwtApp.shared.mathFacts.MathFactListForAParticularPrimaryOperand;
import com.superbrown.superSpell.gwtApp.shared.mathFacts.MathFact;

public class AdditionMathFact extends MathFact
{
    public AdditionMathFact(MathFactListForAParticularPrimaryOperand parentGroup, int primaryOperand, int secondaryOperand, int timeLimit)
    {
        super(parentGroup, primaryOperand, secondaryOperand, timeLimit);
    }

    public AdditionMathFact()
    {
        super();
    }

    protected void initializeCorrectAnswer()
    {
        this.correctAnswer = "" + (primaryOperand + secondaryOperand);
    }

    public String getTestQuestion()
    {
        return primaryOperand + " + " + secondaryOperand + " =";
    }
}