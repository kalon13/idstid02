package main;

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
import javax.swing.JScrollPane;
import java.awt.event.KeyEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class GUI_Valutazione {

	public JFrame frmValutazione;	//Ci vorrebbe il getFrame() e lasciarlo private
	private JTable tableValutazione;
	private String user;
	private int tipo;

	public GUI_Valutazione(String user, int tipo) {
		this.user=user;
		this.tipo=tipo;
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmValutazione = new JFrame();
		frmValutazione.setTitle("Valutazione");
		frmValutazione.setBounds(100, 100, 676, 269);
		frmValutazione.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		
		JPanel panel = new JPanel();
		panel.setMinimumSize(new Dimension(0, 0));
		panel.setMaximumSize(new Dimension(0, 0));
		panel.setDoubleBuffered(false);
		frmValutazione.getContentPane().add(panel, BorderLayout.CENTER);
		panel.setLayout(null);
		
		JLabel lblElencoLavorazioniChiuse = new JLabel("Elenco lavorazioni chiuse non valutate:");
		lblElencoLavorazioniChiuse.setBounds(10, 11, 332, 14);
		panel.add(lblElencoLavorazioniChiuse);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 46, 640, 132);
		panel.add(scrollPane);
		
		tableValutazione = new JTable();
		tableValutazione.setToolTipText("Tasto INVIO per valutare, riga per riga.");
		scrollPane.setViewportView(tableValutazione);
		tableValutazione.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Nome Azienda", "n\u00B0 Rif. Bolla", "Nome Lavorazione", "Data Chiusura", "Voto (0..10)"
			}
		) {
			boolean[] columnEditables = new boolean[] {
				false, false, false, false, true
			};
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		tableValutazione.getColumnModel().getColumn(0).setPreferredWidth(184);
		tableValutazione.getColumnModel().getColumn(1).setPreferredWidth(100);
		tableValutazione.getColumnModel().getColumn(2).setPreferredWidth(169);
		tableValutazione.getColumnModel().getColumn(3).setPreferredWidth(108);
		tableValutazione.getColumnModel().getColumn(4).setPreferredWidth(73);
		tableValutazione.setRowSelectionAllowed(false);
		tableValutazione.setEnabled(false);
		tableValutazione.setAutoCreateColumnsFromModel(false);
		
		JButton btnIndietro = new JButton("Indietro");
		btnIndietro.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				GUI_GestioneDati windowGestione = new GUI_GestioneDati(user, tipo);
				windowGestione.frmGestioneDati.setVisible(true);
				frmValutazione.setVisible(false);
			}
		});
		btnIndietro.setMnemonic(KeyEvent.VK_BACK_SPACE);
		btnIndietro.setBounds(579, 197, 71, 23);
		panel.add(btnIndietro);
	}
}
