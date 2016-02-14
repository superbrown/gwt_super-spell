package com.superbrown.superSpell.gwtApp.client.mathFacts;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.KeyPressEvent;
import com.google.gwt.event.dom.client.KeyPressHandler;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.superbrown.superSpell.gwtApp.client.SuperSpell;
import com.superbrown.superSpell.gwtApp.client.common.IResetable;
import com.superbrown.superSpell.gwtApp.client.common.audio.SongPlayerPanel_usingMidiJs;
import com.superbrown.superSpell.gwtApp.client.util.UiUtilities;
import com.superbrown.superSpell.gwtApp.shared.ITestable;
import com.superbrown.superSpell.gwtApp.shared.ITestableItem;
import com.superbrown.superSpell.gwtApp.shared.mathFacts.MathFactListForAParticularOperator;
import com.superbrown.superSpell.gwtApp.shared.mathFacts.MathFactListForAParticularPrimaryOperand;

import java.util.ArrayList;
import java.util.List;


/**
 */
public class PrimaryOperandChooserPanel extends VerticalPanel implements IResetable
{
    private MathFactListForAParticularOperator mathFactListForAParticularOperator;
    private TestAdministratorPanel testAdministratorPanel;
    private PrimaryOperandRadioButtonPanel primaryOperandRadiioButtonPanel;
    private MathFactListForAParticularPrimaryOperand mathFactSetBeingTestedForAParticularPrimaryOperand;

    public PrimaryOperandChooserPanel(MathFactListForAParticularOperator mathFactListForAParticularOperator)
    {
        this.mathFactListForAParticularOperator = mathFactListForAParticularOperator;
        init();
    }

    private void init()
    {
        SuperSpell.resetDoodleMessage();
        SuperSpell.addAnotherPersonToStayAfterSchoolList();

        clear();

        Label instructions = new Label();
        instructions.setText("Which set do you want to practice?");
        instructions.setStyleName("yellowChalk fontSize150");
        add(instructions);

        if (primaryOperandRadiioButtonPanel == null)
        {
            primaryOperandRadiioButtonPanel =
                    new PrimaryOperandRadioButtonPanel(mathFactListForAParticularOperator, "whiteChalk fontSize130");
        }
        else
        {
            primaryOperandRadiioButtonPanel.refreshButtonStates();
        }

        add(primaryOperandRadiioButtonPanel);

        VerticalPanel buttonPanel = new VerticalPanel();
        buttonPanel.addStyleName("topMargin10");

        VerticalPanel verticalPanel = new VerticalPanel();
        verticalPanel.addStyleName("topMargin10");

        if (primaryOperandRadiioButtonPanel.allButtonsAreDisabled())
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

        primaryOperandRadiioButtonPanel.setKeyPressHandler(new KeyPressHandler(){

            public void onKeyPress(KeyPressEvent keyPressEvent)
            {
                if (isTheEnterKey(keyPressEvent))
                {
                    handleUserSubmission(caller);
                }
            }
        });

        selectAndFocusOnFirstActiveButton();
    }

    private void handleWhenUserGotAllWordsCorrectTheFirstTime(VerticalPanel verticalPanel)
    {
        Label label = new Label("Great work!!");
        label.addStyleName("topMargin10 fontSize320 yellowChalk blink");
        verticalPanel.add(label);

        label = new Label("You have these math facts pretty well mastered.");
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
        primaryOperandRadiioButtonPanel.selectTheFirstActiveButton();
        primaryOperandRadiioButtonPanel.setFocusOnSelectedButton();
    }

    private boolean isTheEnterKey(KeyPressEvent keyPressEvent)
    {
        return UiUtilities.isTheEnterKey(keyPressEvent);
    }

    private void handleUserSubmission(IResetable caller)
    {
        mathFactSetBeingTestedForAParticularPrimaryOperand = primaryOperandRadiioButtonPanel.getSelectedValue();

        clear();

        List<ITestableItem> testableItems = new ArrayList<ITestableItem>();

        for (ITestable testable : mathFactSetBeingTestedForAParticularPrimaryOperand.getTestables())
        {
            testableItems.add((ITestableItem)testable);
        }

        testAdministratorPanel = new TestAdministratorPanel(caller, testableItems);
        add(testAdministratorPanel);
    }

    public void reset()
    {
        init();
        selectAndFocusOnFirstActiveButton();
    }
}
