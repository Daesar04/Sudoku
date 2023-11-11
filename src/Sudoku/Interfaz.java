package Sudoku;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import static java.util.Locale.filter;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.AbstractDocument;
import javax.swing.text.BadLocationException;
import javax.swing.text.Document;
import javax.swing.text.DocumentFilter;
import javax.swing.text.AttributeSet;



/**
 * Esta clase representa la interfaz grafica para un juego de Sudoku.
 */

public class Interfaz extends JFrame {

	/** El tablero de Sudoku asociado a la interfaz. */
	private Juego_Sudoku tablero;

	/** La interfaz de dificultad asociada a la interfaz principal. */
	private InterfazDificultad iDificultad;

	/** Boton para cargar una partida guardada. */
	private JButton botonCargarPartida;

	/** Boton para guardar la partida actual. */
	private JButton botonGuardarPartida;

	/** Boton para iniciar una nueva partida. */
	private JButton botonNuevaPartida;

	/** Boton para generar un nuevo tablero. */
	private JButton botonNuevoTablero;

	/** Boton para mostrar la solucion del Sudoku. */
	private JButton botonSolucionar;

	/** Boton para reiniciar la partida actual. */
	private JButton botonReiniciarPartida;

	/** Panel que contiene los botones de control del juego. */
	private JPanel panelBotones;

	/** Panel que muestra los creditos del juego. */
	private JPanel panelCreditos;

	/** Panel principal que contiene todos los componentes de la interfaz. */
	private JPanel panelPrincipal;

	/** Panel que contiene el tablero de Sudoku. */
	private JPanel panelTablero;

	/** Panel que muestra el titulo del juego. */
	private JPanel panelTitulo;

	/** Etiqueta que muestra el titulo del juego. */
	private JLabel titulo;

	/** Etiqueta que muestra los creditos del juego. */
	private JLabel creditos;



	/**
	 * Constructor de la clase Interfaz.
	 */
	public Interfaz() {

		// Inicializa el tablero de Sudoku y la interfaz de dificultad
		tablero = new Juego_Sudoku();
		iDificultad = new InterfazDificultad(this);
		setTitle("SUDOKU : 'Nivel " + iDificultad.getDificultadSeleccionada() + "'");
		tablero.generarSudokuConDificultad(iDificultad.getDificultadSeleccionada());

		// Inicializa los componentes de la interfaz grafica
		initComponents();
		setLocationRelativeTo(null);
		titulo.setAlignmentX(Component.CENTER_ALIGNMENT);
		panelTablero.setMinimumSize(new Dimension(692, 692));
		panelTablero.setMaximumSize(new Dimension(692, 692));
		panelTablero.setPreferredSize(new Dimension(692, 692));
		panelTablero.setLayout(new GridLayout(9, 9));

		// Rellena el tablero de Sudoku con los valores iniciales
		rellenarTablero();
	}


	/**
	 * Valida el valor de entrada en un JTextField y actualiza el tablero de Sudoku.
	 *
	 * @param textField 	El JTextField a validar.
	 * @param row       	El indice de fila de la celda.
	 * @param col       	El indice de columna de la celda.
	 */
	public void validateInput(JTextField textField, int row, int col) 
	{
		String inputText = textField.getText();

		// Verificar si la cadena de entrada esta vacia
		if (inputText.isEmpty()) 
		{
			tablero.getTablero()[row][col].setValorDado(0); // Establecer el valor dado a 0 si la cadena esta vacia
		} else {
			try 
			{
				int enteredValue = Integer.parseInt(inputText);
				int correctValue = tablero.getTablero()[row][col].getValorBueno();

				if (enteredValue != correctValue) 
				{
					textField.setForeground(Color.RED); // Cambia el color del texto a rojo
				} else 
				{
					textField.setForeground(Color.BLACK); // Cambia el color del texto a negro si es correcto
				}

				tablero.getTablero()[row][col].setValorDado(enteredValue);
			} 
			catch (NumberFormatException e) 
			{
				// Manejar la excepcion de formato si la entrada no es un numero valido
				textField.setForeground(Color.BLACK);
				tablero.getTablero()[row][col].setValorDado(0); // Establecer el valor dado a 0 en caso de error
			}
		}
	}

