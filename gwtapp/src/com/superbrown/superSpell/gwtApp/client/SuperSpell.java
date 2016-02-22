package com.superbrown.superSpell.gwtApp.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.logical.shared.CloseEvent;
import com.google.gwt.event.logical.shared.CloseHandler;
import com.google.gwt.user.client.Timer;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.*;
import com.superbrown.superSpell.gwtApp.client.common.IResetable;
import com.superbrown.superSpell.gwtApp.client.common.audio.SongPlayerPanel_midi;
import com.superbrown.superSpell.gwtApp.client.common.audio.SoundWidget;
import com.superbrown.superSpell.gwtApp.client.common.audio.SoundWidget_audioTag;
import com.superbrown.superSpell.gwtApp.client.common.cheat.Cheat;
import com.superbrown.superSpell.gwtApp.client.common.cheat.CheatCodePopupPanel;
import com.superbrown.superSpell.gwtApp.client.common.cheat.CheatCodes;
import com.superbrown.superSpell.gwtApp.client.common.chooserPanels.SubjectChooserPanel;
import com.superbrown.superSpell.gwtApp.client.common.soundPalettes.SoundPalette;
import com.superbrown.superSpell.gwtApp.client.common.soundPalettes.SoundPalette_GomerPyle;
import com.superbrown.superSpell.gwtApp.client.common.soundPalettes.SoundPalette_ThreeStooges;
import com.superbrown.superSpell.gwtApp.client.mastermind.panels.MastermindGamePanel;
import com.superbrown.superSpell.gwtApp.client.services.ISuperSpellService;
import com.superbrown.superSpell.gwtApp.client.settings.SettingsPopupPanel;
import com.superbrown.superSpell.gwtApp.client.util.UiUtilities;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Entry point classes define <code>onModuleLoad()</code>
 */
public class SuperSpell implements EntryPoint, IResetable
{
    private static RootPanel mainPanel;

    public static RootPanel applicationTitlePanel;
    public static RootPanel linksInUpperRightCornerPanel;
    public static VerticalPanel stayAfterSchoolPostingPanel;
    public static RootPanel doodleMessagePanel;

    private static SoundPalette soundPalette_silent;
    private static SoundPalette soundPalette_ThreeStooges;
    private static SoundPalette soundPalette_GomerPyle;

    private static SoundPalette activeSoundPalette;
    private static MastermindGamePanel mastermindPanel;
    private static RootPanel testableListName;
    private static SoundWidget soundPalletChangeWidget;

    private static List<BoardColor> flashingColorSequence = new ArrayList<BoardColor>();
    static
    {
        flashingColorSequence.add(BoardColor.BROWN);
        flashingColorSequence.add(BoardColor.ORANGE);
        flashingColorSequence.add(BoardColor.RED);
        flashingColorSequence.add(BoardColor.GREEN);
        flashingColorSequence.add(BoardColor.PURPLE);
        flashingColorSequence.add(BoardColor.AQUA);
        flashingColorSequence.add(BoardColor.PINK);
    }

    public SuperSpell()
    {
        super();
    }

    /**
     * This is the entry point method.
     */
    public void onModuleLoad()
    {
        mainPanel = RootPanel.get("mainPanel");

        applicationTitlePanel = RootPanel.get("applicationTitle");
        testableListName = RootPanel.get("testableListName");

        soundPalette_ThreeStooges = new SoundPalette_ThreeStooges(mainPanel);
        soundPalette_GomerPyle = new SoundPalette_GomerPyle(mainPanel);
        soundPalette_silent = new SoundPalette();

        activeSoundPalette = soundPalette_silent;

        linksInUpperRightCornerPanel = RootPanel.get("linksInUpperRightCorner");
        linksInUpperRightCornerPanel.add(createSettingsLink());
        linksInUpperRightCornerPanel.add(createCheatCodeLink());
        linksInUpperRightCornerPanel.add(createStartOverLink());

        doodleMessagePanel = RootPanel.get("doodleMessage");

        RootPanel stayAfterSchoolPostingRootPanel = RootPanel.get("stayAfterSchoolPosting");
        stayAfterSchoolPostingPanel = new VerticalPanel();
        stayAfterSchoolPostingRootPanel.add(stayAfterSchoolPostingPanel);

        init();
    }

