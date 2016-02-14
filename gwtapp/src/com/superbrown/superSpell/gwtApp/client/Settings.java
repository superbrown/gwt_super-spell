package com.superbrown.superSpell.gwtApp.client;

import com.google.gwt.user.client.Cookies;
import com.superbrown.superSpell.gwtApp.client.util.RandomList;

import java.util.Date;

/**
 */
public class Settings
{
    public static final int MAX_NUMBER_OF_WORDS_IN_A_SET = 4;
    public static final int MIN_NUMBER_OF_WORDS_IN_A_SET = 2;

    public static final int HIGHEST_LEVEL_OF_MULTIPLICATION_MATH_FACTS = 9;
    public static final int HIGHEST_LEVEL_OF_DIVISION_MATH_FACTS = 9;
    public static final int HIGHEST_LEVEL_OF_ADDITION_MATH_FACTS = 9;
    public static final int HIGHEST_LEVEL_OF_SUBTRACTION_MATH_FACTS = 9;

    public static final int NUMBER_OF_ACTIONS_BETWEEN_DOODLE_MESSAGE_RESETS = 4;
    public static final int MAX_NUMBER_OF_PEOPLE_STAYING_AFTER_SCHOOL = 6;

    // All of these settings are the defaults. They can be overridden by cookie values
    private static BoardColor boardColor = BoardColor.GREEN;
    private static Integer mathQuestionTimeLimitInSeconds = 10;
    private static SoundPaletteChoice soundEffectsSet = SoundPaletteChoice.NONE;
    private static Boolean inShowAllMisspellingsMode = false;
    private static Boolean inReadImmediatelyMode = false;
    private static Boolean usingPeterMath = false;
    private static Boolean stayAfterSchoolListEnabled = true;
    private static Boolean chalkboardDoodlingEnabled = true;
    public static int numberOfPeopleStayingAfterSchool = 0;

    public static RandomList DOODLE_MESSAGES = new RandomList(new String[]
        {
            "I like Cheese",
            "Thunderbirds Are Go",
            "Agent 86",
            "Battlefront",
            "Star Trek",
            "The Beatles",
            "Aerosmith",
            "RUSH",
            "Iron Maiden",
            "UFO",
            "Styx",
            "REO Speedwagon",
            "Foreigner",
            "Scorpions",
            "Godzilla",
            "The 3 Stooges",
            "Space Ship One",
            "I Survived Moab",
            "The Dream Police",
            "Go Steelers",
            "Turtles Rule",
            "I Love Penguins",
            "Number 9",
            "Ob-La-Di Ob-La-Da",
            "Juice Box Hero",
            "Stingray",
            "Planet of the Apes",
            "Batman and Robin",
            "Night Ranger",
            "Sigmund and the Sea Monsters",
            "I love Carlsbad Caverns",
            "Take Off",
            "Head Games",
            "Katamari Damacy",
            "Guitar Hero",
            "Get Out of My Yard",
            "Lisa Dori",
            "Dark Berry",
            "Don't Tread On Me",
            "I Am Speed",
            "Cave of the Winds",
            "KAOS",
            "Detroit Rock City",
        });

    public static RandomList DOODLE_FONT_STYLES = new RandomList(new String[]
        {
            "OPN_StunFillaWenkay_font",
//            "LEFTHAND_font",
            "CRAZK_font",
//            "Dawgbox_font",
//            "hypertension_font",
            "InsertYourNameHere_font", // like
//            "LOOSH_font", // isn't working
//            "mathbook_font",
            "ScratchedCarPaint_font" // like
        });

