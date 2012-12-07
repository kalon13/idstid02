package main;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import java.awt.BorderLayout;

import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JTextField;
import java.awt.Color;
import java.awt.Font;
import javax.swing.JComboBox;
import javax.swing.JButton;
import javax.swing.JPasswordField;

import classResources.Terzista;
import classResources.Utente;

import java.awt.event.KeyEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class GUI_ModificaAnagrafica {

	public JFrame frmModificaAnagrafica;
	private JTextField piva;
	private JTextField email;
	private JTextField fax;
	private JTextField tel;
	private JTextField cap;
	private JTextField citta;
	private JTextField indirizzo;
	private JTextField prov;
	private JTextField ragsoc;
	private JPasswordField pass;
	
	private String user;
	private int tipo;
	private boolean matched;	//Per il controllo di validità degli input
	private short focus=0;		//Per controllare che non mi mostri il JOption 3 volte
	
	private Terzista t;
	private static Pattern pattern;
	private static Matcher matcher;

	/**
	 * Launch the application.
	 */

	/**
	 * Create the application.
	 */
	public GUI_ModificaAnagrafica(String user, int tipo) {
		this.user=user;
		this.tipo=tipo;
		matched=true;
		t = ResourceClass.getResource(Terzista.class, Global._URLTerz+"utenteId/"+GUI_Autenticazione.ID);
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmModificaAnagrafica = new JFrame();
		frmModificaAnagrafica.setTitle("Modifica Anagrafica");
		frmModificaAnagrafica.setBounds(100, 100, 483, 426);
		frmModificaAnagrafica.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		
		JPanel panel = new JPanel();
		frmModificaAnagrafica.getContentPane().add(panel, BorderLayout.CENTER);
		panel.setLayout(null);
		
		JLabel label = new JLabel("P.IVA");
		label.setBounds(10, 244, 112, 14);
		panel.add(label);
		
		piva = new JTextField(t.getpIva());
		piva.setColumns(40);
		piva.setBounds(132, 241, 326, 20);
		panel.add(piva);
		
		email = new JTextField(t.getEmail());
		email.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent e) {
				//Controlliamo la validità dell'input
				if(!verificaEmail(email.getText())){
					matched=false;
					focus++;
					if(focus==1) JOptionPane.showMessageDialog(null, "Campo non valido!", "Attenzione", 0);	//Questo controllo perche altrim la richiamava 3 volte ma non so perche
					else if(focus==3) focus=0;
					email.requestFocusInWindow();
					email.selectAll();
				}
				else
					matched=true;
			}
		});
		email.setColumns(40);
		email.setBounds(132, 215, 326, 20);
		panel.add(email);
		
		JLabel label_1 = new JLabel("Email");
		label_1.setBounds(10, 218, 112, 14);
		panel.add(label_1);
		
		JLabel label_2 = new JLabel("Fax");
		label_2.setBounds(10, 192, 112, 14);
		panel.add(label_2);
		
		fax = new JTextField(t.getFax());
		fax.setColumns(40);
		fax.setBounds(132, 189, 326, 20);
		panel.add(fax);
		
		tel = new JTextField(t.getTelefono());
		tel.setColumns(40);
		tel.setBounds(132, 163, 326, 20);
		panel.add(tel);
		
		JLabel label_3 = new JLabel("Telefono");
		label_3.setBounds(10, 166, 112, 14);
		panel.add(label_3);
		
		JLabel label_4 = new JLabel("CAP");
		label_4.setBounds(10, 140, 112, 14);
		panel.add(label_4);
		
		cap = new JTextField(t.getCap());
		cap.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent e) {
				if(!verificaCAP(cap.getText())){
					matched=false;
					focus++;
					if(focus==1) JOptionPane.showMessageDialog(null, "Campo non valido!", "Attenzione", 0);
					else if(focus==3) focus=0;
					cap.requestFocusInWindow();
					cap.selectAll();
				}
				else
					matched=true;
			}
		});
		cap.setColumns(40);
		cap.setBounds(132, 137, 326, 20);
		panel.add(cap);
		
		JLabel label_5 = new JLabel("Provincia");
		label_5.setBounds(10, 114, 112, 14);
		panel.add(label_5);
		
		JLabel label_6 = new JLabel("Citt\u00E0");
		label_6.setBounds(10, 88, 112, 14);
		panel.add(label_6);
		
		citta = new JTextField(t.getCitta());
		citta.setColumns(40);
		citta.setBounds(132, 82, 326, 20);
		panel.add(citta);
		
		indirizzo = new JTextField(t.getIndirizzo());
		indirizzo.setColumns(40);
		indirizzo.setBounds(132, 56, 326, 20);
		panel.add(indirizzo);
		
		JLabel lblIndirizzo = new JLabel("Indirizzo");
		lblIndirizzo.setBounds(10, 62, 112, 14);
		panel.add(lblIndirizzo);
		
		prov = new JTextField(t.getProvincia());
		prov.setColumns(40);
		prov.setBounds(132, 111, 326, 20);
		panel.add(prov);
		
		ragsoc = new JTextField(t.getRagioneSociale());
		ragsoc.setEditable(false);
		ragsoc.setForeground(Color.BLACK);
		ragsoc.setFont(new Font("Tahoma", Font.BOLD, 11));
		ragsoc.setColumns(40);
		ragsoc.setBackground(Color.LIGHT_GRAY);
		ragsoc.setBounds(132, 30, 326, 20);
		panel.add(ragsoc);
		
		JLabel lblRagioneSociale = new JLabel("Ragione Sociale");
		lblRagioneSociale.setBounds(10, 33, 112, 14);
		panel.add(lblRagioneSociale);
		
		JLabel lblPassword = new JLabel("Password");
		lblPassword.setBounds(10, 271, 112, 14);
		panel.add(lblPassword);
		
		JButton btnModifica = new JButton("Modifica");
		btnModifica.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				update();
			}
		});
		btnModifica.setMnemonic(KeyEvent.VK_ENTER);
		btnModifica.setBounds(369, 354, 89, 23);
		panel.add(btnModifica);
		
		JButton btnIndietro = new JButton("Indietro");
		btnIndietro.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frmModificaAnagrafica.setVisible(false);
				GUI_ModificaDati windowModificaDati = new GUI_ModificaDati(user, tipo);
				windowModificaDati.frmModificaDati.setVisible(true);
			}
		});
		btnIndietro.setMnemonic(KeyEvent.VK_BACK_SPACE);
		btnIndietro.setBounds(270, 354, 89, 23);
		panel.add(btnIndietro);
		
		pass = new JPasswordField(GUI_Autenticazione.psw);
		pass.setBounds(132, 268, 326, 20);
		panel.add(pass);
	}
	
	public void update(){
		if(matched){
		//Occorre convalidare i dati ed aggiornarli nel DB
		//Convalida ancora da fare
		Terzista updTerzista=new Terzista(t.getId(), email.getText(), piva.getText(), ragsoc.getText(), indirizzo.getText(), cap.getText(),
				prov.getText(), citta.getText(), tel.getText(), fax.getText());
		ResourceClass.updResources(Terzista.class, Global._URLTerz, String.valueOf(t.getId()), updTerzista);
		//Devo modificare la password nella tabella utente
		Utente updUtente=new Utente(GUI_Autenticazione.ID, user, pass.getText(), tipo);
		ResourceClass.updResources(Utente.class, Global._URLUser+"/", String.valueOf(GUI_Autenticazione.ID), updUtente);
		GUI_Autenticazione.psw=pass.getText();
		JOptionPane.showMessageDialog(null, "Dati modificati correttamente.", "Attenzione", 1);
		GUI_Home windowHome=new GUI_Home(user,tipo);
		windowHome.frmHome.setVisible(true);
		frmModificaAnagrafica.setVisible(false);
		}
	}
	
	public static boolean verificaEmail(String email) {
		pattern = Pattern.compile(".+@.+\\.[a-z]+");
		matcher = pattern.matcher(email);
		if(matcher.matches()) return true;
		else return false;
	}
	
	public static boolean verificaCAP(String cap) {
		pattern = Pattern.compile("^[0-9]{5}(?:-[0-9]{4})?$");
		matcher = pattern.matcher(cap);
		if(matcher.matches()) return true;
		else return false;
	}

}
