package com.superbrown.superSpell.gwtApp.client.common.audio;

/**
 */
public class SoundWidget_audioTag extends SoundWidget
{
    public SoundWidget_audioTag(String uri, boolean visible, boolean startImmediately)
    {
        this(uri, visible, startImmediately, null);
    }

    public SoundWidget_audioTag(String uri, boolean visible, boolean startImmediately, String type)
    {
        String mediaTypeString = null;

        if (type != null)
        {
            mediaTypeString = type;
        }
        else if (uri.endsWith(".wav"))
        {
            mediaTypeString = "audio/x-wav";
        }
        else if (uri.endsWith(".mp3"))
        {
            mediaTypeString = "audio/mpeg";
        }
        else if (uri.endsWith(".mid"))
        {
            throw new RuntimeException();
        }

        String html = "<audio";

        if (visible) {
            html += " controls";
        }

        if (startImmediately) {
            html += " autoplay";
        }

        html += ">\n" +
        "  <source src=\"" + uri + "\" type=\"" + mediaTypeString + "\"/>\n" +
                "Your browser does not support the audio element.\n" +
                "</audio>";

        this.setHTML(html);
    }
}
