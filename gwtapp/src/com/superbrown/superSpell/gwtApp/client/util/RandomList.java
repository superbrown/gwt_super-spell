package com.superbrown.superSpell.gwtApp.client.util;


import java.util.ArrayList;
import java.util.List;

public class RandomList
{
    private final List allElements;
    protected List elementsRemaining;

    public RandomList(List elements)
    {
        this.allElements = elements;
        this.elementsRemaining = Shuffler.shuffle(allElements);
    }

    public RandomList(Object[] elements)
    {
        // The list returned from this is one that GWT will find doesn't support the remove()
        // method, so we have to do the work ourselves.
//        this(Arrays.asList(elements));

        this.allElements = new ArrayList();
        for (Object element : elements)
        {
            this.allElements.add(element);
        }

        this.elementsRemaining = Shuffler.shuffle(this.allElements);
    }

    public Object getNextElement()
    {
        if (elementsRemaining.size() == 0)
        {
            elementsRemaining = Shuffler.shuffle(allElements);
        }

        return elementsRemaining.remove(0);
    }
}
