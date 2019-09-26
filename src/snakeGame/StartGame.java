//StartGame.java

package snakeGame;

/** Classe main que fará a chamada do menu da aplicação, para que o jogo possa ser iniciado.
* @author Max Lucio Martins de Assis
* @version 1.0
* @since Release 01 da aplicação
*/

import java.awt.HeadlessException;
import javax.swing.JOptionPane;

public class StartGame {

	/** Método main que instancia um objeto menu e o torna visível na tela */
	public static void main(String[] args) {		
		try
		{
			MainMenu main = new MainMenu(" Snake Game ");
			main.setVisible(true);
		}
		catch(HeadlessException exception) 
		{
			JOptionPane.showMessageDialog(null, " Erro ao executar o programa! "+ exception.toString()) ;
		}
		catch(Exception exception) 
		{
			JOptionPane.showMessageDialog(null, " Erro ao executar o programa! "+ exception.toString()) ;
		}
	}
	

}
