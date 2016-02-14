//package com.superbrown.superspell.spellingWordListSetup;
//
//import com.superbrown.superSpell.gwtApp.server.spelling.spellingLists.WordMisspeller2;
//import com.superbrown.superSpell.gwtApp.shared.spelling.SpellingList;
//import com.superbrown.superSpell.gwtApp.shared.spelling.SpellingWord;
//import com.superbrown.superSpell.gwtApp.shared.spelling.Word;
//
//import java.io.File;
//import java.io.FileInputStream;
//import java.io.IOException;
//import java.net.URL;
//import java.util.ArrayList;
//import java.util.List;
//import java.util.Scanner;
//import java.util.Set;
//
///**
// * Created by IntelliJ IDEA.
// * User: mxbro16
// * Date: Oct 26, 2010
// * Time: 3:31:53 PM
// * To change this template use File | Settings | File Templates.
// */
//public class Test
//{
//    public static final String DELIMITTER = "~";
//
//    public static void main(String[] args)
//    {
//        Test test = new Test();
//        test.createSpellingList("./Test.class");
//    }
//
//
//
//    public SpellingList createSpellingList(String configFilePath)
//    {
////        String spellingListDirectory = "gwtApp/src/com/superbrown/superSpell/gwtApp/server/spelling/spellingLists/";
////        String spellingListDirectory = "com/superbrown/superSpell/gwtApp/server/spelling/spellingLists";
////        String spellingListDirectory = "././././././" +
////                "gwtApp/src/com/superbrown/superSpell/gwtApp/shared/spelling/spellingLists";
////        String spellingListDirectory = "./";
//        String spellingListDirectory = "";
//
//        List<String> lines = getLinesFromFile(spellingListDirectory + configFilePath);
//
//        String labelForSpellingWordList = lines.remove(0);
//        SpellingList spellingList = new SpellingList(labelForSpellingWordList);
//
//
//        for (String line : lines)
//        {
//            String[] segments = line.split(DELIMITTER);
//            String sampleSentence = segments[0];
//            String phoneticSpelling = segments[1];
//
//            String word = SpellingWord.extractWordFromSampleSentence(sampleSentence);
//
//            Set<String> incorrectSpellings =
//                    wordMisspeller.generateMisspellings(new Word(phoneticSpelling, word));
//
//            SpellingWord spellingWord = new SpellingWord(
//                    line,
//                    phoneticSpelling,
//                    incorrectSpellings);
//
//            spellingList.addSpellingWordToInventory(spellingWord);
//        }
//
//        return spellingList;
//    }
//
//
//    public List<String> getLinesFromFile(String path)
//    {
//        List<String> lines = new ArrayList<String>();
//        Scanner scanner = null;
//        FileInputStream fileInputStream = null;
//
//        try
//        {
//            System.out.println("path: " + path);
//
////            URL url = this.getClass().getResource("Test.class");
////
////            System.out.println("url: " + url);
////            System.out.println("url.toString(): " + url.toString());
//
//            File file = new File(path);
//            System.out.println("file.exists(): " + file.exists());
//
//            fileInputStream = new FileInputStream(path);
//            scanner = new Scanner(fileInputStream);
//
//            while (scanner.hasNextLine())
//            {
//                lines.add(scanner.nextLine());
//            }
//        }
//        catch (Throwable e)
//        {
//            e.printStackTrace();
//        }
//        finally
//        {
//            scanner.close();
//            try
//            {
//                fileInputStream.close();
//            }
//            catch (IOException e)
//            {
//                e.printStackTrace();
//            }
//        }
//        return lines;
//    }
//}
