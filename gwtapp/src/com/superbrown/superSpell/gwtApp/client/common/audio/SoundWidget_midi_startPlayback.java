package com.superbrown.superSpell.gwtApp.client.common.audio;

/**
 * This class is a little bit of a hack, because it isn't intended to be used as a widget, but
 * rather, as a holder for javascript to be executed.  It is wrapped as a widget so it can be used
 * in the existing framework.
 */
public class SoundWidget_midi_startPlayback extends SoundWidget
{
    public SoundWidget_midi_startPlayback(String uri)
    {
        if (uri.toLowerCase().endsWith(".mid") == false)
        {
            throw new RuntimeException("Is not a midi file: " + uri);
        }

        setHTML("MIDIjs.play('" + uri + "');");
    }
}
