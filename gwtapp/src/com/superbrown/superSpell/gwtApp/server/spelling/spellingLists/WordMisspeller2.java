package com.superbrown.superSpell.gwtApp.server.spelling.spellingLists;

import com.google.gwt.user.client.rpc.IsSerializable;
import com.superbrown.superSpell.gwtApp.shared.spelling.PossibleSyllableSpellings;
import com.superbrown.superSpell.gwtApp.shared.spelling.SpelledSyllable;
import com.superbrown.superSpell.gwtApp.shared.spelling.Syllable;
import com.superbrown.superSpell.gwtApp.shared.spelling.Word;

import java.io.IOException;
import java.io.InputStream;
import java.util.*;

/**
 */
public class WordMisspeller2 implements IsSerializable
{
    public Map<String, PossibleSyllableSpellings> listOfPossibleSyllableSpellings =
            new HashMap<String, PossibleSyllableSpellings>();


    public WordMisspeller2()
    {
        Properties properties = new Properties();
        InputStream inputStream = null;
        try
        {
            inputStream = this.getClass().getResourceAsStream("SyllableSpellings.properties");
            properties.load(inputStream);

            for (Object key : properties.keySet())
            {
                addPossibleSpelling((String)key, ((String)properties.get(key)).split(","));
            }
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        finally
        {
            try
            {
                inputStream.close();
            }
            catch (IOException e)
            {
                e.printStackTrace();
            }
        }
    }

    private void addPossibleSpelling(String phoneticSpelling, String[] possilbeSpellings)
    {
        listOfPossibleSyllableSpellings.put(phoneticSpelling, new PossibleSyllableSpellings(phoneticSpelling, possilbeSpellings));
    }

    public Set<String> generateMisspellings(Word phoneticSpelling)
    {
        Set<Word> misspelledWords = createMisspellings2(phoneticSpelling);

        Set<String> misspellings = new HashSet<String>();

        boolean wordIsCapitalized = startsWithAnUppercaseCharacter(phoneticSpelling.toString());

        for (Word misspelledWord : misspelledWords)
        {
            if (wordIsCapitalized)
            {
                misspellings.add(capitalize(misspelledWord.toString()));
            }
            else
            {
                misspellings.add(misspelledWord.toString());
            }
        }

        return misspellings;
    }

    private Set<Word> createMisspellings2(Word phoneticSpelling)
    {
        return createMisspellings(phoneticSpelling, 0);
    }

    private boolean areDoubleConsonants(char firstCharacter, char secondCharacter)
    {
        return firstCharacter == secondCharacter &&
            isAConsonant(firstCharacter) &&
            isAConsonant(secondCharacter);
    }

    private boolean isAConsonant(char character)
    {
        return !isAVowel(character);
    }

    private boolean isAVowel(char character)
    {
        return (character == 'a' ||
                character == 'e' ||
                character == 'i' ||
                character == 'o' ||
                character == 'u');
    }

    private Set<Word> createMisspellings(Word word, int syllableEntryIndex)
    {
        Set<Word> mispelledWords = new HashSet<Word>();

        if (syllableEntryIndex > word.getSyllables().size())
        {
            return mispelledWords;
        }

        Syllable syllable = word.getSyllables().get(syllableEntryIndex);

        PossibleSyllableSpellings possibleSyllableSpellings =
                listOfPossibleSyllableSpellings.get(syllable.toString().toLowerCase());

        if (possibleSyllableSpellings != null)
        {
            for (String possibleSpelling : possibleSyllableSpellings.getPossilbeSpellings())
            {
//                System.out.println("syllable: " + syllable + "  possible: " + possibleSpelling + "   " + mispelledWords);

                Word newWord = new Word(word);
                newWord.replace(syllable, new SpelledSyllable(possibleSpelling));
                if (newWord.isFullySpelled())
                {
                    mispelledWords.add(newWord);
                }
                else
                {
                    mispelledWords.addAll(createMisspellings(newWord, syllableEntryIndex + 1));
                }
            }
        }
        else
        {
            System.out.println(syllable.toString().toLowerCase() + "    from the word " + 
                    word.getStandardSpelling() + " is not loaded ");
        }

        return mispelledWords;
    }

    private String capitalize(String string)
    {
        return Character.toUpperCase(string.charAt(0)) + string.substring(1);
    }

    private boolean startsWithAnUppercaseCharacter(String correctSpelling)
    {
        return Character.isUpperCase(correctSpelling.charAt(0));
    }

    protected void addMisspelling()
    {

    }
}