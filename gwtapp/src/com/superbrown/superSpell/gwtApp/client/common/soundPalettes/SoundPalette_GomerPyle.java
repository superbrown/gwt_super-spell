package com.superbrown.superSpell.gwtApp.client.common.soundPalettes;

import com.google.gwt.user.client.ui.Panel;
import com.superbrown.superSpell.gwtApp.client.util.RandomList;

/**
 */
public class SoundPalette_GomerPyle extends SoundPalette 
{
    public static RandomList SUCCESS_SOUND_FILE_NAMES =
            new RandomList(new String[]
        {
                "./audioFiles/gomerPyle/gomer_congrats.wav",
                "./audioFiles/gomerPyle/gomer_golly.wav",
                "./audioFiles/gomerPyle/gomer_surprise.wav",
        });

    public static RandomList FAILURE_SOUND_FILE_NAMES =
            new RandomList(new String[]
        {
                "./audioFiles/gomerPyle/gomer_shame.wav",
                "./audioFiles/gomerPyle/gomer_shut_up.wav",
                "./audioFiles/gomerPyle/gomer_terrible.wav",
                "./audioFiles/gomerPyle/gomer_what_is_this.wav",
        });

    public SoundPalette_GomerPyle(
            Panel panel)
    {
        super(panel, SUCCESS_SOUND_FILE_NAMES, FAILURE_SOUND_FILE_NAMES);
    }
}
