package com.superbrown.superSpell.gwtApp.server.spelling.spellingLists;

import com.superbrown.superSpell.gwtApp.shared.spelling.SpellingList;
import com.superbrown.superSpell.gwtApp.shared.spelling.SpellingWord;
import com.superbrown.superSpell.gwtApp.shared.spelling.Word;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

/**
 */
public class SpellingListFactory
{
    public static final String DELIMITTER = "~";

    public static void main(String[] args)
    {
        SpellingListFactory spellingListFactory = new SpellingListFactory();
        spellingListFactory.createSpellingList("grade6/Unit01.txt");
    }



    public SpellingList createSpellingList(String configFilePath)
    {
        String spellingListDirectory = "./";

        String filePath = spellingListDirectory + configFilePath;
        List<String> lines = getLinesFromFile(filePath);

        SpellingList spellingList;

        // if it's empty (the first line in these lists contains the name of the list
        if (lines.size() > 1)
        {
            // remove the first line because it contains the name of the spelling word list
            String labelForSpellingWordList = lines.remove(0);
            spellingList = new SpellingList(labelForSpellingWordList);
        }
        else
        {
            spellingList = new SpellingList(filePath + " [not properly initialized]!");
            return spellingList;
        }

        WordMisspeller2 wordMisspeller = new WordMisspeller2();

        for (String line : lines)
        {
            String[] segments = line.split(DELIMITTER);
            if (segments.length != 2)
            {
                spellingList.setName(filePath + " [not properly initialized]");
                System.out.println("\nLine in file " + filePath +  " is in an improper format: \n" +
                        line +
                        " \n(This can happen when the phonetic spelling has not yet been added to the file.)");
                return spellingList;
            }
            String sampleSentence = segments[0];
            String phoneticSpelling = segments[1];

            String word = SpellingWord.extractWordFromSampleSentence(sampleSentence);

            Set<String> incorrectSpellings =
                    wordMisspeller.generateMisspellings(new Word(phoneticSpelling, word));

            SpellingWord spellingWord = createSpellingWordObject(sampleSentence, phoneticSpelling, incorrectSpellings);

            spellingList.addSpellingWordToInventory(spellingWord);
        }

        return spellingList;
    }

    protected SpellingWord createSpellingWordObject(String sampleSentence, String phoneticSpelling, Set<String> incorrectSpellings)
    {
        return new SpellingWord(
                        sampleSentence,
                        phoneticSpelling,
                        incorrectSpellings);
    }

    public List<String> getLinesFromFile(String path)
    {
        List<String> lines = new ArrayList<String>();
        Scanner scanner = null;
        InputStream inputStream = null;

        try
        {
            inputStream = this.getClass().getResourceAsStream(path);
            if (inputStream == null)
            {
                // file is not available
                System.err.println("Spelling list configuration file unavailable: " + path);
                return lines;
            }

            scanner = new Scanner(inputStream);

            while (scanner.hasNextLine())
            {
                lines.add(scanner.nextLine());
            }
        }
        catch (Throwable e)
        {
            e.printStackTrace();
        }
        finally
        {
            try
            {
                if (scanner != null)
                {
                    scanner.close();
                }
            }
            finally
            {
                if (inputStream != null)
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
        }
        return lines;
    }
}
