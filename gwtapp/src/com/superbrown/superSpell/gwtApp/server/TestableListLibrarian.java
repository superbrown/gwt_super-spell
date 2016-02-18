package com.superbrown.superSpell.gwtApp.server;


import com.superbrown.superSpell.gwtApp.server.spelling.spellingLists.SpellingListFactory;
import com.superbrown.superSpell.gwtApp.server.spelling.spellingLists.VocabularySpellingListFactory;
import com.superbrown.superSpell.gwtApp.server.vocabulary.vocabularyLists.grade3.VocabularyList_SocialStudies_Unit01;
import com.superbrown.superSpell.gwtApp.server.vocabulary.vocabularyLists.grade3.VocabularyList_WordMaster3;
import com.superbrown.superSpell.gwtApp.shared.ITestable;
import com.superbrown.superSpell.gwtApp.shared.mathFacts.addition.AdditionMathFacts;
import com.superbrown.superSpell.gwtApp.shared.mathFacts.division.DivisionMathFacts;
import com.superbrown.superSpell.gwtApp.shared.mathFacts.multiplication.MultiplicationMathFacts;
import com.superbrown.superSpell.gwtApp.shared.mathFacts.subtraction.SubtractionMathFacts;
import com.superbrown.superSpell.gwtApp.shared.vocabulary.IVocabularyList;
import com.superbrown.superSpell.gwtApp.shared.vocabulary.VocabularyList;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

//import com.superbrown.superSpell.gwtApp.server.spelling.spellingLists.grade3.SpellingList_2010_08_27;
//import com.superbrown.superSpell.gwtApp.server.spelling.spellingLists.grade3.SpellingList_2010_09_03;
//import com.superbrown.superSpell.gwtApp.server.spelling.spellingLists.grade3.SpellingList_2010_09_10;
//import com.superbrown.superSpell.gwtApp.server.spelling.spellingLists.grade6.*;

/**
 */
public class TestableListLibrarian
{
    private Map<String, Map<String, ITestable>> schoolClasses = new HashMap<>();

    private static String STANDARD_CLASS_NAME_PREFIX = "SpellingTest_";
    private static Integer mathFactTimeLimit = 1;

    public TestableListLibrarian()
    {
        super();
        try
        {
            init();
        }
        catch (Throwable e)
        {
            System.out.println(e);
            e.printStackTrace();
        }
    }

