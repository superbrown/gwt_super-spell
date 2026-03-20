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
        super();
        this.spellingWord = spellingWord;
        setText("hear sentence");
        addStyleName("underline");
        getElement().getStyle().setProperty("whiteSpace", "nowrap");
        addDomHandler(clickEvent -> readNow(), ClickEvent.getType());
    }

    public void readNow()
    {
        // Read the word, the sentence, and then the word again (with a pause before the final word).
        String word = spellingWord.getCorrectAnswer();
        String sentence = spellingWord.getFullSentenceWithCorrectSpelling();
        
        speakTextWithPause(word, sentence, word);
    }

    /**
     * Uses the browser's Web Speech API to speak the given text with pauses.
     * This is a free, built-in browser feature that works offline.
     */
    private native void speakTextWithPause(String word1, String sentence, String word2) /*-{
        // Check if the browser supports the Web Speech API
        if (!$wnd.speechSynthesis) {
            console.error('Web Speech API not supported in this browser');
            alert('Text-to-speech is not supported in your browser. Please try Chrome, Firefox, Safari, or Edge.');
            return;
        }

        // Cancel any ongoing speech
        $wnd.speechSynthesis.cancel();

        // Create utterances for each part
        var utterance1 = new SpeechSynthesisUtterance(word1 + ".");
        var utterance2 = new SpeechSynthesisUtterance(sentence);
        var utterance3 = new SpeechSynthesisUtterance(word2 + ".");
        
        // Configure speech parameters
        utterance1.lang = 'en-US';
        utterance1.rate = 0.9;
        utterance1.pitch = 1.0;
        utterance1.volume = 1.0;
        
        utterance2.lang = 'en-US';
        utterance2.rate = 0.9;
        utterance2.pitch = 1.0;
        utterance2.volume = 1.0;
        
        utterance3.lang = 'en-US';
        utterance3.rate = 0.9;
        utterance3.pitch = 1.0;
        utterance3.volume = 1.0;

        // Speak the first word, pause, then sentence, pause, then final word
        utterance1.onend = function() {
            // Add a 500ms pause before speaking the sentence
            setTimeout(function() {
                $wnd.speechSynthesis.speak(utterance2);
            }, 500);
        };
        
        utterance2.onend = function() {
            // Add a 500ms pause before speaking the final word
            setTimeout(function() {
                $wnd.speechSynthesis.speak(utterance3);
            }, 500);
        };
        
        $wnd.speechSynthesis.speak(utterance1);
    }-*/;
}
