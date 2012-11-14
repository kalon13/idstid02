package gui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.JLabel;
import javax.swing.JComboBox;
import java.awt.Component;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JList;
import javax.swing.border.MatteBorder;
import java.awt.Color;
import javax.swing.ListSelectionModel;
import javax.swing.JButton;

public class GUI_SceltaTerzista {

	private JFrame frmSceltaTerzista;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GUI_SceltaTerzista window = new GUI_SceltaTerzista();
					window.frmSceltaTerzista.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public GUI_SceltaTerzista() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmSceltaTerzista = new JFrame();
		frmSceltaTerzista.setTitle("Scelta Terzista");
		frmSceltaTerzista.setResizable(false);
		frmSceltaTerzista.setBounds(100, 100, 351, 273);
		frmSceltaTerzista.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JPanel panel = new JPanel();
		frmSceltaTerzista.getContentPane().add(panel, BorderLayout.CENTER);
		panel.setLayout(null);
		
		JLabel lblSelezionaIlTipo = new JLabel("Seleziona il tipo di lavorazione:");
		lblSelezionaIlTipo.setBounds(10, 11, 157, 14);
		panel.add(lblSelezionaIlTipo);
		
		JComboBox cmbTipoLavorazione = new JComboBox();
		cmbTipoLavorazione.setMaximumRowCount(20);
		cmbTipoLavorazione.setBounds(190, 8, 143, 20);
		panel.add(cmbTipoLavorazione);
		
		JLabel lblTerzistiCheEffettuano = new JLabel("Terzisti che effettuano:");
		lblTerzistiCheEffettuano.setBounds(10, 56, 157, 14);
		panel.add(lblTerzistiCheEffettuano);
		
		JLabel lblTipoLavorazione = new JLabel("");
		lblTipoLavorazione.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		lblTipoLavorazione.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblTipoLavorazione.setAlignmentX(Component.CENTER_ALIGNMENT);
		lblTipoLavorazione.setBounds(190, 56, 143, 14);
		panel.add(lblTipoLavorazione);
		
		JList listTerzisti = new JList();
		listTerzisti.setVisibleRowCount(5);
		listTerzisti.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		listTerzisti.setSelectionForeground(Color.WHITE);
		listTerzisti.setForeground(new Color(0, 0, 0));
		listTerzisti.setBackground(Color.WHITE);
		listTerzisti.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		listTerzisti.setBounds(190, 81, 143, 51);
		panel.add(listTerzisti);
		
		JButton btnAvanti = new JButton("Avanti");
		btnAvanti.setBounds(244, 214, 89, 23);
		panel.add(btnAvanti);
		
		JButton btnIndietro = new JButton("Indietro");
		btnIndietro.setBounds(145, 214, 89, 23);
		panel.add(btnIndietro);
	}
}