    private void init()
    {
        clear();

        add("3rd Grade: Vocabulary", new VocabularyList_WordMaster3("WordMaster 3"));

        initMathFacts();

//        add("5th Grade: Science", new VocabularyList_Lesson13("Chapter 15 vocabulary"));

        add("3rd Grade: Social Studies", new VocabularyList_SocialStudies_Unit01("Unit 1 vocabulary"));

//        add("6th Grade: Vocabulary", new VocabularyList_Lesson13("Vocabulary Lesson 13"));
        add("6th Grade: Vocabulary", new com.superbrown.superSpell.gwtApp.server.vocabulary.vocabularyLists.grade6.VocabularyList_Lesson01("Vocabulary Lesson 01"));
        add("6th Grade: Vocabulary", new com.superbrown.superSpell.gwtApp.server.vocabulary.vocabularyLists.grade6.VocabularyList_Lesson02("Vocabulary Lesson 02"));
        add("6th Grade: Vocabulary", new com.superbrown.superSpell.gwtApp.server.vocabulary.vocabularyLists.grade6.VocabularyList_Lesson03("Vocabulary Lesson 03"));
        add("6th Grade: Vocabulary", new com.superbrown.superSpell.gwtApp.server.vocabulary.vocabularyLists.grade6.VocabularyList_Lesson04("Vocabulary Lesson 04"));
        add("6th Grade: Vocabulary", new com.superbrown.superSpell.gwtApp.server.vocabulary.vocabularyLists.grade6.VocabularyList_Lesson05("Vocabulary Lesson 05"));
        add("6th Grade: Vocabulary", new com.superbrown.superSpell.gwtApp.server.vocabulary.vocabularyLists.grade6.VocabularyList_Lesson06("Vocabulary Lesson 06"));
        add("6th Grade: Vocabulary", new com.superbrown.superSpell.gwtApp.server.vocabulary.vocabularyLists.grade6.VocabularyList_Lesson07("Vocabulary Lesson 07"));
        add("6th Grade: Vocabulary", new com.superbrown.superSpell.gwtApp.server.vocabulary.vocabularyLists.grade6.VocabularyList_Lesson08("Vocabulary Lesson 08"));
        add("6th Grade: Vocabulary", new com.superbrown.superSpell.gwtApp.server.vocabulary.vocabularyLists.grade6.VocabularyList_Lesson09("Vocabulary Lesson 09"));
        add("6th Grade: Vocabulary", new com.superbrown.superSpell.gwtApp.server.vocabulary.vocabularyLists.grade6.VocabularyList_Lesson10("Vocabulary Lesson 10"));
        add("6th Grade: Vocabulary", new com.superbrown.superSpell.gwtApp.server.vocabulary.vocabularyLists.grade6.VocabularyList_Lesson11("Vocabulary Lesson 11"));
        add("6th Grade: Vocabulary", new com.superbrown.superSpell.gwtApp.server.vocabulary.vocabularyLists.grade6.VocabularyList_Lesson12("Vocabulary Lesson 12"));
        add("6th Grade: Vocabulary", new com.superbrown.superSpell.gwtApp.server.vocabulary.vocabularyLists.grade6.VocabularyList_Lesson13("Vocabulary Lesson 13"));
        add("6th Grade: Vocabulary", new com.superbrown.superSpell.gwtApp.server.vocabulary.vocabularyLists.grade6.VocabularyList_Lesson14("Vocabulary Lesson 14"));
        add("6th Grade: Vocabulary", new com.superbrown.superSpell.gwtApp.server.vocabulary.vocabularyLists.grade6.VocabularyList_Lesson15("Vocabulary Lesson 15"));
        add("6th Grade: Vocabulary", new com.superbrown.superSpell.gwtApp.server.vocabulary.vocabularyLists.grade6.VocabularyList_Lesson16("Vocabulary Lesson 16"));
        add("6th Grade: Vocabulary", new com.superbrown.superSpell.gwtApp.server.vocabulary.vocabularyLists.grade6.VocabularyList_Lesson17("Vocabulary Lesson 17"));
        add("6th Grade: Vocabulary", new com.superbrown.superSpell.gwtApp.server.vocabulary.vocabularyLists.grade6.VocabularyList_Lesson18("Vocabulary Lesson 18"));
        add("6th Grade: Vocabulary", new com.superbrown.superSpell.gwtApp.server.vocabulary.vocabularyLists.grade6.VocabularyList_Lesson19("Vocabulary Lesson 19"));
        add("6th Grade: Vocabulary", new com.superbrown.superSpell.gwtApp.server.vocabulary.vocabularyLists.grade6.VocabularyList_Lesson20("Vocabulary Lesson 20"));
        add("6th Grade: Vocabulary", new com.superbrown.superSpell.gwtApp.server.vocabulary.vocabularyLists.grade6.VocabularyList_Lesson21("Vocabulary Lesson 21"));
        add("6th Grade: Vocabulary", new com.superbrown.superSpell.gwtApp.server.vocabulary.vocabularyLists.grade6.VocabularyList_Lesson22("Vocabulary Lesson 22"));
        add("6th Grade: Vocabulary", new com.superbrown.superSpell.gwtApp.server.vocabulary.vocabularyLists.grade6.VocabularyList_Lesson23("Vocabulary Lesson 23"));
        add("6th Grade: Vocabulary", new com.superbrown.superSpell.gwtApp.server.vocabulary.vocabularyLists.grade6.VocabularyList_Lesson24("Vocabulary Lesson 24"));
//        add("6th Grade: Vocabulary", new com.superbrown.superSpell.gwtApp.server.vocabulary.vocabularyLists.grade6.VocabularyList_Lesson11Through20Review("Vocabulary Lesson 11 through 20"));
        add("6th Grade: Vocabulary", new com.superbrown.superSpell.gwtApp.server.vocabulary.vocabularyLists.grade6.VocabularyList_WordMasterI("WordMaster 1"));
        add("6th Grade: Vocabulary", new com.superbrown.superSpell.gwtApp.server.vocabulary.vocabularyLists.grade6.VocabularyList_WordMasterII("WordMaster 2"));

        add("7th Grade: Vocabulary", new com.superbrown.superSpell.gwtApp.server.vocabulary.vocabularyLists.grade7.VocabularyList_Lesson01("Lesson 01"));
        add("7th Grade: Vocabulary", new com.superbrown.superSpell.gwtApp.server.vocabulary.vocabularyLists.grade7.VocabularyList_Lesson02("Lesson 02"));
        add("7th Grade: Vocabulary", new com.superbrown.superSpell.gwtApp.server.vocabulary.vocabularyLists.grade7.VocabularyList_Lesson03("Lesson 03"));
        add("7th Grade: Vocabulary", new com.superbrown.superSpell.gwtApp.server.vocabulary.vocabularyLists.grade7.VocabularyList_Lesson01Thru03Review("Lessons 01-03 Review"));
        add("7th Grade: Vocabulary", new com.superbrown.superSpell.gwtApp.server.vocabulary.vocabularyLists.grade7.VocabularyList_Lesson04("Lesson 04"));
        add("7th Grade: Vocabulary", new com.superbrown.superSpell.gwtApp.server.vocabulary.vocabularyLists.grade7.VocabularyList_Lesson05("Lesson 05"));
        add("7th Grade: Vocabulary", new com.superbrown.superSpell.gwtApp.server.vocabulary.vocabularyLists.grade7.VocabularyList_Lesson06("Lesson 06"));
        add("7th Grade: Vocabulary", new com.superbrown.superSpell.gwtApp.server.vocabulary.vocabularyLists.grade7.VocabularyList_Lesson04Thru06Review("Lessons 04-06 Review"));
        add("7th Grade: Vocabulary", new com.superbrown.superSpell.gwtApp.server.vocabulary.vocabularyLists.grade7.VocabularyList_Lesson07("Lesson 07"));
        add("7th Grade: Vocabulary", new com.superbrown.superSpell.gwtApp.server.vocabulary.vocabularyLists.grade7.VocabularyList_Lesson08("Lesson 08"));
        add("7th Grade: Vocabulary", new com.superbrown.superSpell.gwtApp.server.vocabulary.vocabularyLists.grade7.VocabularyList_Lesson09("Lesson 09"));
        add("7th Grade: Vocabulary", new com.superbrown.superSpell.gwtApp.server.vocabulary.vocabularyLists.grade7.VocabularyList_Lesson07Thru09Review("Lessons 07-09 Review"));
        add("7th Grade: Vocabulary", new com.superbrown.superSpell.gwtApp.server.vocabulary.vocabularyLists.grade7.VocabularyList_Lesson13("Lesson 13"));
        add("7th Grade: Vocabulary", new com.superbrown.superSpell.gwtApp.server.vocabulary.vocabularyLists.grade7.VocabularyList_Lesson14("Lesson 14"));
        add("7th Grade: Vocabulary", new com.superbrown.superSpell.gwtApp.server.vocabulary.vocabularyLists.grade7.VocabularyList_Lesson15("Lesson 15"));
        add("7th Grade: Vocabulary", new com.superbrown.superSpell.gwtApp.server.vocabulary.vocabularyLists.grade7.VocabularyList_Lesson13Thru15Review("Lessons 13-15 Review"));
        add("7th Grade: Vocabulary", new com.superbrown.superSpell.gwtApp.server.vocabulary.vocabularyLists.grade7.VocabularyList_Lesson16("Lesson 16"));
        add("7th Grade: Vocabulary", new com.superbrown.superSpell.gwtApp.server.vocabulary.vocabularyLists.grade7.VocabularyList_Lesson17("Lesson 17"));
        add("7th Grade: Vocabulary", new com.superbrown.superSpell.gwtApp.server.vocabulary.vocabularyLists.grade7.VocabularyList_Lesson18("Lesson 18"));
        add("7th Grade: Vocabulary", new com.superbrown.superSpell.gwtApp.server.vocabulary.vocabularyLists.grade7.VocabularyList_Lesson16Thru18Review("Lessons 16-18 Review"));
        add("7th Grade: Vocabulary", new com.superbrown.superSpell.gwtApp.server.vocabulary.vocabularyLists.grade7.VocabularyList_Lesson19("Lesson 19"));
        add("7th Grade: Vocabulary", new com.superbrown.superSpell.gwtApp.server.vocabulary.vocabularyLists.grade7.VocabularyList_Lesson20("Lesson 20"));
        add("7th Grade: Vocabulary", new com.superbrown.superSpell.gwtApp.server.vocabulary.vocabularyLists.grade7.VocabularyList_Lesson21("Lesson 21"));
        add("7th Grade: Vocabulary", new com.superbrown.superSpell.gwtApp.server.vocabulary.vocabularyLists.grade7.VocabularyList_Lesson19Thru21Review("Lessons 19-21 Review"));
        add("7th Grade: Vocabulary", new com.superbrown.superSpell.gwtApp.server.vocabulary.vocabularyLists.grade7.VocabularyList_WordMaster01("Word Master I"));
        add("7th Grade: Vocabulary", new com.superbrown.superSpell.gwtApp.server.vocabulary.vocabularyLists.grade7.VocabularyList_WordMaster02("Word Master II"));
        add("7th Grade: Vocabulary", new com.superbrown.superSpell.gwtApp.server.vocabulary.vocabularyLists.grade7.VocabularyList_WordMaster03("Word Master III"));

        SpellingListFactory spellingListFactory = new SpellingListFactory();
        SpellingListFactory vocabularySpellingListFactory = new VocabularySpellingListFactory();

//        add("Spelling: 2nd Grade", new com.superbrown.superSpell.gwtApp.shared.spelling.spellingLists.grade2.SpellingList_2010_05_07());
//        add("Spelling: 2nd Grade", new com.superbrown.superSpell.gwtApp.shared.spelling.spellingLists.grade2.SpellingList_2010_05_14());

        //        add("3rd Grade: Spelling", new SpellingList_2010_08_27());
//        add("3rd Grade: Spelling", new SpellingList_2010_09_03());
//        add("3rd Grade: Spelling", new SpellingList_2010_09_10());
//        add(THIRD_GRADE_SPELLING, spellingListFactory.createSpellingList("grade3/2010-10-29.txt"));
        add("3rd Grade: Spelling", spellingListFactory.createSpellingList("grade3/2010-11-05.txt"));
        add("3rd Grade: Spelling", spellingListFactory.createSpellingList("grade3/2010-11-12.txt"));
        add("3rd Grade: Spelling", spellingListFactory.createSpellingList("grade3/2010-11-19.txt"));
        add("3rd Grade: Spelling", spellingListFactory.createSpellingList("grade3/2011-05-20.txt"));

        add("4th Grade: Spelling", spellingListFactory.createSpellingList("grade4/Unit05.txt"));
        add("4th Grade: Spelling", spellingListFactory.createSpellingList("grade4/Unit06.txt"));

        add("6th Grade: Spelling", spellingListFactory.createSpellingList("grade6/WordsLoganHasMisspelled01.txt"));
        add("6th Grade: Spelling", spellingListFactory.createSpellingList("grade6/Unit01.txt"));
        add("6th Grade: Spelling", spellingListFactory.createSpellingList("grade6/Unit02.txt"));
        add("6th Grade: Spelling", spellingListFactory.createSpellingList("grade6/Unit03.txt"));
        add("6th Grade: Spelling", spellingListFactory.createSpellingList("grade6/Unit04.txt"));
        add("6th Grade: Spelling", spellingListFactory.createSpellingList("grade6/Unit07.txt"));
        add("6th Grade: Spelling", spellingListFactory.createSpellingList("grade6/Unit08.txt"));
        add("6th Grade: Spelling", spellingListFactory.createSpellingList("grade6/Lesson13.txt"));
        add("6th Grade: Spelling", spellingListFactory.createSpellingList("grade6/Lesson14.txt"));
        add("6th Grade: Spelling", spellingListFactory.createSpellingList("grade6/Lesson15.txt"));
        add("6th Grade: Spelling", spellingListFactory.createSpellingList("grade6/Lesson16.txt"));
        add("6th Grade: Spelling", spellingListFactory.createSpellingList("grade6/Lesson17.txt"));
        add("6th Grade: Spelling", spellingListFactory.createSpellingList("grade6/Lesson18.txt"));
        add("6th Grade: Spelling", spellingListFactory.createSpellingList("grade6/Lesson19.txt"));
        add("6th Grade: Spelling", spellingListFactory.createSpellingList("grade6/Lesson20.txt"));
        add("6th Grade: Spelling", spellingListFactory.createSpellingList("grade6/Lesson21.txt"));
        add("6th Grade: Spelling", spellingListFactory.createSpellingList("grade6/Lesson22.txt"));

        add("7th Grade: Spelling of Vocabulary Words", vocabularySpellingListFactory.createSpellingList("grade7/Lesson01.txt"));
        add("7th Grade: Spelling of Vocabulary Words", vocabularySpellingListFactory.createSpellingList("grade7/Lesson02.txt"));
        add("7th Grade: Spelling of Vocabulary Words", vocabularySpellingListFactory.createSpellingList("grade7/Lesson03.txt"));
        add("7th Grade: Spelling of Vocabulary Words", vocabularySpellingListFactory.createSpellingList("grade7/Lessons01Thru03Review.txt"));
        add("7th Grade: Spelling of Vocabulary Words", vocabularySpellingListFactory.createSpellingList("grade7/Lesson04.txt"));
        add("7th Grade: Spelling of Vocabulary Words", vocabularySpellingListFactory.createSpellingList("grade7/Lesson05.txt"));
        add("7th Grade: Spelling of Vocabulary Words", vocabularySpellingListFactory.createSpellingList("grade7/Lesson06.txt"));
        add("7th Grade: Spelling of Vocabulary Words", vocabularySpellingListFactory.createSpellingList("grade7/Lessons04Thru06Review.txt"));
        add("7th Grade: Spelling of Vocabulary Words", vocabularySpellingListFactory.createSpellingList("grade7/Lesson07.txt"));
        add("7th Grade: Spelling of Vocabulary Words", vocabularySpellingListFactory.createSpellingList("grade7/Lesson08.txt"));
        add("7th Grade: Spelling of Vocabulary Words", vocabularySpellingListFactory.createSpellingList("grade7/Lesson09.txt"));
        add("7th Grade: Spelling of Vocabulary Words", vocabularySpellingListFactory.createSpellingList("grade7/Lessons07Thru09Review.txt"));
        add("7th Grade: Spelling of Vocabulary Words", vocabularySpellingListFactory.createSpellingList("grade7/Lesson10.txt"));
        add("7th Grade: Spelling of Vocabulary Words", vocabularySpellingListFactory.createSpellingList("grade7/Lesson11.txt"));
        add("7th Grade: Spelling of Vocabulary Words", vocabularySpellingListFactory.createSpellingList("grade7/Lesson12.txt"));
        add("7th Grade: Spelling of Vocabulary Words", vocabularySpellingListFactory.createSpellingList("grade7/Lessons10Thru12Review.txt"));
        add("7th Grade: Spelling of Vocabulary Words", vocabularySpellingListFactory.createSpellingList("grade7/Lesson13.txt"));
        add("7th Grade: Spelling of Vocabulary Words", vocabularySpellingListFactory.createSpellingList("grade7/Lesson14.txt"));
        add("7th Grade: Spelling of Vocabulary Words", vocabularySpellingListFactory.createSpellingList("grade7/Lesson15.txt"));
        add("7th Grade: Spelling of Vocabulary Words", vocabularySpellingListFactory.createSpellingList("grade7/Lessons13Thru15Review.txt"));
        add("7th Grade: Spelling of Vocabulary Words", vocabularySpellingListFactory.createSpellingList("grade7/Lesson16.txt"));
        add("7th Grade: Spelling of Vocabulary Words", vocabularySpellingListFactory.createSpellingList("grade7/Lesson17.txt"));
        add("7th Grade: Spelling of Vocabulary Words", vocabularySpellingListFactory.createSpellingList("grade7/Lesson18.txt"));
        add("7th Grade: Spelling of Vocabulary Words", vocabularySpellingListFactory.createSpellingList("grade7/Lessons16Thru18Review.txt"));
        add("7th Grade: Spelling of Vocabulary Words", vocabularySpellingListFactory.createSpellingList("grade7/Lesson19.txt"));
        add("7th Grade: Spelling of Vocabulary Words", vocabularySpellingListFactory.createSpellingList("grade7/Lesson20.txt"));
        add("7th Grade: Spelling of Vocabulary Words", vocabularySpellingListFactory.createSpellingList("grade7/Lesson21.txt"));
        add("7th Grade: Spelling of Vocabulary Words", vocabularySpellingListFactory.createSpellingList("grade7/Lessons19Thru21Review.txt"));
    }

