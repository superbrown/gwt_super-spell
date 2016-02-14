package com.superbrown.superSpell.gwtApp.client.spelling;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.KeyPressEvent;
import com.google.gwt.event.dom.client.KeyPressHandler;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.Panel;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.superbrown.superSpell.gwtApp.client.SuperSpell;
import com.superbrown.superSpell.gwtApp.client.common.IResetable;
import com.superbrown.superSpell.gwtApp.client.common.audio.SongPlayerPanel_usingMidiJs;
import com.superbrown.superSpell.gwtApp.client.util.UiUtilities;
import com.superbrown.superSpell.gwtApp.shared.ITestable;
import com.superbrown.superSpell.gwtApp.shared.ITestableItem;
import com.superbrown.superSpell.gwtApp.shared.TestableItemList;
import com.superbrown.superSpell.gwtApp.shared.spelling.SpellingList;
import com.superbrown.superSpell.gwtApp.shared.spelling.VocabularySpellingWord;

import java.util.ArrayList;
import java.util.List;


/**
 */
public class SpellingWordSetChooserPanel extends VerticalPanel implements IResetable
{
    private SpellingList spellingList;
    private SpellingWordSetRadioButtonPanel spellingWordSetRadioButtonPanel;
    private TestAdministratorPanel testAdministratorPanel;
    private TestableItemList spellingWordSetBeingTested;

    public SpellingWordSetChooserPanel(SpellingList spellingList)
    {
        this.spellingList = spellingList;
        init();
    }

    private void init()
    {
        boolean breakUpIntoSets = true;

        // If the set of words is from a vocabulary class, we don't breat them up.  That's because the
        // test doesn't give them the phonetic spelling, just a definition.  Part of what is being tested
        // is if the user can identify the word given its definition.
        if (spellingList.getTestables().get(0).getClass().getName().equals(VocabularySpellingWord.class.getName()))
        {
            breakUpIntoSets = false;
        }

        SuperSpell.resetDoodleMessage();
        SuperSpell.addAnotherPersonToStayAfterSchoolList();

        clear();

        Label instructions = new Label();
        instructions.setText("Which set of spelling words would you like to practice?");
        instructions.setStyleName("yellowChalk fontSize150");
        add(instructions);

        if (spellingWordSetRadioButtonPanel == null)
        {
            spellingWordSetRadioButtonPanel =
                    new SpellingWordSetRadioButtonPanel(
                            spellingList.getSpellingWordSets(breakUpIntoSets), "whiteChalk fontSize130");
        }
        else
        {
            spellingWordSetRadioButtonPanel.refreshButtonStates();
        }

        add(spellingWordSetRadioButtonPanel);

        VerticalPanel buttonPanel = new VerticalPanel();
        buttonPanel.addStyleName("topMargin10");

        VerticalPanel verticalPanel = new VerticalPanel();
        verticalPanel.addStyleName("topMargin10");

        if (spellingWordSetRadioButtonPanel.allButtonsAreDisabled())
        {
            handleWhenUserGotAllWordsCorrectTheFirstTime(verticalPanel);
            return;
        }

        Button button = new Button("Next");
        verticalPanel.add(button);
        add(verticalPanel);

        final IResetable caller = this;

        button.addClickHandler(new ClickHandler()
            {
                public void onClick(ClickEvent event)
                {

                    handleUserSubmission(caller);
                }
            });

        spellingWordSetRadioButtonPanel.setKeyPressHandler(new KeyPressHandler()
        {
            public void onKeyPress(KeyPressEvent keyPressEvent)
            {
                if (UiUtilities.isTheEnterKey(keyPressEvent))
                {
                    handleUserSubmission(caller);
                }
            }
        });
    }

    private void handleWhenUserGotAllWordsCorrectTheFirstTime(VerticalPanel verticalPanel)
    {
        Label label = new Label("Great work!!");
        label.addStyleName("topMargin10 fontSize320 yellowChalk blink");
        verticalPanel.add(label);

        label = new Label("You have these words pretty well mastered.");
        label.addStyleName("fontSize150 yellowChalk");
        verticalPanel.add(label);
        add(verticalPanel);

        label = new Label("If you want to start over, hit your browser's refresh button");
        label.addStyleName("fontSize150 yellowChalk");
        verticalPanel.add(label);

        label = new Label("or the \"start over\" link in the upper right corner.");
        label.addStyleName("fontSize150 yellowChalk");
        verticalPanel.add(label);

        verticalPanel.add(new SongPlayerPanel_usingMidiJs());

        add(verticalPanel);
    }

    public void selectAndFocusOnFirstActiveButton()
    {
        spellingWordSetRadioButtonPanel.selectTheFirstActiveButton();
        spellingWordSetRadioButtonPanel.setFocusOnSelectedButton();
    }

    private void handleUserSubmission(IResetable caller)
    {
        spellingWordSetBeingTested = spellingWordSetRadioButtonPanel.getSelectedValue();

        if (SuperSpell.isInShowAllMisspellingsMode())
        {
            clear();
            Panel panel = new AllSpellingCombinationsPanel(
                    spellingWordSetBeingTested,
                    "bold courier fontSize120",
                    caller);
            add(panel);
        }
        else
        {
            clear();

            List<ITestableItem> testableItems = new ArrayList<ITestableItem>();
            for (ITestable testable : spellingWordSetBeingTested.getTestables())
            {
               testableItems.add((ITestableItem)testable);
            }
                    
            testAdministratorPanel = new TestAdministratorPanel(caller, testableItems);
            add(testAdministratorPanel);
        }
    }

    public void reset()
    {
        init();
        selectAndFocusOnFirstActiveButton();
    }
}
