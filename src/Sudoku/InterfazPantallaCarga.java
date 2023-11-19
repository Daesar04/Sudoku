package Sudoku;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.Timer;

/**
 * Clase que representa una interfaz de pantalla de carga del Sudoku.
 */
public class InterfazPantallaCarga extends JFrame 
{
                   
	/** Barra de progreso para mostrar el avance durante la carga. */
    private JProgressBar barraProgreso;

    /** Panel que contiene la pantalla de carga. */
    private JPanel panelPantallaCarga;

    /** Etiqueta para mostrar una imagen superpuesta en la pantalla de carga. */
    private JLabel imagenSuperpuesta;

    /** Temporizador para controlar la animación de la barra de progreso. */
    private Timer temporizador;

    /** Contador para realizar el seguimiento del progreso de la carga. */
    int cont;

    /**
     * Constructor de la clase InterfazPantallaCarga.
     * Inicializa los componentes y configura la pantalla de carga.
     */
    public InterfazPantallaCarga() 
    {
        initComponents();
        setLocationRelativeTo(null);

        ImageIcon imagen = new ImageIcon("pantallaCarga.jpeg");
        imagenSuperpuesta = new JLabel(imagen);
        imagenSuperpuesta.setBounds(0, 0, imagen.getIconWidth(), imagen.getIconHeight());
        panelPantallaCarga.add(imagenSuperpuesta, BorderLayout.CENTER);

        cont = -1;
        barraProgreso.setValue(0);
        barraProgreso.setStringPainted(true);
        temporizador = new Timer(100, new ActionListener() 
        {
            @Override
            public void actionPerformed(ActionEvent e) 
            {
                actualizarProgreso();
            }
        });
        temporizador.start();
    }

    /**
     * Inicializa y configura los componentes de la interfaz de pantalla de carga.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">                          
    private void initComponents() {

        panelPantallaCarga = new javax.swing.JPanel();
        barraProgreso = new javax.swing.JProgressBar();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setMinimumSize(new java.awt.Dimension(600, 880));
        setUndecorated(true);
        setPreferredSize(new java.awt.Dimension(600, 880));
        setResizable(false);

        panelPantallaCarga.setMinimumSize(new java.awt.Dimension(600, 880));
        panelPantallaCarga.setPreferredSize(new java.awt.Dimension(600, 880));

        barraProgreso.setBackground(new java.awt.Color(255, 255, 255));
        barraProgreso.setFont(new java.awt.Font("Arial Black", 1, 18)); // NOI18N
        barraProgreso.setForeground(new java.awt.Color(255, 0, 0));

        javax.swing.GroupLayout panelPantallaCargaLayout = new javax.swing.GroupLayout(panelPantallaCarga);
        panelPantallaCarga.setLayout(panelPantallaCargaLayout);
        panelPantallaCargaLayout.setHorizontalGroup(
            panelPantallaCargaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelPantallaCargaLayout.createSequentialGroup()
                .addGap(80, 80, 80)
                .addComponent(barraProgreso, javax.swing.GroupLayout.PREFERRED_SIZE, 440, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(80, Short.MAX_VALUE))
        );
        panelPantallaCargaLayout.setVerticalGroup(
            panelPantallaCargaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelPantallaCargaLayout.createSequentialGroup()
                .addContainerGap(844, Short.MAX_VALUE)
                .addComponent(barraProgreso, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelPantallaCarga, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelPantallaCarga, javax.swing.GroupLayout.DEFAULT_SIZE, 900, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>                        

    /**
     * Método que actualiza el progreso de la barra de carga.
     * Detiene la animación cuando el progreso llega al 100% y cambia la interfaz después de un tiempo.
     */
    public void actualizarProgreso() 
    {
        cont++;
        barraProgreso.setValue(cont);

        if (cont == 100) 
        {
            temporizador.stop();

            Timer segundoTemporizador = new Timer(1000, new ActionListener() 
            {
                @Override
                public void actionPerformed(ActionEvent e) 
                {
                    cambiarInterfaz();
                }
            });
            segundoTemporizador.setRepeats(false);
            segundoTemporizador.start();
        }
    }
    
    /**
     * Cambia la interfaz de la pantalla de carga a la interfaz del juego Sudoku.
     */
    public void cambiarInterfaz() 
    {
        // Hacer que la interfaz de carga no sea visible
        setVisible(false);
      
        // Crear y mostrar la segunda interfaz después de 10 segundos
        Interfaz interfaz = new Interfaz();
        InterfazDificultad dificultad = new InterfazDificultad(interfaz);
        dificultad.setVisible(true);
    }
}
