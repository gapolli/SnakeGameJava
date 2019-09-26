package snakeGame;

import javax.swing.JFrame;

/** Classe responsável por iniciar o jogo. É chamada quando o botão 'Start' do menu
 * principal é clicado.
* @author Gustavo Adrien Polli
* @version 1.0
* @since Release 01 da aplicação
*/

public class Snake extends JFrame {

	/** Inicializa o jogo e indica a dificuldade escolhida.*/
    public Snake(int difficult) {  
        initUI(difficult);
    }
    
    /** Inicia a interface gráfica do jogo, instanciando um novo 'Board' e configurando
     * titulo, posição na tela, redimensionamento e comportamento ao fechar. */ 
    private void initUI(int difficult) {
        
        add(new Board(difficult));
        
        setResizable(false);
        pack();
        
        setTitle("Snake");
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}
