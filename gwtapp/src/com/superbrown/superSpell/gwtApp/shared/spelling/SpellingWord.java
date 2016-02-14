package com.superbrown.superSpell.gwtApp.shared.spelling;

import com.superbrown.superSpell.gwtApp.shared.TestableItem;
import com.superbrown.superSpell.gwtApp.shared.ThingWithPoolOfIncorrectAnswers;

import java.util.Set;

public class SpellingWord extends TestableItem implements ThingWithPoolOfIncorrectAnswers
{
    protected String phoneticSpelling;
    protected String sampleSentenceToDisplayToUser;
    protected String fullSentenceWithCorrectSpelling;
    protected Set<String> incorrectAnswers;
    protected String rawSampleSentence;
//    public static final String WORD_SUBSTITUTE_IN_SAMPLE_QUESTIONS = "|word|";
    protected String correctAnwerWithBars;

    public SpellingWord()
    {
        super(new SpellingWordTestingMetric());
    }

    public SpellingWord(String sampleSentence, String phoneticSpelling, Set<String> incorrectSpellings)
    {
        this();

        int firstIndex = sampleSentence.indexOf("|");
        int secondIndex = sampleSentence.indexOf("|", firstIndex + 1);
        String correctAnswer = sampleSentence.substring(firstIndex + 1, secondIndex);

        correctAnwerWithBars = "|" + correctAnswer + "|";
//        sampleSentence = sampleSentence.replace(
//                correctAnwerWithBars,
//                WORD_SUBSTITUTE_IN_SAMPLE_QUESTIONS);

        init(correctAnswer, phoneticSpelling, sampleSentence, incorrectSpellings);
    }

    public static String extractWordFromSampleSentence(String sampleSentence)
    {
        int firstIndex = sampleSentence.indexOf("|");
        int secondIndex = sampleSentence.indexOf("|", firstIndex + 1);
        String correctAnswer = sampleSentence.substring(firstIndex + 1, secondIndex);
        return correctAnswer;
    }

    public SpellingWord(String correctAnswer, String phoneticSpelling, String sampleSentence, Set<String> incorrectSpellings)
    {
        this();

        init(correctAnswer, phoneticSpelling, sampleSentence, incorrectSpellings);
    }

    protected void init(String correctAnswer, String phoneticSpelling, String sampleSentence, Set<String> incorrectSpellings)
    {
        this.rawSampleSentence = sampleSentence.trim();

        phoneticSpelling = phoneticSpelling.trim();
        if (startsWithAnUppercaseCharacter(correctAnswer))
        {
            phoneticSpelling = capitalize(phoneticSpelling);
        }
        this.phoneticSpelling = phoneticSpelling;

        this.fullSentenceWithCorrectSpelling = sampleSentence.replace(
                correctAnwerWithBars, correctAnswer);

        this.correctAnswer = correctAnswer.trim();

        this.sampleSentenceToDisplayToUser =
                createSampleSentenceToDisplayToUser(correctAnswer, phoneticSpelling, rawSampleSentence);

        incorrectSpellings.remove(correctAnswer);

        this.incorrectAnswers = incorrectSpellings;
    }

    protected String createSampleSentenceToDisplayToUser(
            String correctAnswer, String phoneticSpelling, String sampleSentence)
    {
        String phoneticSpellingWithQuotationMarks = "\"" + phoneticSpelling + "\"";

        String sampleSentenceWithPhoneticSpelling = sampleSentence.replace(
                correctAnwerWithBars, phoneticSpellingWithQuotationMarks);

        // capitalize word if it's at the beginning of a sentence
        if (sampleSentenceWithPhoneticSpelling.indexOf(phoneticSpellingWithQuotationMarks) == 0)
        {
            sampleSentenceWithPhoneticSpelling = "\"" +
                    Character.toUpperCase(sampleSentenceWithPhoneticSpelling.charAt(1)) +
                    sampleSentenceWithPhoneticSpelling.substring(2);
        }
        sampleSentenceWithPhoneticSpelling = sampleSentenceWithPhoneticSpelling.replace("\",", ",\"");
        sampleSentenceWithPhoneticSpelling = sampleSentenceWithPhoneticSpelling.replace("\".", ".\"");

        return sampleSentenceWithPhoneticSpelling;
    }

    public static String capitalize(String string)
    {
        return Character.toUpperCase(string.charAt(0)) + string.substring(1);
    }

    protected boolean startsWithAnUppercaseCharacter(String correctSpelling)
    {
        return Character.isUpperCase(correctSpelling.charAt(0));
    }

    public Set<String> getIncorrectAnswers()
    {
        return incorrectAnswers;
    }

    public void setIncorrectAnswers(Set<String> incorrectAnswers)
    {
        this.incorrectAnswers = incorrectAnswers;
    }

    public String getPhoneticSpelling()
    {
        return phoneticSpelling;
    }

    public void setPhoneticSpelling(String phoneticSpelling)
    {
        this.phoneticSpelling = phoneticSpelling;
    }

    public String getSampleSentenceToDisplayToUser()
    {
        return sampleSentenceToDisplayToUser;
    }

    public void setSampleSentenceToDisplayToUser(String sampleSentenceToDisplayToUser)
    {
        this.sampleSentenceToDisplayToUser = sampleSentenceToDisplayToUser;
    }

    public String getFullSentenceWithCorrectSpelling()
    {
        return fullSentenceWithCorrectSpelling;
    }

    public String getRawSampleSentence()
    {
        return rawSampleSentence;
    }

    public boolean wordIsAtTheBeginningOfTheSampleSentence()
    {
        return getRawSampleSentence().startsWith(correctAnwerWithBars);
    }
}

