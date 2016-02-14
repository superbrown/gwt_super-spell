package com.superbrown.superspell.spellingWordListSetup;

public class WordAndItsPhoneticSpelling
{
    private String spelling;
    private String phoneticSpelling;

    public WordAndItsPhoneticSpelling(String spelling, String phoneticSpelling)
    {
        this.spelling = spelling;
        this.phoneticSpelling = phoneticSpelling;
    }

    public String getSpelling()
    {
        return spelling;
    }

    public String getPhoneticSpelling()
    {
        return phoneticSpelling;
    }
}
