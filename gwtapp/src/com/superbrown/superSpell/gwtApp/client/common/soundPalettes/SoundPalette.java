package com.superbrown.superSpell.gwtApp.client.common.soundPalettes;

import com.google.gwt.user.client.ui.Panel;
import com.superbrown.superSpell.gwtApp.client.util.RandomList;
import com.superbrown.superSpell.gwtApp.client.common.audio.RandomSoundPlayer;

/**
 */
public class SoundPalette
{
    private RandomSoundPlayer successSound;
    private RandomSoundPlayer failureSound;

    public SoundPalette(
            Panel panel,
            RandomList successSoundFileNames,
            RandomList failureSoundFileNames)
    {
        successSound = new RandomSoundPlayer(panel, successSoundFileNames);
        failureSound = new RandomSoundPlayer(panel, failureSoundFileNames);
    }

    public SoundPalette()
    {
    }

    public void playSuccessSound()
    {
        if (successSound != null)
        {
            successSound.playSound();
        }
    }

    public void playFailureSound()
    {
        if (failureSound != null)
        {
            failureSound.playSound();
        }
    }
}
