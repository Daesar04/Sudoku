package Sudoku;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import static java.util.Locale.filter;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
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





public class Interfaz extends JFrame {

    private Juego_Sudoku tablero;
    private InterfazDificultad iDificultad;
    /**
     * Creates new form Interfaz
     */
    
    
    public Interfaz() {
        
        tablero = new Juego_Sudoku();
        iDificultad = new InterfazDificultad(this);
        setTitle("SUDOKU : 'Nivel " + iDificultad.getDificultadSeleccionada() + "'");
        tablero.generarSudokuConDificultad(iDificultad.getDificultadSeleccionada());
        
        initComponents();
        setLocationRelativeTo(null);
        titulo.setAlignmentX(Component.CENTER_ALIGNMENT);
        panelTablero.setMinimumSize(new Dimension(692, 692));
        panelTablero.setMaximumSize(new Dimension(692, 692));
        panelTablero.setPreferredSize(new Dimension(692, 692));
        panelTablero.setLayout(new GridLayout(9, 9));

        rellenarTablero();
    }
    

    // Método para validar el valor introducido
    public void validateInput(JTextField textField, int row, int col) 
    {
        String inputText = textField.getText();

        // Verificar si la cadena de entrada está vacía
        if (inputText.isEmpty()) 
        {
            tablero.getTablero()[row][col].setValorDado(0); // Establecer el valor dado a 0 si la cadena está vacía
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
            } catch (NumberFormatException e) {
                // Manejar la excepción de formato si la entrada no es un número válido
                textField.setForeground(Color.BLACK);
                tablero.getTablero()[row][col].setValorDado(0); // Establecer el valor dado a 0 en caso de error
            }
        }
    }
    
    
    public void generarNuevoSudokuConDificultad() {
        tablero = new Juego_Sudoku();
        tablero.generarSudokuConDificultad(iDificultad.getDificultadSeleccionada());

        updateInterfaz();
    }
    
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
		// Vuelve a llenar la interfaz con los números del nuevo Sudoku
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

    
    public void updateInterfaz() {
        panelTablero.removeAll(); // Elimina los componentes anteriores del panelTablero
        rellenarTablero();
    }
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


    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">                          
    private void initComponents() {

        panelPrincipal = new javax.swing.JPanel();
        panelTitulo = new javax.swing.JPanel();
        titulo = new javax.swing.JLabel();
        panelCreditos = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        panelTablero = new javax.swing.JPanel();
        panelBotones = new javax.swing.JPanel();
        botonNuevaPartida = new javax.swing.JButton();
        botonNuevoTablero = new javax.swing.JButton();
        botonrReiniciarPartida = new javax.swing.JButton();
        botonGuardarPartida = new javax.swing.JButton();
        botonCargarPartida = new javax.swing.JButton();
        botonSolucionar = new javax.swing.JButton();

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

        jLabel2.setFont(new java.awt.Font("Dialog", 0, 25)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("HECHO POR JAVIER SÁEZ Y DAVID ESCRIBANO");

        javax.swing.GroupLayout panelCreditosLayout = new javax.swing.GroupLayout(panelCreditos);
        panelCreditos.setLayout(panelCreditosLayout);
        panelCreditosLayout.setHorizontalGroup(
            panelCreditosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelCreditosLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 681, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        panelCreditosLayout.setVerticalGroup(
            panelCreditosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
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

        botonrReiniciarPartida.setFont(new java.awt.Font("Comic Sans MS", 1, 15)); // NOI18N
        botonrReiniciarPartida.setText("REINICIAR PARTIDA");
        botonrReiniciarPartida.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        botonrReiniciarPartida.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonrReiniciarPartidaActionPerformed(evt);
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
                    .addComponent(botonrReiniciarPartida, javax.swing.GroupLayout.PREFERRED_SIZE, 226, javax.swing.GroupLayout.PREFERRED_SIZE)
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
                .addComponent(botonrReiniciarPartida, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
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

    private void botonrReiniciarPartidaActionPerformed(java.awt.event.ActionEvent evt) {                                                       
        Component[] components = panelTablero.getComponents();
        for (Component component : components) {
            if (component instanceof JTextField) {
                JTextField textField = (JTextField) component;
                int row = (panelTablero.getComponentZOrder(textField)) / 9;
                int col = (panelTablero.getComponentZOrder(textField)) % 9;
                if (!tablero.getTablero()[row][col].isBase()) {
                    textField.setText("");
                    textField.setForeground(Color.BLACK);
                }
            }
        }
    }                                                      

    private void botonGuardarPartidaActionPerformed(java.awt.event.ActionEvent evt) {                                                    
        // Obtener el nombre de la partida del usuario
        String nombrePartida = JOptionPane.showInputDialog(this, "Ingrese el nombre de la partida:");

        if (nombrePartida != null && !nombrePartida.isEmpty()) {
            // Crear un objeto JFileChooser
            JFileChooser selectorDeArchivo = new JFileChooser();

            // Mostrar el cuadro de diálogo para seleccionar la ubicación y el nombre del archivo
            int seleccion = selectorDeArchivo.showSaveDialog(this);

            if (seleccion == JFileChooser.APPROVE_OPTION) {
                // Obtener el archivo seleccionado por el usuario
                File archivo = selectorDeArchivo.getSelectedFile();

                try {
                    // Crear FileWriter y BufferedWriter para escribir en el archivo
                    FileWriter escritorDeArchivo = new FileWriter(archivo);
                    BufferedWriter escritorBufferizado = new BufferedWriter(escritorDeArchivo);

                    for (int i = 0; i < 9; i++) {
                        for (int j = 0; j < 9; j++) {
                            int valorDado = tablero.getTablero()[i][j].getValorDado();
                            if (!tablero.getTablero()[i][j].isBase()) 
                            {
                                valorDado*=10;
                            }
                            escritorBufferizado.write(Integer.toString(valorDado));
                            escritorBufferizado.newLine();
                        }
                    }

                    // Cerrar BufferedWriter
                    escritorBufferizado.close();

                    JOptionPane.showMessageDialog(this, "Partida guardada correctamente.");

                } catch (IOException e) {
                    e.printStackTrace();
                    JOptionPane.showMessageDialog(this, "Error al guardar la partida.");
                }
            }
        } else {
            JOptionPane.showMessageDialog(this, "Por favor, ingrese un nombre de partida válido.");
        }     
    }                                                   

    private void botonCargarPartidaActionPerformed(java.awt.event.ActionEvent evt) {                                                   
    
        JFileChooser selectorDeArchivo = new JFileChooser();
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
                                tablero.getTablero()[i][j].setValorBueno(numero);
                            }
                            tablero.getTablero()[i][j].setValorDado(numero);
                            linea = lectorBufferizado.readLine();
                        }
                    }
                }

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

    private void botonSolucionarActionPerformed(java.awt.event.ActionEvent evt) {                                                
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                JTextField textField = (JTextField) panelTablero.getComponent(i * 9 + j);
                textField.setText(String.valueOf(tablero.getTablero()[i][j].getValorBueno()));
            }
        }
    }                                               

    private void botonNuevaPartidaActionPerformed(java.awt.event.ActionEvent evt) {                                                  
        InterfazDificultad interfazDificultad = new InterfazDificultad(this);
        interfazDificultad.setVisible(true);
        ((JFrame) SwingUtilities.getWindowAncestor((Component)evt.getSource())).dispose(); // Cerrar la ventana principal
    }                                                 

    private void botonNuevoTableroActionPerformed(java.awt.event.ActionEvent evt) {                                                  
        generarNuevoSudokuConDificultad();
    }                                                 


    // Variables declaration - do not modify                     
    private javax.swing.JButton botonCargarPartida;
    private javax.swing.JButton botonGuardarPartida;
    private javax.swing.JButton botonNuevaPartida;
    private javax.swing.JButton botonNuevoTablero;
    private javax.swing.JButton botonSolucionar;
    private javax.swing.JButton botonrReiniciarPartida;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel panelBotones;
    private javax.swing.JPanel panelCreditos;
    public javax.swing.JPanel panelPrincipal;
    private javax.swing.JPanel panelTablero;
    private javax.swing.JPanel panelTitulo;
    public javax.swing.JLabel titulo;
    // End of variables declaration                   

}