    public static RandomList STAY_AFTER_SCHOOL_NAMES = new RandomList(new String[]
        {
            "Logan",
            "Peter",
            "Hannah",
            "C.J.",
            "Po",
            "Turtley",
            "Mom",
            "Dad",
            "Laurie",
            "Alan",
            "Sage",
            "Craig",
            "Samantha",
            "Uncle Tim",
            "Aunt Jenni",
            "Aunt Joan",
            "Uncle Ron",
            "Grandma Jo",
            "Grandma Brown",
            "Grandpa Brown",
            "Moopy",
            "Ontario",
            "Luke Skywalker",
            "Darth Vadar",
            "Anakin",
            "Count Dooku",
            "Darth Maul",
            "Iron Man",
            "Paul McCarntey",
            "Elton John",
            "Steve Via",
            "Paul Gilbert",
            "Gumby",
            "Sigmund (the sea monster)",
            "Gomer Pyle",
            "Sargent Carter",
            "Tom Sawyer",
            "Huck Finn",
            "HR Puff N Stuff",
            "Buzz Lightyear",
            "Woody",
            "Rosie",
            "Steve Austin",
            "Frankenstein",
            "King Kong",
            "Arnold the Pig",
            "Rocky Raccoon",
            "Moe",
            "Larry",
            "Curley",
            "Tonto",
            "The Lone Ranger",
            "Major Nelson",
            "Major Healy",
            "Jeanie",
            "Gilligan",
            "Mr. Howell",
            "The Skipper",
            "MaryAnn",
            "Ginger",
            "The Professor",
            "Mrs. Howell",
            "The Chief",
            "Laraby",
            "Zigfreid",
            "Hymie",
            "Maxwell Smart",
            "Peter Brady",
            "Greg Brady",
            "Bobby Brady",
            "Cindy Brady",
            "Jan Brady",
            "Marcia Brady",
            "Chaka",
            "Miss Helen",
        });