	/**
	 * Genera un nuevo Sudoku con la dificultad seleccionada.
	 */
	public void generarNuevoSudokuConDificultad() 
	{
		tablero = new Juego_Sudoku();
		tablero.generarSudokuConDificultad(iDificultad.getDificultadSeleccionada());

		updateInterfaz();
	}

	/**
	 * Rellena el tablero de Sudoku con campos de texto y sus valores correspondientes.
	 */
	public void rellenarTablero()
	{
		DocumentFilter filter = new DocumentFilter() {
			@Override
			public void replace(FilterBypass fb, int offset, int length, String text, AttributeSet attrs) throws BadLocationException {
				Document doc = fb.getDocument();
				String currentText = doc.getText(0, doc.getLength());
				currentText = currentText.substring(0, offset) + text + currentText.substring(offset + length);
				if (currentText.length() <= 1 && currentText.matches("\\d?")) {
					super.replace(fb, offset, length, text, attrs);
				}
			}
		};
		// Vuelve a llenar la interfaz con los numeros del nuevo Sudoku
		for (int i = 0; i < 9; i++) 
		{
			for (int j = 0; j < 9; j++) 
			{
				final int row = i; // Definir 'row' como final
				final int col = j; // Definir 'col' como final

				JTextField textField = new JTextField();
				int value = tablero.getTablero()[i][j].getValorDado();
				if (value != 0) 
				{
					textField.setText(String.valueOf(value));
					validateInput(textField, row, col);
					if (tablero.getTablero()[i][j].isBase()) 
					{
						textField.setEditable(false);
					} 
				}
				textField.setHorizontalAlignment(SwingConstants.CENTER);
				((AbstractDocument) textField.getDocument()).setDocumentFilter(filter);
				// Agrega un DocumentListener al JTextField
				textField.getDocument().addDocumentListener(new DocumentListener() 
				{
					@Override
					public void insertUpdate(DocumentEvent e) 
					{
						validateInput(textField, row, col);
					}

					@Override
					public void removeUpdate(DocumentEvent e) 
					{
						validateInput(textField, row, col);
					}

					@Override
					public void changedUpdate(DocumentEvent e) 
					{
						validateInput(textField, row, col);
					}
				});
				panelTablero.add(textField);
			}
		}

		panelTablero.revalidate(); // Vuelve a validar el panelTablero para que se muestren los nuevos componentes
		panelTablero.repaint(); // Repinta el panelTablero para que se muestren los nuevos componentes
	}

	/**
	 * Actualiza la interfaz grafica con un nuevo tablero de Sudoku.
	 */
	public void updateInterfaz() 
	{
		panelTablero.removeAll(); // Elimina los componentes anteriores del panelTablero
		rellenarTablero();
	}

	/**
	 * Valida los valores de entrada cargados en el tablero.
	 */
	public void validarValoresCargados() {
		Component[] components = panelTablero.getComponents();
		for (Component component : components) {
			if (component instanceof JTextField) {
				JTextField textField = (JTextField) component;
				int row = (panelTablero.getComponentZOrder(textField)) / 9;
				int col = (panelTablero.getComponentZOrder(textField)) % 9;
				validateInput(textField, row, col);
			}
		}
	}


