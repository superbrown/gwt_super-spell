package com.superbrown.superSpell.gwtApp.client.mastermind.panels;

import com.allen_sauer.gwt.dnd.client.DragContext;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Timer;
import com.google.gwt.user.client.ui.*;
import com.superbrown.superSpell.gwtApp.client.util.UiUtilities;
import com.superbrown.superSpell.gwtApp.client.mastermind.Guess;
import com.superbrown.superSpell.gwtApp.client.mastermind.pegs.PegSequence;
import com.superbrown.superSpell.gwtApp.client.mastermind.pegs.PegWidget;
import com.superbrown.superSpell.gwtApp.client.mastermind.pegs.ResultPeg;

import java.util.ArrayList;
import java.util.List;

/**
 */
public class GuessPanel extends AbsolutePanel
{
    private GuessResultPanel guessResultPanel;
    private List<IndividualPagPanel> individualPagPanels =
            new ArrayList <IndividualPagPanel>();

    private HorizontalPanel myMainPanel;
    private Button guessButton;
    private GuessPanels guessPanels;
    private PegSequence sequenceToBeGuessed;


    public GuessPanel(GuessPanels guessPanels, PegSequence sequenceToBeGuessed)
    {
        super();

        this.sequenceToBeGuessed = sequenceToBeGuessed;

        this.guessPanels = guessPanels;

        myMainPanel = new HorizontalPanel();
        myMainPanel.setSpacing(0);

        myMainPanel.setBorderWidth(0);
        myMainPanel.setSize("150px", "30px");

        guessResultPanel = new GuessResultPanel();
        guessResultPanel.addStyleName("leftMargin10 rightMargin10 whiteChalk");
        
        myMainPanel.add(guessResultPanel);
        myMainPanel.setCellVerticalAlignment(guessResultPanel, HasVerticalAlignment.ALIGN_MIDDLE);

        add(new IndividualPagPanel());
        add(new IndividualPagPanel());
        add(new IndividualPagPanel());
        add(new IndividualPagPanel());

        guessButton = new Button("Guess");
        guessButton.setVisible(false);
        guessButton.addStyleName("leftMargin10 rightMargin10");

        guessButton.addClickHandler(new ClickHandler()
        {
            public void onClick(ClickEvent clickEvent)
            {
                guessButton.setEnabled(false);
                submitAnswer();
            }
        });

        myMainPanel.add(guessButton);
        myMainPanel.setCellVerticalAlignment(guessButton, HasVerticalAlignment.ALIGN_MIDDLE);

        add(myMainPanel);
    }

    void submitAnswer()
    {
        Guess guess = new Guess();

        for (IndividualPagPanel individualPagPanel : individualPagPanels)
        {
            guess.addPeg(individualPagPanel.getPeg());
        }

        final List<ResultPeg> results = guess.getResults(sequenceToBeGuessed);

        RootPanel.get().addStyleName("busyCursor");

        Timer timer = new Timer()
            {
                public void run()
                {
                    RootPanel.get().removeStyleName("busyCursor");
                    RootPanel.get().addStyleName("defaultCursor");

                    boolean userCrackedTheCode = userCrackedTheCode(results);

                    guessResultPanel.set(results);

                    if (userCrackedTheCode)
                    {
                        guessButton.setVisible(false);
                        guessPanels.userHasCrackedTheCode();
                    }
                    else
                    {
                        guessPanels.advanceToTheNextGuessPanel();
                    }
                }
            };

        timer.schedule(500); 
    }

    private boolean userCrackedTheCode(List<ResultPeg> results)
    {
        boolean allCorrect = false;

        if (results.size() == 4)
        {
            allCorrect = true;

            for (ResultPeg result : results)
            {
                if (result == ResultPeg.WRONG_POSITION)
                {
                    allCorrect = false;
                    break;
                }
            }
        }
        return allCorrect;
    }

    public void add(IndividualPagPanel individualPagPanel)
    {
        myMainPanel.add(individualPagPanel);
        myMainPanel.setCellVerticalAlignment(individualPagPanel, HasVerticalAlignment.ALIGN_MIDDLE);

        individualPagPanels.add(individualPagPanel);
    }

    public boolean contains(int mouseX, int mouseY)
    {
        return UiUtilities.intersects(mouseX, mouseY, this);
    }

    public void handleDrop(DragContext dragContext) throws CannotHandle
    {
        if (!(dragContext.draggable instanceof PegWidget))
        {
            throw new CannotHandle();
        }

        PegWidget pegWidget = (PegWidget) dragContext.draggable;
        
        if (!contains(dragContext.mouseX, dragContext.mouseY))
        {
            throw new CannotHandle();
        }

        for (IndividualPagPanel individualPagPanel : individualPagPanels)
        {
            if (individualPagPanel.contains(dragContext.mouseX, dragContext.mouseY))
            {
                individualPagPanel.clear();
                individualPagPanel.add(pegWidget);

                individualPagPanel.setCellVerticalAlignment(dragContext.draggable, HasVerticalAlignment.ALIGN_MIDDLE);
                individualPagPanel.setCellHorizontalAlignment(dragContext.draggable, HasHorizontalAlignment.ALIGN_CENTER);

                if (hasFourPegs())
                {
                    guessButton.setEnabled(true);
                }

                return;
            }
        }

        throw new CannotHandle();
    }

    public void makeActive()
    {
        removeStyleName("whiteChalk");
        addStyleName("yellowChalk");
        guessButton.setEnabled(false);
        guessButton.setVisible(true);
    }

    public void makeInactive()
    {
        removeStyleName("yellowChalk");
        addStyleName("whiteChalk");
        guessButton.setVisible(false);
    }

    public boolean hasFourPegs()
    {
        for (IndividualPagPanel individualPagPanel : individualPagPanels)
        {
            if (individualPagPanel.getPeg() == null)
            {
                return false;
            }
        }

        return true;
    }
}
