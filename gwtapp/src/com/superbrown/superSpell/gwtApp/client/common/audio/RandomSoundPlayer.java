package com.superbrown.superSpell.gwtApp.client.common.audio;

import com.google.gwt.user.client.ui.Panel;
import com.superbrown.superSpell.gwtApp.client.util.RandomList;

/**
 */
public class RandomSoundPlayer
{
    private RandomList fileNames;
    private Panel parentPanel;
    private SoundWidget songWidget;


    public RandomSoundPlayer(Panel parentPanel, RandomList fileNames)
    {
        this.parentPanel = parentPanel;
        this.fileNames = fileNames;
    }

    public void playSound()
    {
        if (songWidget != null)
        {
            parentPanel.remove(songWidget);
        }

        String fileName = (String)fileNames.getNextElement();
        songWidget = new SoundWidget_audioTag(fileName, false, true);
        parentPanel.add(songWidget);
    }
}
