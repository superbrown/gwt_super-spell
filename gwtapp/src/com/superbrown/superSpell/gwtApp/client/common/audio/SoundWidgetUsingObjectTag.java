package com.superbrown.superSpell.gwtApp.client.common.audio;

/**
 */
public class SoundWidgetUsingObjectTag extends SoundWidget
{
    public SoundWidgetUsingObjectTag(String uri, boolean visible, boolean startImmediately)
    {
        this(uri, visible, startImmediately, null);
    }

    public SoundWidgetUsingObjectTag(String uri, boolean visible, boolean startImmediately, String type)
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
            // format is not supported
//            mediaTypeString = "audio/midi";
        }

        String dimensions;
        if (visible)
        {
            dimensions = "width=\"200\" height=\"14\"";
        }
        else
        {
            dimensions = "width=\"0\" height=\"0\"";
            this.setSize("0", "0");
        }

        String autoStartParams;
        if (startImmediately)
        {
            autoStartParams =
                    "  <param name=\"autoplay\" value=\"true\">\n" +
                    "  <param name=\"autoStart\" value=\"1\">\n";
        }
        else
        {
            autoStartParams =
                    "  <param name=\"autoplay\" value=\"false\">\n" +
                    "  <param name=\"autoStart\" value=\"0\">\n";
        }

        String html = "<object type=\"" + mediaTypeString;

        html += "\" data=\"" + uri;

        html += "\" " + dimensions + ">\n";

        html += "  <param name=\"src\" value=\"" + uri + "\">\n";

        html += autoStartParams + "</object>";

        this.setHTML(html);

//        System.out.println(html);
    }
}
