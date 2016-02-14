package com.superbrown.superSpell.gwtApp.shared.mathFacts.division;

import com.superbrown.superSpell.gwtApp.shared.mathFacts.MathFactListForAParticularPrimaryOperand;
import com.superbrown.superSpell.gwtApp.shared.mathFacts.MathFact;

public class DivisionMathFact extends MathFact
{
    public DivisionMathFact(MathFactListForAParticularPrimaryOperand parentGroup, int primaryOperand, int secondaryOperand, int timeLimit)
    {
        super(parentGroup, primaryOperand, secondaryOperand, timeLimit);
    }

    public DivisionMathFact()
    {
        super();
    }
    
    @Override
    protected void initializeCorrectAnswer()
    {
        // for division, the secondary operand IS the answer
        this.correctAnswer = "" + secondaryOperand;
    }

    public String getTestQuestion()
    {
        return (primaryOperand * secondaryOperand) + " ÷ " + primaryOperand + " =";
    }
}