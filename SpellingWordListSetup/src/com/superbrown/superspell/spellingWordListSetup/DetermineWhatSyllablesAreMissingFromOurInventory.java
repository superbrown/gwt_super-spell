package com.superbrown.superspell.spellingWordListSetup;

import java.util.*;

/**
 */
public class DetermineWhatSyllablesAreMissingFromOurInventory
{
    public static void main(String[] args)
    {
        String spellingListDirectory = "gwtapp/src/com/superbrown/superSpell/gwtApp/server/spelling/spellingLists";

        List<String> paths = Util.getListOfAllSpellingTestConfigurationFiles(spellingListDirectory);

//        // make sure all the words in the lists have phonetic spellings
//        AddPhoneticSpellingsToSpellingLists.addPhoneticSpellingsToSpellingLists(paths, false);

        List<WordAndItsPhoneticSpelling> phoneticSpellings = getAllPhoneticSpellings(paths);

        Map<String, WordAndItsPhoneticSpelling> syllablesInTheSpellingLists =
                extractAListOfSyllables(phoneticSpellings);

        Map<String, String> syllablesInOurInventory = Util.getPropertiesInAlphbeticalOrder(
                spellingListDirectory + "/SyllableSpellings.properties");

        Map<String, WordAndItsPhoneticSpelling> syllablesWeAreMissing =
                deterimineWhichSyllablesWeAreMissingFromOurInventory(
                        syllablesInTheSpellingLists, syllablesInOurInventory);

        Util.writeValuesBackToThePropertiesFile(
                spellingListDirectory + "/SyllableSpellings.properties",
                syllablesInOurInventory,
                syllablesWeAreMissing);
    }

    private static Map<String, WordAndItsPhoneticSpelling> deterimineWhichSyllablesWeAreMissingFromOurInventory(
            Map<String, WordAndItsPhoneticSpelling> syllablesInTheSpellingLists,
            Map<String, String> syllablesInOurInventory)
    {
        Map<String, WordAndItsPhoneticSpelling> syllablesWeAreMissing =
                new LinkedHashMap<String, WordAndItsPhoneticSpelling>();

        for (String syllable : syllablesInTheSpellingLists.keySet())
        {
            String spellings = syllablesInOurInventory.get(syllable);

            if (spellings == null)
            {
                syllablesWeAreMissing.put(syllable, syllablesInTheSpellingLists.get(syllable));
            }
            else if (spellings.startsWith("["))
            {
                syllablesInOurInventory.remove(syllable);
                syllablesWeAreMissing.put(syllable, syllablesInTheSpellingLists.get(syllable));
            }
        }
        return syllablesWeAreMissing;
    }

    private static Map<String, WordAndItsPhoneticSpelling> extractAListOfSyllables(List<WordAndItsPhoneticSpelling> phoneticSpellings)
    {
        Map<String, WordAndItsPhoneticSpelling> syllablesInTheSpellingLists =
                new HashMap<String, WordAndItsPhoneticSpelling>();

        for (WordAndItsPhoneticSpelling wordAndItsPhoneticSpelling : phoneticSpellings)
        {
            String[] arrayOfSyllables = wordAndItsPhoneticSpelling.getPhoneticSpelling().split("-");

            for (int i = 0; i < arrayOfSyllables.length; i++)
            {
                syllablesInTheSpellingLists.put(arrayOfSyllables[i], wordAndItsPhoneticSpelling);
            }
        }
        return syllablesInTheSpellingLists;
    }

    public static List<WordAndItsPhoneticSpelling> getAllPhoneticSpellings(List<String> paths)
    {
        List<WordAndItsPhoneticSpelling> phoneticSpellings = new ArrayList<WordAndItsPhoneticSpelling>();

        for (String path : paths)
        {
            phoneticSpellings.addAll(Util.getPhoneticSpellings(path));
        }
        return phoneticSpellings;
    }

}