    private void init()
    {
        setBoardColor(Settings.getBoardColor());

        setTitlePanel("Super-Spell");
        testableListName.clear();

        mainPanel.clear();

        // DESIGN NOTE: This will go away when the call to addSchoolClassChooserPanel() completes
        //              (below) because it will clear the main panel.
        Label waitingForTeacherLabel = new Label("waiting for teacher to arrive");
        waitingForTeacherLabel.addStyleName("yellowChalk fontSize150 blink");
        mainPanel.add(waitingForTeacherLabel);

        setSoundEffectSet(Settings.getSoundEffectsSet());
//        setInReadImmediatelyMode(Settings.getInReadImmediatelyMode());
        setChalkboardDoodlingEnabled(Settings.getChalkboardDoodlingEnabled(), true);
        SuperSpell.setStayAfterSchoolListEnabled(Settings.getStayAfterSchoolListEnabled(), true);
        setUsingPeterMath(Settings.getUsingPeterMath());

        addSchoolClassChooserPanel(this);
    }

    public native String getUserAgent() /*-{
        return navigator.userAgent.toLowerCase();
    }-*/;


    private void handleCheatCodeDialogClosure(CheatCodePopupPanel panel)
    {
        String valueUserEntered = panel.getValueUserEntered();

        Cheat cheat = CheatCodes.getCheat(valueUserEntered);

        if (cheat == Cheat.UNRECOGNIZED)
        {
            displayNotRecognizedDialogBox();
        }
        else
        {
            // "I Dream of Jeanie" blink sound
//            mainPanel.add(new SoundWidget("./audioFiles/iDreamOfJeanie/idoj-blink.mp3", false, true));

            if (cheat == Cheat.NO_VALUE_ENTERED)
                return;
            else if (cheat == Cheat.UNLOCK_MUSIC)
                toggleUnlockMusic();
//            else if (cheat == Cheat.READ_IMMEDIATELY_MODE)
//                toggleInReadImmediatelyMode();
            else if (cheat == Cheat.SHOW_ALL_MISPELLINGS)
                toggleInShowAllMisspellingsMode();
            else if (cheat == Cheat.TURN_ON_THREE_STOOGES_SOUND)
                setSoundEffectSet(SoundPaletteChoice.THREE_STOOOGES);
            else if (cheat == Cheat.TURN_ON_GOMER_PYLE_SOUND)
                setSoundEffectSet(SoundPaletteChoice.GOMER_PYLE);
            else if (cheat == Cheat.SILENCE_SOUND)
                silenceSound();
            else if (cheat == Cheat.PINK_BACKGROUND)
                setBoardColor(BoardColor.PINK);
            else if (cheat == Cheat.BLUE_BACKGROUND)
                setBoardColor(BoardColor.BLUE);
            else if (cheat == Cheat.PURPLE_BACKGROUND)
                setBoardColor(BoardColor.PURPLE);
            else if (cheat == Cheat.AQUA_BACKGROUND)
                setBoardColor(BoardColor.AQUA);
            else if (cheat == Cheat.BROWN_BACKGROUND)
                setBoardColor(BoardColor.BROWN);
            else if (cheat == Cheat.CHALK_BOARD_BACKGROUND)
                setBoardColor(BoardColor.GREEN);
            else if (cheat == Cheat.ORANGE_BACKGROUND)
                setBoardColor(BoardColor.ORANGE);
            else if (cheat == Cheat.RED_BACKGROUND)
                setBoardColor(BoardColor.RED);
            else if (cheat == Cheat.BLACK_BACKGROUND)
                setBoardColor(BoardColor.BLACK);
            else if (cheat == Cheat.MASTERMIND)
                toggleMastermind();
            else if (cheat == Cheat.USE_PETER_MATH)
                toggleUsingPeterMath();
            else if (cheat == Cheat.CHALKBOARD_DOODLING)
            {
                toggleChalkboardDoodling();
                toggleStayAfterSchoolList();
            }
            else if (cheat == Cheat.MATH_TIMER_IN_SECONDS)
            {
                String mathQuestionTimerInSeconds = valueUserEntered.substring(
                        CheatCodes.MATH_TIMER_IN_SECONDS_PREFIX.length(),
                        valueUserEntered.length());

                try
                {
                    Settings.setMathQuestionTimeLimitInSeconds(Integer.valueOf(mathQuestionTimerInSeconds));
                }
                catch (NumberFormatException e)
                {
                    // don't worry about
                }
            }
        }
    }

