package Sudoku;

/**
 * Celda va a representar cada casilla del sudoku.
 */

public class Celda {
	
	/**
	 * Atributos de la clase.
	 * @param correcto booleano que marca la casilla como correcto o incorrecto.
	 * @param valorDado valor que digita el usuario.
	 * valorBueno valor correcto del sudoku.
	 */
	private boolean correcto;
	private int valorDado; 
	private int valorBueno;
	private boolean valorBase;

	/**
	 * Constructor de la clase Celda.
	 * Inicializa una celda con valores predeterminados.
	 */
	public Celda() {
		this.setValorDado(0);     // Inicializa el valor dado en 0.
		this.setValorBueno(0);    // Inicializa el valor correcto en 0.
		this.setCorrecto(false);  // Inicializa la casilla como incorrecta.
		this.setValorBase(false); // Inicializa la casilla como incorrecta.
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
	 * @param correcto recibe el valor true o false para establecer el valor.
	 */
	public void setCorrecto(boolean correcto) {
		this.correcto = correcto;
	}

	
	public boolean isBase() {
		return valorBase;
	}

	
	public void setValorBase(boolean base) {
		this.valorBase = base;
	}
	
	/**
	 * Obtiene el valor ingresado por el usuario en la casilla.
	 * @return el valor ingresado por el usuario.
	 */
	public int getValorDado() {
		return valorDado;
	}

	/**
	 * Establece el valor ingresado por el usuario en la casilla y verifica si es correcto.
	 * Si el valor coincide con el valor bueno, se marca como correcto.
	 * @param valorDado el valor ingresado por el usuario.
	 */
	public void setValorDado(int valorDado) {
		if (valorDado == this.valorBueno) {
			correcto = true;
		}
		this.valorDado = valorDado;
	}

	/**
	 * Obtiene el valor correcto de la casilla en el sudoku.
	 * @return el valor correcto de la casilla.
	 */
	public int getValorBueno() {
		return valorBueno;
	}

	/**
	 * Establece el valor correcto de la casilla en el sudoku.
	 * @param valorBueno el valor correcto de la casilla.
	 */
	public void setValorBueno(int valorBueno) {
		this.valorBueno = valorBueno;
	}
	
	/**
	 * Verifica si la casilla está ocupada (tiene un valor ingresado por el usuario).
	 * @return true si la casilla está ocupada, false si está vacía.
	 */
	public boolean estaOcupado() {
		if (this.getValorDado() == 0) {
			return false;  // La casilla está vacía.
		} else {
			return true;   // La casilla está ocupada.
		}		
	}
}