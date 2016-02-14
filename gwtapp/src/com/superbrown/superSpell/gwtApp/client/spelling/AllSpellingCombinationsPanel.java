package com.superbrown.superSpell.gwtApp.client.spelling;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.*;
import com.superbrown.superSpell.gwtApp.client.common.IResetable;
import com.superbrown.superSpell.gwtApp.shared.ITestable;
import com.superbrown.superSpell.gwtApp.shared.TestableItemList;
import com.superbrown.superSpell.gwtApp.shared.ThingWithPoolOfIncorrectAnswers;
import com.superbrown.superSpell.gwtApp.shared.spelling.SpellingWord;

/**
 */
public class AllSpellingCombinationsPanel extends VerticalPanel
{
    public AllSpellingCombinationsPanel(
            TestableItemList spellingWordSet,
            String textStyle,
            final IResetable caller)
    {
        VerticalPanel verticalPanel = new VerticalPanel();
        add(verticalPanel);

        for (ITestable testableItem : spellingWordSet.getTestables())
        {
            SpellingWord spellingWord = (SpellingWord)testableItem;

            Label correctSpellingLabel = new Label(
                    spellingWord.getCorrectAnswer() + " |" + spellingWord.getPhoneticSpelling() + "|");
            correctSpellingLabel.addStyleName(textStyle);
            verticalPanel.add(correctSpellingLabel);

            for (String misspelling : ((ThingWithPoolOfIncorrectAnswers)testableItem).getIncorrectAnswers())
            {
                Label misspellingLabel = new Label(misspelling);
                misspellingLabel.addStyleName(textStyle);
                verticalPanel.add(misspellingLabel);
            }

            Label sampleSentenceLabel = new Label(spellingWord.getSampleSentenceToDisplayToUser());
            sampleSentenceLabel.addStyleName(textStyle);
            verticalPanel.add(sampleSentenceLabel);

            verticalPanel.add(new Label("---------------------------------------------------------------"));
        }

        Button button = new Button("Next");
        button.addClickHandler(new ClickHandler()
            {
                public void onClick(ClickEvent clickEvent)
                {
                    caller.reset();
                }
            });

        add(button);
        button.setFocus(true);
    }

}
