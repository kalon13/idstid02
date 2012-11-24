package main;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.Font;

import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JPanel;

import classResources.Materiale;
import classResources.Utente;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Iterator;
import java.util.List;


public class GUI_Autenticazione {

	private JFrame frmAutenticazione;
	private JTextField textUser;
	private JTextField textPsw;
	static GUI_Autenticazione windowAutenticazione;
	static GUI_Home windowHome;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					windowAutenticazione = new GUI_Autenticazione();
					windowAutenticazione.frmAutenticazione.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public GUI_Autenticazione() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmAutenticazione = new JFrame();
		frmAutenticazione.setResizable(false);
		frmAutenticazione.setTitle("Autenticazione");
		frmAutenticazione.setBounds(100, 100, 280, 161);
		frmAutenticazione.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmAutenticazione.getContentPane().setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(35, 25, 198, 85);
		frmAutenticazione.getContentPane().add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Password:");
		lblNewLabel.setBounds(0, 30, 71, 14);
		panel.add(lblNewLabel);
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 11));
		
		JLabel label = new JLabel("Username:");
		label.setBounds(0, 3, 71, 14);
		panel.add(label);
		label.setFont(new Font("Tahoma", Font.BOLD, 11));
		
		textUser = new JTextField();
		textUser.setBounds(80, 0, 117, 20);
		panel.add(textUser);
		textUser.setColumns(10);
		
		textPsw = new JTextField();
		textPsw.setBounds(80, 27, 117, 20);
		panel.add(textPsw);
		textPsw.setColumns(10);
		
		JButton btnAccedi = new JButton("Accedi");
		btnAccedi.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				 List<Utente> lista = ResourceClass.getResources(Utente.class, Global._URLAut);
				 Iterator<Utente> it=lista.iterator();
				 Boolean flgLog = false;String usr=null;
			    while(it.hasNext())
		        { Utente user = it.next();
		          usr = user.getUser();
		          String psw = user.getPsw();
		          if((usr.equals(textUser.getText())) && (psw.equals(textPsw.getText()))){
		          flgLog = true; break;}		        
		        }
			    if(flgLog == true){
			    	windowHome = new GUI_Home(usr);
			    	windowHome.frmHome.setVisible(true);
			    	windowAutenticazione.frmAutenticazione.dispose();			    	
			    	}
			    else
			    	JOptionPane.showMessageDialog(null, "Username o password non corrette!", "Attenzione", 0);
			}
		});
		btnAccedi.setBounds(45, 61, 89, 23);
		panel.add(btnAccedi);
	}
}
