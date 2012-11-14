package gui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JList;
import javax.swing.border.LineBorder;
import java.awt.Color;
import javax.swing.border.MatteBorder;
import javax.swing.AbstractListModel;
import javax.swing.JButton;

public class GUI_CancellazioneTerzistaOp {

	private JFrame frmCancellazioneTerzista;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GUI_CancellazioneTerzistaOp window = new GUI_CancellazioneTerzistaOp();
					window.frmCancellazioneTerzista.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public GUI_CancellazioneTerzistaOp() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmCancellazioneTerzista = new JFrame();
		frmCancellazioneTerzista.setTitle("Cancellazione Terzista");
		frmCancellazioneTerzista.setBounds(100, 100, 288, 242);
		frmCancellazioneTerzista.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JPanel panel = new JPanel();
		panel.setForeground(Color.BLACK);
		frmCancellazioneTerzista.getContentPane().add(panel, BorderLayout.CENTER);
		panel.setLayout(null);
		
		JLabel lblElencoTerzistiChe = new JLabel("Elenco Terzisti che \u00E8 possibile rimuovere:");
		lblElencoTerzistiChe.setBounds(10, 11, 201, 14);
		panel.add(lblElencoTerzistiChe);
		
		JLabel lblNomeAzienda = new JLabel("Nome Azienda");
		lblNomeAzienda.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblNomeAzienda.setBounds(10, 36, 80, 14);
		panel.add(lblNomeAzienda);
		
		JList listTerzisti = new JList();
		listTerzisti.setFont(new Font("Tahoma", Font.PLAIN, 11));
		listTerzisti.setBackground(Color.WHITE);
		listTerzisti.setVisibleRowCount(5);
		listTerzisti.setModel(new AbstractListModel() {
			String[] values = new String[] {};
			public int getSize() {
				return values.length;
			}
			public Object getElementAt(int index) {
				return values[index];
			}
		});
		listTerzisti.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		listTerzisti.setBounds(10, 57, 201, 78);
		panel.add(listTerzisti);
		
		JButton btnRimuovi = new JButton("Rimuovi");
		btnRimuovi.setBounds(181, 176, 89, 23);
		panel.add(btnRimuovi);
		
		JButton btnIndietro = new JButton("Indietro");
		btnIndietro.setBounds(82, 176, 89, 23);
		panel.add(btnIndietro);
	}

}
