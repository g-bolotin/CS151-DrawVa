package gbolotin.drawtools;

import java.awt.*;
import java.awt.geom.Line2D;
import java.util.ArrayList;

public class BrushBehavior implements DrawBehavior
{
    @Override
    public void draw(Graphics2D g2, ArrayList<Point> points, int size, Color color)
    {
        // use Graphics2D to choose the shape of the brushstroke.
        g2.setStroke(new BasicStroke((float)size, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND));

        // WIP, currently responsible for drawing lines between points to create a continuous stroke.
        // Not fully functional, can only draw one continuous line as it connects all points added via clicks/drags.
        if (points.size() == 1)
        {
            g2.fillOval(points.get(0).x, points.get(0).y, size, size);
        }
        else
        {
            for (int i = 1; i < points.size(); i++)
                g2.draw(new Line2D.Float(points.get(i-1), points.get(i)));
        }

    }
}