	/**
	 * Inicializa los componentes graficos de la interfaz de usuario.
	 */
	@SuppressWarnings("unchecked")
	// <editor-fold defaultstate="collapsed" desc="Generated Code">                          
	private void initComponents() {

		panelPrincipal = new JPanel();
		panelTitulo = new JPanel();
		titulo = new JLabel();
		panelCreditos = new JPanel();
		creditos = new JLabel();
		panelTablero = new JPanel();
		panelBotones = new JPanel();
		botonNuevaPartida = new JButton();
		botonNuevoTablero = new JButton();
		botonReiniciarPartida = new JButton();
		botonGuardarPartida = new JButton();
		botonCargarPartida = new JButton();
		botonSolucionar = new JButton();

		setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
		setMinimumSize(new java.awt.Dimension(990, 800));

		panelPrincipal.setMaximumSize(new java.awt.Dimension(945, 762));
		panelPrincipal.setMinimumSize(new java.awt.Dimension(945, 762));
		panelPrincipal.setLayout(null);

		panelTitulo.setBackground(new java.awt.Color(102, 255, 153));

		titulo.setFont(new java.awt.Font("Dialog", 0, 36)); // NOI18N
		titulo.setForeground(new java.awt.Color(255, 51, 51));
		titulo.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
		titulo.setText("SUDOKU");

		javax.swing.GroupLayout panelTituloLayout = new javax.swing.GroupLayout(panelTitulo);
		panelTitulo.setLayout(panelTituloLayout);
		panelTituloLayout.setHorizontalGroup(
				panelTituloLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addComponent(titulo, javax.swing.GroupLayout.DEFAULT_SIZE, 280, Short.MAX_VALUE)
				);
		panelTituloLayout.setVerticalGroup(
				panelTituloLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addComponent(titulo, javax.swing.GroupLayout.DEFAULT_SIZE, 64, Short.MAX_VALUE)
				);

		panelPrincipal.add(panelTitulo);
		panelTitulo.setBounds(0, 0, 280, 64);

		panelCreditos.setBackground(new java.awt.Color(0, 0, 255));

		creditos.setFont(new java.awt.Font("Dialog", 0, 25)); // NOI18N
		creditos.setForeground(new java.awt.Color(255, 255, 255));
		creditos.setText("HECHO POR JAVIER SAEZ Y DAVID ESCRIBANO");

		javax.swing.GroupLayout panelCreditosLayout = new javax.swing.GroupLayout(panelCreditos);
		panelCreditos.setLayout(panelCreditosLayout);
		panelCreditosLayout.setHorizontalGroup(
				panelCreditosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(panelCreditosLayout.createSequentialGroup()
						.addContainerGap()
						.addComponent(creditos, javax.swing.GroupLayout.PREFERRED_SIZE, 681, javax.swing.GroupLayout.PREFERRED_SIZE)
						.addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
				);
		panelCreditosLayout.setVerticalGroup(
				panelCreditosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addComponent(creditos, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
				);

		panelPrincipal.add(panelCreditos);
		panelCreditos.setBounds(286, 0, 693, 64);

		panelTablero.setBackground(new java.awt.Color(255, 51, 51));
		panelTablero.setToolTipText("");
		panelTablero.setAutoscrolls(true);

		javax.swing.GroupLayout panelTableroLayout = new javax.swing.GroupLayout(panelTablero);
		panelTablero.setLayout(panelTableroLayout);
		panelTableroLayout.setHorizontalGroup(
				panelTableroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGap(0, 692, Short.MAX_VALUE)
				);
		panelTableroLayout.setVerticalGroup(
				panelTableroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGap(0, 692, Short.MAX_VALUE)
				);

		panelPrincipal.add(panelTablero);
		panelTablero.setBounds(0, 70, 692, 692);

		panelBotones.setBackground(new java.awt.Color(0, 0, 0));

		botonNuevaPartida.setFont(new java.awt.Font("Comic Sans MS", 1, 15)); // NOI18N
		botonNuevaPartida.setText("NUEVA PARTIDA");
		botonNuevaPartida.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
		botonNuevaPartida.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				botonNuevaPartidaActionPerformed(evt);
			}
		});

