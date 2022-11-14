package gbolotin.drawtools;

import java.awt.*;

public class Brush extends DrawTool
{
    private final DrawBehavior drawBehavior = new BrushBehavior();
    private int size;
    private Color color;

    // constructors, default is a black size 10
    public Brush()
    {
        this.size = 10;
        this.color = Color.BLACK;
    }

    public Brush(int size, Color color)
    {
        this.size = size;
        this.color = color;
    }

    // Getters and setters
    public int getSize()
    {
        return size;
    }

    public void setSize(int size)
    {
        this.size = size;
    }

    public Color getColor()
    {
        return color;
    }

    public void setColor(Color color)
    {
        this.color = color;
    }

    @Override
    public DrawBehavior getDrawBehavior()
    {
        return drawBehavior;
    }
}
