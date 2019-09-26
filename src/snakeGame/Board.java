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
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.Timer;

/** Classe que inicia o 'tabuleiro' do jogo. Desenha a interface gráfica e implementa as funcionalidades
 * do jogo.
* @author Mateus Pim Santos
* @version 1.0
* @since Release 01 da aplicação
*/

public class Board extends JPanel implements ActionListener {

//    private final int B_WIDTH = (int) (Toolkit.getDefaultToolkit().getScreenSize().getWidth() * 0.4);
	private final int B_WIDTH = 845;
    private final int B_HEIGHT = (int) (Toolkit.getDefaultToolkit().getScreenSize().getHeight() * 0.5);
    private final int DOT_SIZE = 25; // the same pixel size as image
    private final int ALL_DOTS = (int) ((B_WIDTH*B_HEIGHT)/(Math.pow(DOT_SIZE, 2)));
    private int DELAY = 150;
    
    private JPanel panelTitle;
    
    private final int RAND_POS = 25;
    
    private SecureRandom rand = new SecureRandom();
        
    private final int x[] = new int[ALL_DOTS];
    private final int y[] = new int[ALL_DOTS];

    private int dots;
    private int apple_x = B_WIDTH / 2;
    private int apple_y = B_HEIGHT / 2;
    private int score = 0;
    
    private String difficulty = "Easy";
    
    private boolean leftDirection = false;
    private boolean rightDirection = true;
    private boolean upDirection = false;
    private boolean downDirection = false;
    private boolean inGame = true;

    private Timer timer;

    private Image title;
    private Image bg;
    private Image apple;
    private Image head;
    private Image body;
    private Image l_head;
    private Image r_head;
    private Image u_head;
    private Image d_head;
    
    /** Inicializa o 'tabuleiro' e implementa a dificuldade. */
    public Board(int difficult) {
    	DELAY = DELAY / difficult;    
    	setDifficulty(difficult);
        initBoard();
        
        System.out.printf("Screen size: ( %d , %d )\n" , B_WIDTH , B_HEIGHT);  
    }
    
    /** Verifica a dificuldade a partir de um int recebido como parâmetro. */
    private void setDifficulty(int d) {
    	switch (d) {
    	case 1 : difficulty = "Easy"; break;
    	case 2 : difficulty = "Medium"; break;
    	case 3 : difficulty = "Hard"; break;
    	case 4 : difficulty = "Impossible"; break;
    	}
    }
    
    /** Método que desenha e carrega os recursos do 'tabuleiro' onde o jogo acontece.*/
    private void initBoard() {

        addKeyListener(new TAdapter());
        setBackground(Color.black);
        setFocusable(true);
       
        this.panelTitle = new JPanel();
		this.panelTitle.setBackground(Color.cyan);
		this.panelTitle.setBorder(BorderFactory.createEtchedBorder());
		
		inGame = true;
		
        setPreferredSize(new Dimension(B_WIDTH, B_HEIGHT));
        loadImages();
        initGame();
    }
    
