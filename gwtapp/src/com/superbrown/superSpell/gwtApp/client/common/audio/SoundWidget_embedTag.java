package com.superbrown.superSpell.gwtApp.client.common.audio;

/**
 */
public class SoundWidget_embedTag extends SoundWidget
{
    public SoundWidget_embedTag(String uri, boolean visible, boolean startImmediately)
    {
        String dimensions;
        if (visible)
        {
            dimensions = "width=\"200\" height=\"14\"";
        }
        else
        {
            dimensions = "width=\"0\" height=\"0\"";
        }

        String autoStartParams;
        if (startImmediately)
        {
            autoStartParams = "autoplay=\"true\" autoStart=\"1\"";
        }
        else
        {
            autoStartParams = "autoplay=\"false\" autoStart=\"0\"";
        }

        String html = "<embed src=\"" + uri + "\" " + dimensions + " " + autoStartParams +
//                "<noembed><bgsound src=\"" + uri + "\" loop=\"1\"></noembed>" +
                "</embed>";

        this.setHTML(html);

//        System.out.println(html);
    }
}
