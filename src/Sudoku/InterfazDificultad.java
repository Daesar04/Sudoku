package Sudoku;

import java.awt.Color;
import java.awt.Component;
import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;
import javax.swing.JButton;
import javax.swing.JPanel;

/**
 * La clase InterfazDificultad representa la interfaz gráfica para seleccionar la dificultad del juego Sudoku.
 */
public class InterfazDificultad extends JFrame
{
    
    /** Botón para la dificultad "Fácil". */
    public JButton botonFacil;
    
    /** Botón para la dificultad "Medio". */
    private JButton botonMedio;
    
	/** Botón para la dificultad "Difícil". */
    private JButton botonDificil;
    
    /** Panel que contiene los botones de dificultad. */
    private JPanel panelDificultad;
    
    /** Referencia a la interfaz principal del juego. */
    private Interfaz interfaz;
    
    /** Almacena la dificultad seleccionada ("Fácil", "Medio" o "Difícil"). */
    static private String dificultadSeleccionada;

    
    
	/**
     * Constructor de la clase InterfazDificultad.
     * @param interfaz 	La interfaz principal del juego.
     */
    public InterfazDificultad(Interfaz interfaz) 
    {
        this.interfaz = interfaz;
        initComponents();
        setTitle("DIFICULTAD");
        setLocationRelativeTo(null);
        botonFacil.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
        botonFacil.setFocusPainted(false);
        botonMedio.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
        botonMedio.setFocusPainted(false);
        botonDificil.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
        botonDificil.setFocusPainted(false);
    }
    
    /**
     * Obtiene la dificultad seleccionada.
     * @return 	La dificultad seleccionada ("Fácil", "Medio" o "Difícil").
     */
    public String getDificultadSeleccionada() 
    {
        return dificultadSeleccionada;
    }
    
    /**
     * Establece la dificultad seleccionada.
     *
     * @param dificultadSeleccionada 	La nueva dificultad seleccionada.
     */
    public static void setDificultadSeleccionada(String dificultadseleccionada)
    {
    	dificultadSeleccionada = dificultadseleccionada;
    }

    
    /**
     * Inicializa los componentes de la interfaz.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">                          
    private void initComponents() 
    {

        panelDificultad = new JPanel();
        botonFacil = new JButton();
        botonMedio = new JButton();
        botonDificil = new JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        botonFacil.setBackground(new java.awt.Color(0, 255, 0));
        botonFacil.setText("FÁCIL");
        botonFacil.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonFacilActionPerformed(evt);
            }
        });

        botonMedio.setBackground(new java.awt.Color(255, 204, 51));
        botonMedio.setText("MEDIO");
        botonMedio.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonMedioActionPerformed(evt);
            }
        });

        botonDificil.setBackground(new java.awt.Color(255, 51, 51));
        botonDificil.setText("DIFÍCIL");
        botonDificil.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonDificilActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout panelDificultadLayout = new javax.swing.GroupLayout(panelDificultad);
        panelDificultad.setLayout(panelDificultadLayout);
        panelDificultadLayout.setHorizontalGroup(
            panelDificultadLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelDificultadLayout.createSequentialGroup()
                .addGap(110, 110, 110)
                .addGroup(panelDificultadLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(botonDificil, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(botonMedio, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(botonFacil, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(110, 110, 110))
        );
        panelDificultadLayout.setVerticalGroup(
            panelDificultadLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelDificultadLayout.createSequentialGroup()
                .addGap(65, 65, 65)
                .addComponent(botonFacil, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(30, 30, 30)
                .addComponent(botonMedio, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(30, 30, 30)
                .addComponent(botonDificil, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(65, 65, 65))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelDificultad, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(panelDificultad, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>                        

    /**
     * Acción realizada cuando se presiona el botón "Fácil".
     * @param evt 	El evento de acción.
     */
    private void botonFacilActionPerformed(java.awt.event.ActionEvent evt) 
    {                                           
        dificultadSeleccionada = "Fácil";
        interfaz = new Interfaz();
        interfaz.setVisible(true);
        interfaz.setLocationRelativeTo(null);
        ((JFrame) SwingUtilities.getWindowAncestor((Component)evt.getSource())).dispose(); // Cerrar la ventana principal
    }   
    
    /**
     * Acción realizada cuando se presiona el botón "Medio".
     * @param evt 	El evento de acción.
     */
    private void botonMedioActionPerformed(java.awt.event.ActionEvent evt) 
    {                                           
        dificultadSeleccionada = "Medio";
        interfaz = new Interfaz();
        interfaz.setVisible(true);
        interfaz.setLocationRelativeTo(null);
        ((JFrame) SwingUtilities.getWindowAncestor((Component)evt.getSource())).dispose(); // Cerrar la ventana principal
    }                                          

    /**
     * Acción realizada cuando se presiona el botón "Difícil".
     * @param evt 	El evento de acción.
     */
    private void botonDificilActionPerformed(java.awt.event.ActionEvent evt) 
    {                                             
        dificultadSeleccionada = "Difícil";
        interfaz = new Interfaz();
        interfaz.setVisible(true);
        interfaz.setLocationRelativeTo(null);
        ((JFrame) SwingUtilities.getWindowAncestor((Component)evt.getSource())).dispose(); // Cerrar la ventana principal
    }                                                                                                         
}
