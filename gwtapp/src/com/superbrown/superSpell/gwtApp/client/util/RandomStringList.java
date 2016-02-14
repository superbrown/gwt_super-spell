//package com.superbrown.superSpell.gwtApp.client.util;
//
//
//import java.util.ArrayList;
//import java.util.List;
//import java.util.Random;
//
///**
// */
//public class RandomStringList
//{
//    private List<String> strings;
//    private List<String> previousStrings = new ArrayList<String>();
//    private Random random = new Random(System.currentTimeMillis());
//
//
//    public RandomStringList(List<String> strings)
//    {
//        this.strings = strings;
//    }
//
//    public RandomStringList(String[] strings)
//    {
//        this.strings = new ArrayList();
//        for (String string : strings)
//        {
//            this.strings.add(string);
//        }
//    }
//
//    public String getNextRandomString()
//    {
//        if (hasUsedAllTheStrings())
//        {
//            strings.addAll(previousStrings);
//            previousStrings.clear();
//        }
//
//        int randomIndex = random.nextInt(strings.size());
//        String newString = strings.get(randomIndex);
//
//        previousStrings.add(newString);
//        strings.remove(newString);
//
//        return newString;
//    }
//
//    public boolean hasUsedAllTheStrings()
//    {
//        return strings.size() == 0;
//    }
//
//    public static List<String> shuffle(List<String> strings)
//    {
//        RandomStringList shuffler = new RandomStringList(strings);
//        List<String> shuffledList = new ArrayList<String>();
//
//        do
//        {
//            shuffledList.add(shuffler.getNextRandomString());
//        }
//        while (!shuffler.hasUsedAllTheStrings());
//
//        return shuffledList;
//    }
//}
