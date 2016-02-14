package com.superbrown.superSpell.gwtApp.shared.mathFacts.subtraction;

import com.superbrown.superSpell.gwtApp.shared.mathFacts.MathFactListForAParticularPrimaryOperand;
import com.superbrown.superSpell.gwtApp.shared.mathFacts.MathFact;

public class SubtractionMathFact extends MathFact
{
    public SubtractionMathFact(MathFactListForAParticularPrimaryOperand parentGroup, int primaryOperand, int secondaryOperand, int timeLimit)
    {
        super(parentGroup, primaryOperand, secondaryOperand, timeLimit);
    }

    public SubtractionMathFact()
    {
        super();
    }

    @Override
    protected void initializeCorrectAnswer()
    {
        // for subtraction, the secondary operand IS the answer
        this.correctAnswer = "" + secondaryOperand;
    }

    public String getTestQuestion()
    {
        return (primaryOperand + secondaryOperand) + " - " + primaryOperand + " =";
    }
}