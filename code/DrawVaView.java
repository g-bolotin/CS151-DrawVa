import java.awt.*;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.event.*;
import java.io.File;
import java.io.IOException;


public class DrawVaView extends JFrame implements Observer {

    private DrawVaModel model;
    private BufferedImage fillColorImage =
            new BufferedImage(16, 16, java.awt.image.BufferedImage.TYPE_INT_RGB);
    private BufferedImage strokeColorImage =
            new BufferedImage(16, 16, java.awt.image.BufferedImage.TYPE_INT_RGB);

    // Menu
    private JMenuItem restore;
    private JRadioButtonMenuItem selectionMode;
    private JRadioButtonMenuItem drawingMode;
    private JMenuItem deleteShape;
    private JMenuItem transformShape;
    private JMenu strokeWidth;
    private JMenuItem fillColor;
    private JMenuItem strokeColor;

    // Tool
    private JButton selectButton;
    private JButton drawButton;
    private JButton undoButton;
    private JComboBox strokes;
    private JButton fillColorButton;
    private JButton strokeColorButton;
    
    private DrawVaController canvasController;

    /**
     * Create a new View.
     */
    public DrawVaView(DrawVaModel model) {
        // Set up the window.
        setTitle("CS 151 DrawVa");
        setMinimumSize(new Dimension(128, 128));
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Hook up this observer so that it will be notified when the model
        // changes.
        this.model = model;
        canvasController = new DrawVaController(model);
        model.addObserver(this);
        createMenuBar();
        createToolBar();
        add(canvasController, BorderLayout.CENTER);
        setVisible(true);
    }
    /**
     * Update with data from the model.
     */
    public void update(Object observable) {
        selectButton.setSelected(!model.getDrawMode());
        selectionMode.setSelected(!model.getDrawMode());
        drawButton.setSelected(model.getDrawMode());
        drawingMode.setSelected(model.getDrawMode());
        if (!model.getDrawMode()) {
            deleteShape.setEnabled(true);
            transformShape.setEnabled(true);
        }
        else {
            deleteShape.setEnabled(false);
            transformShape.setEnabled(false);
            for (DrawVaModel.CanvasShape cs : model.getCanvasShapes()) {
                cs.selected = false;
            }
        }
        if (model.getdeleteTransformOverride()) {
            deleteShape.setEnabled(false);
            transformShape.setEnabled(false);
        }

        // Update colors on toolbar/menu
        strokeColorButton.setIcon(changeColor(strokeColorImage, model.getStrokeColor()));
        fillColorButton.setIcon(changeColor(fillColorImage, model.getFillColor()));
        fillColor.setIcon(changeColor(fillColorImage, model.getFillColor()));
        strokeColor.setIcon(changeColor(strokeColorImage, model.getStrokeColor()));


        // Update stroke width
        strokeWidth.getItem(model.getStrokeThickness() - 1).setSelected(true);
        strokes.setSelectedIndex(model.getStrokeThickness() - 1);

        // Update restore button
        restore.setEnabled(model.checkForInputFile());

    }

    private void createMenuBar() {
        JMenuBar menubar=new JMenuBar();

        // File Menu
        JMenu file = createFileMenu();
        // Edit Menu
        JMenu edit = createEditMenu();
        // Format Menu
        JMenu format = createFormatMenu();

        menubar.add(file);
        menubar.add(edit);
        menubar.add(format);
        setJMenuBar(menubar);
    }

