package com.superbrown.superSpell.gwtApp.client.services;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;
import com.superbrown.superSpell.gwtApp.shared.ITestable;

import java.util.List;

@RemoteServiceRelativePath("SuperSpellService")
public interface ISuperSpellService extends RemoteService
{
    ITestable getTestableList(String schoolClassName, String spellingListName);

    List<String> shuffleStrings(List<String> strings);

    List<String> getTestableListNames(String schoolClassName);

    List<String> getSchoolClassNames();

    void setMathFactTimeLimit(Integer duration);


    /**
     * Utility/Convenience class.
     * Use ISuperSpellService.App.getInstance () to access static instance of SuperSpellServiceAsync
     */
    public static class App
    {
        private static ISuperSpellServiceAsync ourInstance = GWT.create(ISuperSpellService.class);

        public static synchronized ISuperSpellServiceAsync getInstance()
        {
            return ourInstance;
        }
    }
}
