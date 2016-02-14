package com.superbrown.superSpell.gwtApp.client.common.timer;

import com.google.gwt.user.client.Timer;
import com.google.gwt.user.client.ui.Button;

/**
 */
public class CountdownTimerButton
{
    private int countdownFrom;
    private ICountdownTimerListener countdownTimerListener;
    private Timer timer;
    private Button button;


    public CountdownTimerButton(int countdownFrom, Button button)
    {
        super();
        this.countdownFrom = countdownFrom;
        this.button = button;
    }

    public void start()
    {
        if (timer != null)
        {
            return;
        }


        timer = new Timer()
        {
            int currentCount = countdownFrom;

            public void run()
            {
                if (button.getParent() == null)
                {
                    return;
                }
                
                currentCount--;

                if (currentCount == 0)
                {
                    cancel();
                    countdownTimerListener.timerComplete();
                }
                else
                {
                    button.setText("" + currentCount);
                }
            }
        };

        timer.scheduleRepeating(1000);
        timer.run();
    }

    public void stop()
    {
        if (timer != null)
        {
            timer.cancel();
        }
    }

    public void setCountdownTimerListener(ICountdownTimerListener countdownTimerListener)
    {
        this.countdownTimerListener = countdownTimerListener;
    }
}
