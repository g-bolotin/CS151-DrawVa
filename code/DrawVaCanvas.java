import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

public class DrawVaCanvas extends JFrame implements MouseListener, MouseMotionListener {

	//Instance variables
	private Canvas canvas;
	private Color backgroundColor = Color.WHITE;
	private Color brushColor;
	private int brushSize = 10;

	// DrawVaCanvas constructor
	DrawVaCanvas()
	{
		super("DrawVa");
		canvas = new Canvas() {
			public void paint(Graphics g)
			{
			}
		};
		// set background
		canvas.setBackground(backgroundColor);
		canvas.setBounds(0,60,500,440);

		//Border for GUI
		JLabel guiBorder = new JLabel();
		guiBorder.setBounds(0, 0, 500, 60);
		this.add(guiBorder);
		//Creating gui buttons
		createEraseButton();
		createClearButton();
		createColorButtons();
		// add mouse listener
		canvas.addMouseListener(this);
		canvas.addMouseMotionListener(this);
		this.add(canvas);
		this.setSize(500, 500);
		this.setResizable(false);
		this.show();
	}

	// mouse listener and mouse motion listener methods
	public void mouseClicked(MouseEvent e)
	{
		Graphics g = canvas.getGraphics();
		g.setColor(brushColor);
		g.fillOval(e.getX(), e.getY(), brushSize, brushSize);
	}

	public void mouseMoved(MouseEvent e)
	{
	}

	public void mouseDragged(MouseEvent e)
	{
		Graphics g = canvas.getGraphics();
		g.setColor(brushColor);
		g.fillOval(e.getX(), e.getY(), brushSize, brushSize);
	}

	public void mouseExited(MouseEvent e)
	{
	}

	public void mouseEntered(MouseEvent e)
	{
	}

	public void mouseReleased(MouseEvent e)
	{
	}

	public void mousePressed(MouseEvent e)
	{
	}

	public void createEraseButton()
	{
//		create JButton eraseButton 
//		eraseButton.setBounds(305, 0, 30, 60);
//		add eraseButton here
//		eraseButton.addActionListener(new ActionListener() {
//			public void actionPerformed(ActionEvent e) {
//				set brushcolor to bg color
//			}
//		});
	}
	public void createClearButton()
	{
//		create JButton clearButton 
//		setBounds(275, 0, 30, 60) for clear buttom
//	    add clear button
//		clearButton.addActionListener(new ActionListener() {
//			public void actionPerformed(ActionEvent e) {
//				create Graphics g like in mousedragged
//				clearRect(0, 0, 500, 500)
//			}
//		});
	}

	//Create Color Buttons to change color of brush
	//NOTE: This should be done in Controller Class
	public void createColorButtons()
	{
		JButton blackButton = new JButton();
		blackButton.setBounds(455, 30, 30, 30);
		blackButton.setBackground(Color.BLACK);
		this.add(blackButton);
		blackButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
					brushColor = Color.BLACK;
			}
		});

		JButton lightGrayButton = new JButton();
		lightGrayButton.setBounds(425, 30, 30, 30);
		lightGrayButton.setBackground(Color.LIGHT_GRAY);
		this.add(lightGrayButton);
		lightGrayButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
					brushColor = Color.LIGHT_GRAY;
			}
		});

		JButton whiteButton = new JButton();
		whiteButton.setBounds(395, 30, 30, 30);
		whiteButton.setBackground(Color.WHITE);
		this.add(whiteButton);
		whiteButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
					brushColor = Color.WHITE;
			}
		});

		JButton magentaButton = new JButton();
		magentaButton.setBounds(365, 30, 30, 30);
		magentaButton.setBackground(Color.MAGENTA);
		this.add(magentaButton);
			magentaButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
					brushColor = Color.MAGENTA;
			}
		});

		JButton pinkButton = new JButton();
		pinkButton.setBounds(335, 30, 30, 30);
		pinkButton.setBackground(Color.PINK);
		this.add(pinkButton);
		pinkButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
					brushColor = Color.PINK;
			}
		});

		JButton blueButton = new JButton();
		blueButton.setBounds(455, 0, 30, 30);
		blueButton.setBackground(Color.BLUE);
		this.add(blueButton);
		blueButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
					brushColor = Color.BLUE;
			}
		});

		JButton greenButton = new JButton();
		greenButton.setBounds(425, 0, 30, 30);
		greenButton.setBackground(Color.GREEN);
		this.add(greenButton);
		greenButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
					brushColor = Color.GREEN;
			}
		});

		JButton yellowButton = new JButton();
		yellowButton.setBounds(395, 0, 30, 30);
		yellowButton.setBackground(Color.YELLOW);
		this.add(yellowButton);
		yellowButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
					brushColor = Color.YELLOW;
			}
		});

		JButton orangeButton = new JButton();
		orangeButton.setBounds(365, 0, 30, 30);
		orangeButton.setBackground(Color.ORANGE);
		this.add(orangeButton);
		orangeButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
					brushColor = Color.ORANGE;
			}
		});

		JButton redButton = new JButton();
		redButton.setBounds(335, 0, 30, 30);
		redButton.setBackground(Color.RED);
		this.add(redButton);
		redButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
					brushColor = Color.RED;
			}
		});

	}
	//TESTER MAIN METHOD REMOVE IN FINAL PROJECT
	public static void main(String args[])
	{
		DrawVaCanvas c = new DrawVaCanvas();
	}
}

