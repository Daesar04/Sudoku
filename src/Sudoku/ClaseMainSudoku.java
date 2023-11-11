package Sudoku;

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

        Interfaz interfaz = new Interfaz();
                        
        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                new InterfazDificultad(interfaz).setVisible(true);
            }
        });
    }
}