    private void displayNotRecognizedDialogBox()
    {
        final PopupPanel notRecognizedNotificationPopup = new PopupPanel();
        notRecognizedNotificationPopup.setPopupPosition(
                linksInUpperRightCornerPanel.getAbsoluteLeft() + 10,
                linksInUpperRightCornerPanel.getAbsoluteTop() + 10);
        VerticalPanel dialogBoxPanel = new VerticalPanel();
        dialogBoxPanel.addStyleName("blackFont");
        dialogBoxPanel.add(new Label("I don't recognize that."));
        final Button button = new Button("OK");
        button.addClickHandler(new ClickHandler()
        {
            public void onClick(ClickEvent clickEvent)
            {
                notRecognizedNotificationPopup.hide();
            }
        });

        dialogBoxPanel.add(button);
        notRecognizedNotificationPopup.add(dialogBoxPanel);
        notRecognizedNotificationPopup.show();
        // makes dialog not display... go figure!
//        button.setFocus(true);
    }

    public static void setBoardColor(BoardColor boardColor)
    {
        Settings.setBoardColor(boardColor);

        if (boardColor == BoardColor.PINK)
            RootPanel.get().setStyleName("pinkChalkBoardColor");
        else if (boardColor == BoardColor.BLUE)
            RootPanel.get().setStyleName("blueChalkBoardColor");
        else if (boardColor == BoardColor.PURPLE)
            RootPanel.get().setStyleName("purpleChalkBoardColor");
        else if (boardColor == BoardColor.AQUA)
            RootPanel.get().setStyleName("aquaChalkBoardColor");
        else if (boardColor == BoardColor.BROWN)
            RootPanel.get().setStyleName("brownChalkBoardColor");
        else if (boardColor == BoardColor.GREEN)
            RootPanel.get().setStyleName("chalkBoardColor");
        else if (boardColor == BoardColor.ORANGE)
            RootPanel.get().setStyleName("orangeChalkBoardColor");
        else if (boardColor == BoardColor.RED)
            RootPanel.get().setStyleName("redChalkBoardColor");
        else if (boardColor == BoardColor.BLACK)
            RootPanel.get().setStyleName("blackChalkBoardColor");
    }

    public static void setSoundEffectSet(SoundPaletteChoice soundEffectsSet)
    {
        Settings.setSoundEffectsSet(soundEffectsSet);

        RootPanel rootPanel = RootPanel.get();
        UiUtilities.removeAnySoundWidgetsIfPresent(rootPanel, false);

        if (soundPalletChangeWidget != null)
        {
            rootPanel.remove(soundPalletChangeWidget);
        }

        if (soundEffectsSet == SoundPaletteChoice.THREE_STOOOGES)
        {
            activeSoundPalette = soundPalette_ThreeStooges;
            soundPalletChangeWidget = new SoundWidget_audioTag("./audioFiles/threeStooges/wowowowo.mp3", false, true);
            rootPanel.add(soundPalletChangeWidget);
        }
        else if (soundEffectsSet == SoundPaletteChoice.GOMER_PYLE)
        {
            activeSoundPalette = soundPalette_GomerPyle;
            soundPalletChangeWidget = new SoundWidget_audioTag("./audioFiles/gomerPyle/gomer_golly.mp3", false, true);
            rootPanel.add(soundPalletChangeWidget);
        }
        else if (soundEffectsSet == SoundPaletteChoice.NONE)
        {
            activeSoundPalette = soundPalette_silent;
        }
    }

