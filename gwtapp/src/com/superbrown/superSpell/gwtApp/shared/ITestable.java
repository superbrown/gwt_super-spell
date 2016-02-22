package com.superbrown.superSpell.gwtApp.shared;

import com.google.gwt.user.client.rpc.IsSerializable;

/**
 */
public interface ITestable extends IsSerializable
{
    boolean isGotItOnTheFirstTry();

    void reset(boolean force);

    String getName();

    void setNane(String name);
}
