package com.superbrown.superSpell.gwtApp.client.common.audio;

import com.google.gwt.core.client.ScriptInjector;

import java.util.List;

/**
 */
public class SongPlayerPanel_usingMidiJs extends SongPlayerPanel
{
    @Override
    protected SoundWidget createSongWidget(String fileName)
    {
        SoundWidget widget = new SoundWidgetUsingMidiJs_startPlayback(fileName);
        return widget;
    }

    @Override
    protected void startPlayback()
    {
        SoundWidget currentSong = this.songWidgets.get(currentSongWidgetIndex);

        ScriptInjector.FromString fromString = ScriptInjector.fromString(currentSong.getHTML());
        fromString.setWindow(ScriptInjector.TOP_WINDOW);
        fromString.inject();
    }

    @Override
    protected void stopPlayback()
    {
        List<SoundWidget> soundWidgets = getAllMySoundWidgetsCurrentOnPanel();

        for (SoundWidget soundWidget : soundWidgets)
        {
            this.remove(soundWidget);
        }

        ScriptInjector.FromString fromString = ScriptInjector.fromString("MIDIjs.stop();");
        fromString.setWindow(ScriptInjector.TOP_WINDOW);
        fromString.inject();
    }
}
