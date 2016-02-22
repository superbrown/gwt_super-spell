package com.superbrown.superSpell.gwtApp.client.common.soundPalettes;

import com.google.gwt.user.client.ui.Panel;
import com.superbrown.superSpell.gwtApp.client.util.RandomList;

/**
 */
public class SoundPalette_ThreeStooges extends SoundPalette
{
    public static RandomList SUCCESS_SOUND_FILE_NAMES =
            new RandomList(new String[]
        {
                "./audioFiles/threeStooges/certnly.mp3",
                "./audioFiles/threeStooges/curly.mp3",
                "./audioFiles/threeStooges/wowowowo.mp3",
                "./audioFiles/threeStooges/heylarry.mp3",
                "./audioFiles/threeStooges/heymoe.mp3",
        });

    public static RandomList FAILURE_SOUND_FILE_NAMES =
            new RandomList(new String[]
        {
                "./audioFiles/threeStooges/brains.mp3",
                "./audioFiles/threeStooges/nyuk.mp3",
                "./audioFiles/threeStooges/think.mp3",
                "./audioFiles/threeStooges/sorrymoe.mp3",
                "./audioFiles/threeStooges/murder.mp3",
        });

    public SoundPalette_ThreeStooges(Panel panel)
    {
        super(panel, SUCCESS_SOUND_FILE_NAMES, FAILURE_SOUND_FILE_NAMES);
    }
}
