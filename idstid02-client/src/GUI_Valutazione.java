package gui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTable;
import javax.swing.border.MatteBorder;
import java.awt.Color;
import javax.swing.table.DefaultTableModel;
import java.awt.Dimension;
import javax.swing.JButton;

public class GUI_Valutazione {

	private JFrame frmValutazione;
	private JTable tableValutazione;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GUI_Valutazione window = new GUI_Valutazione();
					window.frmValutazione.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public GUI_Valutazione() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmValutazione = new JFrame();
		frmValutazione.setTitle("Valutazione");
		frmValutazione.setBounds(100, 100, 585, 300);
		frmValutazione.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JPanel panel = new JPanel();
		panel.setMinimumSize(new Dimension(0, 0));
		panel.setMaximumSize(new Dimension(0, 0));
		panel.setDoubleBuffered(false);
		frmValutazione.getContentPane().add(panel, BorderLayout.CENTER);
		panel.setLayout(null);
		
		JLabel lblElencoLavorazioniChiuse = new JLabel("Elenco lavorazioni chiuse non valutate:");
		lblElencoLavorazioniChiuse.setBounds(10, 11, 196, 14);
		panel.add(lblElencoLavorazioniChiuse);
		
		JLabel lblNomeAzienda = new JLabel("Nome Azienda");
		lblNomeAzienda.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblNomeAzienda.setBounds(10, 45, 80, 14);
		panel.add(lblNomeAzienda);
		
		JLabel lblNRifBolla = new JLabel("n\u00B0 Rif. Bolla");
		lblNRifBolla.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblNRifBolla.setBounds(167, 45, 80, 14);
		panel.add(lblNRifBolla);
		
		JLabel lblNomeLavorazione = new JLabel("Nome Lavorazione");
		lblNomeLavorazione.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblNomeLavorazione.setBounds(257, 45, 104, 14);
		panel.add(lblNomeLavorazione);
		
		JLabel lblDataChiusura = new JLabel("Data Chiusura");
		lblDataChiusura.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblDataChiusura.setBounds(400, 45, 80, 14);
		panel.add(lblDataChiusura);
		
		JLabel lblVoto = new JLabel("Voto (0..10)");
		lblVoto.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblVoto.setBounds(496, 45, 80, 14);
		panel.add(lblVoto);
		
		tableValutazione = new JTable();
		tableValutazione.setModel(new DefaultTableModel(
			new Object[][] {
				{null, null, null, null, null},
			},
			new String[] {
				"Nome Azienda", "n\u00B0 Rif. Bolla", "Nome Lavorazione", "Data Chiusura", "Voto (0..10)"
			}
		));
		tableValutazione.getColumnModel().getColumn(0).setPreferredWidth(184);
		tableValutazione.getColumnModel().getColumn(1).setPreferredWidth(100);
		tableValutazione.getColumnModel().getColumn(2).setPreferredWidth(169);
		tableValutazione.getColumnModel().getColumn(3).setPreferredWidth(108);
		tableValutazione.getColumnModel().getColumn(4).setPreferredWidth(73);
		tableValutazione.setSize(new Dimension(1, 1));
		tableValutazione.setRowSelectionAllowed(false);
		tableValutazione.setEnabled(false);
		tableValutazione.setAutoCreateColumnsFromModel(false);
		tableValutazione.setBounds(10, 70, 548, 16);
		panel.add(tableValutazione);
		
		JButton btnIndietro = new JButton("Indietro");
		btnIndietro.setBounds(409, 228, 71, 23);
		panel.add(btnIndietro);
		
		JButton btnValuta = new JButton("Valuta");
		btnValuta.setBounds(487, 228, 71, 23);
		panel.add(btnValuta);
	}
}
