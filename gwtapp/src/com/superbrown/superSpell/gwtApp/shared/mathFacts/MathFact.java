package com.superbrown.superSpell.gwtApp.shared.mathFacts;

import com.superbrown.superSpell.gwtApp.shared.ITestable;
import com.superbrown.superSpell.gwtApp.shared.ITestableItem;
import com.superbrown.superSpell.gwtApp.shared.TestableItem;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 */
public abstract class MathFact extends TestableItem
{
    protected int primaryOperand;
    protected int secondaryOperand;
    protected MathFactListForAParticularPrimaryOperand parentGroup;
    private Set<String> incorrectAnswers;
    private int timeLimit;

    public MathFact()
    {
        super(new MathFactTestingMetric());
    }

    public MathFact(
            MathFactListForAParticularPrimaryOperand parentGroup,
            int primaryOperand,
            int secondaryOperand,
            int timeLimit)
    {
        this();

        this.parentGroup = parentGroup;
        this.primaryOperand = primaryOperand;
        this.secondaryOperand = secondaryOperand;
        this.timeLimit = timeLimit;
        initializeCorrectAnswer();
    }

    public MathFact(MathFact mathFact)
    {
        this(
                mathFact.getParentGroup(),
                mathFact.getPrimaryOperand(),
                mathFact.getSecondaryOperand(),
                mathFact.getTimeLimit());
    }


    @Override
    public Set<String> getIncorrectAnswers()
    {
        if (incorrectAnswers != null)
        {
            return incorrectAnswers;
        }

        incorrectAnswers = new HashSet<String>();

        List<ITestable> facts = parentGroup.getTestables();

        for (ITestable fact : facts)
        {
            if (fact != this)
            {
                incorrectAnswers.add(((ITestableItem)fact).getCorrectAnswer());
            }
        }

        return incorrectAnswers;
    }

    public int getPrimaryOperand()
    {
        return primaryOperand;
    }

    public int getSecondaryOperand()
    {
        return secondaryOperand;
    }

    public MathFactListForAParticularPrimaryOperand getParentGroup()
    {
        return parentGroup;
    }

    public abstract String getTestQuestion();

    protected abstract void initializeCorrectAnswer();

    public int getTimeLimit()
    {
        return timeLimit;
    }

    public void setTimeLimit(int timeLimit)
    {
        this.timeLimit = timeLimit;
    }
}
