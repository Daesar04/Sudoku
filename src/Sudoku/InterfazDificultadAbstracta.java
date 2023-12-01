package Sudoku;

/**
 * Clase abstracta InterfazDificultadAbstracta que define una estructura para las distintas interfaces de dificultad del juego.
 * Esta clase se utiliza como base para crear diferentes niveles de dificultad en el juego.
 */
public abstract class InterfazDificultadAbstracta 
{
    /**
     * Nombre de la dificultad, protegido para permitir el acceso sólo a las subclases.
     */
    protected String nombre;

    /**
     * Método abstracto para establecer la dificultad del juego.
     * Las subclases deben proporcionar una implementación concreta para este método.
     */
    public abstract void establecerDificultad();

    /**
     * Obtiene el nombre de la dificultad.
     * @return El nombre de la dificultad.
     */
    public String getNombre() 
    {
        return nombre;
    }
}
