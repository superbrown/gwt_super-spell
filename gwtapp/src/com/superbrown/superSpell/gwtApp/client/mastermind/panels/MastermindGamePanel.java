package com.superbrown.superSpell.gwtApp.client.mastermind.panels;

import com.allen_sauer.gwt.dnd.client.PickupDragController;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.*;
import com.superbrown.superSpell.gwtApp.client.mastermind.pegs.Peg;
import com.superbrown.superSpell.gwtApp.client.mastermind.pegs.PegSequence;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 */
public class MastermindGamePanel extends AbsolutePanel
{
    private PegSequence sequenceToBeGuessed;

    private GuessPanels guessPanels;
    private HorizontalPanel myMainPanel;
    private PegInventoryPanel pegInventoryPanel;

    public MastermindGamePanel()
    {
        super();
        addStyleName("topMargin10");
        init();
    }

    private void init()
    {
        Label label = new Label("See if you can crack the code.");
        label.addStyleName("topMargin10 bottomMargin fontSize150 yellowChalk");
        addStyleName("bottomMargin");
        add(label);

        generateSequenceToBeGuessed();

        myMainPanel = new HorizontalPanel();
        myMainPanel.setSpacing(0);

        // workaround for GWT issue 1813
        // http://code.google.com/p/google-web-toolkit/issues/detail?id=1813
        this.getElement().getStyle().setProperty("position" , "relative");

        pegInventoryPanel = new PegInventoryPanel();
        pegInventoryPanel.setSpacing(0);
        pegInventoryPanel.setBorderWidth(1);
        pegInventoryPanel.addStyleName("rightMargin10");
        myMainPanel.add(pegInventoryPanel);
        myMainPanel.setCellVerticalAlignment(pegInventoryPanel, HasVerticalAlignment.ALIGN_MIDDLE);

        guessPanels = new GuessPanels(this, 10);
        myMainPanel.add(guessPanels);

        initDragController();

        add(myMainPanel);
    }

    private void initDragController()
    {
        PickupDragController pickupDragController = createDragController();
        pegInventoryPanel.setDragController(pickupDragController);
    }

    private PickupDragController createDragController()
    {
        final PickupDragController pickupDragController = new PickupDragController(this, true)
        {
            @Override
            public void dragEnd()
            {
                super.dragEnd();

                try
                {
                    guessPanels.getActivePanel().handleDrop(context);
                }
                catch (CannotHandle cannotHandle)
                {
                    context.draggable.removeFromParent();
                }
            }
        };
        pickupDragController.setBehaviorMultipleSelection(false);
        pickupDragController.setBehaviorDragProxy(false);
        pickupDragController.setBehaviorConstrainedToBoundaryPanel(true);
        pickupDragController.setBehaviorBoundaryPanelDrop(true);
        return pickupDragController;
    }

    public PegSequence getSequenceToBeGuessed()
    {
        return sequenceToBeGuessed;
    }

    public GuessPanels getGuessPanels()
    {
        return guessPanels;
    }


    private void generateSequenceToBeGuessed()
    {
//        List<Peg> pegSequence = new ArrayList<Peg>();
//        pegSequence.add(Peg.G);
//        pegSequence.add(Peg.G);
//        pegSequence.add(Peg.G);
//        pegSequence.add(Peg.G);
        List<Peg> pegSequence = generateRandomSequence(4, PegInventoryPanel.getPegInventory());

        sequenceToBeGuessed = new PegSequence();
        sequenceToBeGuessed.addPegs(pegSequence);
    }

    public static List<Peg> generateRandomSequence(int desiredLength, Peg[] pegInventory)
    {
        Random randomGenerator;

        try
        {
            // Try to get this first...
            randomGenerator = new Random(System.currentTimeMillis());
        }
        catch (Exception e)
        {
            // Failing that use this...
            randomGenerator = new Random();
            randomGenerator.setSeed(System.currentTimeMillis());
        }

        List<Peg> pegSequence = new ArrayList<Peg>();

        for (int i = 0; i < desiredLength; i++)
        {
            int randomIndex = randomGenerator.nextInt(pegInventory.length - 1);
            pegSequence.add(pegInventory[randomIndex]);
        }

        return pegSequence;
    }

    public void userHasCrackedTheCode()
    {
        Label label = new Label("Congratulations!!");
        label.addStyleName("topMargin10 fontSize320 yellowChalk");
        add(label);

        label = new Label("You cracked the code.");
        label.addStyleName("fontSize150 yellowChalk");
        add(label);

        addPlayAgainButton();
    }

    public void userHasLostTheGame()
    {
        Label label = new Label("Oh, you couldn't crack the code.");
        label.addStyleName("topMargin10 fontSize150 yellowChalk");
        add(label);

        HorizontalPanel horizontalPanel = new HorizontalPanel();
        horizontalPanel.addStyleName("topMargin10");

        label = new Label("The correct code was: ");
        label.addStyleName("fontSize150 yellowChalk");
        horizontalPanel.add(label);

        horizontalPanel.add(new HTML("&nbsp&nbsp&nbsp"));

        label = new Label(sequenceToBeGuessed.toString());
        horizontalPanel.add(label);

        add(horizontalPanel);

        label = new Label("Better luck next time!");
        label.addStyleName("fontSize150 yellowChalk");
        add(label);

        addPlayAgainButton();
    }

    private void addPlayAgainButton()
    {
        Button resetButton = new Button("Play Again");
        resetButton.addStyleName("topMargin10");
        resetButton.addClickHandler(new ClickHandler()
            {
                public void onClick(ClickEvent clickEvent)
                {
                    reset();
                }
            });
        add(resetButton);
    }

    public void reset()
    {
        clear();
        init();
    }

}
