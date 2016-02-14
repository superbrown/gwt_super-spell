package com.superbrown.superSpell.gwtApp.client.common.cheat;

import java.util.HashMap;

/**
 */
public class CheatCodes
{
    public static HashMap<String, Cheat> codes = new HashMap<String, Cheat>();
    public final static String MATH_TIMER_IN_SECONDS_PREFIX = "mathtimer";

    static
    {
        codes.put("mycheatcode", Cheat.UNLOCK_MUSIC);
        codes.put("mmusic", Cheat.UNLOCK_MUSIC);

        codes.put("sspell", Cheat.SHOW_ALL_MISPELLINGS);

        codes.put("pink", Cheat.PINK_BACKGROUND);
        codes.put("blue", Cheat.BLUE_BACKGROUND);
        codes.put("purple", Cheat.PURPLE_BACKGROUND);
        codes.put("brown", Cheat.BROWN_BACKGROUND);
        codes.put("aqua", Cheat.AQUA_BACKGROUND);
        codes.put("green", Cheat.CHALK_BOARD_BACKGROUND);
        codes.put("orange", Cheat.ORANGE_BACKGROUND);
        codes.put("red", Cheat.RED_BACKGROUND);
        codes.put("black", Cheat.BLACK_BACKGROUND);

        codes.put("stooges", Cheat.TURN_ON_THREE_STOOGES_SOUND);
        codes.put("gomer", Cheat.TURN_ON_GOMER_PYLE_SOUND);
        codes.put("pyle", Cheat.TURN_ON_GOMER_PYLE_SOUND);
        codes.put("silence", Cheat.SILENCE_SOUND);

//        codes.put("readimmediately", Cheat.READ_IMMEDIATELY_MODE);

        codes.put("mastermind", Cheat.MASTERMIND);

        codes.put("petermath", Cheat.USE_PETER_MATH);

        codes.put("doodle", Cheat.CHALKBOARD_DOODLING);
        codes.put("cheese", Cheat.CHALKBOARD_DOODLING);
        codes.put("pizza", Cheat.CHALKBOARD_DOODLING);
    }

    static public Cheat getCheat(String key)
    {
        Cheat cheat;

        if (key.trim().length() == 0)
        {
            cheat = Cheat.NO_VALUE_ENTERED;
        }
        else if (key.startsWith(MATH_TIMER_IN_SECONDS_PREFIX))
        {
            cheat = Cheat.MATH_TIMER_IN_SECONDS;
        }
        else
        {
            cheat = codes.get(key);
        }

        if (cheat == null)
        {
            cheat = Cheat.UNRECOGNIZED;
        }

        return cheat;
    }
}
