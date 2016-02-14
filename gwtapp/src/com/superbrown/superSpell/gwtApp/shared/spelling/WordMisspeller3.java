package com.superbrown.superSpell.gwtApp.shared.spelling;

import com.google.gwt.user.client.rpc.IsSerializable;

import java.util.*;

/**
 */
public class WordMisspeller3 implements IsSerializable
{
    public static Map<String, PossibleSyllableSpellings> listOfPossibleSyllableSpellings =
            new HashMap<String, PossibleSyllableSpellings>();


    static
    {
        addPossibleSpelling("ad", new String[]{"ad"});
        addPossibleSpelling("ahr", new String[]{"ar"});
        addPossibleSpelling("akt", new String[]{"act"});
        addPossibleSpelling("ap", new String[]{"app", "ap"});
        addPossibleSpelling("bil", new String[] {"bil", "bill"});
        addPossibleSpelling("biz", new String[] {"bus", "bis"});
        addPossibleSpelling("buhl", new String[] {"ble", "bal", "bel"});
        addPossibleSpelling("cher", new String[] {"ture", "tcher", "cher"});
        addPossibleSpelling("dees", new String[] {"dies", "dees", "dease"});
        addPossibleSpelling("dih", new String[] {"di", "de"});
        addPossibleSpelling("dis", new String[] {"dis", "diss"});
        addPossibleSpelling("diz", new String[] {"dizz", "diz"});
        addPossibleSpelling("doo", new String[] {"doo", "du", "due"});
        addPossibleSpelling("dur", new String[] {"dir", "dur"});
        addPossibleSpelling("ee", new String[] {"ee", "y", "ey"});
        addPossibleSpelling("eed", new String[] {"eed", "ied", "ead"});
        addPossibleSpelling("ees", new String[] {"ies", "ys", "ease"});
        addPossibleSpelling("emp", new String[] {"emp", "imp", "amp"});
        addPossibleSpelling("en", new String[] {"en", "an", "in"});
        addPossibleSpelling("er", new String[] {"er", "ir", "ure"});
        addPossibleSpelling("ey", new String[] {"a", "ay", "ey"});
        addPossibleSpelling("fahyd", new String[] {"fied", "fyed", "fyde"});
        addPossibleSpelling("fur", new String[] {"fur", "fir"});
        addPossibleSpelling("gees", new String[] {"gees", "gies"});
        addPossibleSpelling("glans", new String[] {"glance", "glance", "glanse", "glans"});
        addPossibleSpelling("gwij", new String[] {"guage", "gage", "gauge"});
        addPossibleSpelling("hap", new String[] {"hap", "happ"});
        addPossibleSpelling("i", new String[] {"i", "a", "e", "u"});
        addPossibleSpelling("ich", new String[] {"ich", "ach", "ech", "uch"});
        addPossibleSpelling("id", new String[] {"id", "ed"});
        addPossibleSpelling("ij", new String[] {"age", "adge", "ige"});
        addPossibleSpelling("ik", new String[] {"ex", "ec"});
        addPossibleSpelling("in", new String[] {"in"});
        addPossibleSpelling("ist", new String[] {"ist", "est"});
        addPossibleSpelling("jees", new String[] {"gies", "jies"});
        addPossibleSpelling("ki", new String[] {"chi", "che", "ci", "ce"});
        addPossibleSpelling("kol", new String[] {"col", "coll"});
        addPossibleSpelling("kuh", new String[] {"co"});
        addPossibleSpelling("kuhn", new String[] {"con", "coun"});
        addPossibleSpelling("kur", new String[] {"cur", "cer"});
        addPossibleSpelling("lang", new String[] {"lang"});
        addPossibleSpelling("lee", new String[] {"lee", "li", "ly"});
        addPossibleSpelling("lev", new String[] {"lev", "levv"});
        addPossibleSpelling("ley", new String[] {"la", "lay", "lai"});
        addPossibleSpelling("lib", new String[] {"lib", "libb", "leb"});
        addPossibleSpelling("lil", new String[] {"lil", "lill"});
        addPossibleSpelling("luhv", new String[] {"love", "lov", "luv-"});
        addPossibleSpelling("mees", new String[] {"mees", "mies", "mease"});
        addPossibleSpelling("milk", new String[] {"milk", "milck", "milc"});
        addPossibleSpelling("moh", new String[] {"mo"});
        addPossibleSpelling("moun", new String[] {"moun"});
        addPossibleSpelling("nee", new String[] {"ny", "ni", "knee", "nee"});
        addPossibleSpelling("nees", new String[] {"nies", "nease", "knees"});
        addPossibleSpelling("ney", new String[] {"na", "nay"});
        addPossibleSpelling("nis", new String[] {"ness", "nis"});
        addPossibleSpelling("ni", new String[] {"ni", "na"});
        addPossibleSpelling("noh", new String[] {"no", "kno", "know"});
        addPossibleSpelling("nuh", new String[] {"ni", "ne", "na"});
        addPossibleSpelling("pen", new String[] {"pen", "penn"});
        addPossibleSpelling("pit", new String[] {"pit", "pitt"});
        addPossibleSpelling("plahys", new String[] {"plies", "plys"});
        addPossibleSpelling("plezh", new String[] {"pleas", "plaes", "ples"});
        addPossibleSpelling("pruh", new String[] {"pre", "pro", "per"});
        addPossibleSpelling("rek", new String[] {"rec", "reck"});
        addPossibleSpelling("rees", new String[] {"rees", "ries", "rease"});
        addPossibleSpelling("ree", new String[] {"re", "rie", "ree"});
        addPossibleSpelling("ri", new String[] {"re", "ri"});
        addPossibleSpelling("send", new String[] {"send", "cend"});
        addPossibleSpelling("sept", new String[] {"cept", "cet", "sept"});
        addPossibleSpelling("shuhn", new String[] {"sion", "tion"});
        addPossibleSpelling("skair", new String[] {"scar", "scair"});
        addPossibleSpelling("skuhv", new String[] {"scov"});
        addPossibleSpelling("skur", new String[] {"scour", "scor", "scur"});
        addPossibleSpelling("sol", new String[] {"sol", "soll"});
        addPossibleSpelling("spekt", new String[] {"spect"});
        addPossibleSpelling("spahyd", new String[] {"spied", "spyed", "spyde", "spide"});
        addPossibleSpelling("spin", new String[] {"spin"});
        addPossibleSpelling("spond", new String[] {"spond"});
        addPossibleSpelling("strat", new String[] {"strat"});
        addPossibleSpelling("stroi", new String[] {"stroy"});
        addPossibleSpelling("tahy", new String[] {"ti", "tai"});
        addPossibleSpelling("tee", new String[] {"tee", "ty"});
        addPossibleSpelling("tek", new String[] {"tech", "tec"});
        addPossibleSpelling("ten", new String[] {"ten"});
        addPossibleSpelling("tees", new String[] {"tees", "ties", "tease"});
        addPossibleSpelling("thee", new String[] {"thi", "the"});
        addPossibleSpelling("tis", new String[] {"tice", "tise"});
        addPossibleSpelling("tiv", new String[] {"tive", "tiv"});
        addPossibleSpelling("tn", new String[] {"tain", "tian"});
        addPossibleSpelling("trees", new String[] {"trees", "tries", "trease"});
        addPossibleSpelling("trezh", new String[] {"treas", "treash", "traes", "tres"});
        addPossibleSpelling("tuh", new String[] {"to", "ta", "ti"});
        addPossibleSpelling("tuhns", new String[] {"tance", "tants"});
        addPossibleSpelling("uh", new String[] {"a"});
        addPossibleSpelling("uhn", new String[] {"un"});
        addPossibleSpelling("ur", new String[] {"ur", "er", "ir"});
        addPossibleSpelling("ven", new String[] {"ven"});
        addPossibleSpelling("vik", new String[] {"vik", "vic"});
        addPossibleSpelling("voi", new String[] {"voy"});
        addPossibleSpelling("vuhl", new String[] {"vul"});
        addPossibleSpelling("wur", new String[] {"wor", "were", "wir"});
        addPossibleSpelling("yoo", new String[] {"you", "u"});


//        substitutionPairs.add(new PossibleSyllableSpellings("--------", new String[] {"--------", "--------", "--------"});


    }

