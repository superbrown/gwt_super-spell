package com.superbrown.superSpell.gwtApp.client.common.chooserPanels;

import com.google.gwt.event.dom.client.ChangeEvent;
import com.google.gwt.event.dom.client.ChangeHandler;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.*;
import com.superbrown.superSpell.gwtApp.client.SuperSpell;
import com.superbrown.superSpell.gwtApp.client.common.IResetable;
import com.superbrown.superSpell.gwtApp.client.services.ISuperSpellService;

import java.util.List;

/**
 */
public class SubjectChooserPanel extends VerticalPanel implements IResetable
{
    private List<String> schoolClassNames;
    private ListBox schoolClassNamesListBox;
    private String MAKE_A_SELECTION;
    private Panel parentPanel;
    private IResetable caller;

    public SubjectChooserPanel(List<String> schoolClassNames, IResetable caller, Panel parentPanel)
    {
        this.schoolClassNames = schoolClassNames;
        this.caller = caller;
        this.parentPanel = parentPanel;
        init();
    }

    private void init()
    {
        clear();

        Label instructions = new Label();
        instructions.setText("What subject do you want?");
        instructions.setStyleName("yellowChalk fontSize150");
        add(instructions);

        schoolClassNamesListBox = new ListBox();
        MAKE_A_SELECTION = "Make a selection";
        schoolClassNamesListBox.addItem(MAKE_A_SELECTION);

        for (String spellingListName : schoolClassNames)
        {
            schoolClassNamesListBox.addItem(spellingListName);
        }

        add(schoolClassNamesListBox);

        final IResetable caller = this;

        schoolClassNamesListBox.addChangeHandler(new ChangeHandler()
        {

            public void onChange(ChangeEvent changeEvent)
            {
                handleUserSubmission(caller);
            }
        });

    }


    private void handleUserSubmission(final IResetable caller)
    {
        final String schoolClassName =
                schoolClassNamesListBox.getItemText(schoolClassNamesListBox.getSelectedIndex());
                
        if (schoolClassName.equals(MAKE_A_SELECTION))
        {
            return;
        }

        openTestableListChooserPanel(caller, schoolClassName);
    }

    private void openTestableListChooserPanel(final IResetable caller, final String schoolClassName)
    {
        ISuperSpellService.App.getInstance().getTestableListNames(schoolClassName, new AsyncCallback<List<String>>()
        {
            public void onFailure(Throwable throwable)
            {
                // try again
                openTestableListChooserPanel(caller, schoolClassName);
            }

            public void onSuccess(List<String> testableListNames)
            {
                SuperSpell.setSchoolClassName(schoolClassName);

                TestableListChooserPanel testableListChooserPanel =
                        new TestableListChooserPanel(schoolClassName, testableListNames, caller);
                parentPanel.clear();
                parentPanel.add(testableListChooserPanel);
                testableListChooserPanel.getTestableLiistNamesListBox().setFocus(true);
            }
        });
    }


    public ListBox getSchoolClassNamesListBox()
    {
        return schoolClassNamesListBox;
    }

    public void reset()
    {
        clear();
        init();
    }

}