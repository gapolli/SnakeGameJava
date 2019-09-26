//StartGame.java

package snakeGame;

/** Classe main que far� a chamada do menu da aplica��o, para que o jogo possa ser iniciado.
* @author Max Lucio Martins de Assis
* @version 1.0
* @since Release 01 da aplica��o
*/

import java.awt.HeadlessException;
import javax.swing.JOptionPane;

public class StartGame {

	/** M�todo main que instancia um objeto menu e o torna vis�vel na tela */
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
