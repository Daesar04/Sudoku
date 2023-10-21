package Sudoku;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import javax.swing.SwingConstants;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.Dimension;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.ComponentOrientation;
import java.awt.Component;
import javax.swing.JLabel;
import java.awt.Cursor;
import java.awt.Rectangle;
import java.awt.Font;

public class interfaz extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	/**
	 * Launch the application.
	 */

	/**
	 * Create the frame.
	 */
	public interfaz() {
		setBackground(new Color(255, 28, 33));
		setPreferredSize(new Dimension(540, 420));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(0, 0, 570, 460);
		setLocationRelativeTo(null); // Establecemos la ventana en el centro
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 28, 33));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		
		JPanel panel = new JPanel();
		panel.setBounds(10, 10, 534, 399);
		panel.setLayout(null);

		getContentPane().add(panel, BorderLayout.CENTER);
		FlowLayout flowLayout = (FlowLayout) panel.getLayout();
		flowLayout.setAlignOnBaseline(true);
		panel.setBackground(new Color(138, 138, 138));
		panel.setPreferredSize(new java.awt.Dimension(540, 420));
		contentPane.add(panel);
		
		JLabel lblNewLabel = new JLabel("SUDOKU");
		lblNewLabel.setFont(new Font("Copperplate Gothic Bold", Font.BOLD, 15));
		lblNewLabel.setBounds(new Rectangle(0, 0, 87, 38));
		getContentPane().setLayout(null);
		lblNewLabel.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
		lblNewLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setHorizontalTextPosition(SwingConstants.CENTER);
		panel.add(lblNewLabel);
	}
}

class TableroSudoku extends JPanel
{
	private JTextField[][] listaTxt;
	private int txtAncho;
	private int txtAltura;
	private int txtMargen;
	private int txtTamañoLetra;
	private Color panelfondo;
	private Color txtfondo1;
	private Color txtforeground1;
	private Color txtfondo2;
	private Color txtforeground2;
	private Color txtfondo3;
	private Color txtforeground3;
	private Color txtfondo4;
	private Color txtforeground4;


	public JTextField[][] getListaTxt() {
		return listaTxt;
	}

	public void setListaTxt(JTextField[][] listaTxt) {
		this.listaTxt = listaTxt;
	}

	public int getTxtAncho() {
		return txtAncho;
	}

	public void setTxtAncho(int txtAncho) {
		this.txtAncho = txtAncho;
	}

	public int getTxtAltura() {
		return txtAltura;
	}

	public void setTxtAltura(int txtAltura) {
		this.txtAltura = txtAltura;
	}

	public int getTxtMargen() {
		return txtMargen;
	}

	public void setTxtMargen(int txtMargen) {
		this.txtMargen = txtMargen;
	}

	public int getTxtTamañoLetra() {
		return txtTamañoLetra;
	}

	public void setTxtTamañoLetra(int txtTamañoLetra) {
		this.txtTamañoLetra = txtTamañoLetra;
	}

	public Color getPanelfondo() {
		return panelfondo;
	}

	public void setPanelfondo(Color panelfondo) {
		this.panelfondo = panelfondo;
	}

	public Color getTxtfondo1() {
		return txtfondo1;
	}

	public void setTxtfondo1(Color txtfondo1) {
		this.txtfondo1 = txtfondo1;
	}

	public Color getTxtforeground1() {
		return txtforeground1;
	}

	public void setTxtforeground1(Color txtforeground1) {
		this.txtforeground1 = txtforeground1;
	}

	public Color getTxtfondo2() {
		return txtfondo2;
	}

	public void setTxtfondo2(Color txtfondo2) {
		this.txtfondo2 = txtfondo2;
	}

	public Color getTxtforeground2() {
		return txtforeground2;
	}

	public void setTxtforeground2(Color txtforeground2) {
		this.txtforeground2 = txtforeground2;
	}

	public Color getTxtfondo3() {
		return txtfondo3;
	}

	public void setTxtfondo3(Color txtfondo3) {
		this.txtfondo3 = txtfondo3;
	}

	public Color getTxtforeground3() {
		return txtforeground3;
	}

	public void setTxtforeground3(Color txtforeground3) {
		this.txtforeground3 = txtforeground3;
	}

	public Color getTxtfondo4() {
		return txtfondo4;
	}

	public void setTxtfondo4(Color txtfondo4) {
		this.txtfondo4 = txtfondo4;
	}

	public Color getTxtforeground4() {
		return txtforeground4;
	}

	public void setTxtforeground4(Color txtforeground4) {
		this.txtforeground4 = txtforeground4;
	}
	
	


	public TableroSudoku() {
		
		
		
		
	}
	public void iniciarComponentes() {
		setListaTxt(new JTextField[9][9]);
        setTxtAncho(35);
        setTxtAltura(36);
        setTxtMargen(4);
        setTxtTamañoLetra(27);
        setPanelfondo(Color.BLACK);
        setTxtfondo1(Color.WHITE);
        setTxtforeground1(Color.BLACK);
        setTxtfondo2(Color.WHITE);
        setTxtforeground2(Color.BLACK);
        setTxtfondo3(Color.WHITE);
        setTxtforeground3(Color.BLACK);
        setTxtfondo4(Color.RED);
        setTxtforeground4(Color.WHITE);
        /*sudoku = new Sudoku();
        listaTxtAux = new ArrayList<>();
        listaTxtGenerados = new ArrayList<>();
        txtSelected = new JTextField();*/
		
	}
	
}
