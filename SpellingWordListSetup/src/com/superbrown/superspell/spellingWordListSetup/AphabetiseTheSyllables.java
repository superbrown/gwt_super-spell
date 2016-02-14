package com.superbrown.superspell.spellingWordListSetup;

import java.util.*;

/**
 */
public class AphabetiseTheSyllables
{
    public static void main(String[] args)
    {
        String spellingListDirectory = "gwtapp/src/com/superbrown/superSpell/gwtApp/server/spelling/spellingLists/";

        alphabetizeSyllableInventory(spellingListDirectory + "SyllableSpellings.properties");
    }

    public static void alphabetizeSyllableInventory(String configFilePath)
    {
        Map<String, String> syllableInventoryInOrder = Util.getPropertiesInAlphbeticalOrder(configFilePath);
        Util.writeValuesBackToThePropertiesFile(configFilePath, syllableInventoryInOrder);
    }

}
