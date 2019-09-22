package snakeGame;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.security.SecureRandom;

import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.Timer;

public class Board extends JPanel implements ActionListener {

    private final int B_WIDTH = (int) (Toolkit.getDefaultToolkit().getScreenSize().getWidth() * 0.4);
    private final int B_HEIGHT = (int) (Toolkit.getDefaultToolkit().getScreenSize().getHeight() * 0.5);
    private final int DOT_SIZE = 25; // the same pixel size as image
    private final int ALL_DOTS = (int) ((B_WIDTH*B_HEIGHT)/(Math.pow(DOT_SIZE, 2)));
    private final int DELAY = 150;
    
    SecureRandom RAND_POS =  new SecureRandom();
    
    private final int x[] = new int[ALL_DOTS];
    private final int y[] = new int[ALL_DOTS];

    private int dots;
    private int apple_x;
    private int apple_y;
    private int score = 0;

    private boolean leftDirection = false;
    private boolean rightDirection = true;
    private boolean upDirection = false;
    private boolean downDirection = false;
    private boolean inGame = true;

    private Timer timer;
    private Image bg;
    private Image apple;
    private Image head;
    private Image body;
    
    private Image l_head;
    private Image r_head;
    private Image u_head;
    private Image d_head;

    public Board() {
        initBoard();
    }
    
    private void initBoard() {

        addKeyListener(new TAdapter());
        setBackground(Color.black);
        setFocusable(true);

        setPreferredSize(new Dimension(B_WIDTH, B_HEIGHT));
        loadImages();
        initGame();
    }

    private void loadImages() {
    	
    	//Apple image
        ImageIcon iia = new ImageIcon("resources/apple.png");
        apple = iia.getImage();
        
        //Background image
        bg = Toolkit.getDefaultToolkit().createImage("resources/bg.jpg");
        
        //Snake get images
        ImageIcon bd = new ImageIcon("resources/snakeimage.png");
        body = bd.getImage();
        ImageIcon lh = new ImageIcon("resources/leftmouth.png");
        l_head = lh.getImage();
        ImageIcon rh = new ImageIcon("resources/rightmouth.png");
        r_head = rh.getImage();
        ImageIcon uh = new ImageIcon("resources/upmouth.png");
        u_head = uh.getImage();
        ImageIcon dh = new ImageIcon("resources/downmouth.png");
        d_head = dh.getImage();
        
        head = r_head; // default head for the snake
        
    }

    private void initGame() {

        dots = 3;

        for (int z = 0; z < dots; z++) {
            x[z] = 50 - z * 10;
            y[z] = 50;
        }
        
        locateApple();

        timer = new Timer(DELAY, this);
        timer.start();
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        
        
        doDrawing(g);
    }
    
    private void doDrawing(Graphics g) {
        
        if (inGame) {
        	
        	String score_msg = score + "";
            Font small = new Font("Comic Sans", Font.BOLD, 24);
            FontMetrics metr = getFontMetrics(small);

            g.setColor(Color.white);
            g.setFont(small);
            g.drawString(score_msg, (B_WIDTH - metr.stringWidth(score_msg)) / 2, (B_HEIGHT - metr.stringWidth(score_msg)) / 10);
            
            g.drawImage(bg, 0, 0, null);
            g.drawImage(apple, apple_x, apple_y, this);

            for (int z = 0; z < dots; z++) {
                if (z == 0) {
                    g.drawImage(head, x[z], y[z], this);
                } else {
                    g.drawImage(body, x[z], y[z], this);
                }
            }

            Toolkit.getDefaultToolkit().sync();

        } else {

            gameOver(g);
        }        
    }

    private void gameOver(Graphics g) {
        
        String msg = "Game Over";
        Font small = new Font("Comic Sans", Font.BOLD, 20);
        FontMetrics metr = getFontMetrics(small);

        g.setColor(Color.white);
        g.setFont(small);
        g.drawString(msg, (B_WIDTH - metr.stringWidth(msg)) / 2, B_HEIGHT / 2);
    }

    private void checkApple() {

        if ((x[0] == apple_x) && (y[0] == apple_y)) {
            dots++;
            locateApple();
            score++;
        }
    }

    private void move() {

        for (int z = dots; z > 0; z--) {
            x[z] = x[(z - 1)];
            y[z] = y[(z - 1)];
        }

        if (leftDirection) {
            x[0] -= DOT_SIZE;
            head = l_head;
        }

        if (rightDirection) {
            x[0] += DOT_SIZE;
            head = r_head;
        }

        if (upDirection) {
            y[0] -= DOT_SIZE;
            head = u_head;
        }

        if (downDirection) {
            y[0] += DOT_SIZE;
            head = d_head;
        }
    }

    private void checkCollision() {

        for (int z = dots; z > 0; z--) {

            if ((z > 4) && (x[0] == x[z]) && (y[0] == y[z])) {
                inGame = false;
            }
        }

        if (y[0] >= B_HEIGHT) {
            inGame = false;
        }

        if (y[0] < 0) {
            inGame = false;
        }

        if (x[0] >= B_WIDTH) {
            inGame = false;
        }

        if (x[0] < 0) {
            inGame = false;
        }
        
        if (!inGame) {
            timer.stop();
        }
    }

    private void locateApple() {
    	
    	
        int r = (int) (Math.random() * RAND_POS.nextInt(DOT_SIZE));
        apple_x = ((r * DOT_SIZE));

        r = (int) (Math.random() * RAND_POS.nextInt(DOT_SIZE));
        apple_y = ((r * DOT_SIZE));
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if (inGame) {
            checkApple();
            checkCollision();
            move();
        }

        repaint();
    }

    private class TAdapter extends KeyAdapter {

        @Override
        public void keyPressed(KeyEvent e) {

            int key = e.getKeyCode();

            if ((key == KeyEvent.VK_LEFT) && (!rightDirection)) {
                leftDirection = true;
                upDirection = false;
                downDirection = false;
            }

            if ((key == KeyEvent.VK_RIGHT) && (!leftDirection)) {
                rightDirection = true;
                upDirection = false;
                downDirection = false;
            }

            if ((key == KeyEvent.VK_UP) && (!downDirection)) {
                upDirection = true;
                rightDirection = false;
                leftDirection = false;
            }

            if ((key == KeyEvent.VK_DOWN) && (!upDirection)) {
                downDirection = true;
                rightDirection = false;
                leftDirection = false;
            }
        }
    }
}