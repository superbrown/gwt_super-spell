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
                "./audioFiles/threeStooges/certnly.wav",
                "./audioFiles/threeStooges/curly.wav",
                "./audioFiles/threeStooges/wowowowo.wav",
                "./audioFiles/threeStooges/heylarry.wav",
                "./audioFiles/threeStooges/heymoe.wav",
        });

    public static RandomList FAILURE_SOUND_FILE_NAMES =
            new RandomList(new String[]
        {
                "./audioFiles/threeStooges/brains.wav",
                "./audioFiles/threeStooges/nyuk.wav",
                "./audioFiles/threeStooges/think.wav",
                "./audioFiles/threeStooges/sorrymoe.wav",
                "./audioFiles/threeStooges/murder.wav",
        });

    public SoundPalette_ThreeStooges(Panel panel)
    {
        super(panel, SUCCESS_SOUND_FILE_NAMES, FAILURE_SOUND_FILE_NAMES);
    }
}
