package com.superbrown.superSpell.gwtApp.client.services;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.superbrown.superSpell.gwtApp.shared.ITestable;

import java.util.List;

public interface ISuperSpellServiceAsync
{
    void shuffleStrings(List<String> spellingWords, AsyncCallback<List<String>> async);

    void getTestableListNames(String schoolClassName, AsyncCallback<List<String>> async);

    void getTestableList(String schoolClassName, String testableListName, AsyncCallback<ITestable> async);

    void getSchoolClassNames(AsyncCallback<List<String>> asyncCallback);

    void setMathFactTimeLimit(Integer duration, AsyncCallback<Void> async);
}
