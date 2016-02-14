package com.superbrown.superSpell.gwtApp.client.common;

import com.google.gwt.event.dom.client.KeyPressHandler;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.RadioButton;
import com.google.gwt.user.client.ui.VerticalPanel;

import java.util.ArrayList;
import java.util.List;

/**
 */
public class StringValueRadioButtonPanel extends HorizontalPanel
{
    private KeyPressHandler keyPressHandler;
    private boolean hasTwoColumns;
    List<RadioButtonAndStringValue> radioButtonAndStringValues =
            new ArrayList<RadioButtonAndStringValue>();
    private String textStyle;

    public StringValueRadioButtonPanel(List<String> values, String textStyle)
    {
        this.textStyle = textStyle;

        // If there are more than 10 choices, split it up into two columns
        hasTwoColumns = values.size() > 10;

        if (hasTwoColumns)
        {
            VerticalPanel[] verticalPanels = new VerticalPanel[] {new VerticalPanel(), new VerticalPanel()};
            add(verticalPanels[0]);
            verticalPanels[1].addStyleName("leftMargin50");
            add(verticalPanels[1]);

            int halfTheSize = values.size() / 2;

            int i = 0;
            for (String value : values)
            {
                int indexOfPanelToAddTo;
                if (i < halfTheSize)
                {
                    indexOfPanelToAddTo = 0;
                }
                else
                {
                    indexOfPanelToAddTo = 1;
                }

                RadioButton radioButton = new RadioButton("anyNameWillDo", value);
                verticalPanels[indexOfPanelToAddTo].add(radioButton);
                radioButtonAndStringValues.add(new RadioButtonAndStringValue(radioButton, value));

                i++;
            }
        }
        else
        {
            VerticalPanel verticalPanel = new VerticalPanel();
            add(verticalPanel);

            for (String value : values)
            {
                RadioButton radioButton = new RadioButton("anyNameWillDo", value);
                verticalPanel.add(radioButton);
                radioButtonAndStringValues.add(new RadioButtonAndStringValue(radioButton, value));
            }
        }

        for (RadioButtonAndStringValue radioButtonAndStringValue : radioButtonAndStringValues)
        {
            RadioButton radioButton = radioButtonAndStringValue.getRadioButton();

            radioButton.addStyleName(this.textStyle);

            if (keyPressHandler != null)
            {
                radioButton.addKeyPressHandler(keyPressHandler);
            }
        }
    }

    public String getSelectedValue()
    {
        for (RadioButtonAndStringValue radioButtonAndStringValue : radioButtonAndStringValues)
        {
            if (isChecked(radioButtonAndStringValue.getRadioButton()))
            {
                return radioButtonAndStringValue.getString();
            }
        }

        return null;
    }

    private boolean isChecked(RadioButton radioButton)
    {
        return radioButton.getValue();
    }

    public void setKeyPressHandler(KeyPressHandler keyPressHandler)
    {
        this.keyPressHandler = keyPressHandler;

        for (RadioButtonAndStringValue radioButtonAndStringValue : radioButtonAndStringValues)
        {
            radioButtonAndStringValue.getRadioButton().addKeyPressHandler(keyPressHandler);
        }
    }

    public void highlightSelection(String correctSpelling)
    {
        for (RadioButtonAndStringValue radioButtonAndStringValue : radioButtonAndStringValues)
        {
            RadioButton radioButton = radioButtonAndStringValue.getRadioButton();

            if (radioButton.getText().equals(correctSpelling))
            {
                radioButton.addStyleName("highlight");
                break;
            }
        }
    }

    public void setFocusOnAndSelectFirstButton()
    {
        if (radioButtonAndStringValues.size() == 0)
        {
            return;
        }

        RadioButton firstRadioButton = radioButtonAndStringValues.get(0).getRadioButton();
        firstRadioButton.setFocus(true);
        firstRadioButton.setValue(true);
    }

    private class RadioButtonAndStringValue
    {
        private RadioButton radioButton;
        private String string;

        private RadioButtonAndStringValue(RadioButton radioButton, String string)
        {
            this.radioButton = radioButton;
            this.string = string;
        }

        public RadioButtonAndStringValue()
        {
        }

        public RadioButton getRadioButton()
        {
            return radioButton;
        }

        public String getString()
        {
            return string;
        }
    }
}
