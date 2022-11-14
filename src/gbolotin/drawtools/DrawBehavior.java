package gbolotin.drawtools;

import java.awt.*;
import java.util.ArrayList;

public interface DrawBehavior
{
    void draw(Graphics2D g2, ArrayList<Point> points, int size, Color color);
}