		botonNuevoTablero.setFont(new java.awt.Font("Comic Sans MS", 1, 15)); // NOI18N
		botonNuevoTablero.setText("NUEVO TABLERO");
		botonNuevoTablero.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
		botonNuevoTablero.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				botonNuevoTableroActionPerformed(evt);
			}
		});

		botonReiniciarPartida.setFont(new java.awt.Font("Comic Sans MS", 1, 15)); // NOI18N
		botonReiniciarPartida.setText("REINICIAR PARTIDA");
		botonReiniciarPartida.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
		botonReiniciarPartida.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				botonReiniciarPartidaActionPerformed(evt);
			}
		});

		botonGuardarPartida.setFont(new java.awt.Font("Comic Sans MS", 1, 15)); // NOI18N
		botonGuardarPartida.setText("GUARDAR PARTIDA");
		botonGuardarPartida.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
		botonGuardarPartida.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				botonGuardarPartidaActionPerformed(evt);
			}
		});

		botonCargarPartida.setFont(new java.awt.Font("Comic Sans MS", 1, 15)); // NOI18N
		botonCargarPartida.setText("CARGAR PARTIDA");
		botonCargarPartida.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
		botonCargarPartida.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				botonCargarPartidaActionPerformed(evt);
			}
		});

		botonSolucionar.setFont(new java.awt.Font("Comic Sans MS", 1, 15)); // NOI18N
		botonSolucionar.setText("SOLUCIONAR");
		botonSolucionar.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
		botonSolucionar.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				botonSolucionarActionPerformed(evt);
			}
		});

		javax.swing.GroupLayout panelBotonesLayout = new javax.swing.GroupLayout(panelBotones);
		panelBotones.setLayout(panelBotonesLayout);
		panelBotonesLayout.setHorizontalGroup(
				panelBotonesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(panelBotonesLayout.createSequentialGroup()
						.addContainerGap(27, Short.MAX_VALUE)
						.addGroup(panelBotonesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
								.addComponent(botonSolucionar, javax.swing.GroupLayout.PREFERRED_SIZE, 226, javax.swing.GroupLayout.PREFERRED_SIZE)
								.addComponent(botonCargarPartida, javax.swing.GroupLayout.PREFERRED_SIZE, 226, javax.swing.GroupLayout.PREFERRED_SIZE)
								.addComponent(botonGuardarPartida, javax.swing.GroupLayout.PREFERRED_SIZE, 226, javax.swing.GroupLayout.PREFERRED_SIZE)
								.addComponent(botonReiniciarPartida, javax.swing.GroupLayout.PREFERRED_SIZE, 226, javax.swing.GroupLayout.PREFERRED_SIZE)
								.addComponent(botonNuevoTablero, javax.swing.GroupLayout.PREFERRED_SIZE, 226, javax.swing.GroupLayout.PREFERRED_SIZE)
								.addComponent(botonNuevaPartida, javax.swing.GroupLayout.PREFERRED_SIZE, 226, javax.swing.GroupLayout.PREFERRED_SIZE))
						.addContainerGap(27, Short.MAX_VALUE))
				);
		panelBotonesLayout.setVerticalGroup(
				panelBotonesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(panelBotonesLayout.createSequentialGroup()
						.addGap(101, 101, 101)
						.addComponent(botonNuevaPartida, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
						.addGap(38, 38, 38)
						.addComponent(botonNuevoTablero, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
						.addGap(38, 38, 38)
						.addComponent(botonReiniciarPartida, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
						.addGap(38, 38, 38)
						.addComponent(botonGuardarPartida, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
						.addGap(38, 38, 38)
						.addComponent(botonCargarPartida, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
						.addGap(38, 38, 38)
						.addComponent(botonSolucionar, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
						.addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
				);

		botonNuevaPartida.getAccessibleContext().setAccessibleName("botonNuevaPartida");
		botonNuevaPartida.getAccessibleContext().setAccessibleDescription("");

		panelPrincipal.add(panelBotones);
		panelBotones.setBounds(698, 70, 280, 692);
		panelBotones.getAccessibleContext().setAccessibleDescription("");

		getContentPane().add(panelPrincipal, java.awt.BorderLayout.CENTER);

		pack();
	}// </editor-fold>                        

	/**
	 * Reinicia los valores de las celdas no base en el tablero de Sudoku.
	 *
	 * @param evt 	El ActionEvent que activa el metodo.
	 */
	private void botonReiniciarPartidaActionPerformed(ActionEvent evt) 
	{                                                       
		Component[] components = panelTablero.getComponents();

		for (Component component : components) 
		{
			if (component instanceof JTextField) 
			{
				JTextField textField = (JTextField) component;
				int row = (panelTablero.getComponentZOrder(textField)) / 9;
				int col = (panelTablero.getComponentZOrder(textField)) % 9;

				if (!tablero.getTablero()[row][col].isBase()) 
				{
					textField.setText("");
					textField.setForeground(Color.BLACK);
				}
			}
		}
	}                                                      

	/**
	 * Guarda el estado actual del juego en un archivo.
	 *
	 * @param evt 	El ActionEvent que activa el metodo.
	 */
	private void botonGuardarPartidaActionPerformed(ActionEvent evt) 
	{                                                    
		// Obtener el nombre de la partida del usuario
		String nombrePartida = JOptionPane.showInputDialog(this, "Ingrese el nombre de la partida:");

		if (nombrePartida != null && !nombrePartida.isEmpty()) {
			// Crear un objeto JFileChooser
			JFileChooser selectorDeArchivo = new JFileChooser();

			File myFile = null;
			// Establecer la carpeta predeterminada para el JFileChooser
			if(iDificultad.getDificultadSeleccionada() == "Facil")
			{
				myFile = new File("..\\Trabajo_java\\PartidasGuardadas\\ Nivel_Facil");
			}
			else if(iDificultad.getDificultadSeleccionada() == "Medio")
			{
				myFile = new File("..\\Trabajo_java\\PartidasGuardadas\\ Nivel_Medio");
			}
			else if(iDificultad.getDificultadSeleccionada() == "Dificil")
			{

				myFile = new File("..\\Trabajo_java\\PartidasGuardadas\\Nivel_Dificil");
			}
			String rutaCarpeta = myFile.getAbsolutePath();
			selectorDeArchivo.setCurrentDirectory(new File(rutaCarpeta));
			// Mostrar el cuadro de dialogo para seleccionar la ubicacion y el nombre del archivo
			int seleccion = selectorDeArchivo.showSaveDialog(this);

			if (seleccion == JFileChooser.APPROVE_OPTION) 
			{
				// Obtener el archivo seleccionado por el usuario
				File archivo = selectorDeArchivo.getSelectedFile();

				try 
				{
					// Resto del codigo permanece igual...
					FileWriter escritorDeArchivo = new FileWriter(archivo);
					BufferedWriter escritorBufferizado = new BufferedWriter(escritorDeArchivo);

					for (int i = 0; i < 9; i++) {
						for (int j = 0; j < 9; j++) {
							int valorDado = tablero.getTablero()[i][j].getValorDado();
							if (!tablero.getTablero()[i][j].isBase()) 
							{
								valorDado *= 10;
							}
							escritorBufferizado.write(Integer.toString(valorDado));
							escritorBufferizado.newLine();
						}
					}

					// Cerrar BufferedWriter
					escritorBufferizado.close();

					JOptionPane.showMessageDialog(this, "Partida guardada correctamente.");

				} 
				catch (IOException e) 
				{
					e.printStackTrace();
					JOptionPane.showMessageDialog(this, "Error al guardar la partida.");
				}
			}
		} else {
			JOptionPane.showMessageDialog(this, "Por favor, ingrese un nombre de partida valido.");
		}    
	}                                                   

	/**
	 * Carga un estado de juego previamente guardado desde un archivo.
	 *
	 * @param evt 	El ActionEvent que activa el metodo.
	 */
	private void botonCargarPartidaActionPerformed(ActionEvent evt) 
	{                                                   
		int contador = 0;
		File myFile = new File("..\\Trabajo_java\\PartidasGuardadas");;
		String rutaCarpeta = myFile.getAbsolutePath();
		JFileChooser selectorDeArchivo = new JFileChooser();
		selectorDeArchivo.setCurrentDirectory(new File(rutaCarpeta));
		selectorDeArchivo.setFileSelectionMode(JFileChooser.FILES_ONLY);

		int seleccion = selectorDeArchivo.showOpenDialog(this);

		if (seleccion == JFileChooser.APPROVE_OPTION) {
			File archivo = selectorDeArchivo.getSelectedFile();

			try {
				BufferedReader lectorBufferizado = new BufferedReader(new FileReader(archivo));
				String linea;

				while ((linea = lectorBufferizado.readLine()) != null) {
					for (int i = 0; i < 9; i++) {
						for (int j = 0; j < 9; j++) {
							int numero = Integer.parseInt(linea);
							if(numero >= 10 || numero == 0)
							{
								numero/=10;
								tablero.getTablero()[i][j].setValorBase(false);
								tablero.getTablero()[i][j].setValorBueno(0);
							}
							else
							{
								tablero.getTablero()[i][j].setValorBase(true);
								contador++;
								tablero.getTablero()[i][j].setValorBueno(numero);
							}
							tablero.getTablero()[i][j].setValorDado(numero);
							linea = lectorBufferizado.readLine();
						}
					}
				}
				if(contador == 37)	iDificultad.setDificultadSeleccionada("Facil");
				else if(contador == 30) iDificultad.setDificultadSeleccionada("Medio");
				else if(contador == 23) iDificultad.setDificultadSeleccionada("Dificil");
				setTitle("SUDOKU : 'Nivel " + iDificultad.getDificultadSeleccionada() + "'");
				lectorBufferizado.close();
				tablero.resolverSudoku();
				tablero.imprimirTableroDado();
				tablero.imprimirTableroResuelto();
				updateInterfaz();

				JOptionPane.showMessageDialog(this, "Partida cargada correctamente.");

			} catch (IOException e) {
				e.printStackTrace();
				JOptionPane.showMessageDialog(this, "Error al cargar la partida.");
			}
		}
	}                                                  

	/**
	 * Resuelve el rompecabezas de Sudoku actual y muestra la solucion.
	 *
	 * @param evt 	El ActionEvent que activa el metodo.
	 */
	private void botonSolucionarActionPerformed(ActionEvent evt) 
	{                                                
		for (int i = 0; i < 9; i++) 
		{
			for (int j = 0; j < 9; j++) 
			{
				JTextField textField = (JTextField) panelTablero.getComponent(i * 9 + j);
				textField.setText(String.valueOf(tablero.getTablero()[i][j].getValorBueno()));
			}
		}
	}                                               

	/**
	 * Inicia un nuevo juego seleccionando el nivel de dificultad.
	 *
	 * @param evt 	El ActionEvent que activa el metodo.
	 */
	private void botonNuevaPartidaActionPerformed(ActionEvent evt) 
	{                                                  
		InterfazDificultad interfazDificultad = new InterfazDificultad(this);
		interfazDificultad.setVisible(true);
		((JFrame) SwingUtilities.getWindowAncestor((Component)evt.getSource())).dispose(); // Cerrar la ventana principal
	}                                                 

	/**
	 * Genera un nuevo tablero de Sudoku con la dificultad seleccionada.
	 *
	 * @param evt 	El ActionEvent que activa el metodo.
	 */
	private void botonNuevoTableroActionPerformed(ActionEvent evt) 
	{                                                  
		generarNuevoSudokuConDificultad();
	}                                                 
}
