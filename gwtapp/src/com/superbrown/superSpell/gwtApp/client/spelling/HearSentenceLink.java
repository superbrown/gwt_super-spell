package com.superbrown.superSpell.gwtApp.client.spelling;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.Hyperlink;
import com.google.gwt.user.client.ui.Panel;
import com.superbrown.superSpell.gwtApp.client.common.audio.SoundWidget;
import com.superbrown.superSpell.gwtApp.client.common.audio.SoundWidget_audioTag;
import com.superbrown.superSpell.gwtApp.shared.spelling.SpellingWord;

/**
 */
public class HearSentenceLink extends Hyperlink
{
    public static final String GOOGLE_TEXT_TO_SPEECH_SERVICE_URI =
        "http://translate.google.com/translate_tts?tl=en&q=";

    private static SoundWidget soundWidget;
    private SpellingWord spellingWord;


    public HearSentenceLink(final SpellingWord spellingWord)
    {
        this.spellingWord = spellingWord;

        addClickHandler(new ClickHandler()
        {
            public void onClick(ClickEvent clickEvent)
            {
                readNow();
            }
        });
    }

    public void readNow()
    {
        removeSoundWidgetIfItExists();

        // Read the word, the sentence, and then the word again.

        String textToRead =
                spellingWord.getCorrectAnswer() + ". " +
                spellingWord.getFullSentenceWithCorrectSpelling() + " " +
                spellingWord.getCorrectAnswer() + ". ";

        soundWidget = new SoundWidget_audioTag(
                GOOGLE_TEXT_TO_SPEECH_SERVICE_URI + textToRead,
                false,  // not visible
                true);  // read immediately;

        // NOTE: This used Google translate to read the sentences. However, the Google service it
        // used is no longer available, and the REST service Google now makes available is not free.
        // Therefore, this feature has been disabled (by commenting it out below).

//        ((Panel)this.getParent()).add(soundWidget);
    }

    protected void onDetach()
    {
        super.onDetach();
        removeSoundWidgetIfItExists();
    }

    private void removeSoundWidgetIfItExists()
    {
        if (soundWidget != null)
        {
            ((Panel)this.getParent()).remove(soundWidget);
            soundWidget = null;
        }
    }
}
