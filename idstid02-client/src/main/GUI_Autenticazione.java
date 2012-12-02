package main;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.Font;

import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;

import com.sun.jersey.core.util.MultivaluedMapImpl;

import classResources.Materiale;
import classResources.Utente;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Iterator;
import java.util.List;
import javax.swing.JPasswordField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;


public class GUI_Autenticazione {

	private JFrame frmAutenticazione;
	private JTextField textUser;
	private JPasswordField textPsw;
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
		
		textPsw = new JPasswordField();
		textPsw.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				
			}
		});
		textPsw.setBounds(80, 27, 117, 20);
		panel.add(textPsw);
		textPsw.setColumns(10);
		JButton btnAccedi = new JButton("Accedi");
		btnAccedi.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				tryLogin();
			}
		});
		btnAccedi.setBounds(109, 59, 89, 26);
		panel.add(btnAccedi);
		
		JButton btnEsci = new JButton("Esci");
		btnEsci.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				frmAutenticazione.dispose();
			}
		});
		btnEsci.setBounds(0, 59, 88, 26);
		panel.add(btnEsci);
	}
	
	private void tryLogin() {

		String u = textUser.getText().trim();
		String p = Autenticazione.getMD5Sum(textPsw.getPassword());
		
		if(!u.equals("")) {
			MultivaluedMap<String, String> param = new MultivaluedMapImpl();
			param.add("username", u);
			param.add("password", p);
			
			Sessione session = ResourceClass.getService().
					path(Global._URLAutLogin).accept(MediaType.APPLICATION_JSON).post(Sessione.class, param);
			
			if(session.getUtente() != null) {
				Autenticazione.setSessione(session);
				windowHome = new GUI_Home(session.getUtente().getUser());
		    	windowHome.frmHome.setVisible(true);
		    	frmAutenticazione.dispose();
			}
		    else {
		    	JOptionPane.showMessageDialog(null, "Username o password non corretti!", "Attenzione", 0);
		    }
		}
	}
	
	public JFrame getFrame() {
		return frmAutenticazione;
	}
}
