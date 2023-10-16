package Sudoku;
/*
import java.awt.Color;
import java.awt.Font;
import java.awt.Image;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.SwingConstants;
//import java.awt.Dimension;

public class Ventana_Sudoku extends JFrame
{
	
	public JPanel panel;
	
	public Ventana_Sudoku()
	{		
		setSize(750, 750); // Tamaño de la ventana
		setTitle("SUDOKU - Nivel Medio"); // Titulo de la ventana
		//setMinimumSize(new Dimension(500, 500));
		setLocationRelativeTo(null); // Establecemos la ventana en el centro
		iniciarComponentes();
		setDefaultCloseOperation(EXIT_ON_CLOSE); // Cierre de compilacion
		
	}
	private void iniciarComponentes()
	{
		colocarPaneles();
		colocarEtiquetas();
		//colocarBotones();
		//colocarRadioBotones();
	}
	
	private void colocarPaneles()
	{
		panel = new JPanel(); // Creacion de un panel
		//panel.setBackground(Color.WHITE); // Establecemos el color al panel
		panel.setLayout(null); // Desactivando el diseño
		this.getContentPane().add(panel); // Agregamos el panel a la ventana
	}
	
	private void colocarEtiquetas()
	{
		// Etiqueta 1 - Etiqueta tipo texto
		JLabel etiqueta = new JLabel (); // Creamos una etiqueta
		etiqueta.setText("1 2 3 4 5 6 7 8 9"); // Establecemos el texto de la etiqueta
		etiqueta.setBounds(250, 20, 255, 70); 
		etiqueta.setHorizontalAlignment(SwingConstants.CENTER); // Establecer la alineacion horizontal del texto
		etiqueta.setForeground(Color.WHITE); // Establecemos color de la letra
		etiqueta.setOpaque(true); // Establecemos pintar el fondo de la etiqueta
		etiqueta.setBackground(Color.BLACK); // Cambiamos el color del fondo de la etiqueta
		etiqueta.setFont(new Font("chiller", Font.BOLD, 40)); // Establecemos la fuente del texto
		panel.add(etiqueta); // Agregamos la etiqueta al panel
		
		// Etiqueta 2 - Etiqueta tipo imagen
		ImageIcon imagen = new ImageIcon("bellingham.jpg");
		JLabel etiqueta2 = new JLabel (imagen); // Creamos una etiqueta
		etiqueta2.setBounds(95, 140, 550, 300);
		etiqueta2.setIcon(new ImageIcon(imagen.getImage().getScaledInstance(etiqueta2.getWidth(), etiqueta2.getHeight(), Image.SCALE_SMOOTH))); // Escalado de la imagen
		panel.add(etiqueta2);
	}
	
	private void colocarBotones()
	{
		// Boton 1 - boton de texto
		JButton boton1 = new JButton();
		boton1.setText("CLICK"); // Establecemos un texto al botion
		boton1.setBounds(100, 99, 100, 40);
		boton1.setEnabled(true); // Habilitar o deshabilitar el boton
		boton1.setMnemonic('a'); // Establecemos ALT + LETRA
		boton1.setForeground(Color.BLUE); // Establecemos el color de la letra del boton
		boton1.setFont(new Font("cooper black", 3, 15)); // Establecemos la fuente de la letra del boton
		panel.add(boton1);	
		
		
		// Boton 2 - boton de imagen
		JButton boton2 = new JButton();
		boton2.setBounds(100, 200, 100, 90);
		ImageIcon realMadrid = new ImageIcon("RealMadrid.png");
		boton2.setIcon(new ImageIcon(realMadrid.getImage().getScaledInstance(boton2.getWidth(), boton2.getHeight(), Image.SCALE_SMOOTH)));
		boton2.setBackground(Color.BLUE); // Establecemos color de fondo del boton
		panel.add(boton2);
		
		//Boton 3 - boton del bordes 
		JButton boton3 = new JButton(); 
		boton3.setBounds (100, 350, 110, 50); 
		boton3.setBorder (BorderFactory.createLineBorder (Color. GREEN, 4, false)); 
		panel.add(boton3);
		
	}

	private void colocarRadioBotones()
	{
		JRadioButton radioBoton1 = new JRadioButton("Opcion 1", true);
		radioBoton1.setBounds(50, 100, 150, 50);
		//radioBoton1.setEnabled(true);
		radioBoton1.setFont(new Font("cooper black", Font.BOLD, 20));
		panel.add(radioBoton1);
		
		JRadioButton radioBoton2 = new JRadioButton("Opcion 2", false);
		radioBoton2.setBounds(50, 150, 100, 50);
		panel.add(radioBoton2);
		
		JRadioButton radioBoton3 = new JRadioButton("Opcion 3", false);
		radioBoton3.setBounds(50, 200, 100, 50);
		panel.add(radioBoton3);
		
		ButtonGroup grupo = new ButtonGroup();
		grupo.add(radioBoton1);
		grupo.add(radioBoton2);
		grupo.add(radioBoton3);
	}


}
*/
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Ventana_Sudoku {
    private JFrame frame;
    private JTextField[][] sudokuCells;

    public Ventana_Sudoku() {
        frame = new JFrame("Sudoku");
        sudokuCells = new JTextField[9][9];

        // Configuración del diseño del marco
        frame.setLayout(new GridLayout(9, 9));

        // Crear las celdas del Sudoku y agregarlas al marco
        for (int i = 0; i < 9; i++) 
        {
            for (int j = 0; j < 9; j++) 
            {
                sudokuCells[i][j] = new JTextField();
                sudokuCells[i][j].setHorizontalAlignment(JTextField.CENTER);
                sudokuCells[i][j].setFont(new Font("Arial", Font.BOLD, 20));
                frame.add(sudokuCells[i][j]);
            }
        }

        // Configurar la ventana
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500, 500);
        frame.setVisible(true);
    }

    public static void main(String[] args) 
    {
        SwingUtilities.invokeLater(() -> {
            new Ventana_Sudoku();
        });
    }
}