    private void addSchoolClassChooserPanel(final IResetable caller)
    {
//        String userAgent = getUserAgent();
//
//        // if this is Internet Explorer...
//        if (userAgent.toLowerCase().contains("msie"))
//        {
//            Label internetExplorereWarningLabel =
//                    new Label("Unfortunately, Super-Spell doesn't work correctly with Internet " +
//                            "Explorer. You'll have to open it using a different browser " +
//                            "(possibly one of the ones below).");
//            internetExplorereWarningLabel.setWordWrap(true);
//            internetExplorereWarningLabel.setWidth("500px");
//            internetExplorereWarningLabel.addStyleName("fontSize150");
//            internetExplorereWarningLabel.addStyleName("arialFont");
//
//            HTML link = new HTML("<br/><A HREF=\"http://www.mozilla.com/en-US/firefox\" target=\"_new\">Firefox</A>" +
//                    "<br/><A HREF=\"http://www.apple.com/safari\" target=\"_new\">Safari</A>" +
//                    "<br/><A HREF=\"http://www.google.com/chrome\" target=\"_new\">Chrome</A>");
//
//
//            link.addStyleName("fontSize150");
//            link.addStyleName("arialFont");
//
//            mainPanel.clear();
//            mainPanel.add(internetExplorereWarningLabel);
//            mainPanel.add(link);
//        }
//        else
//        {
            ISuperSpellService.App.getInstance().setMathFactTimeLimit(Settings.getMathQuestionTimeLimitInSeconds(),
                new AsyncCallback<Void>()
                {
                    public void onFailure(Throwable e)
                    {
                        System.out.println("Failed on ISuperSpellService.App.getInstance().setMathFactTimeLimit(Settings.getMathQuestionTimeLimitInSeconds().");
                        System.out.println(e.getMessage());
                        e.printStackTrace();
                    }

                    public void onSuccess(Void aVoid)
                    {
                        ISuperSpellService.App.getInstance().getSchoolClassNames(new AsyncCallback<List<String>>()
                        {
                            public void onFailure(Throwable e)
                            {
                                // try again
                                System.out.println("Failed on ISuperSpellService.App.getInstance().getSchoolClassNames().");
                                System.out.println(e.getMessage());
                                e.printStackTrace();
                            }

                            public void onSuccess(List<String> schoolClassNames)
                            {
                                SubjectChooserPanel subjectChooserPanel =
                                        new SubjectChooserPanel(schoolClassNames, caller, mainPanel);
                                mainPanel.clear();
                                mainPanel.add(subjectChooserPanel);
                                subjectChooserPanel.getSchoolClassNamesListBox().setFocus(true);
                            }
                        });
                    }
            });
//        }
    }

    private static void setTitlePanel(String text)
    {
        applicationTitlePanel.clear();
        applicationTitlePanel.add(new Label(text));
    }

    public void reset()
    {

    }

    public static void resetDoodleMessage()
    {
        if (!isChalkboardDoodlingEnabled())
        {
            return;
        }

        String doodleMessage = (String)Settings.DOODLE_MESSAGES.getNextElement();
        Label label = new Label(doodleMessage);
        String fontStyle = (String)Settings.DOODLE_FONT_STYLES.getNextElement();

        // some fonts don't support numbers, so get a font that supports numbers
        while (containsNumbers(doodleMessage) &&
               (fontStyle.equals("CRAZK_font") || fontStyle.equals("ScratchedCarPaint_font")))
        {
            fontStyle = (String)Settings.DOODLE_FONT_STYLES.getNextElement();
        }

        label.addStyleName("whiteColor fontSize320 " + fontStyle);

        doodleMessagePanel.clear();
        doodleMessagePanel.add(label);
    }

    public static void addAnotherPersonToStayAfterSchoolList()
    {
        if (!isStayAfterSchoolListEnabled())
        {
            return;
        }

        if (Settings.numberOfPeopleStayingAfterSchool == Settings.MAX_NUMBER_OF_PEOPLE_STAYING_AFTER_SCHOOL)
        {
            Settings.numberOfPeopleStayingAfterSchool = 0;
        }

        Label label;

        if (Settings.numberOfPeopleStayingAfterSchool == 0)
        {
            stayAfterSchoolPostingPanel.clear();
            label = new Label("Stay after school:");
            label.addStyleName("whiteColor chalkFont fontSize150");
            stayAfterSchoolPostingPanel.add(label);
        }

        label = new Label((String)Settings.STAY_AFTER_SCHOOL_NAMES.getNextElement());
        label.addStyleName("whiteColor chalkFont fontSize150");
        stayAfterSchoolPostingPanel.add(label);
        Settings.numberOfPeopleStayingAfterSchool++;
    }

    public static void setTestableListName(String testableListName)
    {
        SuperSpell.testableListName.clear();
        SuperSpell.testableListName.add(new Label(testableListName));
    }

