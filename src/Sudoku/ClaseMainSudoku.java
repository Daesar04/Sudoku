package Sudoku;
import java.awt.EventQueue;
import javax.swing.*;

/**
 * @author David Escribano - Javier Sáez.
 */

public class ClaseMainSudoku 
{
	/**
     * Punto de entrada para ejecutar el programa.
     * @param args 		Los argumentos de la línea de comandos.
     */
	public static void main(String[] args) 
	{
		
		Juego_Sudoku juego = new Juego_Sudoku();
		juego.generarSudoku();
		
		//Ventana_Sudoku v1 = new Ventana_Sudoku();
		//v1.setVisible(true);
		/*
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					interfaz frame = new interfaz();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		/*
		Juego_Sudoku juego = new Juego_Sudoku();
		juego.generarSudoku();
		SwingUtilities.invokeLater(() -> {
			Ventana_Sudoku sudokuGUI = new Ventana_Sudoku(); // Inicializar la interfaz gráfica
		});*/
	}
}
