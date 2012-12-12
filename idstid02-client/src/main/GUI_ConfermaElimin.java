package main;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.border.MatteBorder;
import java.awt.Color;
import javax.swing.JButton;

public class GUI_ConfermaElimin {

	private JFrame frmConfermaElimin;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GUI_ConfermaElimin window = new GUI_ConfermaElimin();
					window.frmConfermaElimin.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public GUI_ConfermaElimin() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmConfermaElimin = new JFrame();
		frmConfermaElimin.setTitle("Conferma Eliminazione");
		frmConfermaElimin.setBounds(100, 100, 327, 191);
		frmConfermaElimin.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JPanel panel = new JPanel();
		frmConfermaElimin.getContentPane().add(panel, BorderLayout.CENTER);
		panel.setLayout(null);
		
		JLabel lblSeiSicuroDi = new JLabel("Sei sicuro di voler eliminare:");
		lblSeiSicuroDi.setBounds(10, 11, 146, 14);
		panel.add(lblSeiSicuroDi);
		
		JList listConfermaElimin = new JList();
		listConfermaElimin.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		listConfermaElimin.setEnabled(false);
		listConfermaElimin.setBounds(166, 10, 137, 60);
		panel.add(listConfermaElimin);
		
		JButton btnElimina = new JButton("Elimina");
		btnElimina.setBounds(214, 124, 89, 23);
		panel.add(btnElimina);
		
		JButton btnIndietro = new JButton("Indietro");
		btnIndietro.setBounds(115, 124, 89, 23);
		panel.add(btnIndietro);
	}

}
