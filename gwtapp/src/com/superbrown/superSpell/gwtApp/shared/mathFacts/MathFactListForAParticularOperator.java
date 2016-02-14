package com.superbrown.superSpell.gwtApp.shared.mathFacts;

import com.superbrown.superSpell.gwtApp.shared.ITestable;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


/**
 */
public abstract class MathFactListForAParticularOperator implements Serializable, ITestable
{
    private List <MathFactListForAParticularPrimaryOperand> mathFactListForAParticularPrimaryOperand;
    private String name;

    public MathFactListForAParticularOperator(String explicitName, int timeLimit)
    {
        this.name = explicitName;

        mathFactListForAParticularPrimaryOperand = new ArrayList<MathFactListForAParticularPrimaryOperand>();

        for (int primaryOperand = 2; primaryOperand < getHighestLevelToGo() + 1; primaryOperand++)
        {
            mathFactListForAParticularPrimaryOperand.add(createMathFactList(primaryOperand, timeLimit, "" + primaryOperand + "'s"));
        }
    }

    public MathFactListForAParticularOperator()
    {
    }

    protected abstract double getHighestLevelToGo();

    protected abstract MathFactListForAParticularPrimaryOperand createMathFactList(int primaryOperand, int timeLimit, String optionalTitle);

    public List<MathFactListForAParticularPrimaryOperand> getMathFactLists()
    {
        return mathFactListForAParticularPrimaryOperand;
    }

    public boolean isGotItOnTheFirstTry()
    {
        for (MathFactListForAParticularPrimaryOperand factList :
                mathFactListForAParticularPrimaryOperand)
        {
            if (!factList.isGotItOnTheFirstTry())
            {
                return false;
            }
        }

        return true;
    }

    public void reset(boolean force)
    {
        for (MathFactListForAParticularPrimaryOperand factList :
                mathFactListForAParticularPrimaryOperand)
        {
            factList.reset(force);
        }
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }
}