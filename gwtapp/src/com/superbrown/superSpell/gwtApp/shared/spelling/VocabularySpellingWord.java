package com.superbrown.superSpell.gwtApp.shared.spelling;

import com.superbrown.superSpell.gwtApp.shared.ThingWithPoolOfIncorrectAnswers;

import java.util.Set;

public class VocabularySpellingWord extends SpellingWord implements ThingWithPoolOfIncorrectAnswers
{
    public VocabularySpellingWord()
    {
    }

    public VocabularySpellingWord(String correctAnswer, String phoneticSpelling, String sampleSentence, Set<String> incorrectSpellings)
    {
        super(correctAnswer, phoneticSpelling, sampleSentence, incorrectSpellings);
    }

    public VocabularySpellingWord(String sampleSentence, String phoneticSpelling, Set<String> incorrectSpellings)
    {
        super(sampleSentence, phoneticSpelling, incorrectSpellings);
    }

    @Override
    protected String createSampleSentenceToDisplayToUser(
            String correctAnswer, String phoneticSpelling, String sampleSentence)
    {
        return sampleSentence.replace(correctAnwerWithBars, "");
    }
}

