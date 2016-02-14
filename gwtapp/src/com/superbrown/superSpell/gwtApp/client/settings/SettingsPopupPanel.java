package com.superbrown.superSpell.gwtApp.client.settings;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.*;
import com.superbrown.superSpell.gwtApp.client.BoardColor;
import com.superbrown.superSpell.gwtApp.client.Settings;
import com.superbrown.superSpell.gwtApp.client.SuperSpell;
import com.superbrown.superSpell.gwtApp.client.SoundPaletteChoice;

/**
 */
public class SettingsPopupPanel extends PopupPanel
{
    public SettingsPopupPanel()
    {
        super();
        init();
    }

    private void init()
    {
        VerticalPanel mainPanel = new VerticalPanel();

        mainPanel.addStyleName("blackFont");

//        final CheckBox inReadImmediatelyModeCheckBox =
//                new CheckBox("read sample spelling sentences automatically");
//        inReadImmediatelyModeCheckBox.setValue(Settings.getInReadImmediatelyMode());
//        mainPanel.add(inReadImmediatelyModeCheckBox);

        final CheckBox chalkboardDoodlingEnabledCheckBox =
                new CheckBox("chalkboard doodling");
        chalkboardDoodlingEnabledCheckBox.setValue(Settings.getChalkboardDoodlingEnabled());
        chalkboardDoodlingEnabledCheckBox.addStyleName("topMargin10");
        mainPanel.add(chalkboardDoodlingEnabledCheckBox);

        final CheckBox stayAfterSchoolListCheckBox =
                new CheckBox("stay after school list");
        stayAfterSchoolListCheckBox.setValue(Settings.getStayAfterSchoolListEnabled());
        stayAfterSchoolListCheckBox.addStyleName("topMargin10");
        mainPanel.add(stayAfterSchoolListCheckBox);

        final CheckBox usingPeterMathCheckBox =
                new CheckBox("execute addition math facts in \"Peter mode\" (unusual setting)");
        usingPeterMathCheckBox.setValue(Settings.getUsingPeterMath());
        usingPeterMathCheckBox.addStyleName("topMargin10");
        mainPanel.add(usingPeterMathCheckBox);

//        final CheckBox inShowAllMisspellingsModeCheckBox =
//                new CheckBox("show all misspellings (unusual setting)");
//        inShowAllMisspellingsModeCheckBox.setValue(Settings.getInShowAllMisspellingsMode());
//        inShowAllMisspellingsModeCheckBox.addStyleName("topMargin10");
//        mainPanel.add(inShowAllMisspellingsModeCheckBox);

        final ListBox boardColorListBox = createBoardColorListBox();
        boardColorListBox.addStyleName("rightMargin5");
        final Label boardColorLabel = new Label("board color");
        HorizontalPanel boardColorPanel = new HorizontalPanel();
        boardColorPanel.addStyleName("topMargin10");
        boardColorPanel.add(boardColorListBox);
        boardColorPanel.add(boardColorLabel);
        mainPanel.add(boardColorPanel);

        final ListBox mathQuestionTimeLimitListBox = createMathQuestionTimerLimitListBox();
        boardColorListBox.addStyleName("rightMargin5");
        final Label mathQuestionTimeLimitLabel = new Label("math question time limit (must start over for changes to take effect)");
        HorizontalPanel mathQuestionTimeLimitPanel = new HorizontalPanel();
        mathQuestionTimeLimitPanel.addStyleName("topMargin10");
        mathQuestionTimeLimitPanel.add(mathQuestionTimeLimitListBox);
        mathQuestionTimeLimitPanel.add(mathQuestionTimeLimitLabel);
        mainPanel.add(mathQuestionTimeLimitPanel);

        final ListBox soundPalletListBox = createSoundPalletListBox();
        soundPalletListBox.addStyleName("rightMargin5");
        final Label soundPalletLabel = new Label("fun sounds");
        HorizontalPanel soundPalletPanel = new HorizontalPanel();
        soundPalletPanel.addStyleName("topMargin10");
        soundPalletPanel.add(soundPalletListBox);
        soundPalletPanel.add(soundPalletLabel);
        mainPanel.add(soundPalletPanel);

        HorizontalPanel buttonPanel = new HorizontalPanel();
        buttonPanel.addStyleName("topMargin10");

        Button okButton = new Button("OK");
        okButton.addStyleName("rightMargin10");
        okButton.addClickHandler(new ClickHandler()
        {
            public void onClick(ClickEvent clickEvent)
            {
//                SuperSpell.setInReadImmediatelyMode(inReadImmediatelyModeCheckBox.getValue());
//                Settings.setInShowAllMisspellingsMode(inShowAllMisspellingsModeCheckBox.getValue());
                SuperSpell.setChalkboardDoodlingEnabled(chalkboardDoodlingEnabledCheckBox.getValue(), false);
                SuperSpell.setStayAfterSchoolListEnabled(stayAfterSchoolListCheckBox.getValue(), false);
                SuperSpell.setUsingPeterMath(usingPeterMathCheckBox.getValue());
                SuperSpell.setBoardColor(BoardColor.valueOf(boardColorListBox.getValue(boardColorListBox.getSelectedIndex())));
                SuperSpell.setSoundEffectSet(SoundPaletteChoice.valueOf(soundPalletListBox.getValue(soundPalletListBox.getSelectedIndex())));
                SuperSpell.setMathQuestionTimeLimit(Integer.valueOf(mathQuestionTimeLimitListBox.getValue(mathQuestionTimeLimitListBox.getSelectedIndex())));
                hide();
            }
        });
        buttonPanel.add(okButton);

        Button cancelButton = new Button("Cancel");
        cancelButton.addClickHandler(new ClickHandler()
        {
            public void onClick(ClickEvent clickEvent)
            {
                hide();
            }
        });

        buttonPanel.add(cancelButton);

        mainPanel.add(buttonPanel);
        
        this.add(mainPanel);
        this.show();

//        inReadImmediatelyModeCheckBox.setFocus(true);
    }