    public static String[] MIDI_FILE_NAMES = new String[]
        {
                // AC/DC
//                "./midiFiles/songs/TNT.mid",

                // Aeromith
//                "./midiFiles/songs/Kings_And_Queens.mid",
//                "./midiFiles/songs/Walk_This_Way.mid",

                // Alice Cooper
//                "./midiFiles/songs/Schools_Out.mid",

                // Beatles
//                "./midiFiles/songs/beatles-little_help_from_you_friends.mid",
//                "./midiFiles/songs/BackInTheUSSR.mid",
//                "./midiFiles/songs/Birthday.mid",
//                "./midiFiles/songs/Blackbird.mid",
//                "./midiFiles/songs/BungalowBill.mid",
//                "./midiFiles/songs/EleanorRigby.mid",
//                "./midiFiles/songs/hellogoodbye.mid",
//                "./midiFiles/songs/HelterSkelter.mid",
//                "./midiFiles/songs/IAmTheWalrus.mid",
//                "./midiFiles/songs/LucyInTheSkyWithDiamonds.mid",
//                "./midiFiles/songs/Live_and_Let_Die.mid",
//                "./midiFiles/songs/MagicalMysteryTour.mid",
//                "./midiFiles/songs/ObLaDiObLaDa.mid",
//                "./midiFiles/songs/SgtPepper.mid",
//                "./midiFiles/songs/WhenIm64.mid",
//                "./midiFiles/songs/YellowSubmarine.mid",

                // Beatch Boys
//                "./midiFiles/songs/Fun_Fun_Fun.mid",
//                "./midiFiles/songs/I_Get_Around.mid",
//                "./midiFiles/songs/Surfin_USA.mid",

                // Blue Oyseter Cult
//                "./midiFiles/songs/Dont_Fear_The_Reaper.mid",
//                "./midiFiles/songs/Godzilla.mid",

                // Cheap Trick
//                "./midiFiles/songs/Dream_Police.mid",
//                "./midiFiles/songs/I_Want_You_To_Want_Me.mid",

                // Deep Purple
//                "./midiFiles/songs/deep_purple_smoke_on_the_water.mid",
//                "./midiFiles/songs/Highway_Star.mid",

                // eagles
//                "./midiFiles/songs/eagles_hotel_california.mid",

                // Elton John
//                "./midiFiles/songs/elton_john-crocodile_rock.mid",

                // Foreigner
                "./midiFiles/songs/foreigner_head_games.mid",
//                "./midiFiles/songs/foreigner_i_want_to_know_what_love_is.mid",
                "./midiFiles/songs/foreigner_urgent.mid",

                // Iron Maiden
                "./midiFiles/songs/The_Trooper.mid",
                "./midiFiles/songs/Run_To_The_Hills.mid",
                "./midiFiles/songs/Flight_of_Icarus.mid",
                "./midiFiles/songs/The_Number_Of_The_Beast.mid",
                "./midiFiles/songs/Die_With_Your_Boots_On.mid",

                // Kansas
                "./midiFiles/songs/Carry_On_My_Wayward_Son.mid",
                "./midiFiles/songs/Dust_in_the_Wind.mid",

                // KISS
//                "./midiFiles/songs/Detroit_Rock_City.mid",
//                "./midiFiles/songs/Rock_n_Roll_All_Night.mid",

                // Led Zeppelin
//                "./midiFiles/songs/BlackDog.mid",
//                "./midiFiles/songs/ImmigrantSong.mid",
//                "./midiFiles/songs/RockAndRoll.mid",
//                "./midiFiles/songs/stairwaytoheaven.mid",
//                "./midiFiles/songs/WholeLottaLove.mid",

                // Night Ranger
//                "./midiFiles/songs/Dont_Tell_Me_You_Love_Me.mid",
//                "./midiFiles/songs/Sister_Christian.mid",

                // Queen
//                "./midiFiles/songs/Bicycle_Race.mid",
//                "./midiFiles/songs/BohemianRhapsody.MID",
//                "./midiFiles/songs/We_Are_the_Champions.mid",
//                "./midiFiles/songs/wewillrockyou.mid",

                // Pat Benatar
                "./midiFiles/songs/pat_benatar-we_belong.mid",
                "./midiFiles/songs/pat_benatar-hit_me_with_your_best_shot.mid",

                // Rush
//                "./midiFiles/songs/YYZ.mid",
//                "./midiFiles/songs/The_Spirit_of_Radio.mid",
//                "./midiFiles/songs/Tom_Sawyer.mid",

                // Scorpions
                "./midiFiles/songs/scorpions-rock_you_like_a_hurricane.mid",
                "./midiFiles/songs/scorpions-no_one_like_you.mid",
                "./midiFiles/songs/scorpions-big_city_nights.mid",
                "./midiFiles/songs/scorpions-send_me_an_angel.mid",
                "./midiFiles/songs/scorpions-holiday.mid",

                // Styx
                "./midiFiles/songs/styx-blue_collar_man.mid",
                "./midiFiles/songs/Come_Sail_Away.mid",
                "./midiFiles/songs/Fooling_Yourself.mid",
                "./midiFiles/songs/The_Grand_Illusion.mid",
                "./midiFiles/songs/Too_Much_Time_on_my_Hands.mid",

                // Sweet
//                "./midiFiles/songs/SWEET_BLITZ.MID",

                // UFO
                "./midiFiles/songs/ufo-only_you_can_rock_me.mid",
                "./midiFiles/songs/ufo-doctor_doctor.mid",

                // Van Halen
//                "./midiFiles/songs/pretty_woman.mid",
//                "./midiFiles/songs/and_the_cradle_will_rock.mid",
//                "./midiFiles/songs/Aint_Talkin_Bout_Love.mid",
//                "./midiFiles/songs/Dance_the_Night_Away.mid",
//                "./midiFiles/songs/Jump.mid",
//                "./midiFiles/songs/Panama.mid",

                // The Who
//                "./midiFiles/songs/Who_Are_You.mid",
//                "./midiFiles/songs/Wont_Get_Fooled_Again.mid",

                // Yes
//                "./midiFiles/songs/Roundabout.mid",


                // Miscellaneous
//                "./midiFiles/songs/Low_Rider.mid",
//                "./midiFiles/songs/stayinalive.mid",
//                "./midiFiles/songs/StayingAlive.MID",
//                "./midiFiles/songs/WAR_RIDER.MID",
//                "./midiFiles/songs/zipadee.mid",



//                "./midiFiles/tvShows/GhostBusters.mid",
//                "./midiFiles/tvShows/GiligansIsland.mid",
//                "./midiFiles/tvShows/GomerPyleUSMC.mid",
//                "./midiFiles/tvShows/GreenAcres.mid",
//                "./midiFiles/tvShows/IDreamOfJeanie.mid",
//                "./midiFiles/tvShows/LooneyTunes.mid",
//                "./midiFiles/tvShows/Peanuts.mid",
//                "./midiFiles/tvShows/PinkPanther.mid",
//                "./midiFiles/tvShows/StarTrek.mid",
//                "./midiFiles/tvShows/StarWars.mid",
//                "./midiFiles/tvShows/TheBradyBunch.mid",
//                "./midiFiles/tvShows/AddamsFamily.mid",
//                "./midiFiles/tvShows/GetSmart.mid",
//                "./midiFiles/tvShows/HongKongPhooey_V0-5.mid",
//                "./midiFiles/tvShows/Joe90.mid",
//                "./midiFiles/tvShows/LoneRanger.mid",
//                "./midiFiles/tvShows/Marina.mid",
////                "./midiFiles/tvShows/Munsters_3.mid",
//                "./midiFiles/tvShows/STINGRAY_IMF_v2.1.mid",
//                "./midiFiles/tvShows/ThreeStooges_2.mid",
//                "./midiFiles/tvShows/Thunderbirds.mid",
//


                // Note made 2016-02-11:
                //
                // The files below were originally commented out (see above). Since I don't have
                // source code history, I don't know if that was always the case, or if I commented
                // them out for a specific reason at some point.  Perhaps they were songs I had
                // found on the interenet that the boys were not yet familiat with.

                // AC/DC
                "./midiFiles/songs/TNT.mid",

                // Aeromith
                "./midiFiles/songs/Kings_And_Queens.mid",
                "./midiFiles/songs/Walk_This_Way.mid",

                // Alice Cooper
                "./midiFiles/songs/Schools_Out.mid",

                // Beatles
                "./midiFiles/songs/beatles-little_help_from_you_friends.mid",
                "./midiFiles/songs/BackInTheUSSR.mid",
                "./midiFiles/songs/Birthday.mid",
                "./midiFiles/songs/Blackbird.mid",
                "./midiFiles/songs/BungalowBill.mid",
                "./midiFiles/songs/EleanorRigby.mid",
                "./midiFiles/songs/hellogoodbye.mid",
                "./midiFiles/songs/HelterSkelter.mid",
                "./midiFiles/songs/IAmTheWalrus.mid",
                "./midiFiles/songs/LucyInTheSkyWithDiamonds.mid",
                "./midiFiles/songs/Live_and_Let_Die.mid",
                "./midiFiles/songs/MagicalMysteryTour.mid",
                "./midiFiles/songs/ObLaDiObLaDa.mid",
                "./midiFiles/songs/SgtPepper.mid",
                "./midiFiles/songs/WhenIm64.mid",
                "./midiFiles/songs/YellowSubmarine.mid",

                // Beatch Boys
                "./midiFiles/songs/Fun_Fun_Fun.mid",
                "./midiFiles/songs/I_Get_Around.mid",
                "./midiFiles/songs/Surfin_USA.mid",

                // Blue Oyseter Cult
                "./midiFiles/songs/Dont_Fear_The_Reaper.mid",
                "./midiFiles/songs/Godzilla.mid",

                // Cheap Trick
                "./midiFiles/songs/Dream_Police.mid",
                "./midiFiles/songs/I_Want_You_To_Want_Me.mid",

                // Deep Purple
                "./midiFiles/songs/deep_purple_smoke_on_the_water.mid",
                "./midiFiles/songs/Highway_Star.mid",

                // eagles
                "./midiFiles/songs/eagles_hotel_california.mid",

                // Elton John
                "./midiFiles/songs/elton_john-crocodile_rock.mid",

                // KISS
                "./midiFiles/songs/Detroit_Rock_City.mid",
                "./midiFiles/songs/Rock_n_Roll_All_Night.mid",

                // Led Zeppelin
                "./midiFiles/songs/BlackDog.mid",
                "./midiFiles/songs/ImmigrantSong.mid",
                "./midiFiles/songs/RockAndRoll.mid",
                "./midiFiles/songs/stairwaytoheaven.mid",
                "./midiFiles/songs/WholeLottaLove.mid",

                // Night Ranger
                "./midiFiles/songs/Dont_Tell_Me_You_Love_Me.mid",
                "./midiFiles/songs/Sister_Christian.mid",

                // Queen
                "./midiFiles/songs/Bicycle_Race.mid",
                "./midiFiles/songs/BohemianRhapsody.MID",
                "./midiFiles/songs/We_Are_the_Champions.mid",
                "./midiFiles/songs/wewillrockyou.mid",

                // Rush
                "./midiFiles/songs/YYZ.mid",
                "./midiFiles/songs/The_Spirit_of_Radio.mid",
                "./midiFiles/songs/Tom_Sawyer.mid",

                // Sweet
                "./midiFiles/songs/SWEET_BLITZ.MID",

                // Van Halen
                "./midiFiles/songs/pretty_woman.mid",
                "./midiFiles/songs/and_the_cradle_will_rock.mid",
                "./midiFiles/songs/Aint_Talkin_Bout_Love.mid",
                "./midiFiles/songs/Dance_the_Night_Away.mid",
                "./midiFiles/songs/Jump.mid",
                "./midiFiles/songs/Panama.mid",

                // The Who
                "./midiFiles/songs/Who_Are_You.mid",
                "./midiFiles/songs/Wont_Get_Fooled_Again.mid",

                // Yes
                "./midiFiles/songs/Roundabout.mid",

                // Miscellaneous
                "./midiFiles/songs/Low_Rider.mid",
                "./midiFiles/songs/stayinalive.mid",
                "./midiFiles/songs/StayingAlive.MID",
                "./midiFiles/songs/WAR_RIDER.MID",
                "./midiFiles/songs/zipadee.mid",
                "./midiFiles/tvShows/GhostBusters.mid",
                "./midiFiles/tvShows/GiligansIsland.mid",
                "./midiFiles/tvShows/GomerPyleUSMC.mid",
                "./midiFiles/tvShows/GreenAcres.mid",
                "./midiFiles/tvShows/IDreamOfJeanie.mid",
                "./midiFiles/tvShows/LooneyTunes.mid",
                "./midiFiles/tvShows/Peanuts.mid",
                "./midiFiles/tvShows/PinkPanther.mid",
                "./midiFiles/tvShows/StarTrek.mid",
                "./midiFiles/tvShows/StarWars.mid",
                "./midiFiles/tvShows/TheBradyBunch.mid",
                "./midiFiles/tvShows/AddamsFamily.mid",
                "./midiFiles/tvShows/GetSmart.mid",
                "./midiFiles/tvShows/HongKongPhooey_V0-5.mid",
                "./midiFiles/tvShows/Joe90.mid",
                "./midiFiles/tvShows/LoneRanger.mid",
                "./midiFiles/tvShows/Marina.mid",
                "./midiFiles/tvShows/Munsters_3.mid",
                "./midiFiles/tvShows/STINGRAY_IMF_v2.1.mid",
                "./midiFiles/tvShows/ThreeStooges_2.mid",
                "./midiFiles/tvShows/Thunderbirds.mid",
        };


