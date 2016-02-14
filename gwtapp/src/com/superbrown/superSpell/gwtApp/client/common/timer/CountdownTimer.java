package com.superbrown.superSpell.gwtApp.client.common.timer;

import com.google.gwt.user.client.Timer;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.VerticalPanel;

/**
 */
public class CountdownTimer extends VerticalPanel
{
    private int countdownFrom;
    private ICountdownTimerListener countdownTimerListener;
    private Timer timer;
    private String cssStyle;
    private boolean counterWithLineOfNumbers;


    public CountdownTimer(int countdownFrom, String cssStyle, boolean counterWithNumbers)
    {
        super();
        this.countdownFrom = countdownFrom;
        this.cssStyle = cssStyle;
        this.counterWithLineOfNumbers = counterWithNumbers;
    }

    public void start()
    {
        if (timer != null)
        {
            return;
        }

        timer = new Timer()
        {
            Label label;
            int currentCount;

            public void run()
            {
                // This should never be the case. But I am seeing times where I have stopped the
                // counter, but it still seems to run.
                if (timer == null)
                {
                    return;
                }

                if (!isAttached())
                {
                    stop();
                    return;
                }
                
                if (label == null)
                {
                    currentCount = countdownFrom;
                    label = new Label("");
                    label.addStyleName(cssStyle);
                    add(label);
                }
                else
                {
                    currentCount--;
                }

                if (currentCount == 0)
                {
                    stop();
                    label.removeFromParent();
                    countdownTimerListener.timerComplete();
                }
                else
                {
                    String text = "";

                    if (counterWithLineOfNumbers)
                    {
                        for (int i = 1; i <= currentCount; i++)
                        {
                            text += i + " ";
                        }
                    }
                    else
                    {
                        text += currentCount;
                    }

                    label.setText(text);
                }
            }
        };

        timer.scheduleRepeating(1000);
        timer.run();
    }

    public void stop()
    {
        if (timer == null)
        {
            return;
        }

        timer.cancel();
        timer = null;
    }

    public void setCountdownTimerListener(ICountdownTimerListener countdownTimerListener)
    {
        this.countdownTimerListener = countdownTimerListener;
    }
}