    private ListBox createSoundPalletListBox()
    {
        final ListBox soundPalletListBox = new ListBox();
        populateSoundPalletListBox(soundPalletListBox);
        return soundPalletListBox;
    }

    private ListBox createBoardColorListBox()
    {
        final ListBox boardColorListBox = new ListBox();
        populateBoardColorListBox(boardColorListBox);
        return boardColorListBox;
    }

    private ListBox createMathQuestionTimerLimitListBox()
    {
        final ListBox mathQuestionTimeLimitListBox = new ListBox();
        populateMathQuestionTimeLimitListBox(mathQuestionTimeLimitListBox);
        return mathQuestionTimeLimitListBox;
    }

    private void populateBoardColorListBox(ListBox listBox)
    {
        listBox.addItem("Green", BoardColor.GREEN.toString());
        listBox.addItem("Aqua", BoardColor.AQUA.toString());
        listBox.addItem("Black", BoardColor.BLACK.toString());
        listBox.addItem("Blue", BoardColor.BLUE.toString());
        listBox.addItem("Brown", BoardColor.BROWN.toString());
        listBox.addItem("Orange", BoardColor.ORANGE.toString());
        listBox.addItem("Pink", BoardColor.PINK.toString());
        listBox.addItem("Purple", BoardColor.PURPLE.toString());
        listBox.addItem("Red", BoardColor.RED.toString());

        for (int i = 0; i < listBox.getItemCount(); i++)
        {
            if (BoardColor.valueOf(listBox.getValue(i)) == Settings.getBoardColor())
            {
                listBox.setSelectedIndex(i);
                break;
            }
        }
    }

    private void populateMathQuestionTimeLimitListBox(ListBox listBox)
    {
        listBox.addItem("20 seconds", "20");
        listBox.addItem("15 seconds", "15");
        listBox.addItem("10 seconds", "10");
        listBox.addItem("9 seconds", "9");
        listBox.addItem("8 seconds", "8");
        listBox.addItem("7 seconds", "7");
        listBox.addItem("6 seconds", "6");
        listBox.addItem("5 seconds", "5");
        listBox.addItem("4 seconds", "4");
        listBox.addItem("3 seconds", "3");
        listBox.addItem("2 seconds", "2");

        for (int i = 0; i < listBox.getItemCount(); i++)
        {
            if (Integer.valueOf(listBox.getValue(i)) == Settings.getMathQuestionTimeLimitInSeconds())
            {
                listBox.setSelectedIndex(i);
                break;
            }
        }
    }

    private void populateSoundPalletListBox(ListBox soundPalletListBox)
    {
        soundPalletListBox.addItem("None", SoundPaletteChoice.NONE.toString());
        soundPalletListBox.addItem("Gomer Plye", SoundPaletteChoice.GOMER_PYLE.toString());
        soundPalletListBox.addItem("Three Stooges", SoundPaletteChoice.THREE_STOOOGES.toString());

        for (int i = 0; i < soundPalletListBox.getItemCount(); i++)
        {
            if (SoundPaletteChoice.valueOf(soundPalletListBox.getValue(i)) == Settings.getSoundEffectsSet())
            {
                soundPalletListBox.setSelectedIndex(i);
                break;
            }
        }
    }
}

