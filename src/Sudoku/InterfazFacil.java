package Sudoku;

/**
 * Clase InterfazFacil, una subclase de InterfazDificultadAbstracta que representa una interfaz con dificultad fácil.
 * Esta clase define específicamente la dificultad del juego como "Facil".
 */
public class InterfazFacil extends InterfazDificultadAbstracta 
{
    /**
     * Constructor de la clase InterfazFacil.
     * Establece el nombre de la dificultad como "Facil".
     */
    public InterfazFacil() 
    {
        nombre = "Facil";
    }

    /**
     * Sobrescribe el método establecerDificultad de la clase padre InterfazDificultadAbstracta.
     * Este método crea una nueva instancia de la interfaz principal del juego y la hace visible,
     * estableciendo así la dificultad del juego a fácil.
     */
    @Override
    public void establecerDificultad() 
    {
        Interfaz.crearNuevaInstancia().setVisible(true);
    }
}
