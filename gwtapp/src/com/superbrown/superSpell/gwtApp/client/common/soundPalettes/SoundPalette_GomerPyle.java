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
                "./audioFiles/gomerPyle/gomer_congrats.mp3",
                "./audioFiles/gomerPyle/gomer_golly.mp3",
                "./audioFiles/gomerPyle/gomer_surprise.mp3",
        });

    public static RandomList FAILURE_SOUND_FILE_NAMES =
            new RandomList(new String[]
        {
                "./audioFiles/gomerPyle/gomer_shame.mp3",
                "./audioFiles/gomerPyle/gomer_shut_up.mp3",
                "./audioFiles/gomerPyle/gomer_terrible.mp3",
                "./audioFiles/gomerPyle/gomer_what_is_this.mp3",
        });

    public SoundPalette_GomerPyle(
            Panel panel)
    {
        super(panel, SUCCESS_SOUND_FILE_NAMES, FAILURE_SOUND_FILE_NAMES);
    }
}
