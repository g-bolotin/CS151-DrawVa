package gbolotin.canvas;

import gbolotin.drawtools.Brush;
import gbolotin.drawtools.DrawTool;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

public class Canvas extends JPanel
{
    public final ArrayList<Point> points = new ArrayList<>();
    private DrawTool selectedTool;
    private Brush brush = new Brush();

    // Canvas creation
    public Canvas()
    {
        selectedTool = brush;

        addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent event) {
                points.add(event.getPoint());
                repaint();
            }
        });

        addMouseMotionListener(new MouseAdapter() {
            public void mouseDragged(MouseEvent event) {
                points.add(event.getPoint());
                repaint();
            }
        });
    }

    // Paints onto the canvas
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        g2.setColor(selectedTool.getColor());
        selectedTool.getDrawBehavior().draw(g2, points, selectedTool.getSize(), selectedTool.getColor());
    }

    public DrawTool getSelectedTool()
    {
        return selectedTool;
    }

    public void setSelectedTool(DrawTool tool)
    {
        selectedTool = tool;
    }

    public Brush getBrush()
    {
        return brush;
    }

    // TESTER, REMOVE FOR FINAL PROJECT
    public static void main(String[] args)
    {
        JFrame frame = new JFrame();
        frame.setSize(500,500);
        frame.setResizable(false);
        Canvas canvas = new Canvas();
        frame.add(canvas);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setVisible(true);

        canvas.setSelectedTool(canvas.getBrush());
        canvas.getSelectedTool().setColor(Color.RED);
        canvas.getSelectedTool().setSize(30);
    }
}
