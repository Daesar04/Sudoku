package Sudoku;

/**
 * Celda representa cada casilla del sudoku, con sus respectivos valores e información.
 */
public class Celda {
	
	/*
	 * Atributos de la clase.
	 * @param correcto 		Indica si la casilla tiene el valor correcto o no.
	 * @param valorDado 	Valor ingresado por el usuario.
	 * @param valorBueno 	Valor correcto del sudoku para la casilla.
	 * @param valorBase 	Indica si el valor de la celda es parte del sudoku original o es una inserción del usuario.
	 */
	
	/** Booleano que indica si la casilla es correcta o no. */
    private boolean correcto;
    
    /** Valor ingresado por el usuario en la celda. */
    private int valorDado;
    
    /** Valor correcto de la casilla en el sudoku. */
    private int valorBueno;
    
    /** Indica si el valor de la celda es parte de la configuración inicial. */
    private boolean valorBase;

	/**
	 * Constructor de la clase Celda.
	 * Inicializa una celda con valores predeterminados.
	 */
	public Celda() {
		this.setValorDado(0);     // Inicializa el valor dado en 0.
		this.setValorBueno(0);    // Inicializa el valor correcto en 0.
		this.setCorrecto(false);  // Inicializa la casilla como incorrecta.
		this.setValorBase(false); // Inicializa la casilla como parte de la configuración inicial.
	}

	/**
	 * Devuelve si el valor ingresado por el usuario es correcto o no.
	 * @return true si el valor ingresado es correcto, false si es incorrecto.
	 */
	public boolean isCorrecto() {
		return correcto;
	}

	/**
	 * Establece si el valor ingresado por el usuario es correcto o no.
	 * @param correcto 		True si es correcto, false si es incorrecto.
	 */
	public void setCorrecto(boolean correcto) {
		this.correcto = correcto;
	}

	/**
	 * Verifica si la celda es parte de la configuración inicial del sudoku.
	 * @return true si la celda es parte de la configuración inicial, false si fue modificada por el usuario.
	 */
	public boolean isBase() {
		return valorBase;
	}

	/**
	 * Establece si la celda es parte de la configuración inicial del sudoku.
	 * @param base 		True si es parte de la configuración inicial, false si no lo es.
	 */
	public void setValorBase(boolean base) {
		this.valorBase = base;
	}

	/**
	 * Obtiene el valor ingresado por el usuario en la celda.
	 * @return el valor ingresado por el usuario.
	 */
	public int getValorDado() {
		return valorDado;
	}

	/**
	 * Establece el valor ingresado por el usuario en la celda y verifica si es correcto.
	 * Si el valor coincide con el valor correcto, se marca como correcto.
	 * @param valorDado 	El valor ingresado por el usuario.
	 */
	public void setValorDado(int valorDado) {
		if (valorDado == this.valorBueno) {
			correcto = true;
		}
		this.valorDado = valorDado;
	}

	/**
	 * Obtiene el valor correcto de la celda en el sudoku.
	 * @return el valor correcto de la celda.
	 */
	public int getValorBueno() {
		return valorBueno;
	}

	/**
	 * Establece el valor correcto de la celda en el sudoku.
	 * @param valorBueno 	El valor correcto de la celda.
	 */
	public void setValorBueno(int valorBueno) {
		this.valorBueno = valorBueno;
	}

	/**
	 * Verifica si la celda está ocupada (tiene un valor ingresado por el usuario).
	 * @return true si la celda está ocupada, false si está vacía.
	 */
	public boolean estaOcupado() {
		if (this.getValorDado() == 0) {
			return false;  // La celda está vacía.
		} else {
			return true;   // La celda está ocupada.
		}		
	}
}
