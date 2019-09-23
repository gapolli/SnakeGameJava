//MainMenu.java

package snakeGame;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.Graphics;

import javax.swing.AbstractButton;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
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
	private JButton btn_Options;
	private JButton btn_Exit;
	
	private int difficult = 1;
	
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
		
		// program cant be resizable
		this.setResizable(false);
		
		// component distribution throughout the window
		this.setLayout(null);
	}
	
	private void initializeComponents() {
		
		this.panelStatus = new JPanel();
		
        btn_Start = new JButton("Start Game");
        btn_Options = new JButton("Options");
        btn_Exit = new JButton("Exit");
       
        btn_Start.setToolTipText("Start the game");
        btn_Start.setVerticalTextPosition(AbstractButton.CENTER);
        btn_Start.setHorizontalTextPosition(AbstractButton.CENTER); //aka LEFT, for left-to-right locales
        btn_Start.setBounds(0,100,(int) (Toolkit.getDefaultToolkit().getScreenSize().getWidth() * 0.25),100);  
        btn_Start.addActionListener(this);
        btn_Start.setActionCommand("start");
        
        btn_Options.setToolTipText("Choose the difficult of the game");
        btn_Options.setVerticalTextPosition(AbstractButton.CENTER);
        btn_Options.setHorizontalTextPosition(AbstractButton.CENTER); //aka LEFT, for left-to-right locales
        btn_Options.setBounds(0,200,(int) (Toolkit.getDefaultToolkit().getScreenSize().getWidth() * 0.25),100); 
        btn_Options.addActionListener(this);
        btn_Options.setActionCommand("options");
        
        btn_Exit.setToolTipText("Exit the game");
        btn_Exit.setVerticalTextPosition(AbstractButton.CENTER);
        btn_Exit.setHorizontalTextPosition(AbstractButton.CENTER); //aka LEFT, for left-to-right locales
        btn_Exit.setBounds(0,300,(int) (Toolkit.getDefaultToolkit().getScreenSize().getWidth() * 0.25),100); 
        btn_Exit.addActionListener(this);
        btn_Exit.setActionCommand("exit");
        		
		this.labelStatus = new JLabel(this.title);
		labelStatus.setSize(new Dimension(1000,1000));
				
		this.panelStatus.setBackground(Color.cyan);
		this.panelStatus.setBounds(0,450,(int) (Toolkit.getDefaultToolkit().getScreenSize().getWidth() * 0.25),100); 
		

	}

	private void addComponents() {
		
		this.panelStatus.add(labelStatus);
		
		this.add(btn_Start);
		this.add(btn_Options);
		this.add(btn_Exit);
		this.add(panelStatus);
		
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
        String action = e.getActionCommand();
        switch (action) {
        	case "start":
            	System.out.println("Snake Game is about to start!");
            	Snake snake = new Snake(difficult);
            	this.setVisible(false);
            	snake.setVisible(true);
        			break;
        	case "options":
        		difficult = Integer.parseInt(JOptionPane.showInputDialog("Choose game difficult: \n 1 - EASY : 2 - MEDIUM : 3 - HARD : 4 - INSANE"));
        		System.out.println("Difficult: " + difficult);
        		break;
        	case "exit":
        		System.exit(0);
        		break;
        }
       		
	}

	public static void main(String[] args) {
	}
	



}