    /** Método que carrega as imagens utilizadas dentro do jogo.*/
    private void loadImages() {
    	
    	//Apple image
        ImageIcon iia = new ImageIcon("resources/apple.png");
        apple = iia.getImage();
        
        // title image
        title = Toolkit.getDefaultToolkit().createImage("resources/snaketitle.jpg");
        
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
    
    /** Método que inicia propriamente o jogo.*/
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
    	g.drawImage(bg, 0, 0, null);
    	g.drawImage(title, 0, 0, null);
    	
        if (inGame) {        	
        	statistics(g);
            
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
    
    /** Mostra as estátiscias do jogo na tela. */
    private void statistics(Graphics g){
    	String score_msg = "Difficulty: " + difficulty + " Score: " + score;
        Font small = new Font("Comic Sans", Font.BOLD, 24);
        //FontMetrics metr = getFontMetrics(small);

        g.setColor(Color.white);
        g.setFont(small);
        g.drawString(score_msg, 20, 35);
    }

	/** Método responsável por mostrar uma mensagem de 'fim de jogo' na tela.*/
    private void gameOver(Graphics g) {
        
        String msg = "Game Over";
        String msg2 = "Press \"R\" to retry or \"M\" to go to Main Menu";
        
        Font big = new Font("Comic Sans", Font.BOLD, 60);
        Font small = new Font("Comic Sans", Font.BOLD, 20);
        FontMetrics metr = getFontMetrics(big);
        FontMetrics metr2 = getFontMetrics(small);

        g.setColor(Color.white);
        g.setFont(big);
        
        g.drawString(msg, (B_WIDTH - metr.stringWidth(msg)) / 2, B_HEIGHT / 2);
        
        g.setFont(small);
        g.drawString(msg2, (B_WIDTH - metr2.stringWidth(msg2)) / 2, (B_HEIGHT / 2) + 50);
    }
    
    /** Checa se a maçã foi 'capturada' pela cobra. */
    private void checkApple() {
    	        
    	if (checkIfInRange(x[0],y[0])) {
            dots++;
            locateApple();
            score++;
        }
    }
    
    /** Checa se a maçã está posicionada em um local válido */
    private boolean checkIfInRange(int x, int y) {
    	
    	if (x >= apple_x - 15 && x <= apple_x + 15)
    		if (y >= apple_y - 15 && y <= apple_y + 15)
    			return true;
    	
    	return false;
    }
    
    /** Define aleatoriamente o local onde a maçã aparecerá */
    private void locateApple() {
    	
        int x = (int) rand.nextInt(RAND_POS);
        //while ((x % 25 != 0) && (x > B_HEIGHT))
        while(((x * DOT_SIZE) > B_WIDTH))
        	x = (int) rand.nextInt(RAND_POS); 

        int y = (int) rand.nextInt(RAND_POS);
        //while ((y % 25 != 0) && (x > B_WIDTH))
        while( ((y * DOT_SIZE) > B_HEIGHT) || ((y * DOT_SIZE) < title.getHeight(null) + 20) )
        	y = (int) rand.nextInt(RAND_POS);
        
        apple_x = ((x * DOT_SIZE));
        apple_y = ((y * DOT_SIZE) - 4);
        
        System.out.printf("Apple Location: ( %d , %d )\n" , apple_x , apple_y); 
    }
    
    /** Decreta o funcionamento da movimentação da cobra. */
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
    
    /** Verifica quando há colisão da cobra. */
    private void checkCollision() {

        for (int z = dots; z > 0; z--) {

            if ((z > 4) && (x[0] == x[z]) && (y[0] == y[z])) {
                inGame = false;
            }
        }

        if (y[0] >= B_HEIGHT) {
        	y[0] = title.getHeight(null) + 10;
//        	y[0] = 0;
        }
        
//        if (y[0] < 0) {
//        	y[0] = B_HEIGHT;
//        }	

        if (y[0] < title.getHeight(null) + 10) {
        	y[0] = B_HEIGHT;
        }

        if (x[0] >= B_WIDTH) {
        	x[0] = 0;
        }

        if (x[0] < 0) {
        	x[0] = B_WIDTH;
        }
        
        if (!inGame) {
            timer.stop();
        }
    }


    /** Fecha o jogo */
    private void close() {
    	this.setVisible(false);
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
    
    
    /** Classe que mapeia as teclas do teclado e define sua função.
    * @author Mateus Pim Santos
    * @version 1.0
    * @since Release 01 da aplicação
    */
    private class TAdapter extends KeyAdapter {
    	
    	/** Define o comportamento de cada tecla e o que acontece quando a mesma é pressionada */
        @Override
        public void keyPressed(KeyEvent e) {

            int key = e.getKeyCode();
            
            if ((key == KeyEvent.VK_R) && (!inGame)) {
            	initBoard();
            }
            
            if ((key == KeyEvent.VK_M) && (!inGame)) {
            	close();
    			MainMenu main = new MainMenu("Snake");
    			main.setVisible(true);
            }
            
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