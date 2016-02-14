package com.superbrown.superSpell.gwtApp.server;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;
import com.superbrown.superSpell.gwtApp.client.services.ISuperSpellService;
import com.superbrown.superSpell.gwtApp.shared.ITestable;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;

public class SuperSpellServiceImpl extends RemoteServiceServlet implements ISuperSpellService
{
    private TestableListLibrarian testableListLibrarian;

    public SuperSpellServiceImpl()
    {
        testableListLibrarian = new TestableListLibrarian();
    }

    public ITestable getTestableList(String schoolClassName, String testableListName)
    {
        ITestable testableList = testableListLibrarian.getTestableList(schoolClassName, testableListName);
        return testableList;
    }

    public List<String> shuffleStrings(List<String> strings)
    {
        try
        {
            Collections.shuffle(strings);
        }
        catch (Throwable e)
        {
            e.printStackTrace(System.err);
            throw new Error(e);
        }

        return strings;
    }

    public List<String> getTestableListNames(String schoolClassName)
    {
        List<String> spellingListNames = new ArrayList<String>();

        try
        {
            Set<String> listNames = testableListLibrarian.
                    getTestableListNames(schoolClassName);
            spellingListNames.addAll(listNames);
            Collections.sort(spellingListNames);
        }
        catch (Throwable e)
        {
            e.printStackTrace(System.err);
            throw new Error(e);
        }

        
        return spellingListNames;
    }

    public List<String> getSchoolClassNames()
    {
        List<String> schoolClassNames = new ArrayList<String>();

        try
        {
            Set<String> listNames = testableListLibrarian.getSchoolClassNames();
            schoolClassNames.addAll(listNames);
            Collections.sort(schoolClassNames);
        }
        catch (Throwable e)
        {
            e.printStackTrace(System.err);
            throw new Error(e);
        }

        return schoolClassNames;
    }

    public void setMathFactTimeLimit(Integer duration)
    {
        testableListLibrarian.setMathFactTimeLimit(duration);
    }
}