    private static final String IN_READ_IMMEDIATELY_MODE = "inReadImmediatelyMode";
    private static final String BOARD_COLOR = "boardColor";
    private static final String SOUND_EFFECTS_SET = "soundEffectsSet";
    private static final String USING_PETER_MATH = "usingPeterMath";
    private static final String STAY_AFTER_SCHOOL_LIST_ENABLED = "stayAfterSchoolListEnabled";
//    private static final String IN_SHOW_ALL_MISSPELLINGS_MODE = "inShowAllMisspellingsMode";
    private static final String CHALKBOARD_DOODLING_ENABLED = "chalkboardDoodlingEnabled";
    private static final String MATH_QUESTION_TIMER_DURATION_IN_SECONDS = "mathQuestionTimerInSeconds";

    static
    {
        // if available, get values from the cookie

        Settings.boardColor = getValueFromCookieIfAvailable(Settings.boardColor, BOARD_COLOR);
        Settings.soundEffectsSet = getValueFromCookieIfAvailable(Settings.soundEffectsSet, SOUND_EFFECTS_SET);
//        Settings.inShowAllMisspellingsMode = getValueFromCookieIfAvailable(Settings.inShowAllMisspellingsMode, IN_SHOW_ALL_MISSPELLINGS_MODE);
        Settings.inReadImmediatelyMode = getValueFromCookieIfAvailable(Settings.inReadImmediatelyMode, IN_READ_IMMEDIATELY_MODE);
        Settings.usingPeterMath = getValueFromCookieIfAvailable(Settings.usingPeterMath, USING_PETER_MATH);
        Settings.stayAfterSchoolListEnabled = getValueFromCookieIfAvailable(Settings.stayAfterSchoolListEnabled, STAY_AFTER_SCHOOL_LIST_ENABLED);
        Settings.chalkboardDoodlingEnabled = getValueFromCookieIfAvailable(Settings.chalkboardDoodlingEnabled, CHALKBOARD_DOODLING_ENABLED);
        Settings.mathQuestionTimeLimitInSeconds = getValueFromCookieIfAvailable(Settings.mathQuestionTimeLimitInSeconds, MATH_QUESTION_TIMER_DURATION_IN_SECONDS);
    }


