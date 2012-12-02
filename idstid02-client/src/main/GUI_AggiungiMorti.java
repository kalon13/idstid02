package main;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JPanel;
import javax.swing.JComboBox;
import javax.swing.JList;
import javax.swing.JButton;


public class GUI_AggiungiMorti {

	private JFrame frmAggiungiMorti;
	private JTextField txtNumBolla;
	private JTextField txtMortiProdotti;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GUI_AggiungiMorti window = new GUI_AggiungiMorti();
					window.frmAggiungiMorti.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public GUI_AggiungiMorti() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmAggiungiMorti = new JFrame();
		frmAggiungiMorti.setResizable(false);
		frmAggiungiMorti.setTitle("Aggiungi Morti");
		frmAggiungiMorti.setBounds(100, 100, 271, 265);
		frmAggiungiMorti.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frmAggiungiMorti.getContentPane().setLayout(null);
		
		JLabel lblBollaN = new JLabel("Bolla n\u00B0:");
		lblBollaN.setBounds(10, 11, 46, 14);
		frmAggiungiMorti.getContentPane().add(lblBollaN);
		
		txtNumBolla = new JTextField();
		txtNumBolla.setEditable(false);
		txtNumBolla.setBounds(73, 8, 77, 20);
		frmAggiungiMorti.getContentPane().add(txtNumBolla);
		txtNumBolla.setColumns(10);
		
		JPanel panel = new JPanel();
		panel.setBounds(10, 36, 243, 152);
		frmAggiungiMorti.getContentPane().add(panel);
		panel.setLayout(null);
		
		JLabel lblMateriali = new JLabel("Materiali:");
		lblMateriali.setBounds(10, 11, 46, 14);
		panel.add(lblMateriali);
		
		JLabel lblMortiProdotti = new JLabel("Morti Prodotti:");
		lblMortiProdotti.setBounds(119, 25, 99, 14);
		panel.add(lblMortiProdotti);
		
		JList listMateriali = new JList();
		listMateriali.setBounds(10, 30, 99, 111);
		panel.add(listMateriali);
		
		JButton btnAggiungiMorti = new JButton("Aggiungi Morti");
		btnAggiungiMorti.setBounds(119, 75, 114, 23);
		panel.add(btnAggiungiMorti);
		
		txtMortiProdotti = new JTextField();
		txtMortiProdotti.setBounds(119, 44, 114, 20);
		panel.add(txtMortiProdotti);
		txtMortiProdotti.setColumns(10);
		
		JButton btnEsci = new JButton("Esci");
		btnEsci.setBounds(164, 201, 89, 23);
		frmAggiungiMorti.getContentPane().add(btnEsci);
	}
}
