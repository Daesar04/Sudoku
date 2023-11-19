package Sudoku;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.Timer;
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

    /** Boton para guardar el estado actual de la partida. */
    private JButton botonGuardarPartida;

    /** Boton para iniciar una nueva partida. */
    private JButton botonNuevaPartida;

    /** Boton para generar un nuevo tablero de Sudoku. */
    private JButton botonNuevoTablero;

    /** Boton para reiniciar la partida actual. */
    private JButton botonReiniciarPartida;

    /** Boton para mostrar la solucion del Sudoku. */
    private JButton botonSolucionar;

    /** Etiqueta que muestra el tiempo transcurrido durante la partida. */
    private JLabel jLabelTiempo;

    /** Panel que contiene los botones de control del juego. */
    private JPanel panelBotones;

    /** Panel que muestra informacion sobre la partida. */
    private JPanel panelInfo;

    /** Panel principal que agrupa todos los elementos de la interfaz. */
    private JPanel panelPrincipal;

    /** Panel que representa el tablero de Sudoku en la interfaz. */
    private JPanel panelTablero;

    /** Panel que contiene el titulo y la imagen del juego. */
    private JPanel panelTitulo;

    /** Etiqueta que superpone una imagen en el panel del titulo. */
    private final JLabel imagenSuperpuesta;

    /** Indice de fila seleccionada en el tablero (-1 si no hay seleccion). */
    private int filaSeleccionada = -1;

    /** Indice de columna seleccionada en el tablero (-1 si no hay seleccion). */
    private int columnaSeleccionada = -1;

    /** Temporizador utilizado para medir el tiempo transcurrido durante la partida. */
    private Timer timer;

    /** Tiempo total transcurrido durante la partida. */
    private long tiempoTranscurrido;

    /** Tiempo inicial de la partida utilizado para calcular el tiempo transcurrido. */
    private long tiempoInicial;


	/**
	 * Constructor de la clase Interfaz.
	 */
    public Interfaz() 
    {
        tablero = new Juego_Sudoku();
        iniciarTemporizador();
        iDificultad = new InterfazDificultad(this);
        setTitle("SUDOKU : 'Nivel " + iDificultad.getDificultadSeleccionada() + "'");
        tablero.generarSudokuConDificultad(iDificultad.getDificultadSeleccionada());
        
        initComponents();
        setLocationRelativeTo(null);
        panelTablero.setMinimumSize(new Dimension(692, 692));
        panelTablero.setMaximumSize(new Dimension(692, 692));
        panelTablero.setPreferredSize(new Dimension(692, 692));
        panelTablero.setLayout(new GridLayout(9, 9));

        ImageIcon imagen = new ImageIcon("sudokujpeg.png");
        imagenSuperpuesta = new JLabel(imagen);
        imagenSuperpuesta.setBounds(0, 0, imagen.getIconWidth(), imagen.getIconHeight());
        panelTitulo.add(imagenSuperpuesta, BorderLayout.CENTER); // Agrega la imagen al centro del panel
        
        CrearCarpetas();
        
        rellenarTablero();
    }


	/**
	 * Valida el valor de entrada en un JTextField y actualiza el tablero de Sudoku.
	 *
	 * @param textField 	El JTextField a validar.
	 * @param fila       	El indice de fila de la celda.
	 * @param col       	El indice de columna de la celda.
	 */
	public void validateInput(JTextField textField, int fila, int col) 
	{
		String texto = textField.getText();
		Font fuente = textField.getFont();
		final int nuevoTamanioLetra = 20; // Tamanio de letra deseado
		
		// Verificar si la cadena de entrada esta vacia
		if (texto.isEmpty()) 
		{
			tablero.getTablero()[fila][col].setValorDado(0); // Establecer el valor dado a 0 si la cadena esta vacia
		} else {
			try 
			{
				int enteredValue = Integer.parseInt(texto);
				int correctValue = tablero.getTablero()[fila][col].getValorBueno();

				if (enteredValue != correctValue)
				{
					textField.setForeground(Color.RED); // Cambia el color del texto a rojo
					textField.setFont(fuente.deriveFont(Font.PLAIN, nuevoTamanioLetra));
				} else 
				{
					textField.setForeground(Color.BLACK); // Cambia el color del texto a negro si es correcto
					textField.setFont(fuente.deriveFont(Font.PLAIN, nuevoTamanioLetra));
					
					if (tablero.getTablero()[fila][col].isBase())
					{
						textField.setFont(fuente.deriveFont(Font.BOLD, nuevoTamanioLetra));
					}
				}
				
				tablero.getTablero()[fila][col].setValorDado(enteredValue);
			} 
			catch (NumberFormatException e) 
			{
				// Manejar la excepcion de formato si la entrada no es un numero valido
				textField.setForeground(Color.BLACK);
				tablero.getTablero()[fila][col].setValorDado(0); // Establecer el valor dado a 0 en caso de error
			}
		}
	}
	
	/**
     * Inicia el temporizador para medir el tiempo transcurrido durante el juego.
     */
	public void iniciarTemporizador() {
        tiempoInicial = System.currentTimeMillis();
        timer = new Timer(10, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                tiempoTranscurrido = System.currentTimeMillis() - tiempoInicial;
                actualizarTiempo();
            }
        });
        timer.start();
    }
	
	/**
     * Actualiza el tiempo transcurrido en la interfaz.
     */
	public void actualizarTiempo() {
	    long horas = tiempoTranscurrido / 3600000;
	    long minutos = (tiempoTranscurrido % 3600000) / 60000;
	    long segundos = (tiempoTranscurrido % 60000) / 1000;
	    long milisegundos = tiempoTranscurrido % 1000;
	
	    String tiempoFormateado = String.format("Tiempo: %01d:%02d:%02d.%03d", horas, minutos, segundos, milisegundos);
	    
	    jLabelTiempo.setText(tiempoFormateado);
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
		boolean bordeSuperiorGr, BordeIzqGr;
		
		DocumentFilter filter = new DocumentFilter() {
			@Override
			public void replace(FilterBypass fb, int offset, int length, String text, AttributeSet attrs) throws BadLocationException {
				Document doc = fb.getDocument();
				String currentText = doc.getText(0, doc.getLength());
				currentText = currentText.substring(0, offset) + text + currentText.substring(offset + length);
				if (currentText.length() <= 1 && currentText.matches("[1-9]?")) {
					super.replace(fb, offset, length, text, attrs);
				}
			}
		};
		// Vuelve a llenar la interfaz con los numeros del nuevo Sudoku
		for (int i = 0; i < 9; i++) 
		{
			for (int j = 0; j < 9; j++) 
			{
				final int fila = i; // Definir 'fila' como final
				final int col = j; // Definir 'col' como final

				JTextField textField = new JTextField();
				int value = tablero.getTablero()[i][j].getValorDado();
				if (value != 0) 
				{
					textField.setText(String.valueOf(value));
					validateInput(textField, fila, col);
					if (tablero.getTablero()[i][j].isBase()) 
					{
						textField.setEditable(false);
					}
				}
				textField.setHorizontalAlignment(SwingConstants.CENTER);
				((AbstractDocument) textField.getDocument()).setDocumentFilter(filter);
				
				bordeSuperiorGr = (i % 3 == 0);
				BordeIzqGr = (j % 3 == 0);

	            int arriba = 1;
	            int izq = 1;
	            int abajo = 1;
	            int drch = 2;

	            if (bordeSuperiorGr) 
	            {
	            	arriba = 3;
	            }
	            if (BordeIzqGr) 
	            {
	            	izq = 3;
	            }
	            if (i == 8) 
	            {
	            	abajo = 3; // Borde grueso en la última fila
	            }
	            if (j == 8) 
	            {
	            	drch = 3; // Borde grueso en la última columna
	            }

	            textField.setBorder(BorderFactory.createMatteBorder(arriba, izq, abajo, drch, Color.DARK_GRAY));
	            
				// Agrega un DocumentListener al JTextField
				textField.getDocument().addDocumentListener(new DocumentListener() 
				{
					@Override
					public void insertUpdate(DocumentEvent e) 
					{
						validateInput(textField, fila, col);
					}

					@Override
					public void removeUpdate(DocumentEvent e) 
					{
						validateInput(textField, fila, col);
					}

					@Override
					public void changedUpdate(DocumentEvent e) 
					{
						validateInput(textField, fila, col);
					}
				});
				textField.addMouseListener(new MouseAdapter() {
					@Override
				    public void mouseClicked(MouseEvent e) {
				        int fila = (panelTablero.getComponentZOrder(textField)) / 9;
				        int col = (panelTablero.getComponentZOrder(textField)) % 9;
				        if (filaSeleccionada != -1 && columnaSeleccionada != -1) {
				            quitarResaltadoFilaColumna(filaSeleccionada, columnaSeleccionada);
				        }
				        resaltarFilaColumna(fila, col);
				        filaSeleccionada = fila;
				        columnaSeleccionada = col;
				    }
				});
				panelTablero.add(textField);
			}
		}

		panelTablero.revalidate(); // Vuelve a validar el panelTablero para que se muestren los nuevos componentes
		panelTablero.repaint(); // Repinta el panelTablero para que se muestren los nuevos componentes
	}

	/**
     * Crea carpetas necesarias para el juego en la ubicación predeterminada.
     */
	public void CrearCarpetas()
    {
        // Obtener la ruta al escritorio del usuario
        String rutaPartidas = System.getProperty("user.home") + File.separator + "Sudoku";

        // Rutas completas para las carpetas
        String rutaCarpetaPartidas = rutaPartidas + File.separator + "PartidasGuardadas";
        String rutaCarpetaFacil = rutaCarpetaPartidas + File.separator + " Nivel_Facil";
        String rutaCarpetaMedio = rutaCarpetaPartidas + File.separator + " Nivel_Medio";
        String rutaCarpetaDificil = rutaCarpetaPartidas + File.separator + "Nivel_Dificil";

        // Crear objetos File para las carpetas
        File carpetaPartidas = new File(rutaCarpetaPartidas);
        File carpetaFacil = new File(rutaCarpetaFacil);
        File carpetaMedio = new File(rutaCarpetaMedio);
        File carpetaDificil = new File(rutaCarpetaDificil);

         // Verificar si las carpetas ya existen
        if (!carpetaPartidas.exists() && !carpetaFacil.exists() && !carpetaMedio.exists() && !carpetaDificil.exists()) 
        {
            // Si no existen, crearlas
            carpetaPartidas.mkdirs(); // Crear carpeta 'PartidasGuardadas' en el escritorio
            carpetaFacil.mkdirs(); // Crear carpeta para partidas de nivel fácil
            carpetaMedio.mkdirs(); // Crear carpeta para partidas de nivel medio
            carpetaDificil.mkdirs(); // Crear carpeta para partidas de nivel difícil

            System.out.println("Carpetas creadas exitosamente.");
        } else 
        {
            System.out.println("Las carpetas ya existen.");
        }
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
				int fila = (panelTablero.getComponentZOrder(textField)) / 9;
				int col = (panelTablero.getComponentZOrder(textField)) % 9;
				validateInput(textField, fila, col);
			}
		}
	}

	/**
     * Resalta la fila y columna de la celda seleccionada en el tablero.
     *
     * @param fila 	El índice de fila de la celda seleccionada.
     * @param col  	El índice de columna de la celda seleccionada.
     */
	public void resaltarFilaColumna(int fila, int col) {
		Color filacol = new Color(226, 243, 227);
		Color casilla = new Color(187, 251, 191);
	    for (Component component : panelTablero.getComponents()) {
	        if (component instanceof JTextField) {
	            JTextField textField = (JTextField) component;
	            int currentfila = (panelTablero.getComponentZOrder(textField)) / 9;
	            int currentCol = (panelTablero.getComponentZOrder(textField)) % 9;

	            if (currentfila == fila || currentCol == col) {
	                textField.setBackground(filacol);
	            }

	            // Resaltar la casilla seleccionada con un color diferente
	            if (currentfila == fila && currentCol == col) {
	                textField.setBackground(casilla); // Cambia el color de la casilla seleccionada
	            }
	        }
	    }
	}
	
	/**
	 * Quita el resaltado de la fila y columna en el tablero de Sudoku.
	 *
	 * @param fila 	El índice de la fila a la que se le quitará el resaltado.
	 * @param col  	El índice de la columna a la que se le quitará el resaltado.
	 */
	public void quitarResaltadoFilaColumna(int fila, int col) {
	    Color customColor = new Color(238, 238, 238);
	    for (int i = 0; i < 9; i++) {
	        JTextField textFieldfila = (JTextField) panelTablero.getComponent(fila * 9 + i);
	        JTextField textFieldCol = (JTextField) panelTablero.getComponent(i * 9 + col);
	        
	        // Verificar si la celda es editable para cambiar su color de fondo
	        if (tablero.getTablero()[fila][i].isBase()) {
	            textFieldfila.setBackground(customColor); // Color para celda no editable
	        } else {
	            textFieldfila.setBackground(Color.WHITE); // Color para celda editable
	        }
	        
	        if (tablero.getTablero()[i][col].isBase()) {
	            textFieldCol.setBackground(customColor); // Color para celda no editable
	        } else {
	            textFieldCol.setBackground(Color.WHITE); // Color para celda editable
	        }
	    }
	}

	/**
	 * Inicializa los componentes graficos de la interfaz de usuario.
	 */
	@SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">                          
    private void initComponents() {

        panelPrincipal = new javax.swing.JPanel();
        panelTitulo = new javax.swing.JPanel();
        panelInfo = new javax.swing.JPanel();
        jLabelTiempo = new javax.swing.JLabel();
        panelTablero = new javax.swing.JPanel();
        panelBotones = new javax.swing.JPanel();
        botonNuevaPartida = new javax.swing.JButton();
        botonNuevoTablero = new javax.swing.JButton();
        botonReiniciarPartida = new javax.swing.JButton();
        botonGuardarPartida = new javax.swing.JButton();
        botonCargarPartida = new javax.swing.JButton();
        botonSolucionar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setMinimumSize(new java.awt.Dimension(990, 800));
        setResizable(false);

        panelPrincipal.setMaximumSize(new java.awt.Dimension(945, 762));
        panelPrincipal.setMinimumSize(new java.awt.Dimension(945, 762));
        panelPrincipal.setLayout(null);

        panelTitulo.setBackground(new java.awt.Color(102, 255, 153));

        javax.swing.GroupLayout panelTituloLayout = new javax.swing.GroupLayout(panelTitulo);
        panelTitulo.setLayout(panelTituloLayout);
        panelTituloLayout.setHorizontalGroup(
            panelTituloLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 200, Short.MAX_VALUE)
        );
        panelTituloLayout.setVerticalGroup(
            panelTituloLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 64, Short.MAX_VALUE)
        );

        panelPrincipal.add(panelTitulo);
        panelTitulo.setBounds(0, 0, 200, 64);

        panelInfo.setBackground(new java.awt.Color(0, 0, 255));

        jLabelTiempo.setFont(new java.awt.Font("Arial Black", 1, 14)); // NOI18N
        jLabelTiempo.setForeground(new java.awt.Color(255, 255, 255));
        jLabelTiempo.setToolTipText("");

        javax.swing.GroupLayout panelInfoLayout = new javax.swing.GroupLayout(panelInfo);
        panelInfo.setLayout(panelInfoLayout);
        panelInfoLayout.setHorizontalGroup(
            panelInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelInfoLayout.createSequentialGroup()
                .addContainerGap(444, Short.MAX_VALUE)
                .addComponent(jLabelTiempo, javax.swing.GroupLayout.PREFERRED_SIZE, 191, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(135, 135, 135))
        );
        panelInfoLayout.setVerticalGroup(
            panelInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelInfoLayout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addComponent(jLabelTiempo)
                .addContainerGap(43, Short.MAX_VALUE))
        );

        panelPrincipal.add(panelInfo);
        panelInfo.setBounds(209, 0, 770, 64);

        panelTablero.setBackground(new java.awt.Color(64, 64, 64));
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
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonNuevaPartidaActionPerformed(evt);
            }
        });

        botonNuevoTablero.setFont(new java.awt.Font("Comic Sans MS", 1, 15)); // NOI18N
        botonNuevoTablero.setText("NUEVO TABLERO");
        botonNuevoTablero.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        botonNuevoTablero.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonNuevoTableroActionPerformed(evt);
            }
        });

        botonReiniciarPartida.setFont(new java.awt.Font("Comic Sans MS", 1, 15)); // NOI18N
        botonReiniciarPartida.setText("REINICIAR PARTIDA");
        botonReiniciarPartida.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        botonReiniciarPartida.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonReiniciarPartidaActionPerformed(evt);
            }
        });

        botonGuardarPartida.setFont(new java.awt.Font("Comic Sans MS", 1, 15)); // NOI18N
        botonGuardarPartida.setText("GUARDAR PARTIDA");
        botonGuardarPartida.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        botonGuardarPartida.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonGuardarPartidaActionPerformed(evt);
            }
        });

        botonCargarPartida.setFont(new java.awt.Font("Comic Sans MS", 1, 15)); // NOI18N
        botonCargarPartida.setText("CARGAR PARTIDA");
        botonCargarPartida.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        botonCargarPartida.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonCargarPartidaActionPerformed(evt);
            }
        });

        botonSolucionar.setFont(new java.awt.Font("Comic Sans MS", 1, 15)); // NOI18N
        botonSolucionar.setText("SOLUCIONAR");
        botonSolucionar.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        botonSolucionar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
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
				int fila = (panelTablero.getComponentZOrder(textField)) / 9;
				int col = (panelTablero.getComponentZOrder(textField)) % 9;

				if (!tablero.getTablero()[fila][col].isBase()) 
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
        String rutaPartidas = System.getProperty("user.home") + File.separator + "Sudoku";

        if (nombrePartida != null && !nombrePartida.isEmpty()) {
            // Crear un objeto JFileChooser
            JFileChooser selectorDeArchivo = new JFileChooser();
            
            File myFile = null;
            // Establecer la carpeta predeterminada para el JFileChooser
            if(iDificultad.getDificultadSeleccionada() == "Fácil")
            {
                myFile = new File(rutaPartidas + File.separator + "\\PartidasGuardadas\\ Nivel_Facil");
            }
            else if(iDificultad.getDificultadSeleccionada() == "Medio")
            {
                myFile = new File(rutaPartidas + File.separator + "\\PartidasGuardadas\\ Nivel_Medio");
            }
            else if(iDificultad.getDificultadSeleccionada() == "Difícil")
            {
         
               myFile = new File(rutaPartidas + File.separator + "\\PartidasGuardadas\\Nivel_Dificil");
            }
            String rutaCarpeta = myFile.getAbsolutePath();
            selectorDeArchivo.setCurrentDirectory(new File(rutaCarpeta));
            // Mostrar el cuadro de diálogo para seleccionar la ubicación y el nombre del archivo
            int seleccion = selectorDeArchivo.showSaveDialog(this);

            if (seleccion == JFileChooser.APPROVE_OPTION) 
            {
                // Obtener el archivo seleccionado por el usuario
                File archivo = selectorDeArchivo.getSelectedFile();

                try 
                {
                    // Resto del código permanece igual...
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
            JOptionPane.showMessageDialog(this, "Por favor, ingrese un nombre de partida válido.");
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
		String rutaPartidas = System.getProperty("user.home") + File.separator + "Sudoku";
	    File myFile = new File(rutaPartidas + File.separator + "\\PartidasGuardadas");
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
				/*tablero.imprimirTableroDado();
				tablero.imprimirTableroResuelto();*/
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