    static final Date ONE_HUNDRED_YEARS_FROM_NOW =
            new Date(new Date().getTime() + (1000 * 60 * 60 * 24 * 365 * 100));

    public static BoardColor getBoardColor()
    {
        return boardColor;
    }

    public static void setBoardColor(BoardColor boardColor)
    {
        Settings.boardColor = boardColor;
        setValueInCookie(BOARD_COLOR, boardColor.toString());
    }

    public static void setMathQuestionTimeLimitInSeconds(int seconds)
    {
        Settings.mathQuestionTimeLimitInSeconds = seconds;
        setValueInCookie(MATH_QUESTION_TIMER_DURATION_IN_SECONDS, "" + seconds);
    }

    public static int getMathQuestionTimeLimitInSeconds()
    {
        return mathQuestionTimeLimitInSeconds;
    }

    private static boolean isSet(String boardColorStoredInCookie)
    {
        return boardColorStoredInCookie != null && boardColorStoredInCookie.trim().length() != 0;
    }

    public static SoundPaletteChoice getSoundEffectsSet()
    {
        return soundEffectsSet;
    }

    public static void setSoundEffectsSet(SoundPaletteChoice soundEffectsSet)
    {
        Settings.soundEffectsSet = soundEffectsSet;
        Cookies.setCookie(SOUND_EFFECTS_SET, soundEffectsSet.toString());
    }

