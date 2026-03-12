package com.superbrown.superSpell.gwtApp.client.spelling;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.user.client.ui.Hyperlink;
import com.superbrown.superSpell.gwtApp.shared.spelling.SpellingWord;

/**
 * Link that uses the Web Speech API to read spelling words and sentences aloud.
 * Replaces the deprecated Google Translate TTS service.
 */
public class HearSentenceLink extends Hyperlink
{
    private SpellingWord spellingWord;

    public HearSentenceLink(final SpellingWord spellingWord)
    {
        this.spellingWord = spellingWord;
        addDomHandler(clickEvent -> readNow(), ClickEvent.getType());
    }

    public void readNow()
    {
        // Read the word, the sentence, and then the word again.
        String textToRead =
                spellingWord.getCorrectAnswer() + ". " +
                spellingWord.getFullSentenceWithCorrectSpelling() + " " +
                spellingWord.getCorrectAnswer() + ". ";

        speakText(textToRead);
    }

    /**
     * Uses the browser's Web Speech API to speak the given text.
     * This is a free, built-in browser feature that works offline.
     */
    private native void speakText(String text) /*-{
        // Check if the browser supports the Web Speech API
        if (!$wnd.speechSynthesis) {
            console.error('Web Speech API not supported in this browser');
            alert('Text-to-speech is not supported in your browser. Please try Chrome, Firefox, Safari, or Edge.');
            return;
        }

        // Cancel any ongoing speech
        $wnd.speechSynthesis.cancel();

        // Create a new speech utterance
        var utterance = new SpeechSynthesisUtterance(text);
        
        // Configure speech parameters
        utterance.lang = 'en-US';  // English (US)
        utterance.rate = 0.9;      // Slightly slower for clarity (0.1 to 10)
        utterance.pitch = 1.0;     // Normal pitch (0 to 2)
        utterance.volume = 1.0;    // Full volume (0 to 1)

        // Speak the text
        $wnd.speechSynthesis.speak(utterance);
    }-*/;
}
