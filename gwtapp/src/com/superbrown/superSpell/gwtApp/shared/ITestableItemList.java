package com.superbrown.superSpell.gwtApp.shared;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: Steve Austin
 * Date: 11/6/11
 * Time: 4:13 PM
 * To change this template use File | Settings | File Templates.
 */
public interface ITestableItemList extends ITestable
{

    List<ITestable> getTestables();
}
