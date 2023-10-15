package Sudoku;

public class ClaseMainSudoku 
{

	public static void main(String[] args) 
	{
		Ventana_Sudoku v1 = new Ventana_Sudoku();
		v1.setVisible(true);
		
		Juego_Sudoku juego = new Juego_Sudoku();
		juego.generarSudoku();
	}
}
