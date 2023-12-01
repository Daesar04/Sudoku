package Sudoku;

/**
 * Clase InterfazDificil, una subclase de InterfazDificultadAbstracta que representa una interfaz con dificultad difícil.
 * Esta clase define específicamente la dificultad del juego como "Dificil".
 */
public class InterfazDificil extends InterfazDificultadAbstracta 
{
	 /**
     * Constructor de la clase InterfazDificil.
     * Establece el nombre de la dificultad como "Dificil".
     */
    public InterfazDificil() 
    {
        nombre = "Dificil";
    }

    /**
     * Sobrescribe el método establecerDificultad de la clase padre InterfazDificultadAbstracta.
     * Este método crea una nueva instancia de la interfaz principal del juego y la hace visible,
     * estableciendo así la dificultad del juego a difícil.
     */
    @Override
    public void establecerDificultad() 
    {
        Interfaz.crearNuevaInstancia().setVisible(true);
    }
}
