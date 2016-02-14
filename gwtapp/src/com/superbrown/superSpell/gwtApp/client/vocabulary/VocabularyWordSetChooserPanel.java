package com.superbrown.superSpell.gwtApp.client.vocabulary;

import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.superbrown.superSpell.gwtApp.client.SuperSpell;
import com.superbrown.superSpell.gwtApp.client.common.IResetable;
import com.superbrown.superSpell.gwtApp.client.common.audio.SongPlayerPanel_usingMidiJs;
import com.superbrown.superSpell.gwtApp.shared.ITestable;
import com.superbrown.superSpell.gwtApp.shared.ITestableItem;
import com.superbrown.superSpell.gwtApp.shared.vocabulary.IVocabularyList;

import java.util.ArrayList;
import java.util.List;


/**
 */
public class VocabularyWordSetChooserPanel extends VerticalPanel implements IResetable
{
    private IVocabularyList vocabularyList;
    private TestAdministratorPanel testAdministratorPanel;

    public VocabularyWordSetChooserPanel(IVocabularyList vocabularyList)
    {
        this.vocabularyList = vocabularyList;
        init();
    }

    private void init()
    {
        SuperSpell.resetDoodleMessage();
        SuperSpell.addAnotherPersonToStayAfterSchoolList();

        clear();

        VerticalPanel buttonPanel = new VerticalPanel();
        buttonPanel.addStyleName("topMargin10");

        VerticalPanel verticalPanel = new VerticalPanel();
        verticalPanel.addStyleName("topMargin10");

        List<ITestableItem> testableItems = new ArrayList<ITestableItem>();
        for (ITestable testableItem : vocabularyList.getTestables())
        {
            testableItems.add((ITestableItem)testableItem);
        }

        testAdministratorPanel = new TestAdministratorPanel(this, testableItems);
        add(testAdministratorPanel);
    }

    public void reset()
    {
        clear();

        Label label = new Label("Great work!!");
        label.addStyleName("topMargin10 fontSize320 yellowChalk blink");
        add(label);

        label = new Label("You have these words pretty well mastered.");
        label.addStyleName("fontSize150 yellowChalk");
        add(label);

        label = new Label("If you want to start over, hit your browser's refresh button");
        label.addStyleName("fontSize150 yellowChalk");
        add(label);

        label = new Label("or the \"start over\" link in the upper right corner.");
        label.addStyleName("fontSize150 yellowChalk");
        add(label);

        add(new SongPlayerPanel_usingMidiJs());
    }
}
