package com.superbrown.superSpell.gwtApp.client.spelling;

import com.google.gwt.event.dom.client.KeyPressHandler;
import com.google.gwt.user.client.ui.RadioButton;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.superbrown.superSpell.gwtApp.shared.TestableItemList;

import java.util.ArrayList;
import java.util.List;

/**
 */
public class SpellingWordSetRadioButtonPanel extends VerticalPanel
{
    List<RadioButtonAndSpellingWordSet> radioButtonsAndSpellingWordSets =
            new ArrayList<RadioButtonAndSpellingWordSet>();

    private KeyPressHandler keyPressHandler;
    private String textStyle;


    public SpellingWordSetRadioButtonPanel(List<TestableItemList> spellingWordSets, String textStyle)
    {
        this.textStyle = textStyle;

        for (TestableItemList spellingWordSet : spellingWordSets)
        {
            RadioButton radioButton = new RadioButton("anyNameWillDo", spellingWordSet.toString());
            radioButton.addStyleName(this.textStyle);

            RadioButtonAndSpellingWordSet radioButtonAndSpellingWordSet =
                    new RadioButtonAndSpellingWordSet(radioButton, spellingWordSet);

            this.radioButtonsAndSpellingWordSets.add(radioButtonAndSpellingWordSet);

            add(radioButton);

            if (keyPressHandler != null)
            {
                radioButtonAndSpellingWordSet.getRadioButton().addKeyPressHandler(keyPressHandler);
            }
        }
    }


    public void selectTheFirstActiveButton()
    {
        // clear any existing selection
        for (RadioButtonAndSpellingWordSet radioButtonAndSpellingWordSet : radioButtonsAndSpellingWordSets)
        {
            RadioButton radioButton = radioButtonAndSpellingWordSet.getRadioButton();
            radioButton.setValue(false);
        }

        for (RadioButtonAndSpellingWordSet radioButtonAndSpellingWordSet : radioButtonsAndSpellingWordSets)
        {
            RadioButton radioButton = radioButtonAndSpellingWordSet.getRadioButton();

            if (radioButton.isEnabled() == true)
            {
                radioButton.setValue(true);
                break;
            }
        }
    }

    public TestableItemList getSelectedValue()
    {
        for (int i = 0; i < getWidgetCount(); i++)
        {
            RadioButton radioButton = (RadioButton) getWidget(i);

            if (isChecked(radioButton))
            {
                return radioButtonsAndSpellingWordSets.get(i).getSpellingWordSet();
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

        for (int i = 0; i < getWidgetCount(); i++)
        {
            RadioButton radioButton = (RadioButton) getWidget(i);
            radioButton.addKeyPressHandler(keyPressHandler);
        }
    }

    public void setFocusOnSelectedButton()
    {
        for (RadioButtonAndSpellingWordSet radioButtonsAndSpellingWordSet : radioButtonsAndSpellingWordSets)
        {
            RadioButton radioButton = radioButtonsAndSpellingWordSet.getRadioButton();
            if (radioButton.getValue() == true)
            {
                radioButton.setFocus(true);
                break;
            }
        }
    }

    public void refreshButtonStates()
    {
        for (RadioButtonAndSpellingWordSet radioButtonsAndSpellingWordSet : radioButtonsAndSpellingWordSets)
        {
            radioButtonsAndSpellingWordSet.refreshButtonState();
        }
    }

    public boolean allButtonsAreDisabled()
    {
        for (RadioButtonAndSpellingWordSet radioButtonsAndSpellingWordSet : radioButtonsAndSpellingWordSets)
        {
            if (radioButtonsAndSpellingWordSet.getRadioButton().isEnabled())
            {
                return false;
            }
        }

        return true;
    }

    private class RadioButtonAndSpellingWordSet
    {
        private RadioButton radioButton;
        private TestableItemList spellingWordSet;

        public RadioButtonAndSpellingWordSet()
        {
        }

        private RadioButtonAndSpellingWordSet(RadioButton radioButton, TestableItemList spellingWordSet)
        {
            this.radioButton = radioButton;
            this.spellingWordSet = spellingWordSet;
        }

        public RadioButton getRadioButton()
        {
            return radioButton;
        }

        public TestableItemList getSpellingWordSet()
        {
            return spellingWordSet;
        }

        public void refreshButtonState()
        {
            if (spellingWordSet.isGotItOnTheFirstTry())
            {
                radioButton.setEnabled(false);
                radioButton.addStyleName("disabledColor");
                
                String currentText = radioButton.getText();
                if (!currentText.contains("(PASSED)"))
                {
                    radioButton.setText("(PASSED) " + currentText);
                }
            }
            else
            {
                getSpellingWordSet().reset(true);
                radioButton.setEnabled(true);
            }
        }
    }
}
