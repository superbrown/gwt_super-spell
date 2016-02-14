package com.superbrown.superspell.spellingWordListSetup;

import com.superbrown.superSpell.gwtApp.server.spelling.spellingLists.SpellingListFactory;
import com.superbrown.superSpell.gwtApp.shared.spelling.SpellingWord;

import java.util.ArrayList;
import java.util.List;

/**
 */
public class AddPhoneticSpellingsToSpellingLists
{
    public static void main(String[] args)
    {
        String spellingListDirectory = "gwtapp/src/com/superbrown/superSpell/gwtApp/server/spelling/spellingLists/";

//        List<String> paths = new ArrayList<String>();
//
//        paths.add(spellingListDirectory + "grade6/Unit08.txt");
//        paths.add(spellingListDirectory + "grade6/Unit01.txt");
//        paths.add(spellingListDirectory + "grade3/10-29.txt");

        List<String> paths = Util.getListOfAllSpellingTestConfigurationFiles(spellingListDirectory);

        addPhoneticSpellingsToSpellingLists(paths, false);
    }

    public static void addPhoneticSpellingsToSpellingLists(List<String> paths, boolean force)
    {
        for (String path : paths)
        {
            addPhoneticSpellingsToSpellingLists(path, force);
        }
    }

    public static void addPhoneticSpellingsToSpellingLists(String configFilePath, boolean force)
    {
        List<String> lines = Util.getLinesFromFile(configFilePath);
        List<String> newList = new ArrayList<String>();

        String labelForSpellingWordList = lines.remove(0);

        for (String line : lines)
        {
            String[] segments = line.split(SpellingListFactory.DELIMITTER);
            String sampleSentence = segments[0];
            String phoneticSpelling;

            if (segments.length == 2 && !force)
            {
                newList.add(line);
               continue;
            }

            String word = SpellingWord.extractWordFromSampleSentence(sampleSentence);

            try
            {
                System.out.print("getting phonetic spelling for " + word);
                phoneticSpelling = Util.getPhoneticSpellingFromDictionaryDotCom(word);
            }
            catch (Util.NoPhoneticSpellingFound e)
            {
                phoneticSpelling = "NOT_FOUND";
            }

            System.out.println(" [" + phoneticSpelling + "]");

            String newValue = line + SpellingListFactory.DELIMITTER + phoneticSpelling;
            
            newList.add(newValue);
        }

        Util.writeNewValuesToFile(configFilePath, labelForSpellingWordList, newList);
    }

}
