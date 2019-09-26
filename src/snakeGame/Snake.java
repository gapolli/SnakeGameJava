package snakeGame;

import javax.swing.JFrame;

/** Classe respons�vel por iniciar o jogo. � chamada quando o bot�o 'Start' do menu
 * principal � clicado.
* @author Gustavo Adrien Polli
* @version 1.0
* @since Release 01 da aplica��o
*/

public class Snake extends JFrame {

	/** Inicializa o jogo e indica a dificuldade escolhida.*/
    public Snake(int difficult) {  
        initUI(difficult);
    }
    
    /** Inicia a interface gr�fica do jogo, instanciando um novo 'Board' e configurando
     * titulo, posi��o na tela, redimensionamento e comportamento ao fechar. */ 
    private void initUI(int difficult) {
        
        add(new Board(difficult));
        
        setResizable(false);
        pack();
        
        setTitle("Snake");
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}