    public void initMathFacts()
    {
        removeAllMathFacts();

        add("Math Facts", new MultiplicationMathFacts("Multiplication (" + mathFactTimeLimit + " second time limit)", mathFactTimeLimit));
        add("Math Facts", new DivisionMathFacts("Division (" + mathFactTimeLimit + " second time limit)", mathFactTimeLimit));
        add("Math Facts", new AdditionMathFacts("Addition (" + mathFactTimeLimit + " second time limit)", mathFactTimeLimit));
        add("Math Facts", new SubtractionMathFacts("Subtraction (" + mathFactTimeLimit + " second time limit)", mathFactTimeLimit));
    }

    public void add(String schoolClassName, IVocabularyList vocabularyList) {

        // We need to make sure the vocabulary list is generic as only the generic class is
        // "shared," and therefore serializable.
        VocabularyList genericVocabularyList = new VocabularyList(vocabularyList);
        add(schoolClassName, (ITestable) genericVocabularyList);
    }

    public void add(String schoolClassName, ITestable testableList)
    {
        String name = testableList.getName();

        if (name.trim().equals(""))
        {
            name = testableList.getClass().getSimpleName();
            name = name.substring(STANDARD_CLASS_NAME_PREFIX.length(), name.length());
            name = name.replaceAll("_", "-");
        }

        testableList.setNane(name);

//        for (SpellingWord spellingWord : spellingList.getSpellingWords())
//        {
//            System.out.println();
//            System.out.println();
//            System.out.println(spellingWord.getCorrectSpelling() + "  " + spellingWord.getPhoneticSpelling());
//            System.out.println();
//            System.out.println(spellingWord.getIncorrectSpellings());
//        }

        Map<String, ITestable> testableListMapForThisClass = schoolClasses.get(schoolClassName);
        if (testableListMapForThisClass == null)
        {
            testableListMapForThisClass = new LinkedHashMap<>();
            schoolClasses.put(schoolClassName, testableListMapForThisClass);
        }

        testableListMapForThisClass.put(testableList.getName(), testableList);
    }

    protected void removeAllMathFacts()
    {
        Map<String, ITestable> testableListMapForThisClass = schoolClasses.get("Math Facts");
        if (testableListMapForThisClass == null)
        {
            return;
        }

        schoolClasses.remove("Math Facts");
    }

    public void clear()
    {
        schoolClasses.clear();
    }

    public Set<String> getTestableListNames(String schoolClassName)
    {
        return schoolClasses.get(schoolClassName).keySet();
    }

    public ITestable getTestableList(String schoolClassName, String listName)
    {
        return schoolClasses.get(schoolClassName).get(listName);
    }

    public Set<String> getSchoolClassNames()
    {
        return schoolClasses.keySet();
    }

    public static Integer getMathFactTimeLimit()
    {
        return mathFactTimeLimit;
    }

    public void setMathFactTimeLimit(Integer mathFactTimeLimit)
    {
        TestableListLibrarian.mathFactTimeLimit = mathFactTimeLimit;
        initMathFacts();
    }
}
