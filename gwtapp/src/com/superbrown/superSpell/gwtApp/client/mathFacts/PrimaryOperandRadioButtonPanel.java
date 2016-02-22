package com.superbrown.superSpell.gwtApp.client.mathFacts;

import com.google.gwt.event.dom.client.KeyPressHandler;
import com.google.gwt.user.client.ui.RadioButton;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.superbrown.superSpell.gwtApp.shared.mathFacts.MathFactListForAParticularOperator;
import com.superbrown.superSpell.gwtApp.shared.mathFacts.MathFactListForAParticularPrimaryOperand;

import java.util.ArrayList;
import java.util.List;

/**
 */
public class PrimaryOperandRadioButtonPanel extends VerticalPanel
{
    List<RadioButtonAndMathFact> radioButtonsAndMathFacts =
            new ArrayList<RadioButtonAndMathFact>();

    private KeyPressHandler keyPressHandler;
    private String textStyle;


    public PrimaryOperandRadioButtonPanel(
            MathFactListForAParticularOperator mathFactListForAParticularOperator,
            String textStyle)
    {
        this.textStyle = textStyle;

        for (MathFactListForAParticularPrimaryOperand mathFactListForAParticularPrimaryOperand : mathFactListForAParticularOperator.getMathFactLists())
        {
            RadioButton radioButton = new RadioButton("anyNameWillDo", mathFactListForAParticularPrimaryOperand.getName());
            radioButton.addStyleName(this.textStyle);

            RadioButtonAndMathFact radioButtonAndMathFact =
                    new RadioButtonAndMathFact(radioButton, mathFactListForAParticularPrimaryOperand);

            this.radioButtonsAndMathFacts.add(radioButtonAndMathFact);

            add(radioButton);

            if (keyPressHandler != null)
            {
                radioButtonAndMathFact.getRadioButton().addKeyPressHandler(keyPressHandler);
            }
        }
    }

    public void refreshButtonStates()
    {
        for (RadioButtonAndMathFact radioButtonAndMathFact : radioButtonsAndMathFacts)
        {
            radioButtonAndMathFact.refreshButtonState();
        }
    }

    public void selectTheFirstActiveButton()
    {
        // clear any existing selection
        for (RadioButtonAndMathFact radioButtonAndMathFact : radioButtonsAndMathFacts)
        {
            RadioButton radioButton = radioButtonAndMathFact.getRadioButton();
            radioButton.setValue(false);
        }

        for (RadioButtonAndMathFact radioButtonAndMathFact : radioButtonsAndMathFacts)
        {
            RadioButton radioButton = radioButtonAndMathFact.getRadioButton();

            if (radioButton.isEnabled() == true)
            {
                radioButton.setValue(true);
                break;
            }
        }
    }

    public MathFactListForAParticularPrimaryOperand getSelectedValue()
    {
        for (int i = 0; i < getWidgetCount(); i++)
        {
            RadioButton radioButton = (RadioButton) getWidget(i);

            if (isChecked(radioButton))
            {
                return radioButtonsAndMathFacts.get(i).getMathFacts();
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
        for (RadioButtonAndMathFact radioButtonsAndMathFact : radioButtonsAndMathFacts)
        {
            RadioButton radioButton = radioButtonsAndMathFact.getRadioButton();
            if (radioButton.getValue() == true)
            {
                radioButton.setFocus(true);
                break;
            }
        }
    }

    public boolean allButtonsAreDisabled()
    {
        for (RadioButtonAndMathFact radioButtonsAndMathFact : radioButtonsAndMathFacts)
        {
            if (radioButtonsAndMathFact.getRadioButton().isEnabled())
            {
                return false;
            }
        }

        return true;
    }

    private class RadioButtonAndMathFact
    {
        private RadioButton radioButton;
        private MathFactListForAParticularPrimaryOperand mathFactSetForAParticularPrimaryOperand;

        public RadioButtonAndMathFact()
        {
        }

        private RadioButtonAndMathFact(RadioButton radioButton, MathFactListForAParticularPrimaryOperand mathFactSetForAParticularPrimaryOperand)
        {
            this.radioButton = radioButton;
            this.mathFactSetForAParticularPrimaryOperand = mathFactSetForAParticularPrimaryOperand;
        }

        public RadioButton getRadioButton()
        {
            return radioButton;
        }

        public MathFactListForAParticularPrimaryOperand getMathFacts()
        {
            return mathFactSetForAParticularPrimaryOperand;
        }

        public void refreshButtonState()
        {
            if (mathFactSetForAParticularPrimaryOperand.isGotItOnTheFirstTry())
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
                getMathFacts().reset(true);
                radioButton.setEnabled(true);
            }
        }
    }
}
