package gui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.JButton;

public class GUI_ModificaLavorazioni {

	private JFrame frmModificaLavorazioni;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GUI_ModificaLavorazioni window = new GUI_ModificaLavorazioni();
					window.frmModificaLavorazioni.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public GUI_ModificaLavorazioni() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmModificaLavorazioni = new JFrame();
		frmModificaLavorazioni.setTitle("Modifica Lavorazioni");
		frmModificaLavorazioni.setBounds(100, 100, 482, 446);
		frmModificaLavorazioni.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JPanel panel = new JPanel();
		frmModificaLavorazioni.getContentPane().add(panel, BorderLayout.CENTER);
		panel.setLayout(null);
	}

}
