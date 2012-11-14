package gui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import java.awt.BorderLayout;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JTextField;
import java.awt.Color;
import java.awt.Font;
import javax.swing.JComboBox;
import javax.swing.JButton;
import javax.swing.JPasswordField;

public class GUI_ModificaAnagrafica {

	private JFrame frmModificaAnagrafica;
	private JTextField piva;
	private JTextField email;
	private JTextField fax;
	private JTextField tel;
	private JTextField cap;
	private JTextField citta;
	private JTextField indirizzo;
	private JTextField nome;
	private JTextField prov;
	private JTextField ragsoc;
	private JPasswordField pass;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GUI_ModificaAnagrafica window = new GUI_ModificaAnagrafica();
					window.frmModificaAnagrafica.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public GUI_ModificaAnagrafica() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmModificaAnagrafica = new JFrame();
		frmModificaAnagrafica.setTitle("Modifica Anagrafica");
		frmModificaAnagrafica.setBounds(100, 100, 483, 426);
		frmModificaAnagrafica.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JPanel panel = new JPanel();
		frmModificaAnagrafica.getContentPane().add(panel, BorderLayout.CENTER);
		panel.setLayout(null);
		
		JLabel label = new JLabel("P.IVA");
		label.setBounds(10, 244, 27, 14);
		panel.add(label);
		
		piva = new JTextField();
		piva.setColumns(40);
		piva.setBounds(132, 241, 326, 20);
		panel.add(piva);
		
		email = new JTextField();
		email.setColumns(40);
		email.setBounds(132, 215, 326, 20);
		panel.add(email);
		
		JLabel label_1 = new JLabel("Email");
		label_1.setBounds(10, 218, 24, 14);
		panel.add(label_1);
		
		JLabel label_2 = new JLabel("Fax");
		label_2.setBounds(10, 192, 18, 14);
		panel.add(label_2);
		
		fax = new JTextField();
		fax.setColumns(40);
		fax.setBounds(132, 189, 326, 20);
		panel.add(fax);
		
		tel = new JTextField();
		tel.setColumns(40);
		tel.setBounds(132, 163, 326, 20);
		panel.add(tel);
		
		JLabel label_3 = new JLabel("Telefono");
		label_3.setBounds(10, 166, 42, 14);
		panel.add(label_3);
		
		JLabel label_4 = new JLabel("CAP");
		label_4.setBounds(10, 140, 20, 14);
		panel.add(label_4);
		
		cap = new JTextField();
		cap.setColumns(40);
		cap.setBounds(132, 137, 326, 20);
		panel.add(cap);
		
		JLabel label_5 = new JLabel("Provincia");
		label_5.setBounds(10, 114, 43, 14);
		panel.add(label_5);
		
		JLabel label_6 = new JLabel("Citt\u00E0");
		label_6.setBounds(10, 88, 23, 14);
		panel.add(label_6);
		
		citta = new JTextField();
		citta.setColumns(40);
		citta.setBounds(132, 82, 326, 20);
		panel.add(citta);
		
		indirizzo = new JTextField();
		indirizzo.setColumns(40);
		indirizzo.setBounds(132, 56, 326, 20);
		panel.add(indirizzo);
		
		JLabel lblIndirizzo = new JLabel("Indirizzo");
		lblIndirizzo.setBounds(10, 62, 44, 14);
		panel.add(lblIndirizzo);
		
		nome = new JTextField();
		nome.setForeground(Color.BLACK);
		nome.setFont(new Font("Tahoma", Font.BOLD, 11));
		nome.setColumns(40);
		nome.setBackground(Color.LIGHT_GRAY);
		nome.setBounds(132, 30, 326, 20);
		panel.add(nome);
		
		JLabel lblNomeAzienda = new JLabel("Nome Azienda");
		lblNomeAzienda.setBounds(10, 33, 84, 14);
		panel.add(lblNomeAzienda);
		
		prov = new JTextField();
		prov.setColumns(40);
		prov.setBounds(132, 111, 326, 20);
		panel.add(prov);
		
		ragsoc = new JTextField();
		ragsoc.setForeground(Color.BLACK);
		ragsoc.setFont(new Font("Tahoma", Font.BOLD, 11));
		ragsoc.setColumns(40);
		ragsoc.setBackground(Color.LIGHT_GRAY);
		ragsoc.setBounds(132, 267, 326, 20);
		panel.add(ragsoc);
		
		JLabel lblRagioneSociale = new JLabel("Ragione Sociale");
		lblRagioneSociale.setBounds(10, 270, 84, 14);
		panel.add(lblRagioneSociale);
		
		JLabel lblPassword = new JLabel("Password");
		lblPassword.setBounds(10, 299, 84, 14);
		panel.add(lblPassword);
		
		JButton btnModifica = new JButton("Modifica");
		btnModifica.setBounds(369, 354, 89, 23);
		panel.add(btnModifica);
		
		JButton btnIndietro = new JButton("Indietro");
		btnIndietro.setBounds(270, 354, 89, 23);
		panel.add(btnIndietro);
		
		pass = new JPasswordField();
		pass.setBounds(132, 296, 326, 20);
		panel.add(pass);
	}
}
