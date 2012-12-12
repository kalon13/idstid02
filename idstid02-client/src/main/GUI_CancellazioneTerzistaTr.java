package main;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import java.awt.Component;
import java.awt.ComponentOrientation;
import javax.swing.DebugGraphics;
import javax.swing.JTextArea;
import javax.swing.UIManager;
import javax.swing.JButton;

public class GUI_CancellazioneTerzistaTr {

	private JFrame frmCancellazioneTerzista;

	/**
	 * Launch the application.
	 */

	/**
	 * Create the application.
	 */
	public GUI_CancellazioneTerzistaTr() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmCancellazioneTerzista = new JFrame();
		frmCancellazioneTerzista.setTitle("Cancellazione Terzista");
		frmCancellazioneTerzista.setBounds(100, 100, 422, 168);
		frmCancellazioneTerzista.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JPanel panel = new JPanel();
		frmCancellazioneTerzista.getContentPane().add(panel, BorderLayout.CENTER);
		panel.setLayout(null);
		
		JTextArea txtInfo = new JTextArea();
		txtInfo.setBackground(UIManager.getColor("Panel.background"));
		txtInfo.setLineWrap(true);
		txtInfo.setEditable(false);
		txtInfo.setBorder(null);
		txtInfo.setText("Sei sicuro di voler rimuovere il tuo profilo dal sistema e, quindi, di non ricevere pi\u00F9 lavori dall'azienda?");
		txtInfo.setBounds(10, 11, 354, 61);
		panel.add(txtInfo);
		
		JButton btnSi = new JButton("Si");
		btnSi.setBounds(355, 96, 41, 23);
		panel.add(btnSi);
		
		JButton btnNo = new JButton("No");
		btnNo.setBounds(302, 96, 45, 23);
		panel.add(btnNo);
	}
}
