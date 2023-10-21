package Sudoku;
import java.awt.EventQueue;

import javax.swing.*;

/**
 * @author David Escribano - Javier Sáez.
 */

public class ClaseMainSudoku 
{

	public static void main(String[] args) 
	{
		//Ventana_Sudoku v1 = new Ventana_Sudoku();
		//v1.setVisible(true);
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
