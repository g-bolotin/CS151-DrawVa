package gbolotin.drawtools;

import java.awt.*;

// Every tool will have a size, color, and behavior.
public abstract class DrawTool
{
    private DrawBehavior drawBehavior;
    public abstract DrawBehavior getDrawBehavior();
    public abstract int getSize();
    public abstract Color getColor();
    public abstract void setSize(int size);
    public abstract void setColor(Color color);
}
