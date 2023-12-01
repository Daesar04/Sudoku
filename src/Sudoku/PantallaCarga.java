package Sudoku;

/**
 * Interfaz PantallaCarga que define los métodos para gestionar una pantalla de carga.
 * Esta interfaz puede ser implementada por clases que requieran una funcionalidad de pantalla de carga con progreso actualizable y transición a otra interfaz al completarse la carga.
 */
public interface PantallaCarga
{
    /**
     * Método para actualizar el progreso en la pantalla de carga.
     * Incrementa un contador que representa el progreso y actualiza una barra de progreso visualmente.
     * Una vez que el contador alcanza un valor específico, detiene el temporizador y programa otro temporizador para cambiar la interfaz.
     */
    public void actualizarProgreso();
    
    /**
     * Método para cambiar la interfaz una vez completada la carga.
     * Oculta la pantalla de carga actual y muestra una nueva interfaz, en este caso, la interfaz de selección de dificultad del juego Sudoku.
     * Antes de cambiar, realiza la creación de carpetas.
     */
    public void cambiarInterfaz();
}
