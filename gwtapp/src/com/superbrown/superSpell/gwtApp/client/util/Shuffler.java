package com.superbrown.superSpell.gwtApp.client.util;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Shuffler
{
	public static List shuffle(final List elements)
	{
		List copyOfElements = new ArrayList(elements);

		Random random = new Random(System.currentTimeMillis());

		List shuffledList = new ArrayList();

		while (copyOfElements.size() > 0)
		{
			int randomIndex = random.nextInt(copyOfElements.size());
			Object randomlySelectedElement = copyOfElements.get(randomIndex);

			shuffledList.add(randomlySelectedElement);
			copyOfElements.remove(randomlySelectedElement);
		}

		return shuffledList;
	}
}
