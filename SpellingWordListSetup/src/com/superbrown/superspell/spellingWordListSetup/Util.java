package com.superbrown.superspell.spellingWordListSetup;

import com.superbrown.superSpell.gwtApp.server.spelling.spellingLists.SpellingListFactory;
import com.superbrown.superSpell.gwtApp.shared.spelling.SpellingWord;

import java.io.*;
import java.net.*;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 */
public class Util
{
    public static List<String> getLinesFromFile(String path)
    {
        List<String> lines = new ArrayList<String>();
        Scanner scanner = null;

        FileInputStream fileInputStream = null;
        try
        {
            fileInputStream = new FileInputStream(path);

            scanner = new Scanner(fileInputStream);

            while (scanner.hasNextLine())
            {
                lines.add(scanner.nextLine());
            }
        }
        catch (FileNotFoundException e)
        {
            e.printStackTrace();
        }
        finally
        {
            scanner.close();
            try
            {
                fileInputStream.close();
            }
            catch (IOException e)
            {
                e.printStackTrace();
            }
        }
        return lines;
    }

    public static List<WordAndItsPhoneticSpelling> getPhoneticSpellings(String configFilePath)
    {
        List<String> lines = getLinesFromFile(configFilePath);

        lines.remove(0);

        List<WordAndItsPhoneticSpelling> phoneticSpellings =
                new ArrayList<WordAndItsPhoneticSpelling>();

        for (String line : lines)
        {
            System.out.println(line);
            String[] segments = line.split(SpellingListFactory.DELIMITTER);
            try
            {
                phoneticSpellings.add(
                        new WordAndItsPhoneticSpelling(
                                SpellingWord.extractWordFromSampleSentence(segments[0]),
                                segments[1]));
            }
            catch (ArrayIndexOutOfBoundsException e)
            {
                e.printStackTrace();
            }
        }

        return phoneticSpellings;
    }

    public static String extractPhoneticSpelling(String line)
    {
        String openingString = "[</span><span class=\"pron\">";
        String closingString = "]";
        String alternateClosingString = ",";
//        String optionalBoldTag = "<span class=\"boldface\">";

        int indexOfOpeningTag = line.indexOf(openingString);
        if (indexOfOpeningTag < 0)
        {
            return null;
        }

  //      int indexOfOptionalBoldTag = line.indexOf(optionalBoldTag, indexOfOpeningTag);
        int indexOfClosingTag = line.indexOf(closingString, indexOfOpeningTag);
        int indexOfAlternateClosingString = line.indexOf(alternateClosingString, indexOfOpeningTag);

        if (indexOfAlternateClosingString != -1 &&
            indexOfAlternateClosingString < indexOfClosingTag)
        {
            indexOfClosingTag = indexOfAlternateClosingString; 
        }

//        if (indexOfOptionalBoldTag > -1 && indexOfOptionalBoldTag < indexOfClosingTag)
//        {
//            indexOfClosingTag = line.indexOf(closingString, indexOfOptionalBoldTag);
//            indexOfClosingTag = line.indexOf(closingString, indexOfClosingTag + closingString.length());
//            phoneticSpelling = line.substring(indexOfOpeningTag + lengthOfOpeningTag, indexOfClosingTag);
//            phoneticSpelling = phoneticSpelling.replaceAll(optionalBoldTag, "");
//            phoneticSpelling = phoneticSpelling.replaceAll(closingString, "");
//        }
//        else
//        {
//            indexOfClosingTag = line.indexOf(closingString, indexOfOpeningTag);

        String phoneticSpelling = removeTagContent(line.substring(indexOfOpeningTag, indexOfClosingTag));

        if (containsIllegalCharacters(phoneticSpelling))
        {
            String remainderOfTheLine = line.substring(indexOfClosingTag + closingString.length());
            return extractPhoneticSpelling(remainderOfTheLine);
        }

        return phoneticSpelling;
    }

    public static String removeTagContent(String string)
    {
        int indexOfAClosingTag = string.indexOf(">");
        if (indexOfAClosingTag == -1)
        {
            return "";
        }

        int indexOfAnOpeningTag = string.indexOf("<", indexOfAClosingTag);

        if (indexOfAnOpeningTag == -1)
        {
            return string.substring(indexOfAClosingTag + 1);
        }

        return string.substring(indexOfAClosingTag + 1, indexOfAnOpeningTag) +
                removeTagContent(string.substring(indexOfAnOpeningTag));
    }

    public static boolean containsIllegalCharacters(String phoneticSpelling)
    {
        char[] characters = phoneticSpelling.toCharArray();
        for (int i = 0; i < characters.length; i++)
        {
            String character = "" + characters[i];

            Pattern isALetterPattern = Pattern.compile("[A-Za-z]");
            Matcher matcher = isALetterPattern.matcher(character);
            boolean isALetter = matcher.find();

            if (!isALetter && !character.equals("-"))
            {
                return true;
            }
        }

        return false;
    }

