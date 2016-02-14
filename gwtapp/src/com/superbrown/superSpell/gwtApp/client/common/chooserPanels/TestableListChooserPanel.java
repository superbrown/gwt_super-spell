package com.superbrown.superSpell.gwtApp.client.common.chooserPanels;

import com.google.gwt.event.dom.client.ChangeEvent;
import com.google.gwt.event.dom.client.ChangeHandler;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.superbrown.superSpell.gwtApp.client.SuperSpell;
import com.superbrown.superSpell.gwtApp.client.common.IResetable;
import com.superbrown.superSpell.gwtApp.client.mathFacts.PrimaryOperandChooserPanel;
import com.superbrown.superSpell.gwtApp.client.services.ISuperSpellService;
import com.superbrown.superSpell.gwtApp.client.spelling.SpellingWordSetChooserPanel;
import com.superbrown.superSpell.gwtApp.client.vocabulary.VocabularyWordSetChooserPanel;
import com.superbrown.superSpell.gwtApp.shared.ITestable;
import com.superbrown.superSpell.gwtApp.shared.mathFacts.MathFactListForAParticularOperator;
import com.superbrown.superSpell.gwtApp.shared.spelling.SpellingList;
import com.superbrown.superSpell.gwtApp.shared.vocabulary.IVocabularyList;

import java.util.List;

/**
 */
public class TestableListChooserPanel extends VerticalPanel implements IResetable
{
    private String schoolClassName;
    private List<String> testableListNames;
    private ListBox testableListNamesListBox;
    private String MAKE_A_SELECTION;
    private IResetable caller;

    public TestableListChooserPanel(String schoolClassName, List<String> testableListNames, IResetable caller)
    {
        this.schoolClassName = schoolClassName;
        this.testableListNames = testableListNames;
        this.caller = caller;
        init();
    }

    private void init()
    {
        clear();

        Label instructions = new Label();
        instructions.setText("Which of these do you want to practice?");
        instructions.setStyleName("yellowChalk fontSize150");
        add(instructions);

        testableListNamesListBox = new ListBox();
        MAKE_A_SELECTION = "Make a selection";
        testableListNamesListBox.addItem(MAKE_A_SELECTION);

        for (String spellingListName : testableListNames)
        {
            testableListNamesListBox.addItem(spellingListName);
        }

        add(testableListNamesListBox);

        final IResetable caller = this;

        testableListNamesListBox.addChangeHandler(new ChangeHandler()
        {
            public void onChange(ChangeEvent changeEvent)
            {
                handleUserSubmission(caller);
            }
        });
    }


    private void handleUserSubmission(final IResetable caller)
    {
        String testableListName =
                testableListNamesListBox.getItemText(testableListNamesListBox.getSelectedIndex());

        if (testableListName.equals(MAKE_A_SELECTION))
        {
            return;
        }

        openTestSetChooserPanel(testableListName);
    }

    private void openTestSetChooserPanel(final String testableListName)
    {
        ISuperSpellService.App.getInstance().getTestableList(
                schoolClassName, testableListName, new AsyncCallback<ITestable>()
        {
            RootPanel parentPanel = RootPanel.get("mainPanel");

            public void onFailure(Throwable e)
            {
                // try again
                throw new RuntimeException(e);
            }

            public void onSuccess(ITestable testableList)
            {
                SuperSpell.setTestableListName(testableList.getName());

                if (testableList instanceof SpellingList)
                {
                    SpellingWordSetChooserPanel spellingWordSetChooserPanel =
                            new SpellingWordSetChooserPanel((SpellingList)testableList);
                    parentPanel.clear();
                    parentPanel.add(spellingWordSetChooserPanel);
                    spellingWordSetChooserPanel.selectAndFocusOnFirstActiveButton();
                }
                else if (testableList instanceof IVocabularyList)
                {
                    VocabularyWordSetChooserPanel testableWordSetChooserPanel =
                            new VocabularyWordSetChooserPanel((IVocabularyList)testableList);
                    parentPanel.clear();
                    parentPanel.add(testableWordSetChooserPanel);
                }
                else if (testableList instanceof MathFactListForAParticularOperator)
                {
                    PrimaryOperandChooserPanel testableWordSetChooserPanel =
                            new PrimaryOperandChooserPanel((MathFactListForAParticularOperator)testableList);
                    parentPanel.clear();
                    parentPanel.add(testableWordSetChooserPanel);
                }
            }
        });
    }


    public ListBox getTestableLiistNamesListBox()
    {
        return testableListNamesListBox;
    }

    public void reset()
    {
        clear();
        init();
    }

}
