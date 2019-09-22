//StartGame.java


package snakeGame;

import java.awt.HeadlessException;
import java.io.File;
import java.util.Scanner;

import javax.print.attribute.standard.Media;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import java.net.*;
import java.io.*;
import java.util.*;
import java.net.URL;

public class StartGame {

	public static void main(String[] args) {
		
		try 
		{
			MainMenu main = new MainMenu(" Snake Game ");
			main.setVisible(true);
			
			Board board = new Board(null); 
			board.setVisible(true);
			
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
