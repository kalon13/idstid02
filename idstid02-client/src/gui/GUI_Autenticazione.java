package gui;


import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.Font;

import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;

import main.Autenticazione;
import main.Sessione;

import utils.Global;
import utils.ResourceClass;

import com.sun.jersey.core.util.MultivaluedMapImpl;


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
import java.awt.GridLayout;
import java.awt.BorderLayout;


public class GUI_Autenticazione {

        public JFrame frmAutenticazione;
        private JTextField textUser;
        private JPasswordField textPsw;
        private JButton btnAccedi, btnEsci;
        private EnterListener enterListener;
        static GUI_Home windowHome;
        private JLabel lblNewLabel_1;
        private JLabel lblNewLabel_2;
        private JLabel lblNewLabel_3;
        private JLabel lblNewLabel_4;
        
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
        	frmAutenticazione.getContentPane().setLayout(new BorderLayout(16, 16));
   
        	JPanel panel = new JPanel();
        	frmAutenticazione.getContentPane().add(panel);
        	panel.setLayout(new GridLayout(0, 2, 10, 10));
   
        	JLabel label = new JLabel("Username:");
        	panel.add(label);
   
        	textUser = new JTextField();
        	panel.add(textUser);
    
        	JLabel lblNewLabel = new JLabel("Password:");
        	panel.add(lblNewLabel);
               
                textPsw = new JPasswordField();
                panel.add(textPsw);
                textPsw.addKeyListener(enterListener);
               
                btnAccedi = new JButton("Accedi");
                btnAccedi.addActionListener(enterListener);
                
                btnEsci = new JButton("Esci");
                panel.add(btnEsci);
                btnEsci.addActionListener(new ActionListener() {
                	public void actionPerformed(ActionEvent e) {
                		frmAutenticazione.dispose();
                		System.exit(0);
                	}
                });
                panel.add(btnAccedi);
                
                lblNewLabel_1 = new JLabel("");
                frmAutenticazione.getContentPane().add(lblNewLabel_1, BorderLayout.NORTH);
                
                lblNewLabel_2 = new JLabel("");
                frmAutenticazione.getContentPane().add(lblNewLabel_2, BorderLayout.SOUTH);
                
                lblNewLabel_3 = new JLabel("");
                frmAutenticazione.getContentPane().add(lblNewLabel_3, BorderLayout.WEST);
                
                lblNewLabel_4 = new JLabel("");
                frmAutenticazione.getContentPane().add(lblNewLabel_4, BorderLayout.EAST);
        }
        
        private void tryLogin(){
        	
        	final String user = textUser.getText().trim();
        	final char[] pass = textPsw.getPassword();
            
        	if(!user.equals("")) {
            	textUser.setEnabled(false);
            	textPsw.setEnabled(false);
            	btnAccedi.setEnabled(false);
            	btnEsci.setEnabled(false);
            	textPsw.removeActionListener(enterListener);
        		
	        	new Thread() {
	        	    @Override
	        	    public void run () {
	        	    	try {
		                	String password = Autenticazione.getMD5Sum(pass);
		                	
		                    MultivaluedMap<String, String> param = new MultivaluedMapImpl();
		                    param.add("username", user);
		                    param.add("password", password);
		                   
		                    try{
			                    Sessione session = ResourceClass.getService().
			                                    path(Global._URLAutLogin).accept(MediaType.APPLICATION_JSON).post(Sessione.class, param);
			                    
			                    if(session.getUtente() != null) {
			                    	Autenticazione.setSessione(session);
			                        windowHome = new GUI_Home();
			                        windowHome.frmHome.setVisible(true);
			                        frmAutenticazione.setVisible(false);
			                    }
			                    else {
			                    	JOptionPane.showMessageDialog(null, "Username o password non corretti!", "Attenzione", 0);
			                    	textPsw.selectAll();
			                    }
		                    }
		                    catch(Exception ex){
		                    	JOptionPane.showMessageDialog(null, "Impossibile connettersi al server!", "Attenzione", 0);
		                    }
	        	    	}
	        	    	catch(Exception ex) {
	        	    		ex.printStackTrace();
	        	    	}
	        	    	finally {
		                    SwingUtilities.invokeLater(new Runnable(){
		                    	@Override
		                    	public void run() {
		                    		textUser.setEnabled(true);
			                    	textPsw.setEnabled(true);
			                    	btnAccedi.setEnabled(true);
			                    	btnEsci.setEnabled(true);
			                    	textPsw.removeActionListener(enterListener);
		                    	}
		                    });
	        	    	}
	        	    }
	        	  }.start();                
            }
        	else
        		JOptionPane.showMessageDialog(null, "Dati mancanti!", "Attenzione", 0);
        }
        
        private class EnterListener extends KeyAdapter implements ActionListener  {
            public void actionPerformed(ActionEvent e) {
            	tryLogin();
            }
            
        	@Override
        	public void keyPressed(KeyEvent e) {
        		if(e.getKeyCode() == KeyEvent.VK_ENTER){
        			tryLogin();
        		}
        	}
        }
        	        
        public JFrame getFrame() {	//Forse serve per il ritorno indietro!
            return frmAutenticazione;
        }

}
