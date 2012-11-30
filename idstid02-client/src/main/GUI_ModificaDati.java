package main;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.JButton;
import java.awt.event.KeyEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class GUI_ModificaDati {

	public JFrame frmModificaDati;
	private String user;
	private int tipo;
	
	GUI_ModificaAnagrafica windowAnagrafica;
	GUI_ModificaLavorazioni windowLavorazioni;

	/**
	 * Launch the application.
	 */

	/**
	 * Create the application.
	 */
	public GUI_ModificaDati(String user, int tipo) {
		this.user=user;
		this.tipo=tipo;
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmModificaDati = new JFrame();
		frmModificaDati.setTitle("Modifica Dati");
		frmModificaDati.setBounds(100, 100, 292, 216);
		frmModificaDati.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		
		JPanel panel = new JPanel();
		frmModificaDati.getContentPane().add(panel, BorderLayout.CENTER);
		panel.setLayout(null);
		
		JButton btnModificaAnagrafica = new JButton("Modifica Anagrafica");
		btnModificaAnagrafica.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				windowAnagrafica = new GUI_ModificaAnagrafica(user, tipo);
				windowAnagrafica.frmModificaAnagrafica.setVisible(true);
				frmModificaDati.setVisible(false);
			}
		});
		btnModificaAnagrafica.setMnemonic(KeyEvent.VK_A);
		btnModificaAnagrafica.setBounds(59, 27, 156, 23);
		panel.add(btnModificaAnagrafica);
		
		JButton btnModificaLavorazioni = new JButton("Modifica Lavorazioni");
		btnModificaLavorazioni.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//frmModificaDati.setVisible(false);
				windowLavorazioni= new GUI_ModificaLavorazioni(user, tipo);
				windowLavorazioni.frmModificaLavorazioni.setVisible(true);
			}
		});
		btnModificaLavorazioni.setMnemonic(KeyEvent.VK_L);
		btnModificaLavorazioni.setBounds(59, 70, 156, 23);
		panel.add(btnModificaLavorazioni);
		
		JButton btnIndietro = new JButton("Indietro");
		btnIndietro.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frmModificaDati.setVisible(false);
				GUI_GestioneDati windowGestione = new GUI_GestioneDati(user, tipo);
				windowGestione.frmGestioneDati.setVisible(true);
			}
		});
		btnIndietro.setMnemonic(KeyEvent.VK_BACK_SPACE);
		btnIndietro.setBounds(95, 141, 89, 23);
		panel.add(btnIndietro);
	}

}
