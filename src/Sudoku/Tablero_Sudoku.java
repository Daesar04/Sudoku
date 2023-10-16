package Sudoku;

/**
 * clase tablero_sudoku
 */
public class Tablero_Sudoku extends Celda{
	
	public static final String ANSI_RED = "\u001B[31m"; //Declaracion del texto en rojo
	public static final String ANSI_RESET = "\u001B[0m"; //Declaracion del texto por defecto
	
	private Celda[][] tablero;

	/**
	 * Constructor de la clase Tablero_Sudoku que inicializa el tablero con objetos Celda.
	 */
	public Tablero_Sudoku()
	{
		this.tablero = new Celda[9][9];
		for (int i = 0; i < 9; i++) {
	        for (int j = 0; j < 9; j++) {
	            this.tablero[i][j] = new Celda(); // Inicializa cada celda con un nuevo objeto Celda
	        }
	    }
	}
	
	/**
	 * Obtiene el tablero del Sudoku.
	 * @return el tablero del Sudoku como una matriz de celdas.
	 */
	public Celda[][] getTablero() {
		return tablero;
	}

	/**
	 * Establece el tablero del Sudoku.
	 * @param tablero la matriz de celdas que representa el tablero del Sudoku.
	 */
	public void setTablero(Celda[][] tablero) {
		this.tablero = tablero;
	}

	/**
	 * Comprueba si el Sudoku está resuelto correctamente.
	 * @return true si el Sudoku está resuelto correctamente, false de lo contrario.
	 */
	public boolean sudokuResuelto()
	{
		for(int i = 0; i < 9; i++)
		{
			for(int j = 0; j < 9; j++)
			{
				if (tablero[i][j].isCorrecto() != true)
				{
					return false;
				}
			}
		}
		return true;
	}	

	/**
	 * Imprime el tablero del Sudoku con los valores dados por el usuario, 
	 * resaltando en rojo los valores incorrectos.
	 */
	public void imprimirTableroDado()
	{
		for(int i = 0; i < 9; i++)
		{
			for(int j = 0; j < 9; j++)
			{			
				if(tablero[i][j].isCorrecto() != true)
				{
					//pintar en rojo, esta mal
					System.out.print(ANSI_RED + tablero[i][j].getValorDado() + ANSI_RESET  + "  ");
								
				} else
				{
					//pintar en negro, esta bien
					System.out.print(tablero[i][j].getValorDado()  + "  ");
				}
			}
			System.out.println("");
		}
		System.out.println("\n\n");
	}
	
	/**
	 * Imprime el tablero del Sudoku con los valores correctos.
	 */
	public void imprimirTableroResuelto()
	{
		for(int i = 0; i < 9; i++)
		{
			for(int j = 0; j < 9; j++)
			{
				System.out.print(tablero[i][j].getValorBueno() + "  ");
			}
			System.out.println("");
		}
		System.out.println("\n\n");
	}
	
}