package main;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.Font;

import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;

import com.sun.jersey.core.util.MultivaluedMapImpl;

import classResources.Utente;

import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Iterator;
import java.util.List;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;


public class GUI_Autenticazione {

        public JFrame frmAutenticazione;
        private JTextField textUser;
        private JPasswordField textPsw;
        private JButton btnAccedi, btnEsci;
        private EnterListener enterListener;
        static GUI_Home windowHome;
        
        /**
         * Create the application.
         */
        public GUI_Autenticazione() {
        	enterListener = new EnterListener();
            initialize();
        }

        /**
         * Initialize the contents of the frame.
         */
        private void initialize() {
                frmAutenticazione = new JFrame();
                frmAutenticazione.setResizable(false);
                frmAutenticazione.setTitle("Autenticazione");
                frmAutenticazione.setBounds(100, 100, 268, 159);
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
                textPsw.setBounds(80, 27, 117, 20);
                panel.add(textPsw);
                textPsw.setColumns(10);
                textPsw.addKeyListener(new KeyAdapter() {
                	@Override
                	public void keyPressed(KeyEvent e) {
                		if(e.getKeyCode() == KeyEvent.VK_ENTER){
                			//Occorre cmq spostarsi sopra il bottone con il tasto tab
                			tryLogin();
                		}
                	}
                });
               
                btnAccedi = new JButton("Accedi");
                btnAccedi.setMnemonic(KeyEvent.VK_ENTER);
                
                btnAccedi.addActionListener(enterListener);
                             
                btnAccedi.setBounds(108, 62, 89, 23);
                panel.add(btnAccedi);
                
                btnEsci = new JButton("Esci");
                btnEsci.setBounds(0, 62, 89, 23);
                panel.add(btnEsci);
                btnEsci.addActionListener(new ActionListener() {
                	public void actionPerformed(ActionEvent e) {
                		frmAutenticazione.dispose();
                	}
                });
        }
        
        private void tryLogin(){
        	textUser.setEnabled(false);
        	textPsw.setEnabled(false);
        	btnAccedi.setEnabled(false);
        	btnEsci.setEnabled(false);
        	textPsw.removeActionListener(enterListener);
        	
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
                    windowHome = new GUI_Home();
                    windowHome.frmHome.setVisible(true);
                    frmAutenticazione.setVisible(false);
                }
                else {
                	textUser.setEnabled(true);
                	textPsw.setEnabled(true);
                	btnAccedi.setEnabled(true);
                	btnEsci.setEnabled(true);
                	textPsw.removeActionListener(enterListener);
                	JOptionPane.showMessageDialog(null, "Username o password non corretti!", "Attenzione", 0);
                }
            }
        }
        
        class EnterListener implements ActionListener {
            public void actionPerformed(ActionEvent e) {
            	tryLogin();
            }
        }
        	        
        public JFrame getFrame() {	//Forse serve per il ritorno indietro!
            return frmAutenticazione;
        }

}