    public static void setSchoolClassName(String schoolClassName)
    {
        SuperSpell.setTitlePanel(schoolClassName);
    }

    private static Boolean containsNumbers(String doodleMessage)
    {
        for (int i = 0; i < 10; i++)
        {
            if (doodleMessage.contains("" + i))
            {
                return true;
            }
        }

        return false;
    }

    public static Boolean isInShowAllMisspellingsMode()
    {
        return Settings.getInShowAllMisspellingsMode();
    }

    public void toggleInShowAllMisspellingsMode()
    {
        Settings.setInShowAllMisspellingsMode(!Settings.getInShowAllMisspellingsMode());
    }

    public void toggleMastermind()
    {
        if (mastermindPanel == null)
        {
            mastermindPanel = new MastermindGamePanel();
            mainPanel.add(mastermindPanel);
        }
        else if (hasWidget(mainPanel, mastermindPanel))
        {
            mainPanel.remove(mastermindPanel);
        }
        else
        {
            mastermindPanel.getElement().getStyle().setProperty("position" , "relative");
            mainPanel.add(mastermindPanel);
        }
    }

    private Boolean hasWidget(RootPanel panel, Widget widget)
    {
        Boolean has = false;
        Iterator<Widget> iterator = panel.iterator();
        while (iterator.hasNext())
        {
            if (iterator.next() == widget)
            {
                has = true;
            }
        }
        return has;
    }


    private Widget createCheatCodeLink()
    {
        HorizontalPanel horizontalPanel = new HorizontalPanel();

        final Hyperlink cheatCodeLink = new Hyperlink();
        cheatCodeLink.setText("enter cheat code");
        cheatCodeLink.addStyleName("underline");
        cheatCodeLink.addClickHandler(new ClickHandler()
        {
            public void onClick(ClickEvent clickEvent)
            {
                final CheatCodePopupPanel panel = new CheatCodePopupPanel();
                panel.setPopupPosition(
                        linksInUpperRightCornerPanel.getAbsoluteLeft(),
                        linksInUpperRightCornerPanel.getAbsoluteTop() + 10);
                panel.show();
                panel.addCloseHandler(new CloseHandler()
                {
                    public void onClose(CloseEvent closeEvent)
                    {
                        handleCheatCodeDialogClosure(panel);
                    }
                });
            }
        });

        horizontalPanel.add(cheatCodeLink);

        HTML cheatCodeCheatSheetLink = new HTML(
                "<a href='cheats/index.html' target='_blank' style='padding-left: 4px'>cheat sheet</a>");
        horizontalPanel.add(cheatCodeCheatSheetLink);

        return horizontalPanel;
    }

    private Widget createSettingsLink()
    {
        final Hyperlink cheatCodeLink = new Hyperlink();
        cheatCodeLink.setText("settings");
        cheatCodeLink.addStyleName("underline");
        cheatCodeLink.addClickHandler(new ClickHandler()
        {
            public void onClick(ClickEvent clickEvent)
            {
                final SettingsPopupPanel panel = new SettingsPopupPanel();
                panel.setPopupPosition(
                        linksInUpperRightCornerPanel.getAbsoluteLeft() - panel.getOffsetWidth() + linksInUpperRightCornerPanel.getOffsetWidth(),
                        linksInUpperRightCornerPanel.getAbsoluteTop());
                panel.show();
            }
        });

        return cheatCodeLink;
    }

    private Widget createStartOverLink()
    {
        final Hyperlink cheatCodeLink = new Hyperlink();
        cheatCodeLink.setText("start over");
        cheatCodeLink.addStyleName("underline");
        cheatCodeLink.addClickHandler(new ClickHandler()
        {
            public void onClick(ClickEvent clickEvent)
            {
                init();
            }
        });

        return cheatCodeLink;
    }

    private void silenceSound()
    {
        setSoundEffectSet(SoundPaletteChoice.NONE);
//        setInReadImmediatelyMode(false);
    }

    private void toggleUnlockMusic()
    {
        Boolean soundPlayerFoundAndRemoved = UiUtilities.removeAnySoundPlayersIfPresent(mainPanel, true);

        if (soundPlayerFoundAndRemoved)
        {
            return;
        }
        else
        {
            mainPanel.add(new SongPlayerPanel_midi());
        }
    }

