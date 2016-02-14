package com.superbrown.superSpell.gwtApp.shared.mathFacts;

import com.superbrown.superSpell.gwtApp.shared.ITestable;
import com.superbrown.superSpell.gwtApp.shared.TestableItemList;

import java.util.ArrayList;

/**
 */
public abstract class MathFactListForAParticularPrimaryOperand extends TestableItemList
{
    protected Integer primaryOperand;
    protected int  timeLimit;

    public MathFactListForAParticularPrimaryOperand()
    {
    }

    public MathFactListForAParticularPrimaryOperand(Integer primaryOperand, String explicitName, int timeLimit)
    {
        super(explicitName);

        this.timeLimit = timeLimit;

        this.primaryOperand = primaryOperand;

        if (explicitName != null &&
            !explicitName.equals(""))
        {
            setName(explicitName);
        }
        else
        {
            setName("" + primaryOperand + "'s");
        }

        testables = new ArrayList<ITestable>();

        for (int i = 2; i <= 9 ; i++)
        {
            MathFact mathFact = createMathFact(i, timeLimit);
            testables.add(mathFact);
        }
    }


    public Integer getPrimaryOperand()
    {
        return primaryOperand;
    }

    public int getTimeLimit()
    {
        return timeLimit;
    }


    @Override
    public String toString()
    {
        return primaryOperand.toString();
    }

    protected abstract MathFact createMathFact(int secondaryOperand, int timeLimit);
}