    public static Boolean getInShowAllMisspellingsMode()
    {
        return inShowAllMisspellingsMode;
    }

    public static void setInShowAllMisspellingsMode(Boolean inShowAllMisspellingsMode)
    {
        Settings.inShowAllMisspellingsMode = inShowAllMisspellingsMode;
//        setValueInCookie(IN_SHOW_ALL_MISSPELLINGS_MODE, inShowAllMisspellingsMode.toString());
    }

    public static Boolean getInReadImmediatelyMode()
    {
        return inReadImmediatelyMode;
    }

    public static void setInReadImmediatelyMode(Boolean inReadImmediatelyMode)
    {
        Settings.inReadImmediatelyMode = inReadImmediatelyMode;
        setValueInCookie(IN_READ_IMMEDIATELY_MODE, inReadImmediatelyMode.toString());
    }

    public static Boolean getUsingPeterMath()
    {
        return usingPeterMath;
    }

    public static void setUsingPeterMath(Boolean usingPeterMath)
    {
        Settings.usingPeterMath = usingPeterMath;
    }

    public static Boolean getStayAfterSchoolListEnabled()
    {
        return stayAfterSchoolListEnabled;
    }

    public static void setStayAfterSchoolListEnabled(Boolean stayAfterSchoolListEnabled)
    {
        Settings.stayAfterSchoolListEnabled = stayAfterSchoolListEnabled;
        setValueInCookie(STAY_AFTER_SCHOOL_LIST_ENABLED, stayAfterSchoolListEnabled.toString());
    }