    public static SoundPalette getActiveSoundPalette()
    {
        return activeSoundPalette;
    }

    public static Boolean isInReadImmediatelyMode()
    {
        return Settings.getInReadImmediatelyMode();
    }

//    public void toggleInReadImmediatelyMode()
//    {
//        Boolean newSetting = !Settings.getInReadImmediatelyMode();
//        setInReadImmediatelyMode(newSetting);
//    }

//    public static void setInReadImmediatelyMode(Boolean newSetting)
//    {
//        Settings.setInReadImmediatelyMode(newSetting);
//    }

    public static Boolean isUsingPeterMath()
    {
        return Settings.getUsingPeterMath();
    }

    public void toggleUsingPeterMath()
    {
        setUsingPeterMath(!Settings.getUsingPeterMath());
    }

    public static void setUsingPeterMath(Boolean newSetting)
    {
        Settings.setUsingPeterMath(newSetting);
    }

    public static Boolean isChalkboardDoodlingEnabled()
    {
        return Settings.getChalkboardDoodlingEnabled();
    }

    public void toggleChalkboardDoodling()
    {
        Boolean newSetting = !Settings.getChalkboardDoodlingEnabled();
        setChalkboardDoodlingEnabled(newSetting, false);
    }

    public static void setChalkboardDoodlingEnabled(Boolean setting,
                                                    Boolean thisIsTheFirstTimeThisIsBeingSet)
    {
        // if this is already the current setting, do nothing
        if (thisIsTheFirstTimeThisIsBeingSet == false &&
                setting == Settings.getChalkboardDoodlingEnabled())
            return;

        Settings.setChalkboardDoodlingEnabled(setting);

        doodleMessagePanel.setVisible(setting);

        if (doodleMessagePanel.getWidgetCount() == 0)
        {
            resetDoodleMessage();
        }
    }

    public static Boolean isStayAfterSchoolListEnabled()
    {
        return Settings.getStayAfterSchoolListEnabled();
    }

    public void toggleStayAfterSchoolList()
    {
        Boolean newSetting = !Settings.getStayAfterSchoolListEnabled();
        setStayAfterSchoolListEnabled(newSetting, false);
    }

    public static void setStayAfterSchoolListEnabled(Boolean setting,
                                                     Boolean thisIsTheFirstTimeThisIsBeingSet)
    {
        // if this is already the current setting, do nothing
        if (thisIsTheFirstTimeThisIsBeingSet == false &&
                setting == Settings.getStayAfterSchoolListEnabled())
            return;

        Settings.setStayAfterSchoolListEnabled(setting);

        stayAfterSchoolPostingPanel.setVisible(setting);

        if (setting)
        {
            if (Settings.numberOfPeopleStayingAfterSchool == 0)
            {
                addAnotherPersonToStayAfterSchoolList();
            }
        }
    }

    public static void setMathQuestionTimeLimit(Integer integer)
    {
        Settings.setMathQuestionTimeLimitInSeconds(integer);
    }

    public static void flashColorsOnScreen(final ICompletedListener completedListener)
    {
        final BoardColor orginalColor = Settings.getBoardColor();
        final int millisecsPerColor = 250;

        setBoardColor(flashingColorSequence.get(0));
        Timer timer = new Timer() {public void run(){
            setBoardColor(flashingColorSequence.get(1));
            Timer timer = new Timer() {public void run(){
                setBoardColor(flashingColorSequence.get(2));
                Timer timer = new Timer() {public void run(){
                    setBoardColor(flashingColorSequence.get(3));
                    Timer timer = new Timer() {public void run(){
                        setBoardColor(flashingColorSequence.get(4));
                        Timer timer = new Timer() {public void run(){
                            setBoardColor(orginalColor);
                            completedListener.completed();
                        }}; timer.schedule(millisecsPerColor);
                    }};timer.schedule(millisecsPerColor);
                }};timer.schedule(millisecsPerColor);
            }};timer.schedule(millisecsPerColor);
        }};

        // shuffle the colors a little bit just for variety
        BoardColor firstColor = flashingColorSequence.remove(0);
        flashingColorSequence.add(firstColor);

        timer.schedule(millisecsPerColor);
    }
}
