//MainMenu.java

package snakeGame;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Graphics;

import javax.swing.AbstractButton;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import java.awt.HeadlessException;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;


public class MainMenu extends JFrame implements ActionListener{
	
	private static final long serialVersionUID = 1L;
	
	private JPanel panelStatus;
	private JLabel labelStatus;
	private String title;
	private JButton btn_Start;
	private JButton  btn_Options;
	private JButton btn_Exit;
	
	public MainMenu(String title) throws HeadlessException {
		
		// super constructor
		super(title);
		this.title = title;
		
		setupWindow();
		initializeComponents();
		addComponents();
	}
	
	private void setupWindow() {
		
		// set the screen size of Main Menu
		this.setSize( 	(int) (Toolkit.getDefaultToolkit().getScreenSize().getWidth() * 0.25),
						(int) (Toolkit.getDefaultToolkit().getScreenSize().getHeight() * 0.5)
					);
		
		// set window position to screen center
		this.setLocationRelativeTo(null);
		
		// program will close if the window is closed
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		// component distribution throughout the window
		this.setLayout(new BorderLayout(5,5));
	}
	
	private void initializeComponents() {
		this.panelStatus = new JPanel();
		
        btn_Start = new JButton("Start Game");
       
        btn_Start.setVerticalTextPosition(AbstractButton.CENTER);
        btn_Start.setHorizontalTextPosition(AbstractButton.CENTER); //aka LEFT, for left-to-right locales
        btn_Start.setMnemonic(KeyEvent.VK_D);
        btn_Start.setSize(20,20);
        btn_Start.addActionListener(this);
        btn_Start.setActionCommand("start");
        		
		this.labelStatus = new JLabel(this.title);
		labelStatus.setSize(new Dimension(50,50));
		
		this.panelStatus.add(labelStatus);
		
		this.panelStatus.setBackground(Color.cyan);
		this.panelStatus.setBorder(BorderFactory.createEtchedBorder());
		

	}

	private void addComponents() {
		this.add(panelStatus, BorderLayout.SOUTH);
		this.add(btn_Start,BorderLayout.CENTER);
		
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
        String action = e.getActionCommand();
        if (action.equals("start")) {
        	System.out.println("Game is about to start!");
        	Snake snake = new Snake();
        	this.setVisible(false);
        	snake.setVisible(true);
        	
        }
		
	}

	public static void main(String[] args) {
		
		
		

	}
	

	
	void initGame() {
		
	}



}
