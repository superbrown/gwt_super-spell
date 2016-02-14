package com.superbrown.superSpell.gwtApp.shared.spelling;

/**
 */
public class SubstitutionPair
{
    private String replaceThis;
    private String withThat;

    public SubstitutionPair(String replaceThis, String withThat)
    {
        this.replaceThis = replaceThis;
        this.withThat = withThat;
    }

    public String getReplaceThis()
    {
        return replaceThis;
    }

    public String getWithThat()
    {
        return withThat;
    }
}
