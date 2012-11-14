package gui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.JButton;

public class GUI_ModificaDati {

	private JFrame frmModificaDati;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GUI_ModificaDati window = new GUI_ModificaDati();
					window.frmModificaDati.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public GUI_ModificaDati() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmModificaDati = new JFrame();
		frmModificaDati.setTitle("Modifica Dati");
		frmModificaDati.setBounds(100, 100, 316, 244);
		frmModificaDati.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JPanel panel = new JPanel();
		frmModificaDati.getContentPane().add(panel, BorderLayout.CENTER);
		panel.setLayout(null);
		
		JButton btnModificaAnagrafica = new JButton("Modifica Anagrafica");
		btnModificaAnagrafica.setBounds(74, 39, 141, 23);
		panel.add(btnModificaAnagrafica);
		
		JButton btnModificaLavorazioni = new JButton("Modifica Lavorazioni");
		btnModificaLavorazioni.setBounds(74, 86, 141, 23);
		panel.add(btnModificaLavorazioni);
		
		JButton btnIndietro = new JButton("Indietro");
		btnIndietro.setBounds(207, 174, 89, 23);
		panel.add(btnIndietro);
	}

}
