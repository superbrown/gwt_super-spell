package com.superbrown.superSpell.gwtApp.client.util;

import com.google.gwt.event.dom.client.KeyCodes;
import com.google.gwt.event.dom.client.KeyPressEvent;
import com.google.gwt.user.client.rpc.IsSerializable;
import com.google.gwt.user.client.ui.Panel;
import com.google.gwt.user.client.ui.Widget;
import com.superbrown.superSpell.gwtApp.client.common.audio.SongPlayerPanel;
import com.superbrown.superSpell.gwtApp.client.common.audio.SoundWidget;

import java.util.Iterator;

/**
 */
public class UiUtilities implements IsSerializable
{
    public static boolean isTheEnterKey(KeyPressEvent keyPressEvent)
    {
        int keyCode = keyPressEvent.getNativeEvent().getKeyCode();
        return (KeyCodes.KEY_ENTER == keyCode);
    }

    public static boolean removeAnySoundPlayersIfPresent(Panel panel, boolean recursive)
    {
        boolean returnValue = false;

        for (Iterator iterator = panel.iterator(); iterator.hasNext();)
        {
            Object widget = iterator.next();

            if (widget instanceof SongPlayerPanel)
            {
                panel.remove((Widget)widget);
                returnValue = true;
            }
            else if (widget instanceof Panel && recursive)
            {
                boolean foundSomething = removeAnySoundPlayersIfPresent((Panel)widget, true);
                if (foundSomething)
                {
                    returnValue = true;
                }
            }
        }

        return returnValue;
    }

    public static boolean removeAnySoundWidgetsIfPresent(Panel panel, boolean recursive)
    {
        boolean returnValue = false;

        for (Iterator iterator = panel.iterator(); iterator.hasNext();)
        {
            Object widget = iterator.next();

            if (widget instanceof SoundWidget)
            {
                panel.remove((SoundWidget)widget);
                returnValue = true;
            }
            else if (widget instanceof Panel && recursive)
            {
                boolean foundSomething = removeAnySoundWidgetsIfPresent((Panel)widget, true);
                if (foundSomething)
                {
                    returnValue = true;
                }
            }
        }

        return returnValue;
    }

    public static boolean intersects(int mouseX, int mouseY, Panel panel)
    {
        int topBoundry = panel.getAbsoluteTop();
        int bottomBoundry = topBoundry + panel.getOffsetHeight();

        int leftBoundry = panel.getAbsoluteLeft();
        int rightBoundry = leftBoundry + panel.getOffsetWidth();

        if ((mouseX >= leftBoundry && mouseX <= rightBoundry) &&
            (mouseY >= topBoundry && mouseY <= bottomBoundry))
        {
            return true;
        }
        else
        {
            return false;
        }
    }
}