    public static Boolean getChalkboardDoodlingEnabled()
    {
        return chalkboardDoodlingEnabled;
    }

    public static void setChalkboardDoodlingEnabled(Boolean chalkboardDoodlingEnabled)
    {
        Settings.chalkboardDoodlingEnabled = chalkboardDoodlingEnabled;
        setValueInCookie(CHALKBOARD_DOODLING_ENABLED, chalkboardDoodlingEnabled.toString());
    }

    private static BoardColor getValueFromCookieIfAvailable(BoardColor currentValue, String attributeName)
    {
        String valueInCookie = Cookies.getCookie(attributeName);
        BoardColor newValue = currentValue;
        if (isSet(valueInCookie))
        {
            newValue = BoardColor.valueOf(valueInCookie);
            // store it anew so the expiration date is pushed out
            setValueInCookie(attributeName, valueInCookie);
        }
        return newValue;
    }

    private static int getValueFromCookieIfAvailable(int currentValue, String attributeName)
    {
        String valueInCookie = Cookies.getCookie(attributeName);
        int newValue = currentValue;
        if (isSet(valueInCookie))
        {
            newValue = Integer.valueOf(valueInCookie);
            // store it anew so the expiration date is pushed out
            setValueInCookie(attributeName, valueInCookie);
        }
        return newValue;
    }

    private static String getValueFromCookieIfAvailable(String currentValue, String attributeName)
    {
        String valueInCookie = Cookies.getCookie(attributeName);
        String newValue = currentValue;
        if (isSet(valueInCookie))
        {
            newValue = String.valueOf(valueInCookie);
            // store it anew so the expiration date is pushed out
            setValueInCookie(attributeName, valueInCookie);
        }
        return newValue;
    }

    private static Boolean getValueFromCookieIfAvailable(Boolean currentValue, String attributeName)
    {
        String valueInCookie = Cookies.getCookie(attributeName);
        Boolean newValue = currentValue;
        if (isSet(valueInCookie))
        {
            newValue = Boolean.valueOf(valueInCookie);
            // store it anew so the expiration date is pushed out
            setValueInCookie(attributeName, valueInCookie);
        }
        return newValue;
    }

    private static SoundPaletteChoice getValueFromCookieIfAvailable(SoundPaletteChoice currentValue, String attributeName)
    {
        String valueInCookie = Cookies.getCookie(attributeName);
        SoundPaletteChoice newValue = currentValue;
        if (isSet(valueInCookie))
        {
            newValue = SoundPaletteChoice.valueOf(valueInCookie);
            // store it anew so the expiration date is pushed out
            setValueInCookie(attributeName, valueInCookie);
        }
        return newValue;
    }

    private static void setValueInCookie(String name, String value)
    {
        Cookies.setCookie(name, value, ONE_HUNDRED_YEARS_FROM_NOW);
    }
}
