package com.superbrown.superSpell.gwtApp.shared.spelling;

import java.util.*;

/**
 */
public class WordMisspeller
{
    public static List<SubstitutionPair> substitutionPairs = new ArrayList<SubstitutionPair>();


    static
    {
        substitutionPairs.addAll(generateSubstitutionPairs("a", new String[] {"ay", "eigh", "ai"}));
        substitutionPairs.addAll(generateSubstitutionPairs("e", new String[] {"ee", "ea", "ey", "y", "ie"}));
        substitutionPairs.addAll(generateSubstitutionPairs("i", new String[] {"i", "y", "igh"}));
        substitutionPairs.addAll(generateSubstitutionPairs("o", new String[] {"o", "oa"}));
        substitutionPairs.addAll(generateSubstitutionPairs("u", new String[] {"u", "ue", "oo", "ew"}));

        substitutionPairs.addAll(generateSubstitutionPairs("n", new String[] {"n", "gn", "kn"}));
        substitutionPairs.addAll(generateSubstitutionPairs("f", new String[] {"ph", "f", "ffe"}));
        substitutionPairs.addAll(generateSubstitutionPairs("s", new String[] {"s", "c", "ce", "se"}));
        substitutionPairs.addAll(generateSubstitutionPairs("z", new String[] {"z", "s", "ze", "zy"}));
        substitutionPairs.addAll(generateSubstitutionPairs("k", new String[] {"c", "ck", "ch", "cc"}));
        substitutionPairs.addAll(generateSubstitutionPairs("j", new String[] {"j", "ge", "gi", "ge"}));
        substitutionPairs.addAll(generateSubstitutionPairs("ch", new String[] {"ch", "tch", "tu"}));
        substitutionPairs.addAll(generateSubstitutionPairs("sh", new String[] {"sh", "ss", "ci", "ti"}));

        substitutionPairs.addAll(generateSubstitutionPairs("oi", new String[] {"oi", "oy"}));
        substitutionPairs.addAll(generateSubstitutionPairs("ou", new String[] {"ou", "ow"}));
        substitutionPairs.addAll(generateSubstitutionPairs("oo", new String[] {"oo", "ew", "oe"}));
        substitutionPairs.addAll(generateSubstitutionPairs("ar", new String[] {"ar", "er"}));
        substitutionPairs.addAll(generateSubstitutionPairs("or", new String[] {"or", "ar"}));
        substitutionPairs.addAll(generateSubstitutionPairs("ur", new String[] {"ur", "er", "ir", "or", "ar"}));
        substitutionPairs.addAll(generateSubstitutionPairs("el", new String[] {"le", "al", "el", "il"}));
        substitutionPairs.addAll(generateSubstitutionPairs("en", new String[] {"en", "on", "ain"}));
    }

    public static Set<String> generateMisspellings(String word)
    {
        List<SubstitutionPair> applicableSubstitutionPairs = new ArrayList<SubstitutionPair>();

        for (SubstitutionPair substitutionPair : substitutionPairs)
        {
            if (word.contains(substitutionPair.getReplaceThis()))
            {
                applicableSubstitutionPairs.add(substitutionPair);
            }
        }

        if (word.length() > 2)
        {
            for (int i = 1; i < word.length(); i++)
            {
                char firstCharacter = word.charAt(i - 1);
                char secondCharacter = word.charAt(i);

                if (areDoubleConsonants(firstCharacter, secondCharacter))
                {
                    applicableSubstitutionPairs.add(
                            new SubstitutionPair("" + firstCharacter + secondCharacter,
                                                 "" + firstCharacter));
                }
            }
        }

        return createMisspellings(word, applicableSubstitutionPairs);
    }

    private static boolean areDoubleConsonants(char firstCharacter, char secondCharacter)
    {
        return firstCharacter == secondCharacter &&
            isAConsonant(firstCharacter) &&
            isAConsonant(secondCharacter);
    }

    private static boolean isAConsonant(char character)
    {
        return !isAVowel(character);
    }

    private static boolean isAVowel(char character)
    {
        return (character == 'a' ||
                character == 'e' ||
                character == 'i' ||
                character == 'o' ||
                character == 'u');
    }

    private static Set<String> createMisspellings(String word, List<SubstitutionPair> substitutionPairs)
    {
        Set<String> mispelledWords = new HashSet<String>();

        for (SubstitutionPair substitutionPair : substitutionPairs)
        {
            List<SubstitutionPair> copyOfSubstitutionPairs = new ArrayList<SubstitutionPair>();
            copyOfSubstitutionPairs.addAll(substitutionPairs);
            copyOfSubstitutionPairs.remove(substitutionPair);
            String misspelledWord = word.replaceFirst(substitutionPair.getReplaceThis(), substitutionPair.getWithThat());
            mispelledWords.add(misspelledWord);
            mispelledWords.addAll(createMisspellings(misspelledWord, copyOfSubstitutionPairs));
        }

        return mispelledWords;
    }

    public static List<SubstitutionPair> generateSubstitutionPairs(String sound, String[] synonyms)
    {
        List<SubstitutionPair> substitutionPairs = new ArrayList<SubstitutionPair>();

        for (int i = 0; i < synonyms.length; i++)
        {
            substitutionPairs.add(new SubstitutionPair(sound, synonyms[i]));
        }

        return substitutionPairs;
    }
}
