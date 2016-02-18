package com.superbrown.superSpell.gwtApp.client.common.audio;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.superbrown.superSpell.gwtApp.client.Settings;
import com.superbrown.superSpell.gwtApp.client.util.Shuffler;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;


public class SongPlayerPanel extends VerticalPanel
{
    protected HorizontalPanel myButtonPanel;

    protected Button stopPlaybackButton;
    protected Button startPlaybackButton;
    protected Button previousSongButton;
    protected Button nextSongButton;
    protected List<SoundWidget> songWidgets;
    protected int currentSongWidgetIndex = 0;

    public SongPlayerPanel()
    {
        init();
    }

    protected void init()
    {
        List<String> midiFileNames = Arrays.asList(Settings.MIDI_FILE_NAMES);
        List<String> suffledMidiFileNames = Shuffler.shuffle(midiFileNames);

        songWidgets = new ArrayList<>();

        for (String midiFileName : suffledMidiFileNames)
        {
            SoundWidget songWidget = createSongWidget(midiFileName);
            try
            {
                songWidgets.add(songWidget);
            } catch (Throwable e)
            {
                e.printStackTrace();
                throw e;
            }
        }

        myButtonPanel = new HorizontalPanel();

        startPlaybackButton = new Button("Play");
        startPlaybackButton.addClickHandler(new ClickHandler()
        {
            public void onClick(ClickEvent event)
            {
                startPlayback();
                startPlaybackButton.setEnabled(false);
                stopPlaybackButton.setEnabled(true);
                setPreviousAndNextSongButtonsToTheCorrectEnabledState();
            }
        });

        stopPlaybackButton = new Button("Stop");
        stopPlaybackButton.addStyleName("leftMargin10");
        stopPlaybackButton.addClickHandler(new ClickHandler()
        {
            public void onClick(ClickEvent clickEvent)
            {
                stopPlayback();
                startPlaybackButton.setEnabled(true);
                stopPlaybackButton.setEnabled(false);
            }
        });

        previousSongButton = new Button("Previous Song");
        previousSongButton.addStyleName("leftMargin10");
        previousSongButton.addClickHandler(new ClickHandler()
        {
            public void onClick(ClickEvent event)
            {
                try {
                    playPreviousSong();
                } catch (AlreadyAtBeginningOfSongList e) {
                    // this shouldn't happen because the button should disabled when this is
                    // the case
                    throw new RuntimeException(e);
                }
                startPlaybackButton.setEnabled(false);
                stopPlaybackButton.setEnabled(true);
                setPreviousAndNextSongButtonsToTheCorrectEnabledState();
            }
        });

        nextSongButton = new Button("Next Song");
        nextSongButton.addStyleName("leftMargin10");
        nextSongButton.addClickHandler(new ClickHandler()
        {
            public void onClick(ClickEvent event)
            {
                try {
                    playNextSong();
                } catch (AlreadyAtEndOfSongList e) {
                    // this shouldn't happen because the button should disabled when this is
                    // the case
                    throw new RuntimeException(e);
                }
                startPlaybackButton.setEnabled(false);
                stopPlaybackButton.setEnabled(true);
                setPreviousAndNextSongButtonsToTheCorrectEnabledState();
            }
        });

        Label title = new Label();
        title.setText("Play a song to Celebrate!");
        title.setStyleName("whiteChalk fontSize150 topMargin10");
        add(title);

        title = new Label();
        title.setText("(sometimes the songs are slow to load, so be patient)");
        title.setStyleName("whiteChalk fontSize150");
        add(title);

        myButtonPanel.add(startPlaybackButton);
        myButtonPanel.add(stopPlaybackButton);
        myButtonPanel.add(previousSongButton);
        myButtonPanel.add(nextSongButton);

        startPlaybackButton.setEnabled(true);
        stopPlaybackButton.setEnabled(false);
        previousSongButton.setEnabled(false);
        nextSongButton.setEnabled(false);

        add(myButtonPanel);
    }

    protected SoundWidget createSongWidget(String fileName)
    {
        SoundWidget widget = new SoundWidget_audioTag(fileName, true, true);
        widget.addStyleName("topMargin10");
        return widget;
    }

    protected void setPreviousAndNextSongButtonsToTheCorrectEnabledState()
    {
        previousSongButton.setEnabled(currentSongWidgetIndex > 0);
        nextSongButton.setEnabled(currentSongWidgetIndex < (songWidgets.size() - 1));
    }

    protected void playPreviousSong()
            throws AlreadyAtBeginningOfSongList
    {
        stopPlayback();

        currentSongWidgetIndex--;

        if (currentSongWidgetIndex < 0) {
            currentSongWidgetIndex++;
            throw new AlreadyAtBeginningOfSongList();
        }

        startPlayback();
    }

    protected void playNextSong()
            throws AlreadyAtEndOfSongList
    {
        stopPlayback();

        currentSongWidgetIndex++;

        if (currentSongWidgetIndex == songWidgets.size()) {
            currentSongWidgetIndex--;
            throw new AlreadyAtEndOfSongList();
        }

        startPlayback();
    }

    protected void startPlayback()
    {
        insert(songWidgets.get(currentSongWidgetIndex), 0);
    }

    protected void stopPlayback()
    {
        // This may be overkill, but it just assures that if for some
        // reason there multiple sound widgets, they'll all be removed.

        List<SoundWidget> soundWidgets = getAllMySoundWidgetsCurrentOnPanel();

        for (SoundWidget soundWidget : soundWidgets)
        {
            this.remove(soundWidget);
        }
    }

    protected List<SoundWidget> getAllMySoundWidgetsCurrentOnPanel()
    {
        List<SoundWidget> soundWidgets = new ArrayList<SoundWidget>();

        for (Iterator iterator = this.getChildren().iterator(); iterator.hasNext();)
        {
            Object childWidget = iterator.next();
            if (childWidget instanceof SoundWidget)
            {
                soundWidgets.add((SoundWidget)childWidget);
            }
        }
        return soundWidgets;
    }

    private class AlreadyAtEndOfSongList extends Exception
    {
    }

    private class AlreadyAtBeginningOfSongList extends Exception
    {
    }
}
