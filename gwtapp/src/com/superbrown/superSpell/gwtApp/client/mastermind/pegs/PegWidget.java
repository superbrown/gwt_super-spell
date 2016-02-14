package com.superbrown.superSpell.gwtApp.client.mastermind.pegs;

import com.allen_sauer.gwt.dnd.client.HasDragHandle;
import com.google.gwt.user.client.ui.*;

/**
 */
public class PegWidget extends AbsolutePanel implements HasDragHandle
{
    private FocusPanel shim = new FocusPanel();

    private final PegLabel pegLabel;

    /**
     * Default constructor to wrap the provided widget.
     *
     * @param pegLabel the widget to be wrapped
     */
    public PegWidget(PegLabel pegLabel)
    {
        setWidth("20px");

        this.pegLabel = pegLabel;
        add(pegLabel);

        // Add some CSS styling
//        addStyleName("demo-PaletteWidget");
//        pegLabel.addStyleName("demo-PaletteWidget-widget");
//        shim.addStyleName("demo-PaletteWidget-shim");
    }

    public PegWidget cloneWidget()
    {
        PegLabel clone = new PegLabel(pegLabel.getPeg());

        // Wrap the cloned widget in a new PaletteWidget instance
        return new PegWidget(clone);
    }

    public Widget getDragHandle()
    {
        return shim;
    }

    /**
     * Let shim size match our size.
     *
     * @param width  the desired pixel width
     * @param height the desired pixel height
     */
    @Override
    public void setPixelSize(int width, int height)
    {
        super.setPixelSize(width, height);
        shim.setPixelSize(width, height);
    }

    /**
     * Let shim size match our size.
     *
     * @param width  the desired CSS width
     * @param height the desired CSS height
     */
    @Override
    public void setSize(String width, String height)
    {
        super.setSize(width, height);
        shim.setSize(width, height);
    }

    /**
     * Adjust the shim size and attach once our widget dimensions are known.
     */
    @Override
    protected void onLoad()
    {
        super.onLoad();
        shim.setPixelSize(getOffsetWidth(), getOffsetHeight());
        add(shim, 0, 0);
    }

    /**
     * Remove the shim to allow the widget to size itself when reattached.
     */
    @Override
    protected void onUnload()
    {
        super.onUnload();
        shim.removeFromParent();
    }

    public PegLabel getPegLabel()
    {
        return pegLabel;
    }

    public Peg getPeg()
    {
        return pegLabel.getPeg();
    }
}
