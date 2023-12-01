package Sudoku;

import java.awt.Window;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.util.Random;
import javax.swing.JFrame;

/**
 * Clase InterfazRepetir, extiende de JFrame.
 * Esta clase proporciona una interfaz de usuario para preguntar al jugador si desea jugar otra partida.
 */
public class InterfazRepetir extends javax.swing.JFrame 
{    
	/**
	 * Botón para rechazar la oferta de jugar otra partida.
	 */
	private javax.swing.JButton botonNo;

	/**
	 * Botón para aceptar la oferta de jugar otra partida.
	 */
	private javax.swing.JButton botonSi;

	/**
	 * Etiqueta que muestra la pregunta al usuario sobre si quiere jugar otra partida.
	 */
	private javax.swing.JLabel labelPregunta;

	/**
	 * Panel que contiene los componentes de la interfaz para repetir la partida.
	 */
	private javax.swing.JPanel panelRepetir;

	/**
	 * Contador para el número de veces que el botón "No" se ha movido para evitar ser presionado.
	 */
	private int contadorNo;

	/**
	 * Objeto Random para generar posiciones aleatorias para el botón "No".
	 */
	private final Random random;
	
     /**
     * Constructor de la clase InterfazRepetir.
     * Inicializa los componentes de la interfaz y configura el comportamiento de los botones "Sí" y "No".
     */
    public InterfazRepetir() 
    {
        initComponents();
        setLocationRelativeTo(null);
        botonSi.setFocusPainted(false);
        botonNo.setFocusPainted(false);
        
        contadorNo = 0;
        random = new Random();
        
        // Agregar MouseMotionListener al botón "No"
        botonNo.addMouseMotionListener(new MouseMotionAdapter() {
            @Override
            public void mouseMoved(MouseEvent e) {
                if(contadorNo < 5){
                    moverBotonAleatorio();
                }
            }
        });
    }

    /**
     * Inicializa los componentes de la interfaz de usuario.
     * Este método es generado automáticamente y configura los elementos visuales de la interfaz.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">                          
    private void initComponents() {

        panelRepetir = new javax.swing.JPanel();
        labelPregunta = new javax.swing.JLabel();
        botonSi = new javax.swing.JButton();
        botonNo = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);

        panelRepetir.setBackground(new java.awt.Color(28, 28, 28));
        panelRepetir.setForeground(new java.awt.Color(255, 255, 255));

        labelPregunta.setBackground(new java.awt.Color(102, 102, 102));
        labelPregunta.setFont(new java.awt.Font("Corbel Light", 1, 18)); // NOI18N
        labelPregunta.setForeground(new java.awt.Color(255, 255, 255));
        labelPregunta.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        labelPregunta.setText("¿ Quieres jugar otra partida ?");

        botonSi.setBackground(new java.awt.Color(102, 102, 102));
        botonSi.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        botonSi.setForeground(new java.awt.Color(255, 255, 255));
        botonSi.setText("Sí");
        botonSi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonSiActionPerformed(evt);
            }
        });

        botonNo.setBackground(new java.awt.Color(102, 102, 102));
        botonNo.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        botonNo.setForeground(new java.awt.Color(255, 255, 255));
        botonNo.setText("No");
        botonNo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonNoActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout panelRepetirLayout = new javax.swing.GroupLayout(panelRepetir);
        panelRepetir.setLayout(panelRepetirLayout);
        panelRepetirLayout.setHorizontalGroup(
            panelRepetirLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelRepetirLayout.createSequentialGroup()
                .addGroup(panelRepetirLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelRepetirLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(labelPregunta, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(panelRepetirLayout.createSequentialGroup()
                        .addGap(73, 73, 73)
                        .addComponent(botonSi, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(61, 61, 61)
                        .addComponent(botonNo, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 73, Short.MAX_VALUE)))
                .addContainerGap())
        );
        panelRepetirLayout.setVerticalGroup(
            panelRepetirLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelRepetirLayout.createSequentialGroup()
                .addContainerGap(58, Short.MAX_VALUE)
                .addComponent(labelPregunta, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(35, 35, 35)
                .addGroup(panelRepetirLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(botonSi, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(botonNo, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(76, 76, 76))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelRepetir, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelRepetir, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>                        
    
    /**
     * Mueve el botón "No" a una posición aleatoria dentro del panel.
     * Este método se activa cuando el mouse se mueve sobre el botón "No".
     */
    private void moverBotonAleatorio() {
        contadorNo++;
        int panelWidth = panelRepetir.getWidth() - botonNo.getWidth();
        int panelHeight = panelRepetir.getHeight() - botonNo.getHeight();

        int x, y;

        do {
            x = random.nextInt(panelWidth);
            y = random.nextInt(panelHeight);
        } while (posicionToqueBotonSi(x, y));

        botonNo.setLocation(x, y);
    }
    
    /**
     * Determina si una posición dada toca el área del botón "Sí".
     * @param x La coordenada x de la posición.
     * @param y La coordenada y de la posición.
     * @return true si la posición toca el botón "Sí", de lo contrario false.
     */
    private boolean posicionToqueBotonSi(int x, int y) {
        // Obtener las posiciones del botón "SI"
        int botonSiX = botonSi.getX();
        int botonSiY = botonSi.getY();

        // Verificar si la posición toca al botón "SI"
        return (x >= botonSiX && x <= botonSiX + botonSi.getWidth() && y >= botonSiY && y <= botonSiY + botonSi.getHeight());
    }
    
    /**
     * Acción realizada al presionar el botón "Sí".
     * Cierra la ventana actual y abre la interfaz de selección de dificultad para empezar una nueva partida.
     * @param evt El evento de acción.
     */
    private void botonSiActionPerformed(java.awt.event.ActionEvent evt) {                                        
        setVisible(false);
        
        Window[] ventanas = Window.getWindows();
        for (Window ventana : ventanas) {
            if (ventana instanceof JFrame && ventana != this) {
                ventana.dispose();
            }
        }
        
        InterfazDificultad.crearNuevaInstancia().setVisible(true);
    }                                       

    /**
     * Acción realizada al presionar el botón "No".
     * Cierra la ventana actual y finaliza la aplicación.
     * @param evt El evento de acción.
     */
    private void botonNoActionPerformed(java.awt.event.ActionEvent evt) {                                        
            dispose(); // Cierra la ventana
            System.exit(0); // Cierra la aplicación
    }                                       
}
