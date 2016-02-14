package com.superbrown.superSpell.gwtApp.shared.mathFacts.multiplication;

import com.superbrown.superSpell.gwtApp.shared.mathFacts.MathFact;
import com.superbrown.superSpell.gwtApp.shared.mathFacts.MathFactListForAParticularPrimaryOperand;

public class MultiplicationMathFact extends MathFact
{
    public MultiplicationMathFact(MathFactListForAParticularPrimaryOperand parentGroup, int primaryOperand, int secondaryOperand, int timeLimit)
    {
        super(parentGroup, primaryOperand, secondaryOperand, timeLimit);
    }

    public MultiplicationMathFact()
    {
        super();
    }

    @Override
    protected void initializeCorrectAnswer()
    {
        this.correctAnswer = "" + (primaryOperand * secondaryOperand);
    }

    public String getTestQuestion()
    {
        return primaryOperand + " x " + secondaryOperand + " =";
    }
}