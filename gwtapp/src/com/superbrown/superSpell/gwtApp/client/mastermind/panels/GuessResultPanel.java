package com.superbrown.superSpell.gwtApp.client.mastermind.panels;

import com.google.gwt.user.client.ui.*;
import com.superbrown.superSpell.gwtApp.client.mastermind.pegs.ResultPeg;

import java.util.ArrayList;
import java.util.List;

/**
 */
public class GuessResultPanel extends VerticalPanel
{
    private final VerticalPanel verticalPanel;
    private List<HorizontalPanel> myMiniPanels = new ArrayList<HorizontalPanel>();

    public GuessResultPanel()
    {
//        setSize("50px", "30px");
        setBorderWidth(1);

        verticalPanel = new VerticalPanel();

        HorizontalPanel horizontalPanel = new HorizontalPanel();
        putMiniPanelOnThis(horizontalPanel);
        putMiniPanelOnThis(horizontalPanel);
        verticalPanel.add(horizontalPanel);

        horizontalPanel = new HorizontalPanel();
        putMiniPanelOnThis(horizontalPanel);
        putMiniPanelOnThis(horizontalPanel);
        verticalPanel.add(horizontalPanel);

        for (Panel panel: myMiniPanels)
        {
            panel.add(createHtmlSpace());
        }

        add(verticalPanel);
    }

    private void putMiniPanelOnThis(HorizontalPanel horizontalPanel)
    {
        HorizontalPanel miniPanel = new HorizontalPanel();
        miniPanel.setSize("13px", "13px");
        myMiniPanels.add(miniPanel);
        horizontalPanel.add(miniPanel);
    }

    public void set(List<ResultPeg> resultPegs)
    {
        for (HorizontalPanel miniPanel : myMiniPanels)
        {
            miniPanel.clear();
            miniPanel.add(createHtmlSpace());
        }

        int i = 0;

        for (ResultPeg resultPeg : resultPegs)
        {
            HorizontalPanel miniPanel = myMiniPanels.get(i++);
            miniPanel.clear();

            Label label = null;

            if (resultPeg == ResultPeg.RIGHT_POSITION)
            {
                label = new Label("R");
            }
            if (resultPeg == ResultPeg.WRONG_POSITION)
            {
                label = new Label("W");
            }

            label.setStyleName("fontSize65");
            miniPanel.add(label);
            miniPanel.setCellVerticalAlignment(label, HasVerticalAlignment.ALIGN_MIDDLE);
            miniPanel.setCellHorizontalAlignment(label, HasHorizontalAlignment.ALIGN_CENTER);

        }
    }

    private HTML createHtmlSpace()
    {
        HTML html = new HTML("&nbsp");
        html.addStyleName("fontSize65");
        return html;
    }
}
