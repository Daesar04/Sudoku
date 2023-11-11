package Sudoku;

import java.util.Random;

/**
 * La clase Juego_Sudoku extiende de Tablero_Sudoku y gestiona las reglas y logica del juego Sudoku.
 */
public class Juego_Sudoku extends Tablero_Sudoku
{

    /**
     * Comprueba si el tablero de Sudoku esta resuelto.
     * @return true si el tablero esta completamente resuelto, false de lo contrario.
     */
    public boolean resuelto() 
    {
        for (int i = 0; i < 9; i++) 
        {
            for (int j = 0; j < 9; j++) 
            {
                if (!this.getTablero()[i][j].isCorrecto()) 
                {
                    return false;
                }
            }
        }
        return true;
    }
	
	
    /*public void posicionValida(int fila, int columna, int num)
    {

            if(this.getTablero()[fila][columna].getValorBueno() != num)
            {
                    this.getTablero()[fila][columna].setCorrecto(false);

            }else
            {
                    this.getTablero()[fila][columna].setCorrecto(true);
            }

    }*/
	
    /**
     * Verifica si el numero ingresado en una casilla, es valido en esa fila.
     * @param fila 		La fila en la que se encuentra la casilla.
     * @param columna 	La columna en la que se encuentra la casilla.
     * @param num 		El numero a verificar.
     * @return true 	Si el numero es valido en la fila, false de lo contrario.
     */
    public boolean posicionValidaFila(int fila, int columna, int num)
    {
        for (int j = 0; j < 9; j++) 
        {
            if (this.getTablero()[fila][j].getValorBueno() == num && j != columna) 
            {
                this.getTablero()[fila][columna].setCorrecto(false);
                return false;
            }
        }
        return true;
    }
	
    /**
     * Verifica si el numero ingresado en una casilla, es valido en esa columna.
     * @param fila 		La fila en la que se encuentra la casilla.
     * @param columna 	La columna en la que se encuentra la casilla.
     * @param num 		El numero a verificar.
     * @return true 	Si el numero es valido en la columna, false de lo contrario.
     */
    public boolean posicionValidaColumna(int fila, int columna, int num) 
    {
        for (int i = 0; i < 9; i++) 
        {
            if (this.getTablero()[i][columna].getValorBueno() == num && i != fila) 
            {
                this.getTablero()[fila][columna].setCorrecto(false);
                return false;
            }
        }
        return true;
    }
	
    /**
     * Verifica si el numero ingresado en una casilla de 3x3 es valido.
     * @param fila 		La fila en la que se encuentra la casilla.
     * @param columna 	La columna en la que se encuentra la casilla.
     * @param num 		El numero a verificar.
     * @return true 	Si el numero es valido en la region 3x3, false de lo contrario.
     */
    public boolean posicionValida3x3(int fila, int columna, int num) 
    {
        int cuadranteFila = ((fila) / 3) * 3;
        int cuadranteColumna = ((columna) / 3) * 3;
        for (int i = cuadranteFila; i < cuadranteFila + 3; i++) 
        {
            for (int j = cuadranteColumna; j < cuadranteColumna + 3; j++) 
            {
                if (getTablero()[i][j].getValorBueno() == num && i != fila && j != columna) 
                {
                    return false;
                }
            }
        }
        return true;
    }
	
    /**
     * Genera un nuevo tablero de Sudoku aleatorio.
     */
    public void generarSudoku() 
    {
        Random random = new Random();
        int cont = 0;
        do {
            if(cont > 0)
            {
                    Juego_Sudoku aux = new Juego_Sudoku();
                    this.setTablero(aux.getTablero());
            }

            for (int i = 0; i < 30; i++) 
            {
                int fila = random.nextInt(9);
                int columna = random.nextInt(9);
                int numero = random.nextInt(9) + 1;

                if (this.getTablero()[fila][columna].getValorBueno() == 0 && posicionValidaFila(fila, columna, numero) && posicionValidaColumna(fila, columna, numero) && posicionValida3x3(fila, columna, numero)) 
                {
                    getTablero()[fila][columna].setValorBueno(numero);
                    getTablero()[fila][columna].setValorDado(numero);
                    getTablero()[fila][columna].setValorBase(true);
                } else {
                    i--;
                }
            }
            cont ++;
        } while (!resolverSudoku());
        imprimirTableroResuelto();
        imprimirTableroDado();
    }
	
    /**
     * Resuelve el Sudoku recursivamente utilizando la tecnica de "backtracking".
     * @return 	true si el Sudoku se resuelve, false si no se puede resolver.
     */
    public boolean resolverSudoku() {
        for (int fila = 0; fila < 9; fila++) {
            for (int columna = 0; columna < 9; columna++) {
                if (this.getTablero()[fila][columna].getValorBueno() == 0) {
                    for (int numero = 1; numero < 10; numero++) {
                        if (posicionValidaFila(fila, columna, numero) && posicionValidaColumna(fila, columna, numero) && posicionValida3x3(fila, columna, numero)) {
                                this.getTablero()[fila][columna].setValorBueno(numero);

                            if (resolverSudoku()) {
                                return true;
                            } else {
                                this.getTablero()[fila][columna].setValorBueno(0);
                            }
                        }
                    }
                    return false;
                }
            }
        }
        return true;
    }
	
    /*public boolean hacerMovimiento(int fila, int columna, int num)
    {
            posicionValida(fila, columna, num);
            this.getTablero()[fila][columna].setValorDado(num);

            return false;
    }*/

    
    /**
     * Genera un nuevo tablero de Sudoku con la dificultad especificada.
     * @param dificultadSeleccionada 	La dificultad seleccionada para el nuevo Sudoku.
     */
    void generarSudokuConDificultad(String dificultadSeleccionada) {
        //throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
        int dificultad = 0;
        
        if(dificultadSeleccionada == "Facil")
        {
            dificultad = 37;
        } 
        else if(dificultadSeleccionada == "Medio")
        {
            dificultad = 30;
        } 
        else if(dificultadSeleccionada == "Dificil")
        {
            dificultad = 23;
        }
        
        Random random = new Random();
        int cont = 0;
        do {
            if(cont > 0)
            {
                Juego_Sudoku aux = new Juego_Sudoku();
                this.setTablero(aux.getTablero());
            }

            for (int i = 0; i < dificultad; i++) 
            {
                int fila = random.nextInt(9);
                int columna = random.nextInt(9);
                int numero = random.nextInt(9) + 1;

                if (this.getTablero()[fila][columna].getValorBueno() == 0 && posicionValidaFila(fila, columna, numero) && posicionValidaColumna(fila, columna, numero) && posicionValida3x3(fila, columna, numero)) 
                {
                    getTablero()[fila][columna].setValorBueno(numero);
                    getTablero()[fila][columna].setValorDado(numero);
                    getTablero()[fila][columna].setValorBase(true);
                } else {
                    i--;
                }
            }
            cont ++;
        } while (!resolverSudoku());
        imprimirTableroResuelto();
        imprimirTableroDado();
    }
}