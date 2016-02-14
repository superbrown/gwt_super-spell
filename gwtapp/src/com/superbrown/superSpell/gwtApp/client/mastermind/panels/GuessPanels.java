package com.superbrown.superSpell.gwtApp.client.mastermind.panels;

import com.google.gwt.user.client.ui.VerticalPanel;

import java.util.ArrayList;
import java.util.List;

/**
 */
public class GuessPanels extends VerticalPanel
{
    private List<GuessPanel> guessPanels;
    private GuessPanel activePanel;
    private MastermindGamePanel mastermindGamePanel;

    public GuessPanels(MastermindGamePanel mastermindGamePanel, int numberOfPanels)
    {
        super();

        this.mastermindGamePanel = mastermindGamePanel;

        setBorderWidth(0);
        setSpacing(0);

        guessPanels = new ArrayList<GuessPanel>();

        for (int i = 0; i < numberOfPanels; i++)
        {
            GuessPanel panel = new GuessPanel(this, mastermindGamePanel.getSequenceToBeGuessed());
            panel.makeInactive();
            
            add(panel);
        }

        setActivePanel(guessPanels.get(0));
        guessPanels.get(0).makeActive();
    }

    public void add(GuessPanel guessPanel)
    {
        super.add(guessPanel);
        guessPanels.add(0, guessPanel);
    }

    public GuessPanel getActivePanel()
    {
        return activePanel;
    }

    public void setActivePanel(GuessPanel activePanel)
    {
        GuessPanel currentlyActivePanel = getActivePanel();
        if (currentlyActivePanel != null)
        {
            currentlyActivePanel.makeInactive();
        }
        this.activePanel = activePanel;
        activePanel.makeActive();
    }

    public void advanceToTheNextGuessPanel()
    {
        if (getActivePanel() == getLastPanel())
        {
            getActivePanel().makeInactive();
            mastermindGamePanel.userHasLostTheGame();
        }

        int currentlySelectedPanelsIndex = guessPanels.indexOf(getActivePanel());
        setActivePanel(guessPanels.get(currentlySelectedPanelsIndex + 1));
    }

    private GuessPanel getLastPanel()
    {
        return guessPanels.get(guessPanels.size() - 1);
    }

    public void userHasCrackedTheCode()
    {
        mastermindGamePanel.userHasCrackedTheCode();
    }
}