    private static void addPossibleSpelling(String phoneticSpelling, String[] possilbeSpellings)
    {
        listOfPossibleSyllableSpellings.put(phoneticSpelling, new PossibleSyllableSpellings(phoneticSpelling, possilbeSpellings));
    }

    public static Set<String> generateMisspellings(Word phoneticSpelling)
    {
        List<PossibleSyllableSpellings> possibleSyllableSpellingsList = new ArrayList<PossibleSyllableSpellings>();

        for (Syllable syllable : phoneticSpelling.getSyllables())
        {
            PossibleSyllableSpellings possibleSyllableSpellings = listOfPossibleSyllableSpellings.get(syllable.toString());
            if (possibleSyllableSpellings != null)
            {
                possibleSyllableSpellingsList.add(possibleSyllableSpellings);
            }
        }

        Set<Word> misspelledWords = createMisspellings(phoneticSpelling);

        Set<String> misspellings = new HashSet<String>();

        for (Word misspelledWord : misspelledWords)
        {
            misspellings.add(misspelledWord.toString());
        }

        if (misspellings.size() == 0)
        {
            System.err.println("At least one of the syllables of the word with the phonetic spelling of " +
                    phoneticSpelling.spellingWithSyllableDashes() + " is not defined in the syllable substitution list.");
        }

        return misspellings;
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

    private static Set<Word> createMisspellings(Word word)
    {
        Set<Word> mispelledWords = new HashSet<Word>();

        for (Syllable syllable : word.getSyllables())
        {
            if (syllable instanceof SpelledSyllable)
            {
                continue;
            }

            PossibleSyllableSpellings possibleSyllableSpellings =
                    listOfPossibleSyllableSpellings.get(syllable.toString());

            if (possibleSyllableSpellings != null)
            {

                for (String possibleSpelling : possibleSyllableSpellings.getPossilbeSpellings())
                {
                    Word newWord = new Word(word);
                    newWord.replace(syllable, new SpelledSyllable(possibleSpelling));
                    if (newWord.isFullySpelled())
                    {
                        mispelledWords.add(newWord);
                    }
                    mispelledWords.addAll(createMisspellings(newWord));
                }
            }
            else
            {
            }
        }

        return mispelledWords;
    }
}