    public static List<String> getLinesFromURL(String urlAddress)
    {
        urlAddress = urlAddress.replaceAll(" ", "+");

        List<String> lines = new ArrayList<String>();

        HttpURLConnection httpURLConnection = null;

        try
        {
            URL url;
            String line;

            url = new URL(urlAddress);

//            Proxy proxy = new Proxy(
//                    Proxy.Type.HTTP,
//                    new InetSocketAddress("elavonproxy.nova.prv", 8080));

//            httpURLConnection = (HttpURLConnection)url.openConnection(proxy);
            httpURLConnection = (HttpURLConnection)url.openConnection();
            httpURLConnection.connect();
            BufferedReader bufferedReader =
                    new BufferedReader(new InputStreamReader(httpURLConnection.getInputStream()));

            while ((line = bufferedReader.readLine()) != null)
            {
                lines.add(line);
            }
        }
        catch (MalformedURLException mue)
        {

            System.out.println("Ouch - a MalformedURLException happened.");
            mue.printStackTrace();
            System.exit(1);
        }
        catch (IOException ioe)
        {

            System.out.println("Oops- an IOException happened.");
            ioe.printStackTrace();
            System.exit(1);
        }
        finally
        {
            httpURLConnection.disconnect();
        }
        return lines;
    }

    public static void writeNewValuesToFile(String configFilePath, String labelForSpellingWordList, List<String> lines)
    {
        FileOutputStream fos = null;
        OutputStreamWriter out = null;

        try
        {
            File file = new File(configFilePath);
            file.delete();

            fos = new FileOutputStream(configFilePath);

            out = new OutputStreamWriter(fos);

            writeLine(out, labelForSpellingWordList);

            for (String line : lines)
            {
                writeLine(out, line);
            }

            out.flush();
        }
        catch (FileNotFoundException e)
        {
            e.printStackTrace();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        finally
        {
            try
            {
                out.close();
                fos.close();
            }
            catch (IOException e)
            {
                e.printStackTrace();
            }
        }
    }

    public static void writeLine(OutputStreamWriter out, String string)
            throws IOException
    {
        out.write(string + "\n");
    }

    public static String getPhoneticSpellingFromDictionaryDotCom(String word)
            throws NoPhoneticSpellingFound
    {
        List<String> lines = null;
        lines = getLinesFromURL("http://dictionary.reference.com/browse/" + word);

        for (String line : lines)
        {
            String phoneticSpelling = extractPhoneticSpelling(line);

            if (phoneticSpelling != null)
            {
                return phoneticSpelling;
            }
        }

        throw new NoPhoneticSpellingFound();
    }

    public static Map<String, String> getPropertiesInAlphbeticalOrder(String configFilePath)
    {
        Properties properties = new Properties();
        Map<String, String> linkedHashMap = new LinkedHashMap<String, String>();

        FileInputStream inputStream = null;
        try
        {
            inputStream = new FileInputStream(configFilePath);
            properties.load(inputStream);

            List<String> keys = new ArrayList<String>();

            for (Object key : properties.keySet())
            {
                keys.add(key.toString());
            }

            Collections.sort(keys);

            for (String key : keys)
            {
                linkedHashMap.put(key, properties.get(key).toString());
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
        return linkedHashMap;
    }

    static void writeValuesBackToThePropertiesFile(
            String configFilePath, Map map)
    {
        FileOutputStream fos = null;
        OutputStreamWriter out = null;

        try
        {
            File file = new File(configFilePath);
            file.delete();

            fos = new FileOutputStream(configFilePath);

            out = new OutputStreamWriter(fos);

            for (Object key : map.keySet())
            {
                writeLine(out, key + "=" + map.get(key));
            }

            out.flush();
        }
        catch (FileNotFoundException e)
        {
            e.printStackTrace();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        finally
        {
            try
            {
                out.close();
                fos.close();
            }
            catch (IOException e)
            {
                e.printStackTrace();
            }
        }
    }

    public static void writeValuesBackToThePropertiesFile(
            String configFilePath,
            Map<String, String> syllablesInOurInventory,
            Map<String, WordAndItsPhoneticSpelling> syllablesWeAreMissing)
    {
        Map<String, String> allSyllablesToAdd =
                new LinkedHashMap<String, String>();

        for (String key : syllablesInOurInventory.keySet())
        {
            allSyllablesToAdd.put(key, syllablesInOurInventory.get(key));
        }

        for (String key : syllablesWeAreMissing.keySet())
        {
            WordAndItsPhoneticSpelling spelling = syllablesWeAreMissing.get(key);
            allSyllablesToAdd.put(
                    key,
                    "[" + spelling.getSpelling() + " " + spelling.getPhoneticSpelling() + "]");
        }

        writeValuesBackToThePropertiesFile(configFilePath, allSyllablesToAdd);
    }

    public static List<String> getListOfAllSpellingTestConfigurationFiles(String spellingListDirectory)
    {
        List<String> paths = new ArrayList<String>();

        File rootSpellingListDirectory = new File(spellingListDirectory);

        String[] childNamesOfRootSpellingListDirectory = rootSpellingListDirectory.list();

        for (String childNameOfRootSpellingListDirectory : childNamesOfRootSpellingListDirectory)
        {
            File childOfRootSpellingListDirectory =
                    new File(spellingListDirectory + "/" + childNameOfRootSpellingListDirectory);

            if (childOfRootSpellingListDirectory.isDirectory())
            {
                String[] fileNames = childOfRootSpellingListDirectory.list();

                for (String fileName : fileNames)
                {
                    if (fileName.endsWith(".txt"))
                    {
                        paths.add(
                                spellingListDirectory + "/" +
                                childNameOfRootSpellingListDirectory + "/" +
                                fileName);
                    }
                }
            }
        }
        return paths;
    }

    public static class NoPhoneticSpellingFound extends Throwable
    {
    }
}
