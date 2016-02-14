package com.superbrown.superSpell.gwtApp.client.mathFacts;

import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;
import com.superbrown.superSpell.gwtApp.client.common.IPanelInitializationListener;
import com.superbrown.superSpell.gwtApp.shared.mathFacts.MathFact;

/**
 */
public abstract class TestQuestionPanel extends com.superbrown.superSpell.gwtApp.client.common.TestQuestionPanel
{
    public TestQuestionPanel(MathFact mathFact, IPanelInitializationListener panelInitializationListener)
    {
        super(mathFact, panelInitializationListener);
    }

    protected Widget createTestQuestionWidget()
    {
        VerticalPanel panel = new VerticalPanel();

        MathFact mathFact = (MathFact)getTestableItem();

        Label label = new Label("Fill in the blank.");
        label.setStyleName("yellowChalk fontSize150");
        panel.add(label);

        label = new Label(mathFact.getTestQuestion());
        label.addStyleName("bold bottomMargin fontSize150");
        label.setWidth("300");
        label.setWordWrap(true);
        panel.add(label);

        return panel;
    }
}