package com.superbrown.superSpell.gwtApp.client.mastermind.panels;

import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.superbrown.superSpell.gwtApp.client.util.UiUtilities;
import com.superbrown.superSpell.gwtApp.client.mastermind.pegs.Peg;
import com.superbrown.superSpell.gwtApp.client.mastermind.pegs.PegWidget;

/**
 */
public class IndividualPagPanel extends VerticalPanel
{
    private PegWidget pegWidget;

    public IndividualPagPanel()
    {
        super();

        setBorderWidth(1);
        setSize("30px", "30px");

        HTML html = new HTML("&nbsp;");

        add(html);
    }

    public Peg getPeg()
    {
        if (pegWidget == null)
        {
            return null;
        }

        return pegWidget.getPeg();
    }

    public boolean contains(int mouseX, int mouseY)
    {
        return UiUtilities.intersects(mouseX, mouseY, this);
    }

    public void add(PegWidget pegWidget)
    {
        super.clear();
        super.add(pegWidget);
        this.pegWidget = pegWidget;
    }
}