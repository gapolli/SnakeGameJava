package snakeGame;

import java.awt.EventQueue;
import javax.swing.JFrame;

public class Snake extends JFrame {

    public Snake(int difficult) {
  
        initUI(difficult);
    }
    
    private void initUI(int difficult) {
        
        add(new Board(difficult));
        
        setResizable(false);
        pack();
        
        setTitle("Snake");
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    
    public static void main(String[] args) {
    	
    }
}