    private JMenu createFileMenu() {
        JMenu file = new JMenu("File");
        JMenuItem new1 = new JMenuItem("New");
        new1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                model.resetCanvasShapes();
            }
        });
        JMenuItem save = new JMenuItem("Save");
        save.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                model.outputToFile();
                System.out.println("File successfully output to output.txt");
                restore.setEnabled(true);
            }
        });
        
        JMenuItem download = new JMenuItem("Download");
        download.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JFileChooser fc = new JFileChooser(System.getProperty("user.home") + "/Downloads");
                fc.setDialogType(JFileChooser.SAVE_DIALOG);
                fc.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
                
                fc.setAcceptAllFileFilterUsed(false);
                FileFilter pngFilter = new FileNameExtensionFilter("PNG File", "png", "PNG");
                fc.addChoosableFileFilter(pngFilter);
                FileFilter jpgFilter = new FileNameExtensionFilter("JPEG File", "jpg", "JPG", "jpeg", "JPEG");
                fc.addChoosableFileFilter(jpgFilter);
                FileFilter gifFilter = new FileNameExtensionFilter("GIF File", "gif", "GIF");
                fc.addChoosableFileFilter(gifFilter);


                JTextArea text = new JTextArea("Do not add a file extension to the file name! \n\nIt will be added automatically based on your selection in File Format.");
                text.setLineWrap(true);
                text.setWrapStyleWord(true);
                text.setEditable(false);
                text.setMargin(new Insets(10,10,10,10));
                fc.setAccessory(text);

                int returnValFC = fc.showSaveDialog(download);
                if (returnValFC == JFileChooser.APPROVE_OPTION) {
                    try {
                        BufferedImage currentCanvas = canvasController.createBufferedImageFromCanvas();

                        if (fc.getFileFilter().equals(jpgFilter))
                            ImageIO.write(currentCanvas, "jpg", new File(fc.getSelectedFile().getAbsolutePath() + ".jpg"));
                        else if (fc.getFileFilter().equals(gifFilter))
                            ImageIO.write(currentCanvas, "gif", new File(fc.getSelectedFile().getAbsolutePath() + ".gif"));
                        else
                            ImageIO.write(currentCanvas, "png", new File(fc.getSelectedFile().getAbsolutePath() + ".png"));

                        model.outputToFile();
                        restore.setEnabled(true);
                        System.out.println("File successfully saved to: " + fc.getSelectedFile().getAbsolutePath());
                    } catch (IOException ioe) {
                        ioe.printStackTrace();
                    }
                }
                else {
                    System.out.println("Save cancelled.");
                }
            }
        });

        restore = new JMenuItem("Restore");
        restore.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                model.readFromFile();
                System.out.println("File read from output.txt");
                repaint();
            }
        });

        restore.setEnabled(model.checkForInputFile());


        JMenuItem exit = new JMenuItem("Exit");
        exit.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
        file.add(new1);
        file.add(save);
        file.add(download);
        file.add(restore);
        file.add(exit);
        return file;
    }

    private JMenu createEditMenu() {
        JMenu edit = new JMenu("Edit");
        ButtonGroup radiogroup = new ButtonGroup();
        selectionMode = new JRadioButtonMenuItem("Selection Mode");
        radiogroup.add(selectionMode);

        drawingMode = new JRadioButtonMenuItem("Drawing Mode", true);
        radiogroup.add(drawingMode);

        deleteShape = new JMenuItem("Delete Shape");
        deleteShape.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                for (DrawVaModel.CanvasShape cs : model.getCanvasShapes()) {
                    if (cs.selected) {
                        cs.shape = null;
                        cs.freeHandPoints = null;
                        cs.selected = false;
                        model.setDeleteTransformOverride(true);
                    }
                }
            }
        });

        transformShape = new JMenuItem("Transform Shape");
        transformShape.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                addTransformShapeModel();
            }
        });


        deleteShape.setEnabled(false);
        transformShape.setEnabled(false);

        selectionMode.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                if (model.getDrawMode()) {
                    model.setDrawMode();
                }
            }
        });

        drawingMode.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                if (!model.getDrawMode()) {
                    model.setDrawMode();
                }
            }
        });

        edit.add(selectionMode);
        edit.add(drawingMode);
        edit.addSeparator();
        edit.add(deleteShape);
        edit.add(transformShape);
        return edit;
    }

    private JMenu createFormatMenu() {
        JMenu format = new JMenu("Format");
        JMenu strokeWidth = addStrokeWidthMenuItem();
        JMenuItem fillColor = addFillColorMenuItem();
        JMenuItem strokeColor = addStrokeColorMenuItem();

        format.add(strokeWidth);
        format.add(fillColor);
        format.add(strokeColor);
        return format;
    }

    private JMenu addStrokeWidthMenuItem() {
        strokeWidth = new JMenu("Stroke Width");
        String[] strokeThickness = { "1px", "2px", "3px", "4px", "5px", "6px", "7px", "8px", "9px", "10px" };
        ButtonGroup strokes = new ButtonGroup();

        ActionListener radioActionListener = new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                AbstractButton button = (AbstractButton) e.getSource();
                for (DrawVaModel.CanvasShape cs : model.getCanvasShapes()) {
                    if (cs.selected) {
                        if ("10px" == button.getText()) {
                            cs.strokeWidth = 10;
                        }
                        else {
                            int val = Character.getNumericValue(button.getText().charAt(0));
                            cs.strokeWidth = val;
                        }
                    }
                }
                updateStrokeThickness(button.getText());
            }
        };

        for (String stroke : strokeThickness) {
            JRadioButtonMenuItem temp = new JRadioButtonMenuItem(stroke);
            strokes.add(temp);
            strokeWidth.add(temp);
            temp.addActionListener(radioActionListener);
        }
        strokeWidth.getItem(0).setSelected(true);

        return strokeWidth;
    }

    private JMenuItem addFillColorMenuItem() {
        ImageIcon fill = changeColor(fillColorImage,  model.getFillColor());
        fillColor = new JMenuItem("Fill Colour...", fill);
        fillColor.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e){
                openFillColorDialog();
                fillColor.setIcon(changeColor(fillColorImage, model.getFillColor()));

            }
        });
        return fillColor;
    }

    private JMenuItem addStrokeColorMenuItem() {
        ImageIcon stroke = changeColor(strokeColorImage,  model.getStrokeColor());
        strokeColor = new JMenuItem("Stroke Colour...", stroke);
        strokeColor.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e){
                openStrokeColorDialog();
                strokeColor.setIcon(changeColor(strokeColorImage, model.getStrokeColor()));

            }
        });
        return strokeColor;
    }

    private void createToolBar() {
        JToolBar toolbar = new JToolBar();
        toolbar.setFloatable(false);

        ImageIcon cursor = new ImageIcon("src/icons/cursor.png");
        ImageIcon paintBrush = new ImageIcon("src/icons/paintbrush.png");
        selectButton = new JButton("Select", cursor);
        drawButton = new JButton("Draw", paintBrush);
        undoButton = new JButton("Undo");
        drawButton.setSelected(true);

        selectButton.addActionListener(new ActionListener(){
             public void actionPerformed(ActionEvent e){
                if (model.getDrawMode()) {
                    model.setDrawMode();
                    model.setDeleteTransformOverride(true);
                }
            }
        });

        drawButton.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                if (!model.getDrawMode()) {
                    model.setDrawMode();
                    model.setDeleteTransformOverride(true);
                }
            }
        });
        
        undoButton.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                canvasController.undo();
            }
        });

        JComboBox drawingModes = addDrawingModesDropdown();
        strokes = addStrokesDropdown();
        JButton fillColor = addFillColorButton();
        JButton strokeColor = addStrokeColorButton();

        toolbar.add(selectButton);
        toolbar.add(drawButton);
        toolbar.add(drawingModes);
        toolbar.add(undoButton);
        toolbar.add(strokes);
        toolbar.add(fillColor);
        toolbar.add(strokeColor);
        add(toolbar, BorderLayout.NORTH);
    }

    private JComboBox addDrawingModesDropdown() {
        String[] drawingModeValues = { "Freeform line", "Line", "Rectangle", "Ellipse" };
        JComboBox drawingModes = new JComboBox(drawingModeValues);
        drawingModes.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e){
                String selectedDrawingMode = (String) drawingModes.getSelectedItem();
                switch (selectedDrawingMode) {
                    case "Freeform line":
                        model.setDrawingMode(DrawVaModel.drawingModeType.FREEFORM);
                        break;
                    case "Line":
                        model.setDrawingMode(DrawVaModel.drawingModeType.LINE);
                        break;
                    case "Rectangle":
                        model.setDrawingMode(DrawVaModel.drawingModeType.RECTANGLE);
                        break;
                    case "Ellipse":
                        model.setDrawingMode(DrawVaModel.drawingModeType.ELLIPSE);
                        break;
                }
            }
        });
        return drawingModes;
    }

    private JComboBox addStrokesDropdown() {
        String[] strokeThickness = { "1px", "2px", "3px", "4px", "5px", "6px", "7px", "8px", "9px", "10px" };
        JComboBox strokes = new JComboBox<>(strokeThickness);
        strokes.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                String dropdownVal = (String) strokes.getSelectedItem();
                int dropdownNumerical;
                if (dropdownVal.equals("10px")) {
                    dropdownNumerical = 10;
                }
                else {
                    dropdownNumerical = Character.getNumericValue(dropdownVal.charAt(0));
                }
                if ( model.getStrokeThickness() != dropdownNumerical) {
                    updateStrokeThickness(dropdownVal);
                    for (DrawVaModel.CanvasShape cs : model.getCanvasShapes()) {
                        if (cs.selected) {
                            if ("10px" == dropdownVal) {
                                cs.strokeWidth = 10;
                            } else {
                                cs.strokeWidth = Character.getNumericValue(dropdownVal.charAt(0));
                            }
                        }
                    }
                }
            }
        });
        return strokes;
    }

    private JButton addFillColorButton() {
        ImageIcon fill = changeColor(fillColorImage,  model.getFillColor());
        fillColorButton = new JButton("Fill Colour", fill);
        fillColorButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e){
                openFillColorDialog();
                fillColorButton.setIcon(changeColor(fillColorImage, model.getFillColor()));

            }
        });
        return fillColorButton;
    }

    private JButton addStrokeColorButton() {
        ImageIcon stroke = changeColor(strokeColorImage, model.getStrokeColor());
        strokeColorButton = new JButton("Stroke Colour", stroke);
        strokeColorButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e){
                openStrokeColorDialog();
                strokeColorButton.setIcon(changeColor(strokeColorImage, model.getStrokeColor()));

            }
        });
        return strokeColorButton;
    }

    private void updateStrokeThickness(String selectedStrokeThickness) {
        if ("10px".equals(selectedStrokeThickness)) {
            model.setStrokeThickness(10);
        }
        else {
            int val = Character.getNumericValue(selectedStrokeThickness.charAt(0));
            model.setStrokeThickness(val);
        }
    }

    private void openFillColorDialog() {
        Color newColor = JColorChooser.showDialog(null, "Choose Fill Colour", model.getFillColor());
        if (newColor != null) {
            model.setFillColor(newColor);
        }
        for (DrawVaModel.CanvasShape cs : model.getCanvasShapes()) {
            if (cs.selected) {
                cs.fillColor = model.getFillColor();
            }
        }
    }

    private void openStrokeColorDialog() {
        Color newColor = JColorChooser.showDialog(null, "Choose Stroke Colour", model.getStrokeColor());
        if (newColor != null) {
            model.setStrokeColor(newColor);
        }
        for (DrawVaModel.CanvasShape cs : model.getCanvasShapes()) {
            if (cs.selected) {
                cs.strokeColor = model.getStrokeColor();
            }
        }
    }

    private void addTransformShapeModel() {
    	DrawVaModel.CanvasShape selectedShape = null;
        for (DrawVaModel.CanvasShape cs : model.getCanvasShapes()) {
            if (cs.selected) {
                selectedShape = cs;
            }
        }
        JSpinner translateX = new JSpinner(
                new SpinnerNumberModel(selectedShape.translateX, -1000, 1000, 1));
        JSpinner translateY = new JSpinner(
                new SpinnerNumberModel(selectedShape.translateY, -1000, 1000, 1));
        JSpinner rotate = new JSpinner(
                new SpinnerNumberModel(selectedShape.rotate, -360, 360, 1));
        JSpinner scaleX = new JSpinner(
                new SpinnerNumberModel(selectedShape.scaleX, -10, 10, 0.1));
        JSpinner scaleY = new JSpinner(
                new SpinnerNumberModel(selectedShape.scaleY, -10, 10, 0.1));
        JSpinner shearX = new JSpinner(
                new SpinnerNumberModel(selectedShape.shearX, -10, 10, 0.1));
        JSpinner shearY = new JSpinner(
                new SpinnerNumberModel(selectedShape.shearY, -10, 10, 0.1));
        JPanel panel = new JPanel(new GridLayout(4, 3));
        JPanel subpanel = new JPanel(new GridLayout(1, 2));
        panel.add(new JLabel("Translate (px):"));
        subpanel.add(new JLabel("                        x:"));
        subpanel.add(translateX);
        panel.add(subpanel);
        JPanel subpanel2 = new JPanel(new GridLayout(1, 2));
        subpanel2.add(new JLabel("                        y:"));
        subpanel2.add(translateY);
        panel.add(subpanel2);
        panel.add(new JLabel("Rotate (degrees):"));
        panel.add(rotate);
        panel.add(new JLabel(""));
        panel.add(new JLabel("Scale (times):"));
        JPanel subpanel3 = new JPanel(new GridLayout(1, 2));
        subpanel3.add(new JLabel("                        x:"));
        subpanel3.add(scaleX);
        panel.add(subpanel3);
        JPanel subpanel4 = new JPanel(new GridLayout(1, 2));
        subpanel4.add(new JLabel("                        y:"));
        subpanel4.add(scaleY);
        panel.add(subpanel4);
        panel.add(new JLabel("Shear:"));
        JPanel subpanel5 = new JPanel(new GridLayout(1, 2));
        subpanel5.add(new JLabel("                        x:"));
        subpanel5.add(shearX);
        panel.add(subpanel5);
        JPanel subpanel6 = new JPanel(new GridLayout(1, 2));
        subpanel6.add(new JLabel("                        y:"));
        subpanel6.add(shearY);
        panel.add(subpanel6);
        int result = JOptionPane.showConfirmDialog(null, panel, "Transform Shape",
                JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
        if (result == JOptionPane.OK_OPTION) {
            selectedShape.translateX = (int) translateX.getValue();
            selectedShape.translateY = (int) translateY.getValue();
            selectedShape.rotate = (int) rotate.getValue();
            selectedShape.scaleX = (double) scaleX.getValue();
            selectedShape.scaleY = (double) scaleY.getValue();
            selectedShape.shearX = (double) shearX.getValue();
            selectedShape.shearY = (double) shearY.getValue();
            model.notifyObservers();
        }
    }

    private ImageIcon changeColor(BufferedImage image, Color color){
        Graphics2D graphics = image.createGraphics();
        graphics.setPaint(color);
        graphics.fillRect(0, 0, 16, 16);
        image.flush();
        return new ImageIcon(image);
    }
}
