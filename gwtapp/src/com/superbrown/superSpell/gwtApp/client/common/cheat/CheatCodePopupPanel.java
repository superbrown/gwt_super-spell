package com.superbrown.superSpell.gwtApp.client.common.cheat;

import com.google.gwt.event.dom.client.*;
import com.google.gwt.user.client.ui.*;
import com.superbrown.superSpell.gwtApp.client.util.UiUtilities;

/**
 */
public class CheatCodePopupPanel extends PopupPanel
{
    private VerticalPanel panel = new VerticalPanel();
    private PasswordTextBox textBox;
    private Button button;
    private String valueUserEntered;

    public CheatCodePopupPanel()
    {
        super();
        init();
    }

    private void init()
    {
        panel.addStyleName("blackFont");
        panel.add(new Label("Enter cheat code:"));
        textBox = new PasswordTextBox();
        panel.add(textBox);
        button = new Button("OK");
        button.addClickHandler(new ClickHandler()
        {
            public void onClick(ClickEvent clickEvent)
            {
                valueUserEntered = textBox.getValue();
                hide();
            }
        });

        panel.add(button);

        textBox.addKeyPressHandler(
                new KeyPressHandler()
                {

                    @Override
                    public void onKeyPress(KeyPressEvent event)
                    {
                        if (UiUtilities.isTheEnterKey(event))
                        {
                            button.click();
                        }
                    }
                }
        );

        this.add(panel);
        this.show();
        textBox.setFocus(true);
    }

    public String getValueUserEntered()
    {
        return valueUserEntered;
    }
}

