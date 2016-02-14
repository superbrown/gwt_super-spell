package com.superbrown.superSpell.gwtApp.shared.vocabulary;

import com.google.gwt.user.client.rpc.IsSerializable;
import com.superbrown.superSpell.gwtApp.shared.ITestableItemList;

import java.util.Set;

/**
 * Created by IntelliJ IDEA.
 * User: Steve Austin
 * Date: 11/5/11
 * Time: 11:11 AM
 * To change this template use File | Settings | File Templates.
 */
public interface IVocabularyList extends IsSerializable, ITestableItemList
{
    Set<String> getAllWords();

}
