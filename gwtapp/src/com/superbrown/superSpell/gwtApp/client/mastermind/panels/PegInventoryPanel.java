package com.superbrown.superSpell.gwtApp.client.mastermind.panels;

import com.allen_sauer.gwt.dnd.client.DragController;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;
import com.superbrown.superSpell.gwtApp.client.mastermind.pegs.Peg;
import com.superbrown.superSpell.gwtApp.client.mastermind.pegs.PegLabel;
import com.superbrown.superSpell.gwtApp.client.mastermind.pegs.PegWidget;

/**
 */
public class PegInventoryPanel extends VerticalPanel
{
    private DragController dragController;

    public static Peg[] pegInventory = new Peg[]
            {Peg.G, Peg.O, Peg.B, Peg.R, Peg.W, Peg.Y};


    public PegInventoryPanel()
    {
        setHorizontalAlignment(HasHorizontalAlignment.ALIGN_CENTER);
        setSpacing(5);

        for (int i = 0; i < pegInventory.length; i++)
        {
            Peg peg = pegInventory[i];
            PegWidget pegWidget = new PegWidget(new PegLabel(peg));
            add(pegWidget);
        }
    }

    /**
     * Overloaded method that makes widgets draggable.
     *
     * @param w the widget to be added are made draggable
     */
    public void add(PegWidget w)
    {
        super.add(w);

        if (getDragController() != null)
        {
            getDragController().makeDraggable(w);
        }
    }

    /**
     * Removed widgets that are instances of {@link PegWidget} are immediately replaced with a
     * cloned copy of the original.
     *
     * @param w the widget to remove
     */
    @Override
    public boolean remove(Widget w)
    {
        int index = getWidgetIndex(w);
        if (index != -1 && w instanceof PegWidget)
        {
            PegWidget clone = ((PegWidget) w).cloneWidget();
            dragController.makeDraggable(clone);
            insert(clone, index);
        }
        return super.remove(w);
    }

    public DragController getDragController()
    {
        return dragController;
    }

    public void setDragController(DragController dragController)
    {
        this.dragController = dragController;

        for (Widget widget : getChildren())
        {
            if (widget instanceof PegWidget)
            {
                dragController.makeDraggable(widget);
            }
        }
    }

    public static Peg[] getPegInventory()
    {
        return pegInventory;
    }
}
